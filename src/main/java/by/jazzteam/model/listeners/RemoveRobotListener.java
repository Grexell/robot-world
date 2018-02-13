package by.jazzteam.model.listeners;

import by.jazzteam.model.pool.RobotPool;
import by.jazzteam.model.robots.Robot;
import by.jazzteam.model.tasks.Task;

public class RemoveRobotListener implements RobotTaskExecutionListener {
    @Override
    public void onStartTask(Robot robot, Task task) {

    }

    @Override
    public void onEndTask(Robot robot, Task task) {
        if (!robot.isAlive()) {
            RobotPool.getRobotPool().removeRobot(robot);
        }
    }
}