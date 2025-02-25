package bork.ui;

import bork.task.Task;
import bork.task.TaskList;

import java.util.Scanner;

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
     */
    public void showWelcome() {
        System.out.println("Hello! I'm Bork");
        System.out.println("What can I do for you?");
    }

    /**
     * Displays a message when the task list is reset.
     */
    public void showResetMessage() {
        System.out.println("Task list has been reset. Starting fresh!");
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
     */
    public void showError(String message) {
        System.out.println("Error: " + message);
    }

    /**
     * Displays an error message if there is an issue loading tasks from storage.
     */
    public void showLoadingError() {
        System.out.println("Error loading tasks. Starting with an empty bork.task list.");
    }

    /**
     * Displays a message confirming that a task has been added.
     *
     * @param task The task that was added/
     * @param taskCount The total number of tasks after adding the new task.
     */
    public void showTaskAdded(Task task, int taskCount) {
        System.out.println("Got it. I've added this bork.task:");
        System.out.println(" " + task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

    /**
     * Displays a message confirming that a task has been removed.
     *
     * @param task The task that was removed.
     * @param taskCount THe total number of tasks remaining after removing the task.
     */
    public void showTaskRemoved(Task task, int taskCount) {
        System.out.println("Noted. I've removed this bork.task:");
        System.out.println(" " + task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

    /**
     * Displays the list of tasks.
     *
     * @param tasks The list of tasks to be displayed.
     */
    public void showTaskList(TaskList tasks) {
        if (tasks.isEmpty()) {
            System.out.println("No tasks added yet.");
            return;
        }
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    /**
     * Displays a message confirmed that a task has been marked as done.
     *
     * @param task THe task that has been marked as done.
     */
    public void showMarkedTask(Task task) {
        System.out.println("Nice! I've marked this bork.task as done:");
        System.out.println(" " + task);
    }

    /**
     * Displays a message confirming that a task has been marked as not done.
     *
     * @param task The task that has been marked as not done.
     */
    public void showUnmarkedTask(Task task) {
        System.out.println("OK, I've marked this bork.task as not done yet:");
        System.out.println(" " + task);
    }

    /**
     * Displays the goodbye message when the application is exiting.
     */
    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
