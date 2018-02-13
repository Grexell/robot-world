package by.jazzteam.util;

import by.jazzteam.model.robots.Robot;
import by.jazzteam.model.robots.Seller;
import by.jazzteam.model.robots.Warrior;
import by.jazzteam.model.robots.Worker;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class RobotCreatorTest {

    private static final String ROBOT_NAME = "Test robot";

    @Test
    public void testName(){
        Robot testRobot = RobotCreator.createRobot("", ROBOT_NAME);

        assertEquals(testRobot.getName(), ROBOT_NAME);
    }

    @Test
    public void testWarriorCreation(){
        Robot testRobot = RobotCreator.createRobot(RobotCreator.WARRIOIR, ROBOT_NAME);

        assertEquals(testRobot.getClass(), Warrior.class);
    }

    @Test
    public void testSellerCreation(){
        Robot testRobot = RobotCreator.createRobot(RobotCreator.SELLER, ROBOT_NAME);

        assertEquals(testRobot.getClass(), Seller.class);
    }

    @Test
    public void testWorkerCreation(){
        Robot testRobot = RobotCreator.createRobot(RobotCreator.WORKER, ROBOT_NAME);

        assertEquals(testRobot.getClass(), Worker.class);
    }

    @Test
    public void testSimpleRobotCreation() {
        Robot testRobot = RobotCreator.createRobot("", ROBOT_NAME);

        assertNotNull(testRobot);
        assertEquals(testRobot.getClass(), Robot.class);
    }
}
