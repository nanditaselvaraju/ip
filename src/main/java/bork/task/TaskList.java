package bork.task;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * Represents a list of tasks.
 * Provides methods to add, remove, retrieve, and iterate over tasks.
 */
public class TaskList implements Iterable<Task> {
    private List<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with a given list of tasks.
     *
     * @param tasks The list of tasks to initialise the TaskList with.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns an iterator over the tasks in the TaskList.
     *
     * @return An iterator over the tasks.
     */
    @Override
    public Iterator<Task> iterator() {
        return tasks.iterator();
    }

    /**
     * Clears all the tasks from the TaskList.
     */
    public void reset() {
        this.tasks.clear();
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        assert task != null : "Task cannot be null";
        tasks.add(task);
    }

    /**
     * Removes and returns a task at the specified index.
     *
     * @param index The index of the task to be removed.
     * @return The removed task.
     */
    public Task remove(int index) {
        return tasks.remove(index);
    }

    /**
     * Retrieves a task at the specified index.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks in the TaskList.
     *
     * @return The number of tasks in the list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Checks if the TaskList is empty.
     *
     * @return {@code true} if the list is empty, otherwise {@code false}.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Checks if the task index is valid.
     * @param index The task index
     * @return {@code true} if the index is a valid integer, otherwise {@code false}.
     */
    public boolean isValidIndex(int index) {
        return index >= 0 && index < size();
    }

    /**
     * Sorts tasks chronologically. Events and deadlines are sorted by start time or due dates.
     * ToDos remain unchanged at the end.
     */
    public void sortTasks() {
        tasks.sort(Comparator.comparing(task -> {
            if (task instanceof Event) {
                return ((Event) task).getStart();
            } else if (task instanceof Deadline) {
                return ((Deadline) task).getDeadline();
            }
            return null;
        }, Comparator.nullsLast(Comparator.naturalOrder())));
    }
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Returns the list of all tasks in the TaskList.
     *
     * @return A list of tasks.
     */
    public List<Task> getAllTasks() {
        return tasks;
    }
}
