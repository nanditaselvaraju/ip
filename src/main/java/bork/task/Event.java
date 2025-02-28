package bork.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task with a start and end time.
 */
public class Event extends Task {
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a");

    private final LocalDateTime start;
    private final LocalDateTime end;

    /**
     * Constructs an Event task with the given description, start time, and end time.
     *
     * @param description The description of the event.
     * @param start The start time of the event.
     * @param end The end time of the event.
     * @throws IllegalArgumentException If start or end time is null, or start is after end.
     */
    public Event(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
      
        assert description != null && !description.trim().isEmpty() : "Description should not be null or empty";
        assert start != null : "Start time should not be null";
        assert end != null : "End time should not be null";
        assert !end.isBefore(start) : "End time should not be before start time";

        if (start == null || end == null) {
            throw new IllegalArgumentException("Start and end times cannot be null.");
        }
        if (start.isAfter(end)) {
            throw new IllegalArgumentException("Start time must be before end time.");
        }
      
        this.start = start;
        this.end = end;
    }

    /**
     * Formats a LocalDateTime to a human-readable string.
     *
     * @param dateTime The date-time to format.
     * @param formatter The formatter to use.
     * @return A formatted date-time string.
     */
    private String formatDateTime(LocalDateTime dateTime, DateTimeFormatter formatter) {
        return dateTime.format(formatter);
    }

    /**
     * Returns the string representation of the event formatted for file storage.
     *
     * @return A formatted string representation of the event for storage.
     */
    @Override
    public String toFileString() {
        String startStr = (start != null) ? start.format(INPUT_FORMAT) : "N/A";
        String endStr = (end != null) ? end.format(INPUT_FORMAT) : "N/A";
        return "E | " + (isDone ? "1" : "0") + " | " + description
                + " | " + startStr + " | " + endStr;

    }

    /**
     * Returns the string representation of the event for display.
     *
     * @return A formatted string representation of the event.
     */
    @Override
    public String toString() {
        String startStr = (start != null) ? start.format(OUTPUT_FORMAT) : "N/A";
        String endStr = (end != null) ? end.format(OUTPUT_FORMAT) : "N/A";
        return "[E]" + super.toString() + " (from: " + startStr + " to: " + endStr + ")";
    }

    /**
     * Returns the start time of the event.
     *
     * @return The start time as a LocalDateTime.
     */
    public LocalDateTime getStart() {
        return start;
    }

    /**
     * Returns the end time of the event.
     *
     * @return The end time as a LocalDateTime.
     */
    public LocalDateTime getEnd() {
        return end;
    }
}
