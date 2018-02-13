package by.jazzteam.model.tasks;

import by.jazzteam.model.robots.Robot;
import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class KillTaskTest {
    @Test
    public void testKillTaskExecution(){
        Robot robot = new Robot("Test robot");

        KillTask killTask = new KillTask("Test kill task", robot);
        killTask.execute();

        assertFalse(robot.isAlive());
    }
}
