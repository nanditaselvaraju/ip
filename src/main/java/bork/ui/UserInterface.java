package bork.ui;

import java.util.List;
import java.util.Scanner;

import bork.task.Task;
import bork.task.TaskList;

/**
 * Handles user interaction, including displaying messages and reading input.
 */
public class UserInterface {
    private Scanner scanner;

    /**
     * Constructs a {@code UserInterface} and initialises the input scanner.
     */
    public UserInterface() {
        scanner = new Scanner(System.in);
    }

    /**
     * Displays the welcome message when the application starts.
     *
     * @return The welcome message.
     */
    public String showWelcome() {
        return "Hello! I'm Bork\nWhat can I do for you?";
    }

    /**
     * Displays a message when the task list is reset.
     *
     * @return The reset message.
     */
    public String showResetMessage() {
        return "Task list has been reset. Starting fresh!";
    }

    /**
     * Reads and returns a command entered by the user.
     *
     * @return The trimmed command input from the user.
     */
    public String readCommand() {
        return scanner.nextLine().trim();
    }

    /**
     * Displays an error message.
     *
     * @param message THe error message to be displayed.
     * @return THe error message.
     */
    public String showError(String message) {
        return "Error: " + message;
    }

    /**
     * Displays an error message if there is an issue loading tasks from storage.
     *
     * @return The loading error message.
     */
    public String showLoadingError() {
        return "Error loading tasks. Starting with an empty task list.";
    }

    /**
     * Displays a message confirming that a task has been added.
     *
     * @param task The task that was added.
     * @param taskCount The total number of tasks after adding the new task.
     * @return The task added message.
     */
    public String showTaskAdded(Task task, int taskCount) {
        return "Got it. I've added this task:\n "
                + task
                + "\nNow you have "
                + taskCount
                + " tasks in the list.";
    }

    /**
     * Displays a message confirming that a task has been removed.
     *
     * @param task The task that was removed.
     * @param taskCount The total number of tasks remaining after removing the task.
     * @return The task removed message.
     */
    public String showTaskRemoved(Task task, int taskCount) {
        return "Noted. I've removed this task:\n "
                + task
                + "\nNow you have "
                + taskCount
                + " tasks in the list.";
    }

    /**
     * Displays the list of tasks.
     *
     * @param tasks The list of tasks to be displayed.
     * @return The task list message.
     */
    public String showTaskList(TaskList tasks) {
        if (tasks.isEmpty()) {
            return "No tasks added yet.";
        }
        StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append((i + 1)).append(". ").append(tasks.get(i)).append("\n");
        }
        return sb.toString();
    }

    /**
     * Displays a message confirmed that a task has been marked as done.
     *
     * @param task The task that has been marked as done.
     * @return TThe task marked as done message.
     */
    public String showMarkedTask(Task task) {
        return "Nice! I've marked this task as done:\n " + task;
    }

    /**
     * Displays a message confirming that a task has been marked as not done.
     *
     * @param task The task that has been marked as not done.
     * @return The task marked as not done message.
     */
    public String showUnmarkedTask(Task task) {
        return "OK, I've marked this task as not done yet:\n " + task;
    }

    /**
     * Displays the goodbye message when the application is exiting.
     *
     * @return The goodbye message.
     */
    public String showGoodbye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Displays a message to the standard output.
     *
     * @param message The message to be displayed.
     * @return The message.
     */
    public String showMessage(String message) {
        return message;
    }

    /**
     * Displays a list of matching tasks.
     * If the list is empty, a message indicating no matches is shown.
     * Otherwise the tasks are displayed with their corresponding index.
     *
     * @param tasks The list of matching tasks to be displayed.
     * @return The matching tasks message.
     */
    public String showMatchingTasks(List<Task> tasks) {
        if (tasks.isEmpty()) {
            showMessage("No matching tasks found.");
        }
        StringBuilder sb = new StringBuilder("Here are the matching tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append((i + 1)).append(". ").append(tasks.get(i)).append("\n");
        }
        return sb.toString();
    }

    /**
     * Displays the list of tasks sorted chronologically.
     *
     * @param tasks The task list that has been sorted.
     * @return A formatted string showing the sorted tasks.
     */
    public String showSortedTasks(TaskList tasks) {
        StringBuilder sb = new StringBuilder("Tasks sorted chronologically:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append((i + 1)).append(". ").append(tasks.get(i)).append("\n");
        }
        return sb.toString();
    }
}
