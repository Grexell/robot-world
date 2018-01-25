package by.jazzteam.model.robots;

import by.jazzteam.model.tasks.EmptyTask;
import by.jazzteam.model.tasks.Task;

public class Worker extends Robot{

    private int backpackSize;

    {
        Task task = new EmptyTask("Cut down a tree", 6);
        taskList.put(task.getName(), task);

        task = new EmptyTask("Build castle", 360);
        taskList.put(task.getName(), task);

        task = new EmptyTask("Build fence", 30);
        taskList.put(task.getName(), task);

        task = new EmptyTask("Get iron", 10);
        taskList.put(task.getName(), task);
    }

    public Worker(int backpackSize) {
        this.backpackSize = backpackSize;
    }

    public Worker(String name) {
        super(name);
    }

    public Worker(String name, int backpackSize) {
        super(name);
        this.backpackSize = backpackSize;
    }

    public int getBackpackSize() {
        return backpackSize;
    }

    public void setBackpackSize(int backpackSize) {
        if (backpackSize >= 0) {
            this.backpackSize = backpackSize;
        } else {
            throw new IllegalArgumentException();
        }
    }
}