import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TaskList implements Iterable<Task> {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public Iterator<Task> iterator() {
        return tasks.iterator();
    }

    public void reset() {
        this.tasks.clear();
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public Task remove(int index) {
        return tasks.remove(index);
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public int size() {
        return tasks.size();
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public List<Task> getAllTasks() {
        return tasks;
    }
}
