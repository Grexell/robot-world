package by.jazzteam.model.listeners;

import by.jazzteam.model.robots.Robot;
import by.jazzteam.model.tasks.Task;

public interface RobotTaskAddListener {
    void onAddTask(Robot robot, Task task);
}
