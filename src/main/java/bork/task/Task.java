package bork.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a description and completion status.
 * Subclasses may define specific types of tasks.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task with the specified description.
     * The task is initially not marked as done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        assert description != null && !description.trim().isEmpty() : "Description should not be null or empty";

        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the description of the object.
     *
     * @return The description as a string.
     */
    public String getDescription() {
        assert description != null : "Description should not be null";
        return description;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        assert !isDone : "Task is already marked as done";
        isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        assert isDone : "Task is already marked as not done";
        isDone = false;
    }

    /**
     * Returns the status of the task.
     *
     * @return "X" if the task is done, otherwise a space (" ").
     */
    public String getStatus() {
        return (isDone ? "X" : " ");
    }

    /**
     * Creates a Task object from a string representation stored in a file.
     * The method parses the task type and details from the file format.
     *
     * @param fileString The string representation of the task from a file.
     * @return A corresponding Task object, or {@code null} if the format is invalid.
     */
    public static Task fromFileString(String fileString) {
        String[] parts = fileString.split(" \\| ");
        if (parts.length < 3) {
            return null;
        }

        Task task;
        switch (parts[0]) {
        case "T":
            task = new ToDo(parts[2]);
            break;
        case "D":
            if (parts.length < 4) {
                return null;
            }
            task = new Deadline(parts[2], LocalDateTime.parse(parts[3],
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")));
            break;
        case "E":
            if (parts.length < 5) {
                return null;
            }
            task = new Event(parts[2],
                        LocalDateTime.parse(parts[3],
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")),
                        LocalDateTime.parse(parts[4], DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")));
            break;
        default:
            return null;
        }
        if (parts[1].equals("1")) {
            task.markAsDone();
        }
        return task;
    }

    /**
     * Returns a string representation of the task formatted for file storage.
     *
     * @return A formatted string representation of the task.
     */
    public abstract String toFileString();

    @Override
    public String toString() {
        return "[" + getStatus() + "] " + description;
    }
}
