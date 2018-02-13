## About Robot world
Robot world is a web application which represents the game world consisting of robots. User can create a robot of the selected type. Robots have REST API that allow users to manage their actions.
This project is written in Java.
## Instalation
To install application to the web-server you need to deploy [robot-world.war](https://github.com/Grexell/robot-world/blob/master/target/robot-world.war).

You also can download source code which is located in the catalog [src](https://github.com/Grexell/robot-world/tree/master/src).
## Creating a robot
To create a robot you need to extend [by.jazzteam.model.robots.Robot](https://github.com/Grexell/robot-world/blob/master/src/main/java/by/jazzteam/model/robots/Robot.java). At this point you will get prepared  version of robot with a single task ***Kill***. You can add new tasks or implement any logic. To add new task you need to code something like this:

```java
Task task = new KillTask("Kill",this);
taskList.put(task.getName(), task);
```

## REST API
the management of the world is simple. You have 3 methods:
* api/robots/add/{***type***}/{***name***} - creates an ***type*** robot and named ***name***
* api/robots/commands/{***command***} - sends command with name ***name*** to all robots
* api/robot/{***name***}/{***command***}" - sends command with name ***name*** to robot with name ***name***
