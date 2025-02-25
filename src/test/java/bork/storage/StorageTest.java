package bork.storage;

import bork.task.Task;
import bork.task.TaskList;
import bork.task.ToDo;
import bork.exception.BorkException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class StorageTest {

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
        assertEquals(task.toString(), loadedTaskList.get(0).toString(), "The loaded task should match the saved task.");
    }
}
