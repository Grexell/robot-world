package by.jazzteam.model.listeners;

import by.jazzteam.model.robots.Robot;
import by.jazzteam.model.tasks.Task;

public interface RobotTaskExecutionListener {
    void onStartTask(Robot robot, Task task);
    void onEndTask(Robot robot, Task task);
}
