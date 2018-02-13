package by.jazzteam.model.json;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class MessageTest {

    private static final String MESSAGE_TEXT = "sdaf";
    private static RobotData MESSAGE_ROBOT_DATA;
    private static Set<String> METHODS_NAMES;
    private static final String ROBOT_NAME = "Test robot";

    private static Message message;

    @BeforeClass
    public static void setUpClass(){
        METHODS_NAMES = new HashSet<>(Arrays.asList("method 0", "method 1"));
        MESSAGE_ROBOT_DATA = new RobotData();
        MESSAGE_ROBOT_DATA.setMethods(METHODS_NAMES);
        MESSAGE_ROBOT_DATA.setName(ROBOT_NAME);
    }

    @Before
    public void setUp(){
        message = new Message();
        message.setMessage(MESSAGE_TEXT);
        message.setRobot(MESSAGE_ROBOT_DATA);
    }

    @Test
    public void testGetMessage() {
        assertEquals(message.getMessage(), MESSAGE_TEXT);
    }

    @Test
    public void testGetRobotData() {
        assertEquals(message.getRobot(), MESSAGE_ROBOT_DATA);
    }

    @Test
    public void testSetMessage() {
        String newMessage = MESSAGE_TEXT + MESSAGE_TEXT;

        message.setMessage(newMessage);

        assertEquals(message.getMessage(), newMessage);
    }

    @Test
    public void testSetRobotData() {
        RobotData newRobotData = new RobotData();

        message.setRobot(newRobotData);

        assertEquals(message.getRobot(), newRobotData);
    }
}
