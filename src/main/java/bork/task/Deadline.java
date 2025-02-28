package bork.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a specific deadline.
 * Includes a description and a due date/time.
 */
public class Deadline extends Task {
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a");

    protected LocalDateTime deadline;

    /**
     * Constructs a Deadline task with the given description and deadline.
     *
     * @param description The description of the deadline task.
     * @param deadline The date and time by which the task must be completed.
     */
    public Deadline(String description, LocalDateTime deadline) {
        super(description);

        assert description != null && !description.trim().isEmpty() : "Description should not be null or empty";
        assert deadline != null : "Deadline should not be null";

        this.deadline = deadline;
    }

    /**
     * Converts the Deadline task to a formatted string suitable for file storage.
     *
     * @return A string representation of the task in a format suitable for saving to a file.
     */
    @Override
    public String toFileString() {
        assert deadline != null : "Deadline should not be null when saving to file";
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + deadline.format(INPUT_FORMAT);
    }

    /**
     * Returns a string representation of the Deadline task.
     * Includes the task type, status, description, and formatted deadline.
     *
     * @return A string representation of the Deadline task.
     */
    @Override
    public String toString() {
        assert deadline != null : "Deadline should not be null when generating string representation";
        return "[D]" + super.toString() + " (by: " + deadline.format(OUTPUT_FORMAT) + ")";
    }
}
