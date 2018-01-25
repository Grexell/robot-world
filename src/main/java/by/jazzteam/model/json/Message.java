package by.jazzteam.model.json;

public class Message {

    private String message;
    private RobotData robot;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public RobotData getRobot() {
        return robot;
    }

    public void setRobot(RobotData robot) {
        this.robot = robot;
    }
}
