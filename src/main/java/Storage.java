import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage (String filePath) {
        this.filePath = filePath;
    }

    public List<Task> load() throws BorkException {
        List<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            new File("data").mkdirs();
            return tasks;
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Task task = Task.fromFileString(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (FileNotFoundException e) {
            throw new BorkException("Error loading tasks from file.");
        }
        return tasks;
    }

    public void save(TaskList tasks) throws BorkException {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (Task task : tasks) {
                writer.write(task.toFileString() + System.lineSeparator());
            }
        } catch (IOException e) {
            throw new BorkException("Error saving tasks to file.");
        }
    }
}
