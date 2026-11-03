import java.util.ArrayList;
import java.util.Scanner;

public class TodoApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayList<Task> tasks = new ArrayList<>();
        boolean running = true;

        while (running) {
            System.out.println("\n=== TO-DO LIST ===");
            System.out.println("1. Add Task");
            System.out.println("2. List Task");
            System.out.println("3. Delete Task");
            System.out.println("4. Exit");
            System.out.print("Choose: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> {
                    System.out.print("Tasks: ");
                    String text = scanner.nextLine();
                    Task newTask = new Task(text);
                    tasks.add(newTask);
                    System.out.println("Added. Total: " + tasks.size());
                }

                case "2" -> {
                    if (tasks.isEmpty()) {
                        System.out.println("No tasks yet.");
                    } else {
                        for (int i = 0; i < tasks.size(); i++) {
                            Task t = tasks.get(i);
                            System.out.println((i + 1) + ". " + t.title + " [done: " + t.done + "]");
                        }
                    }
                }

                case "3" -> {
                    if (tasks.isEmpty()) {
                        System.out.println("Nothing to delete.");
                    } else {
                        System.out.print("Select task number to delete: ");
                        int number = Integer.parseInt(scanner.nextLine());
                        if (number < 1 || number > tasks.size()) {
                            System.out.println("No task with that number.");
                        } else {
                            Task removed = tasks.remove(number - 1);
                            System.out.println("Deleted: " + removed.title);
                        }
                    }
                }

                case "4" -> running = false;
                default -> System.out.println("Invalid choice.");
            }
        }

        System.out.println("Bye.");
        scanner.close();
    }
}