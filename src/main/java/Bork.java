import java.util.Scanner;
import java.util.ArrayList;

public class Bork {
    private static final int maxTasks = 100;
    private static ArrayList<Task> tasks = new ArrayList<>();
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
            } else if (command.equals("todo")) {
                addToDo(inputParts);
            } else if (command.equals("deadline")) {
                addDeadline(inputParts);
            } else if (command.equals("event")) {
                addEvent(inputParts);
            } else if (command.equals("delete")) {
                deleteTask(inputParts);
            } else {
                System.out.println("Unknown command.");;
            }
        }
        scanner.close();
    }
    private static void printTaskList() {
        if (taskCount == 0) {
            System.out.println("No tasks added yet.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < taskCount; i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }

    private static void addTask(Task task) {
        if (taskCount < maxTasks) {
            tasks.add(task);
            taskCount++;
            System.out.println("Got it. I've added this task:");
            System.out.println(" " + task);
            System.out.println("Now you have " + taskCount + " tasks in the list.");
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
                tasks.get(taskIndex).markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  " + tasks.get(taskIndex));
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
                tasks.get(taskIndex).markAsNotDone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("  " + tasks.get(taskIndex));
            } else {
                System.out.println("Invalid task number.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid format! Use: unmark <task number>");
        }
    }

    private static void addToDo(String[] inputParts) {
        if (inputParts.length < 2) {
            System.out.println("Description of a todo cannot be empty.");
            return;
        }
        addTask(new ToDo(inputParts[1]));
    }

    private static void addDeadline(String[] inputParts) {
        if (inputParts.length < 2 || !inputParts[1].contains("/by")) {
            System.out.println("Invalid format! Use: deadline <description> /by <date>");
            return;
        }
        String[] details = inputParts[1].split(" /by ", 2);
        addTask(new Deadline(details[0], details[1]));
    }
    private static void addEvent(String[] inputParts) {
        if (inputParts.length < 2 || !inputParts[1].contains("/from") || !inputParts[1].contains("/to")) {
            System.out.println("Invalid format! Use: event <description> /from <start time> /to <end time>");
            return;
        }
        String[] details = inputParts[1].split(" /from ", 2);
        String[] timeDetails = details[1].split(" /to ", 2);
        addTask(new Event(details[0], timeDetails[0], timeDetails[1]));
    }

    private static void deleteTask(String[] inputParts) {
        if (inputParts.length < 2) {
            System.out.println("Please specify a task number.");
            return;
        }
        try {
            int taskIndex = Integer.parseInt(inputParts[1]) - 1;
            if (taskIndex >= 0 && taskIndex < taskCount) {
                Task removedTask = tasks.remove(taskIndex);
                taskCount--;
                System.out.println("Noted. I've removed this task:");
                System.out.println("  " + removedTask.toString());
                System.out.println("Now you have " + taskCount + " tasks in the list.");
            } else {
                System.out.println("Invalid task number.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid format! Use: delete <task number>");
        }
    }
}

