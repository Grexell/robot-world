package by.jazzteam.model.robots;

import by.jazzteam.model.tasks.EmptyTask;
import by.jazzteam.model.tasks.Task;

public class Warrior extends Robot {
    private int damage;

    {
        Task task = new EmptyTask("Hit", 10);
        taskList.put(task.getName(), task);
        task = new EmptyTask("Chain combo", 15);
        taskList.put(task.getName(), task);
    }

    public Warrior() {
        damage = 0;
    }

    public Warrior(String name) {
        super(name);
    }

    public Warrior(int damage) {
        this.damage = damage;
    }

    public Warrior(String name, int damage) {
        super(name);
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
