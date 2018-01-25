package by.jazzteam.model.listeners;

import by.jazzteam.model.robots.Robot;

public class RobotConfigListener implements RobotPoolListener {

    private RobotTaskExecutionListener robotTaskExecutionListener;
    private RobotTaskAddListener robotTaskAddListener;

    public RobotConfigListener(RobotTaskExecutionListener robotTaskExecutionListener) {
        this.robotTaskExecutionListener = robotTaskExecutionListener;
    }

    public RobotConfigListener(RobotTaskAddListener robotTaskAddListener) {
        this.robotTaskAddListener = robotTaskAddListener;
    }

    public RobotConfigListener(RobotTaskExecutionListener robotTaskExecutionListener, RobotTaskAddListener robotTaskAddListener) {
        this.robotTaskExecutionListener = robotTaskExecutionListener;
        this.robotTaskAddListener = robotTaskAddListener;
    }

    @Override
    public void onAddRobot(Robot robot) {
        if (robotTaskExecutionListener != null) {
            robot.addTaskExecutionListener(robotTaskExecutionListener);
        }
        if (robotTaskAddListener != null) {
            robot.addTaskAddListener(robotTaskAddListener);
        }
    }

    @Override
    public void onRemoveRobot(Robot robot) {
        if (robotTaskExecutionListener != null) {
            robot.removeTaskExecutionListener(robotTaskExecutionListener);
        }
        if (robotTaskAddListener != null) {
            robot.removeTaskAddListener(robotTaskAddListener);
        }
    }
}
