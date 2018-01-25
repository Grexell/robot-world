package by.jazzteam.controller;

import by.jazzteam.model.pool.RobotPool;
import by.jazzteam.model.listeners.RobotTaskExecutionListener;
import by.jazzteam.model.listeners.RobotConfigListener;
import by.jazzteam.model.listeners.WebSocketPoolListener;
import by.jazzteam.model.listeners.WebSocketRobotTaskExecutionListener;
import by.jazzteam.model.robots.Robot;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/messages")
public class RobotWorldWebSocket {

    private WebSocketPoolListener webSocketPoolListener;
    private RobotConfigListener configPoolListener;

    private RobotTaskExecutionListener robotTaskExecutionListener;

    @OnOpen
    public void addListeners(Session session) {
        RobotPool pool = RobotPool.getRobotPool();
        webSocketPoolListener = new WebSocketPoolListener(session);

        robotTaskExecutionListener = new WebSocketRobotTaskExecutionListener(session);

        configPoolListener = new RobotConfigListener(robotTaskExecutionListener);
        pool.addListener(webSocketPoolListener);
        pool.addListener(configPoolListener);

        for (Robot currentRobot: pool.getRobots()) {
            webSocketPoolListener.onAddRobot(currentRobot);
            configPoolListener.onAddRobot(currentRobot);
        }
    }

    @OnClose
    public void removeListeners(Session session) {
        RobotPool pool = RobotPool.getRobotPool();
        pool.removeListener(webSocketPoolListener);
        pool.removeListener(configPoolListener);
        for (Robot currentRobot: pool.getRobots()) {
            configPoolListener.onRemoveRobot(currentRobot);
        }
    }

    @OnError
    public void errorListener(Session session, Throwable th) {
        RobotPool pool = RobotPool.getRobotPool();
        pool.removeListener(webSocketPoolListener);
        pool.removeListener(configPoolListener);
        for (Robot currentRobot: pool.getRobots()) {
            configPoolListener.onRemoveRobot(currentRobot);
        }
    }
}
