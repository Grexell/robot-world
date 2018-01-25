package by.jazzteam.model.listeners;

import by.jazzteam.model.json.Message;
import by.jazzteam.model.json.RobotData;
import by.jazzteam.model.robots.Robot;
import com.google.gson.Gson;

import javax.websocket.Session;
import java.io.IOException;

public class WebSocketPoolListener implements RobotPoolListener {

    private Session session;

    public WebSocketPoolListener(Session session) {
        this.session = session;
    }

    public void onAddRobot(Robot robot) {
        if (session.isOpen()) {
            Message message = new Message();
            RobotData robotData = new RobotData();
            message.setMessage("add");
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

    public void onRemoveRobot(Robot robot) {
        if (session.isOpen()) {
            Message message = new Message();
            message.setMessage("remove");
            RobotData robotData = new RobotData();
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
                System.out.println("end");
            }
        }
    }
}
