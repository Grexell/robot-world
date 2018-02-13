package by.jazzteam.model.listeners;

import by.jazzteam.model.pool.RobotPool;
import by.jazzteam.model.robots.Robot;
import by.jazzteam.model.robots.Warrior;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RemoveRobotListenerTest {

    private static final String ROBOT_NAME = "test robot";
    private static final String TASK_NAME = "Kill";
    private static final int THREAD_WAIT_DELTA = 20;

    @Test
    public void onEndTask() {
        Robot robot = new Warrior(ROBOT_NAME);

        RobotPool.getRobotPool().addRobot(robot);

        int beforeSize = RobotPool.getRobotPool().size();

        robot.addTask(TASK_NAME);

        try {
            Thread.sleep(THREAD_WAIT_DELTA);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int afterSize =  RobotPool.getRobotPool().size();

        assertEquals(--beforeSize, afterSize);
    }
}