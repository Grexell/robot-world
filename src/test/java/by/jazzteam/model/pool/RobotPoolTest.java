package by.jazzteam.model.pool;

import by.jazzteam.model.listeners.RobotPoolListener;
import by.jazzteam.model.robots.Robot;
import by.jazzteam.model.robots.Warrior;
import by.jazzteam.model.robots.Worker;
import javafx.util.Pair;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class RobotPoolTest {

    private static final String ROBOT_NAME = "Test Robot";
    private Robot robot;

    @Before
    public void setUp() {
        robot = new Warrior(ROBOT_NAME);

        RobotPool.getRobotPool().getRobots().clear();
    }

    @Test
    public void getRobotPool() {
        assertNotNull(RobotPool.getRobotPool());
    }

    @Test
    public void size() {
        assertEquals(RobotPool.getRobotPool().size(),0);
    }

    @Test
    public void addRobot() {
        int poolSize = RobotPool.getRobotPool().size();

        RobotPool.getRobotPool().addRobot(robot);

        int newPoolSize = RobotPool.getRobotPool().size();

        RobotPool.getRobotPool().removeRobot(robot);

        assertEquals(++poolSize, newPoolSize);
    }

    @Test
    public void removeRobot() {
        robot = new Robot(ROBOT_NAME + " test remove robot");

        RobotPool.getRobotPool().addRobot(robot);
        int poolSize = RobotPool.getRobotPool().size();

        RobotPool.getRobotPool().removeRobot(robot);

        int newPoolSize = RobotPool.getRobotPool().size();

        assertEquals(--poolSize, newPoolSize);
    }

    @Test
    public void getRobot() {
        RobotPool.getRobotPool().addRobot(robot);

        Robot testRobot = RobotPool.getRobotPool().getRobot(robot.getName());

        RobotPool.getRobotPool().removeRobot(robot);

        assertEquals(testRobot, robot);
    }

    @Test
    public void getRobots() {
        Collection<Robot> robots = RobotPool.getRobotPool().getRobots();

        assertNotNull(robots);
        assertEquals(robots.size(), RobotPool.getRobotPool().size());
    }

    @Test
    public void addListener() {
        RobotPoolListener listener = new RobotPoolListener() {
            @Override
            public void onAddRobot(Robot robot) {}

            @Override
            public void onRemoveRobot(Robot robot) {}
        };

        assertTrue(RobotPool.getRobotPool().addListener(listener));
    }

    @Test
    public void removeListener() {
        RobotPoolListener listener = new RobotPoolListener() {
            @Override
            public void onAddRobot(Robot robot) {}

            @Override
            public void onRemoveRobot(Robot robot) {}
        };

        RobotPool.getRobotPool().addListener(listener);
        assertTrue(RobotPool.getRobotPool().removeListener(listener));
    }

    @Test
    public void removeListenerWithInvalid() {
        RobotPoolListener listener = new RobotPoolListener() {
            @Override
            public void onAddRobot(Robot robot) {}

            @Override
            public void onRemoveRobot(Robot robot) {}
        };

        assertFalse(RobotPool.getRobotPool().removeListener(listener));
    }

    @Test
    public void sendTaskToAll() {
        String taskName = "Hit";

        Robot testRobot1 = new Warrior(" test warrior");
        Robot testRobot2 = new Worker(" test worker");

        RobotPool.getRobotPool().addRobot(testRobot1);
        RobotPool.getRobotPool().addRobot(testRobot2);

        Map<Robot, Integer> taskQueueSizes = new HashMap<>();

        for (Robot r: RobotPool.getRobotPool().getRobots()) {
            taskQueueSizes.put(r, r.getTaskQueueSize());
            r.addTask(taskName);
        }

        RobotPool.getRobotPool().sendTaskToAll(taskName);

        RobotPool.getRobotPool().removeRobot(testRobot1);
        RobotPool.getRobotPool().removeRobot(testRobot2);

        for (Map.Entry<Robot, Integer> entry: taskQueueSizes.entrySet()) {
            if (entry.getKey().getMethods().contains(taskName)) {
                assertEquals(entry.getValue() + 1, entry.getKey().getTaskQueueSize());
            } else {
                assertEquals(entry.getValue().intValue(), entry.getKey().getTaskQueueSize());
            }
        }
    }

    @Test
    public void sendTask() {
        String taskName = "Hit";

        Robot testRobot1 = new Warrior(" test warrior");
        Robot testRobot2 = new Worker(" test worker");

        RobotPool.getRobotPool().addRobot(testRobot1);
        RobotPool.getRobotPool().addRobot(testRobot2);

        List<Pair<Integer, Integer>> results = new LinkedList<>();

        for (Robot r: RobotPool.getRobotPool().getRobots()) {
            int before = r.getTaskQueueSize();

            //add current task
            r.addTask(taskName);

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //send task into the queue
            RobotPool.getRobotPool().sendTask(r.getName(), taskName);

            if (r.getMethods().contains(taskName)) {
                results.add(new Pair<>(before + 1, r.getTaskQueueSize()));
            } else {
                results.add(new Pair<>(before, r.getTaskQueueSize()));
            }
        }

        RobotPool.getRobotPool().removeRobot(testRobot1);
        RobotPool.getRobotPool().removeRobot(testRobot2);

        for (Pair<Integer, Integer> result: results) {
            assertEquals(result.getKey(), result.getValue());
        }
    }
}