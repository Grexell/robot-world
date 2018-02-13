package by.jazzteam.model.tasks;

import by.jazzteam.model.robots.Robot;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class EmptyTaskTest {
    private static final int EXECUTION_TIME = 2;
    private static final double TESTING_TIME_DELTA = 100;

    @Test
    public void testEmptyTaskExecution() {
        Robot robot = new Robot("Test robot");

        long timeBefore = System.currentTimeMillis();

        EmptyTask task = new EmptyTask("Test kill task", EXECUTION_TIME);
        task.execute();

        long timeAfter = System.currentTimeMillis();

        assertTrue(timeAfter - timeBefore <= EXECUTION_TIME * EmptyTask.MILISEC_IN_SEC + TESTING_TIME_DELTA);
    }
}