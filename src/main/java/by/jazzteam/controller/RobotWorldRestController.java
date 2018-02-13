package by.jazzteam.controller;

import by.jazzteam.model.pool.RobotPool;
import by.jazzteam.model.robots.Robot;
import by.jazzteam.util.RobotCreator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RobotWorldRestController {

    /*
        Rest method of adding new robot
     */
    @PostMapping("robots/add/{type}/{name}")
    public ResponseEntity<String> addRobot(@PathVariable String type, @PathVariable String name){
        Robot r = RobotCreator.createRobot(type, name);
        RobotPool.getRobotPool().addRobot(r);

        return new ResponseEntity<>(name, HttpStatus.OK);
    }

    /*
        Rest method of sending task to the all robots
     */
    @PostMapping("robots/commands/{command}")
    public ResponseEntity<String> sendCommandToAllRobots(@PathVariable String command){
        RobotPool.getRobotPool().sendTaskToAll(command);

        return new ResponseEntity<>("", HttpStatus.OK);
    }

    /*
        Rest method of sending task to the robot
     */
    @PostMapping("robot/{name}/{command}")
    public ResponseEntity<String> sendCommandToRobot(@PathVariable String name, @PathVariable String command){
        RobotPool.getRobotPool().sendTask(name, command);
        return new ResponseEntity<>("", HttpStatus.OK);
    }
}
