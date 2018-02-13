package by.jazzteam.model.robots;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class WorkerTest {

    private static final String ROBOT_NAME = "Test robot";
    private static final int BACKPACK_SIZE = 5;
    private static Worker worker;

    @Before
    public void setUp() {
        worker = new Worker(ROBOT_NAME, BACKPACK_SIZE);
    }

    @Test
    public void getBackpackSize() {
        assertEquals(worker.getBackpackSize(), BACKPACK_SIZE);
    }

    @Test
    public void setBackpackSize() {
        int newSize = Math.abs(BACKPACK_SIZE + BACKPACK_SIZE);

        worker.setBackpackSize(newSize);

        assertEquals(worker.getBackpackSize(), newSize);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setBackpackSizeWithNegativeSize() {

        int newSize = -worker.getBackpackSize();

        worker.setBackpackSize(newSize);
    }
}