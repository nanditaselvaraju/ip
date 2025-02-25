package bork.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {

    @Test
    public void testAddTask() {
        TaskList taskList = new TaskList();
        Task task = new ToDo("Test Task");

        taskList.add(task);
        assertEquals(1, taskList.size(), "TaskList should have 1 task after adding.");
        assertEquals(task, taskList.get(0), "The added task should match the retrieved task.");
    }

    @Test
    public void testRemoveTask() {
        TaskList taskList = new TaskList();
        Task task = new ToDo("Test Task");
        taskList.add(task);

        Task removedTask = taskList.remove(0);
        assertEquals(0, taskList.size(), "TaskList should be empty after removing the task.");
        assertEquals(task, removedTask, "The removed task should match the added task.");
    }
}
