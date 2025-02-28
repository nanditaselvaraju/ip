package bork.task;

/**
 * Represents a simple ToDo task.
 * This type of task only contains a description and completion status.
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDo task with the given description.
     * @param description The description of the ToDo task.
     */
    public ToDo(String description) {
        super(description);
        assert description != null && !description.trim().isEmpty() : "Description should not be null or empty";
    }

    /**
     * Converts the ToDo task to a formatted string suitable for file storage.
     *
     * @return A string representation of the task in a format suitable for saving to a file.
     */
    @Override
    public String toFileString() {
        assert description != null : "Description should not be null";
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }

    /**
     * Returns a string representation of the ToDo task.
     * Includes the task type, status, and description.
     *
     * @return A string representation of the ToDo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
