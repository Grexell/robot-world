package by.jazzteam.model.robots;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class WarriorTest {

    private static final String ROBOT_NAME = "Test robot";
    private static final int DAMAGE = 5;

    private static Warrior warrior;

    @Before
    public void setUp() throws Exception {
        warrior = new Warrior(ROBOT_NAME, DAMAGE);
    }

    @Test
    public void getDamage() {
        assertEquals(warrior.getDamage(), DAMAGE);
    }

    @Test
    public void setDamage() {
        int newDamage = Math.abs(warrior.getDamage() + warrior.getDamage());

        warrior.setDamage(newDamage);

        assertEquals(warrior.getDamage(), newDamage);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setDamageWithNegative() {
        int newDamage = -warrior.getDamage();

        warrior.setDamage(newDamage);
    }
}