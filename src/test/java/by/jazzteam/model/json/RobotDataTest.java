package by.jazzteam.model.json;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class RobotDataTest {
    private static RobotData robotData;

    private static Set<String> METHODS_NAMES;
    private static final String ROBOT_NAME = "Test robot";

    private static Set<String> createStrngSet(String message, int amount) {
        Set<String> result = new HashSet<>(amount);

        for (int i = 0; i < amount; i++) {
            result.add(message + i);
        }

        return result;
    }

    @BeforeClass
    public static void setUpClass(){
        METHODS_NAMES = createStrngSet("Test ", 4);
    }

    @Before
    public void setUp(){
        robotData = new RobotData();
        robotData.setName(ROBOT_NAME);
        robotData.setMethods(METHODS_NAMES);
    }

    @Test
    public void testGetMethods() {
        assertEquals(robotData.getMethods(), METHODS_NAMES);
    }

    @Test
    public void testGetRobotName(){
        assertEquals(robotData.getName(), ROBOT_NAME);
    }

    @Test
    public void testSetName(){
        String newName = ROBOT_NAME + ROBOT_NAME;

        robotData.setName(newName);
        assertEquals(robotData.getName(), newName);
    }

    @Test
    public void testSetMethods(){
        Set<String> newMethods = createStrngSet("Test method ", 5);

        robotData.setMethods(newMethods);
        assertEquals(robotData.getMethods(), newMethods);
    }
}
