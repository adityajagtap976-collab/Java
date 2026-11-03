import java.util.Scanner;

public class TodoApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] tasks = new String[5];
        int count = 0;
        boolean running = true;

        while (running) {
            System.out.println("\n=== TO-DO LIST ===");
            System.out.println("1. Add Task");
            System.out.println("2. List Task");
            System.out.println("3. Exit");
            System.out.print("Choose: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> {
                    if (count == tasks.length) {
                        System.out.println("List is full (" + tasks.length + " max).");
                    } else {
                        System.out.print("Task: ");
                        tasks[count] = scanner.nextLine();
                        count++;
                        System.out.println("Task added successfully");
                    }
                }

                case "2" -> {
                    if (count == 0) {
                        System.out.println("No tasks yet.");
                    } else {
                        for (int i = 0; i < count; i++) {
                            System.out.println((i + 1) + ". " + tasks[i]);
                        }
                    }
                }

                case "3" -> running = false;
                default -> System.out.println("Invalid choice.");
            }
        }

        System.out.println("Bye.");
        scanner.close();
    }
}