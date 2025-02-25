package bork.task;

public class ToDo extends Task {

    /**
     * Constructs a ToDo task with the given description.
     * @param description The description of the ToDo task.
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toFileString() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
