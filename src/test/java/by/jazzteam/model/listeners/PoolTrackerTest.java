package by.jazzteam.model.listeners;

import by.jazzteam.model.pool.RobotPool;
import by.jazzteam.model.robots.Robot;
import by.jazzteam.model.robots.Warrior;
import by.jazzteam.model.tasks.EmptyTask;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PoolTrackerTest {

    @Test
    public void onAddTask() {

        Robot robot = new Warrior();

        PoolTracker poolTracker = new PoolTracker();

        for (int i = 0; i <= PoolTracker.CRITICAL_AMOUNT_OF_TASKS; i++) {
            robot.addTask(robot.getMethods().iterator().next());
        }

        int beforeAmount = RobotPool.getRobotPool().size();

        poolTracker.onAddTask(robot, new EmptyTask("Test task", 0));

        int afterAmount = RobotPool.getRobotPool().size();

        assertEquals(++beforeAmount, afterAmount);
    }
}