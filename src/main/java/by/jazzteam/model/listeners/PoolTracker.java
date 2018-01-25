package by.jazzteam.model.listeners;

import by.jazzteam.model.pool.RobotPool;
import by.jazzteam.model.robots.Robot;
import by.jazzteam.model.tasks.Task;
import by.jazzteam.util.NameGenerator;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;

public class PoolTracker implements RobotTaskAddListener{

    public static final int CRITICAL_AMOUNT_OF_TASKS = 5;

    private List<Class<? extends Robot>> exceptionalClassList;

    public PoolTracker() {
        exceptionalClassList = new LinkedList<>();
    }

    /*
        Check size of Task queue
        if size is greater than or equals to the CRITICAL_AMOUNT_OF_TASKS, it creates new robot of the same type
     */
    @Override
    public void onAddTask(Robot robot, Task task) {
        if (robot.getTaskQueueSize() >= CRITICAL_AMOUNT_OF_TASKS && !exceptionalClassList.contains(robot.getClass())) {
            try {
                RobotPool.getRobotPool().addRobot(robot.getClass().getDeclaredConstructor(String.class).newInstance(NameGenerator.randomString()));
            } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
                exceptionalClassList.add(robot.getClass());
            }
        }
    }
}
