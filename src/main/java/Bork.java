import java.util.Scanner;

public class Bork {
    private static final int maxTasks = 100;
    private static Task[] tasks = new Task[maxTasks];
    private static int taskCount = 0; // tracking number of tasks
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello! I'm Bork");
        System.out.println("What can I do for you?");

        while (true) {
            String input = scanner.nextLine().trim();
            String[] inputParts = input.split(" ", 2);
            String command = inputParts[0];

            if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (command.equals("list")) {
                printTaskList();
            } else if (command.equals("mark")) {
                markTask(inputParts);
            } else if (command.equals("unmark")) {
                unmarkTask(inputParts);
            } else {
                addTask(input);
            }
        }
        scanner.close();
    }
    private static void printTaskList() {
        if (taskCount == 0) {
            System.out.println("No tasks added yet.");
        } else {
            System.out.println("Here are the tasks in your list: ");
            for (int i = 0; i < taskCount; i++) {
                System.out.println((i + 1) + ". " + tasks[i]);
            }
        }
    }

    private static void addTask(String description) {
        if (taskCount < maxTasks) {
            tasks[taskCount] = new Task(description);
            taskCount++;
            System.out.println("added: " + description);
        } else {
            System.out.println("Task list is full!");
        }
    }

    private static void markTask(String[] inputParts) {
        if (inputParts.length < 2) {
            System.out.println("Please specify a task number.");
            return;
        }
        try {
            int taskIndex = Integer.parseInt(inputParts[1]) - 1;
            if (taskIndex >= 0 && taskIndex < taskCount) {
                tasks[taskIndex].markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  " + tasks[taskIndex]);
            } else {
                System.out.println("Invalid task number.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid format! Use: mark <task number>");
        }
    }

    private static void unmarkTask(String[] inputParts) {
        if (inputParts.length < 2) {
            System.out.println("Please specify a task number.");
            return;
        }
        try {
            int taskIndex = Integer.parseInt(inputParts[1]) - 1;
            if (taskIndex >= 0 && taskIndex < taskCount) {
                tasks[taskIndex].markAsNotDone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("  " + tasks[taskIndex]);
            } else {
                System.out.println("Invalid task number.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid format! Use: unmark <task number>");
        }
    }
}

