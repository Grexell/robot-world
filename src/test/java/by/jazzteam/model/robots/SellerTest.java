package by.jazzteam.model.robots;

import by.jazzteam.model.items.Item;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SellerTest {

    private static final String ROBOT_NAME = "Test robot";
    private static final int MONEY = 5;
    private static List<Item> items;


    private static Seller seller;

    @BeforeClass
    public static void setUpClass() {
        items = new LinkedList<>();
        items.add(new Item(12, "Item 1"));
        items.add(new Item(14, "Item 2"));
        items.add(new Item(2, "Item 3"));
        items.add(new Item(5, "Item 4"));
    }

    @Before
    public void setUp() {
        seller = new Seller(ROBOT_NAME, MONEY, items);
    }

    @Test
    public void getMoney() {
        assertEquals(seller.getMoney(), MONEY);
    }

    @Test
    public void setMoney() {
        int newMoney = Math.abs(seller.getMoney() + seller.getMoney());

        seller.setMoney(newMoney);

        assertEquals(seller.getMoney(), newMoney);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setMoneyWithNegative() {
        int newMoney = -seller.getMoney();

        seller.setMoney(newMoney);
    }

    @Test
    public void getItems() {
        assertEquals(seller.getItems(), items);
    }

    @Test
    public void setItems() {
        List<Item> newItems = new LinkedList<>(items);

        newItems.add(new Item(9, "new item"));

        seller.setItems(newItems);

        assertEquals(seller.getItems(), newItems);
    }

    @Test(expected = NullPointerException.class)
    public void setItemsWithNull() {
        List<Item> newItems = null;

        seller.setItems(newItems);
    }
}