package by.jazzteam.model.items;

public class Item {

    public static final int DEFAULT_COST = 0;
    public static final String DEFAULT_NAME = "";

    private int cost;
    private String name;

    public Item() {
        cost = DEFAULT_COST;
        name = DEFAULT_NAME;
    }

    public Item(int cost, String name) {
        this.cost = cost;
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        if (cost >= 0) {
            this.cost = cost;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name != null) {
            this.name = name;
        } else {
            throw new NullPointerException();
        }
    }
}
