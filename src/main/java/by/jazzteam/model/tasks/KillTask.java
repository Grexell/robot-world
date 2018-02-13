package by.jazzteam.model.tasks;

import by.jazzteam.model.robots.Robot;

public class KillTask extends Task {

    public static final boolean KILLED_EXPRESSION = false;

    private Robot robot;

    public KillTask(String name, Robot robot) {
        super(name);
        this.robot = robot;
    }

    @Override
    public void execute() {
        robot.setAlive(KILLED_EXPRESSION);
    }
}
