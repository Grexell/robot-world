package by.jazzteam.model.tasks;

/*
    Describes task
 */
public abstract class Task {

    private String name;

    public String getName() {
        return name;
    }

    public Task(String name) {
        this.name = name;
    }

    /*
        The method where the action of the task occurs
     */
    public abstract void execute();
}
