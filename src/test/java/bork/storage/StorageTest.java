package bork.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;

import bork.exception.BorkException;
import bork.task.Task;
import bork.task.TaskList;
import bork.task.ToDo;

/**
 * Unit tests for the Storage class.
 */
public class StorageTest {

    /**
     * Tests saving and loading tasks to ensure data integrity.
     *
     * @throws BorkException If an error occurs in Storage operations.
     * @throws IOException If an error occurs while handling the temporary file.
     */
    @Test
    public void testSaveAndLoadTasks() throws BorkException, IOException {
        File tempFile = File.createTempFile("test", ".txt");
        tempFile.deleteOnExit();

        Storage storage = new Storage(tempFile.getAbsolutePath());
        TaskList taskList = new TaskList();
        Task task = new ToDo("Test Task");
        taskList.add(task);

        storage.save(taskList);

        List<Task> loadedTaskList = storage.load();

        assertEquals(1, loadedTaskList.size(), "Loaded TaskList should have 1 task.");
        assertEquals(task.toString(), loadedTaskList.get(0).toString(),
                "The loaded task should match the saved task.");
    }
}
