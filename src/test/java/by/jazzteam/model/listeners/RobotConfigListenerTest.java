package by.jazzteam.model.listeners;

import by.jazzteam.model.pool.RobotPool;
import by.jazzteam.model.robots.Robot;
import by.jazzteam.model.tasks.Task;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RobotConfigListenerTest {

    private RobotTaskExecutionListener executionListener;
    private RobotTaskAddListener addListener;
    private Robot robot;

    @Before
    public void setUp() {
        executionListener = new RobotTaskExecutionListener() {
            @Override
            public void onStartTask(Robot robot, Task task) {}

            @Override
            public void onEndTask(Robot robot, Task task) {}
        };
        addListener = (robot, task) -> {};
        RobotPool.getRobotPool().addListener(new RobotConfigListener(executionListener));
        RobotPool.getRobotPool().addListener(new RobotConfigListener(addListener));

        robot = new Robot();

        RobotPool.getRobotPool().addRobot(robot);
    }

    @Test
    public void onAddRobotWithRobotTaskExecutionListener() {
        assertFalse(robot.addTaskExecutionListener(executionListener));
    }

    @Test
    public void onRemoveRobotWithRobotTaskExecutionListener() {
        RobotPool.getRobotPool().removeRobot(robot);

        assertTrue(robot.addTaskExecutionListener(executionListener));
    }

    @Test
    public void onAddRobotWithRobotTaskAddListener() {
        assertFalse(robot.addTaskAddListener(addListener));
    }

    @Test
    public void onRemoveRobotWithRobotTaskAddListener() {
        RobotPool.getRobotPool().removeRobot(robot);

        assertTrue(robot.addTaskAddListener(addListener));
    }
}