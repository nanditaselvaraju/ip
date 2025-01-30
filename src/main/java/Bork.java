import java.util.Scanner;

public class Bork {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] tasks = new String[100];
        int taskCount = 0; // tracking number of tasks

        System.out.println("Hello! I'm Bork");
        System.out.println("What can I do for you?");

        while (true) {
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }

            if (input.equals("list")) {
                if (taskCount == 0) {
                    System.out.println("No tasks added yet.");
                } else {
                    for (int i = 0; i < taskCount; i++) {
                        System.out.println((i + 1) + " . " + tasks[i]);
                    }
                }
            } else {
                if (taskCount < 100) {
                    tasks[taskCount] = input;
                    taskCount++;
                    System.out.println("added: " + input);
                } else {
                    System.out.println("Task list is full! Cannot add more.");
                }
            }
        }
        scanner.close();
    }
}
