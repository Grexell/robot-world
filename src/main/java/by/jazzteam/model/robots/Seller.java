package by.jazzteam.model.robots;

import by.jazzteam.model.items.Item;
import by.jazzteam.model.tasks.EmptyTask;
import by.jazzteam.model.tasks.Task;

import java.util.LinkedList;
import java.util.List;

public class Seller extends Robot {

    private int money;

    private List<Item> items;

    {
        Task task = new EmptyTask("Sell", 3);
        taskList.put(task.getName(), task);
        task = new EmptyTask("Buy", 5);
        taskList.put(task.getName(), task);
    }

    public Seller(String name) {
        this(name, 0, new LinkedList<Item>());
    }

    public Seller(int money, List<Item> items) {
        this.money = money;
        this.items = items;
    }

    public Seller(String name, int money, List<Item> items) {
        super(name);
        this.money = money;
        this.items = items;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        if (money >= 0){
            this.money = money;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        if (items != null) {
            this.items = items;
        } else {
            throw new NullPointerException();
        }
    }
}
