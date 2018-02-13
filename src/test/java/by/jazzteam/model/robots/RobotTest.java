package by.jazzteam.model.robots;

import by.jazzteam.model.listeners.RobotTaskAddListener;
import by.jazzteam.model.listeners.RobotTaskExecutionListener;
import by.jazzteam.model.tasks.Task;
import org.junit.Before;
import org.junit.Test;

import java.util.AbstractMap;
import java.util.Map;

import static org.junit.Assert.*;

public class RobotTest {

    private static final String ROBOT_NAME = "Test robot";

    private static Robot robot;

    @Before
    public void setUp(){
        robot = new Robot(ROBOT_NAME);
    }

    @Test
    public void getName() {
        assertEquals(robot.getName(), ROBOT_NAME);
    }

    @Test
    public void setName() {
        String newName = ROBOT_NAME + ROBOT_NAME;

        robot.setName(newName);

        assertEquals(robot.getName(), newName);
    }

    @Test
    public void isAlive() {
        assertTrue(robot.isAlive());
    }

    @Test
    public void setAlive() {
        boolean alive = !robot.isAlive();

        robot.setAlive(alive);

        assertTrue(robot.isAlive() == alive);
    }

    @Test
    public void getTaskQueueSize() {
        assertEquals(robot.getTaskQueueSize(), 0);
    }

    @Test
    public void addTaskAddListener() {
        assertTrue(robot.addTaskAddListener((robot, task) -> {}));
    }

    @Test
    public void removeTaskAddListener() {
        RobotTaskAddListener listener = (robot, task) -> {};
        robot.addTaskAddListener(listener);
        assertTrue(robot.removeTaskAddListener(listener));
    }

    @Test
    public void removeTaskAddListenerWithInvalid() {
        RobotTaskAddListener listener = (robot, task) -> {};
        assertFalse(robot.removeTaskAddListener(listener));
    }

    @Test
    public void addTaskExecutionListener() {
        assertTrue(robot.addTaskExecutionListener(new RobotTaskExecutionListener() {
            @Override
            public void onStartTask(Robot robot, Task task) {}

            @Override
            public void onEndTask(Robot robot, Task task) {}
        }));
    }

    @Test
    public void removeTaskExecutionListener() {
        RobotTaskExecutionListener listener = new RobotTaskExecutionListener() {
            @Override
            public void onStartTask(Robot robot, Task task) {}

            @Override
            public void onEndTask(Robot robot, Task task) {}
        };

        robot.addTaskExecutionListener(listener);

        assertTrue(robot.removeTaskExecutionListener(listener));
    }

    @Test
    public void removeTaskExecutionListenerWithInvalid() {
        RobotTaskExecutionListener listener = new RobotTaskExecutionListener() {
            @Override
            public void onStartTask(Robot robot, Task task) {}

            @Override
            public void onEndTask(Robot robot, Task task) {}
        };

        assertFalse(robot.removeTaskExecutionListener(listener));
    }

    @Test
    public void addTask() {
        robot = new Warrior(ROBOT_NAME);

        int oldTaskQueueSize = robot.getTaskQueueSize();

        robot.addTask(robot.getMethods().iterator().next());

        int currentTasQueueSize = robot.getTaskQueueSize();

        assertEquals(++oldTaskQueueSize, currentTasQueueSize);
    }

    @Test
    public void getMethods() {
        assertNotNull(robot.getMethods());
        assertNotEquals(robot.getMethods().size(), 0);
    }

    @Test
    public void run() throws InterruptedException {

        robot = new Warrior(ROBOT_NAME);

        int tasksAmount = 3;

        Map.Entry<Integer, Integer> startTasks = new AbstractMap.SimpleEntry<>(tasksAmount, 0);
        Map.Entry<Integer, Integer> endTasks = new AbstractMap.SimpleEntry<>(tasksAmount, 0);

        robot.addTaskExecutionListener(new RobotTaskExecutionListener() {
            @Override
            public void onStartTask(Robot robot, Task task) {
                startTasks.setValue(startTasks.getValue() + 1);
            }

            @Override
            public void onEndTask(Robot robot, Task task) {
                endTasks.setValue(endTasks.getValue() + 1);
            }
        });

        for (int i = 0; i < tasksAmount; i++) {
            robot.addTask("Hit");
        }
        Thread.sleep(10 * tasksAmount * 1000 + 50);
        robot.setAlive(false);
        assertEquals(startTasks.getKey(), startTasks.getValue());
        assertEquals(endTasks.getKey(), endTasks.getValue());
    }
}