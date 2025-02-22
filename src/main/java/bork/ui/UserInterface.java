package bork.ui;

import bork.task.Task;
import bork.task.TaskList;

import java.util.Scanner;

public class UserInterface {
    private Scanner scanner;

    public UserInterface() {
        scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println("Hello! I'm Bork");
        System.out.println("What can I do for you?");
    }

    public void showResetMessage() {
        System.out.println("Task list has been reset. Starting fresh!");
    }

    public String readCommand() {
        return scanner.nextLine().trim();
    }

    public void showError(String message) {
        System.out.println("Error: " + message);
    }

    public void showLoadingError() {
        System.out.println("Error loading tasks. Starting with an empty bork.task list.");
    }

    public void showTaskAdded(Task task, int taskCount) {
        System.out.println("Got it. I've added this bork.task:");
        System.out.println(" " + task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

    public void showTaskRemoved(Task task, int taskCount) {
        System.out.println("Noted. I've removed this bork.task:");
        System.out.println(" " + task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

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

    public void showMarkedTask(Task task) {
        System.out.println("Nice! I've marked this bork.task as done:");
        System.out.println(" " + task);
    }

    public void showUnmarkedTask(Task task) {
        System.out.println("OK, I've marked this bork.task as not done yet:");
        System.out.println(" " + task);
    }

    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
