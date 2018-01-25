package by.jazzteam.model.tasks;

public class EmptyTask extends Task{

    public static final int MILISEC_IN_SEC = 1000;

    private int executionTime;

    public EmptyTask(String name, int executionTime){
        super(name);
        this.executionTime = executionTime;
    }

    @Override
    public void execute() {
        try {
            Thread.sleep(executionTime * MILISEC_IN_SEC);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
