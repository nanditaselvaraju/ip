import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;


public class Bork {
    private static final String FILE_PATH = "data/bork.txt";
    private static List<Task> tasks = new ArrayList<>();
    public static void main(String[] args) {
        loadTasks();
        System.out.println("Hello! I'm Bork");
        System.out.println("What can I do for you?");

        Scanner scanner = new Scanner(System.in);
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
    }

    private static void loadTasks() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            new File("data").mkdirs();
            return;
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" \\| ");
                if (parts.length < 3) {
                    continue;
                }

                Task task;
                switch (parts[0]) {
                    case "T":
                        task = new ToDo(parts[2]);
                        break;
                    case "D":
                        if (parts.length < 4) {
                            continue;
                        }
                        task = new Deadline(parts[2], parts[3]);
                        break;
                    case "E":
                        if (parts.length < 5) {
                            continue;
                        }
                        task = new Event(parts[2], parts[3], parts[4]);
                        break;
                    default:
                        continue;
                }
                if (parts[1].equals("1")) {
                    task.markAsDone();
                }
                tasks.add(task);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error loading tasks.");
        }
    }

    private static void saveTasks() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Task task : tasks) {
                writer.write(task.toFileFormat());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks.");
        }
    }
    private static void printTaskList() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks added yet.");
            return;
        }
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    private static void addTask(Task task) {
        tasks.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(" " + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        saveTasks();
    }

    private static void markTask(String[] inputParts) {
        if (inputParts.length < 2) {
            System.out.println("Please specify a task number.");
            return;
        }
        try {
            int taskIndex = Integer.parseInt(inputParts[1]) - 1;
            if (taskIndex >= 0 && taskIndex < tasks.size()) {
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
            if (taskIndex >= 0 && taskIndex < tasks.size()) {
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
            if (taskIndex >= 0 && taskIndex < tasks.size()) {
                Task removedTask = tasks.remove(taskIndex);
                System.out.println("Noted. I've removed this task:");
                System.out.println("  " + removedTask.toString());
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            } else {
                System.out.println("Invalid task number.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid format! Use: delete <task number>");
        }
    }
}

