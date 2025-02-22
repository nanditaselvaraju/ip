package bork.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime start;
    protected LocalDateTime end;
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a");

    public Event(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toFileString() {
        String startStr = (start != null) ? start.format(INPUT_FORMAT) : "N/A";
        String endStr = (end != null) ? end.format(INPUT_FORMAT) : "N/A";
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + start.format(INPUT_FORMAT) + " | " + end.format(INPUT_FORMAT);
    }

    @Override
    public String toString() {
        String startStr = (start != null) ? start.format(OUTPUT_FORMAT) : "N/A";
        String endStr = (end != null) ? end.format(OUTPUT_FORMAT) : "N/A";
        return "[E]" + super.toString() + " (from: " + start.format(OUTPUT_FORMAT) + " to: " + end.format(OUTPUT_FORMAT) + ")";
    }
}
