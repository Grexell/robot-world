package by.jazzteam.util;

import by.jazzteam.model.robots.Robot;
import by.jazzteam.model.robots.Seller;
import by.jazzteam.model.robots.Warrior;
import by.jazzteam.model.robots.Worker;

public class RobotCreator {

    public static final String WARRIOIR = "warrior";
    public static final String WORKER = "worker";
    public static final String SELLER = "seller";


    public static Robot createRobot(String type, String name) {

        Robot result;

        type = type.toLowerCase();

        switch(type) {
            case WARRIOIR:
                result = new Warrior(name);
                break;
            case WORKER:
                result = new Worker(name);
                break;
            case SELLER:
                result = new Seller(name);
                break;
            default:
                result = new Robot(name);
        }

        return result;
    }
}
