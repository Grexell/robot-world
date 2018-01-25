package by.jazzteam.model.pool;

import by.jazzteam.model.listeners.PoolTracker;
import by.jazzteam.model.listeners.RemoveRobotLIstener;
import by.jazzteam.model.listeners.RobotConfigListener;
import by.jazzteam.model.listeners.RobotPoolListener;
import by.jazzteam.model.robots.Robot;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class RobotPool {

    private static class RobotPoolSingleton{
        private static final RobotPool ROBOT_POOL = new RobotPool();
    }

    public static RobotPool getRobotPool(){
        return RobotPoolSingleton.ROBOT_POOL;
    }

    /*
        Map of robots
     */
    private ConcurrentHashMap<String, Robot> robotHashMap;

    /*
        Map of pool listeners
     */
    private List<RobotPoolListener> poolListeners;

    private RobotPool() {
        robotHashMap = new ConcurrentHashMap<>();
        poolListeners = new LinkedList<>();

        poolListeners.add(new RobotConfigListener(new PoolTracker()));
        poolListeners.add(new RobotConfigListener(new RemoveRobotLIstener()));
    }

    public int size() {
        return robotHashMap.size();
    }

    public boolean addRobot(Robot robot) {
        boolean result = false;

        if (!robotHashMap.containsKey(robot.getName())) {

            for (RobotPoolListener listener: poolListeners) {

                listener.onAddRobot(robot);

            }
            robotHashMap.put(robot.getName(), robot);

            result = true;
        }

        return result;
    }

    public boolean removeRobot(Robot robot){
        boolean result = false;

        if (robotHashMap.remove(robot.getName()) != null){

            for (RobotPoolListener listener: poolListeners) {

                listener.onRemoveRobot(robot);

            }

            result = true;
        }
        return result;
    }

    public Robot getRobot(String name) {
        return robotHashMap.get(name);
    }

    public Collection<Robot> getRobots() {
        return robotHashMap.values();
    }

    public boolean addListener(RobotPoolListener poolListener) {
        return poolListeners.add(poolListener);
    }

    public boolean removeListener(RobotPoolListener poolListener) {
        return poolListeners.remove(poolListener);
    }

    public void sendTaskToAll(String task){
        for (Robot robot: robotHashMap.values()) {
            if (robot.getMethods().contains(task)) {
                robot.addTask(task);
            }
        }
    }

    public void sendTask(String name, String task) {
        Robot robot = robotHashMap.get(name);
        if (robot != null && robot.getMethods().contains(task)) {
            robot.addTask(task);
        }

    }
}
