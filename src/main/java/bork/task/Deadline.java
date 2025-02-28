package bork.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Represents a task with a specific deadline.
 * Includes a description and a due date/time.
 */
public class Deadline extends Task {
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a");

    private final LocalDateTime deadline;

    /**
     * Constructs a Deadline task with the given description and deadline.
     *
     * @param description The description of the deadline task.
     * @param deadline The date and time by which the task must be completed.
     */
    public Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = Objects.requireNonNull(deadline, "Deadline cannot be null");
    }

    /**
     * Converts the Deadline task to a formatted string suitable for file storage.
     *
     * @return A string representation of the task in a format suitable for saving to a file.
     */
    @Override
    public String toFileString() {
        DateTimeFormatter storageFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + deadline.format(storageFormat);
    }

    /**
     * Returns a string representation of the Deadline task.
     * Includes the task type, status, description, and formatted deadline.
     *
     * @return A string representation of the Deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline.format(OUTPUT_FORMAT) + ")";
    }

    /**
     * Retrieves the deadline of the task.
     *
     * @return The deadline as a LocalDateTime object.
     */
    public LocalDateTime getDeadline() {
        return deadline;
    }
}
