package by.jazzteam.model.listeners;

import by.jazzteam.model.robots.Robot;

public interface RobotPoolListener {
    void onAddRobot(Robot robot);
    void onRemoveRobot(Robot robot);
}
