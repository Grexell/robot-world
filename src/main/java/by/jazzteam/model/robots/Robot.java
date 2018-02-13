package by.jazzteam.model.robots;

import by.jazzteam.model.listeners.RobotTaskAddListener;
import by.jazzteam.model.listeners.RobotTaskExecutionListener;
import by.jazzteam.model.tasks.KillTask;
import by.jazzteam.model.tasks.Task;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/*
    Basic robot
 */
public class Robot implements Runnable{

    public static final int TASK_CHECK_TIMEOUT = 1000;

    /*
        Map with tasks, enabled to be executed by the robot
     */
    protected HashMap<String, Task> taskList;

    /*
        Queue of tasks
     */
    private BlockingQueue<Task> taskQueue;

    /*
        Set of RobotTaskExecutionlistener
     */
    private Set<RobotTaskExecutionListener> robotTaskExecutionListenerList;

    /*
        Set of RobotTaskAddListener
     */
    private Set<RobotTaskAddListener> robotTaskAddListeners;

    /*
        Robot's life cycle
        If false, robot stop task execution
     */
    private boolean alive;

    /*
        Robot's name
     */
    private String name;

    /*
        Thread, used to execute tasks
     */
    private Thread executionThread;

    public Robot() {
        name = "";
        alive = true;

        taskList = new HashMap<>();
        Task task = new KillTask("Kill",this);
        taskList.put(task.getName(), task);

        taskQueue = new LinkedBlockingQueue<>();

        robotTaskExecutionListenerList = new HashSet<>();
        robotTaskAddListeners = new HashSet<>();

        executionThread = new Thread(this);
        executionThread.start();
    }

    public Robot(String name) {
        this();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public int getTaskQueueSize() {
        return taskQueue.size();
    }

    public boolean addTaskAddListener(RobotTaskAddListener robotTaskAddListener) {
        return robotTaskAddListeners.add(robotTaskAddListener);
    }

    public boolean removeTaskAddListener(RobotTaskAddListener robotTaskAddListener) {
        return robotTaskAddListeners.remove(robotTaskAddListener);
    }


    public boolean addTaskExecutionListener(RobotTaskExecutionListener robotTaskExecutionListener) {
        return robotTaskExecutionListenerList.add(robotTaskExecutionListener);
    }

    public boolean removeTaskExecutionListener(RobotTaskExecutionListener robotTaskExecutionListener) {
        return robotTaskExecutionListenerList.remove(robotTaskExecutionListener);
    }

    public boolean addTask(String name){

        Task addTask = taskList.get(name);

        boolean result = false;

        if (null != addTask) {
            result = taskQueue.add(taskList.get(name));

            if (result) {
                for (RobotTaskAddListener robotTaskAddListener: robotTaskAddListeners) {
                    robotTaskAddListener.onAddTask(this, taskList.get(name));
                }
            }

        }

        return result;
    }

    public Set<String> getMethods() {
        return taskList.keySet();
    }

    /*
        Executes tasks from the queue while robot is alive
     */
    public void run() {
        while(isAlive()) {
            try {
                Task currentTask =  taskQueue.poll(TASK_CHECK_TIMEOUT, TimeUnit.MILLISECONDS);
                if (currentTask != null) {
                    for (RobotTaskExecutionListener listener: robotTaskExecutionListenerList) {
                        listener.onStartTask(this, currentTask);
                    }

                    currentTask.execute();

                    for (RobotTaskExecutionListener listener: robotTaskExecutionListenerList) {
                        listener.onEndTask(this, currentTask);
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
