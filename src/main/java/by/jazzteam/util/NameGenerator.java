package by.jazzteam.util;

import java.util.Iterator;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Set;

public class NameGenerator {

    public static final String NAME_LIST_FILENAME = "by.jazzteam.resources.names.NameList";

    public static int MIN_VALUE = 5;
    public static int MAX_VALUE = 50;

    private static Random random = new Random();

    /*
        Returns random name from NAME_LIST_FILENAME resource file
     */
    public static String randomString() {
        Set<String> names = ResourceBundle.getBundle(NAME_LIST_FILENAME).keySet();

        int index = random.nextInt(names.size());
        Iterator<String> iterator = names.iterator();

        String name = "";

        for(int i = 0; i <= index; i++) {
            if (iterator.hasNext()) {
                name = iterator.next();
            }
        }

        return name + (random.nextInt(MAX_VALUE - MIN_VALUE) + MIN_VALUE);
    }
}
