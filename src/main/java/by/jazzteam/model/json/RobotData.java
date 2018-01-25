package by.jazzteam.model.json;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RobotData {
    private String name;
    private Set<String> methods;

    public RobotData() {
        name = "";
        methods = new HashSet<String>();
    }

    public RobotData(String name, Set<String> methods) {
        this.name = name;
        this.methods = methods;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getMethods() {
        return methods;
    }

    public void setMethods(Set<String> methods) {
        this.methods = methods;
    }
}
