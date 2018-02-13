package by.jazzteam.util;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class NameGeneratorTest {

    @Test
    public void testRandomName() {
        String name = NameGenerator.randomString();

        assertNotNull(name);
        assertFalse(name.isEmpty());
    }
}
