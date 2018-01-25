package by.jazzteam.model.tasks;

import by.jazzteam.model.pool.RobotPool;
import by.jazzteam.model.robots.Robot;

public class KillTask extends Task {

    public static final boolean KILLED_EXPRESSION = false;

    private Robot robot;

    private RobotPool pool;

    public KillTask(String name, Robot robot, RobotPool pool) {
        super(name);
        this.robot = robot;
        this.pool = pool;
    }

    @Override
    public void execute() {
        robot.setAlive(KILLED_EXPRESSION);
    }
}
