package bork.storage;

import bork.task.Task;
import bork.task.TaskList;
import bork.exception.BorkException;

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

    /**
     * Loads tasks from the specified file.
     * If the file does not exist, an empty list is returned.
     *
     * @return A list of tasks loaded from the file.
     * @throws BorkException If an error occurs while loading tasks.
     */
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

    /**
     * Saves the given list of tasks to the file.
     *
     * @param tasks The TaskList containing tasks to be saved.
     * @throws BorkException If an error occurs while writing to the file.
     */
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
