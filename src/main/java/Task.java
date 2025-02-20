import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsNotDone() {
        isDone = false;
    }

    public String getStatus() {
        return (isDone ? "X" : " ");
    }

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
                task = new Deadline(parts[2], LocalDateTime.parse(parts[3], DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")));
                break;
            case "E":
                if (parts.length < 5) {
                    return null;
                }
                task = new Event(parts[2], LocalDateTime.parse(parts[3], DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")),
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

    public abstract String toFileString();

    @Override
    public String toString() {
        return "[" + getStatus() + "] " + description;
    }
}
