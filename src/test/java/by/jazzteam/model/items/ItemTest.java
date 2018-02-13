package by.jazzteam.model.items;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ItemTest {

    private static final int ITEM_COST = 120;
    private static final String ITEM_NAME = "Test item";

    private static Item item;

    @Before
    public void setUp(){
        item = new Item(ITEM_COST, ITEM_NAME);
    }

    @Test
    public void testGetCost(){
        assertEquals(item.getCost(), ITEM_COST);
    }

    @Test
    public void testGetName(){
        assertEquals(item.getName(), ITEM_NAME);
    }

    @Test
    public void testSetCost(){

        int newCost = ITEM_COST + ITEM_COST;

        item.setCost(newCost);

        assertEquals(item.getCost(), newCost);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetCostWithNegativeNumber(){
        int newCost = -ITEM_COST;

        item.setCost(newCost);
    }

    @Test
    public void testSetName(){
        String newName = ITEM_NAME + ITEM_NAME;

        item.setName(newName);

        assertEquals(item.getName(), newName);
    }

    @Test(expected = NullPointerException.class)
    public void testSetNameWithNull(){
        String newName = null;

        item.setName(newName);
    }
}
