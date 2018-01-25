package by.jazzteam.model.listeners;

import by.jazzteam.model.json.Message;
import by.jazzteam.model.json.RobotData;
import by.jazzteam.model.robots.Robot;
import by.jazzteam.model.tasks.Task;
import com.google.gson.Gson;

import javax.websocket.Session;
import java.io.IOException;

public class WebSocketRobotTaskExecutionListener implements RobotTaskExecutionListener {

    private Session session;

    public WebSocketRobotTaskExecutionListener(Session session) {
        this.session = session;
    }

    @Override
    public void onStartTask(Robot robot, Task task) {
        if (session.isOpen()) {
            Message message = new Message();
            RobotData robotData = new RobotData();
            message.setMessage("Robot " + robot.getName() + " started task " + task.getName());
            robotData.setName(robot.getName());
            robotData.setMethods(robot.getMethods());
            message.setRobot(robotData);

            Gson gson = new Gson();

            String mes = gson.toJson(message);

            try {
                synchronized (session) {
                    session.getBasicRemote().sendText(mes);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onEndTask(Robot robot, Task task) {
        if (session.isOpen()) {
            Message message = new Message();
            RobotData robotData = new RobotData();
            message.setMessage("Robot " + robot.getName() + " ended task " + task.getName());
            robotData.setName(robot.getName());
            robotData.setMethods(robot.getMethods());
            message.setRobot(robotData);

            Gson gson = new Gson();

            String mes = gson.toJson(message);

            try {
                synchronized (session) {
                    session.getBasicRemote().sendText(mes);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
