import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class TodoApp {

    static final String FILE_NAME = "tasks.txt";

    static void saveTasks(ArrayList<Task> tasks) {
        ArrayList<String> lines = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            lines.add(tasks.get(i).toFileLine());
        }
        try {
            Files.write(Path.of(FILE_NAME), lines);
        } catch (IOException e) {
            System.out.println("Could not save: " + e.getMessage());
        }
    }

    static void addTask(Scanner scanner, ArrayList<Task> tasks) {
        System.out.print("Task: ");
        String text = scanner.nextLine().trim();
        if (text.isBlank()) {
            System.out.println("Title can't be empty.");
            return;
        }
        tasks.add(new Task(text));
        saveTasks(tasks);
        System.out.println("Added. Total: " + tasks.size());
    }

    static void listTasks(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("No tasks yet.");
            return;
        }
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i).display());
        }
    }

    static int askForIndex(Scanner scanner, ArrayList<Task> tasks, String verb) {
        if (tasks.isEmpty()) {
            System.out.println("No tasks yet.");
            return -1;
        }
        System.out.println(verb + " which number? ");
        String input = scanner.nextLine().trim();

        int number;
        try {
            number = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("That's not a number: " + input);
            return -1;
        }

        if (number < 1 || number > tasks.size()) {
            System.out.println("No task with that number.");
            return -1;
        }
        return number - 1;
    }

    static void completeTask(Scanner scanner, ArrayList<Task> tasks) {
        int index = askForIndex(scanner, tasks, "Complete");
        if (index == -1) {
            return;
        }
        Task t = tasks.get(index);
        if (t.done) {
            System.out.println("Already done.");
        } else {
            t.markDone();
            saveTasks(tasks);
            System.out.println("Completed: " + t.title);
        }
    }

    static void deleteTask(Scanner scanner, ArrayList<Task> tasks) {
        int index = askForIndex(scanner, tasks, "Delete");
        if (index == -1) {
            return;
        }
        Task removed = tasks.remove(index);
        saveTasks(tasks);
        System.out.println("Deleted: " + removed.title);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayList<Task> tasks = new ArrayList<>();
        boolean running = true;

        while (running) {
            System.out.println("\n=== TO-DO LIST ===");
            System.out.println("1. Add task");
            System.out.println("2. List tasks");
            System.out.println("3. Complete task");
            System.out.println("4. Delete task");
            System.out.println("5. Exit");
            System.out.print("Choose: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> addTask(scanner, tasks);
                case "2" -> listTasks(tasks);
                case "3" -> completeTask(scanner, tasks);
                case "4" -> deleteTask(scanner, tasks);
                case "5" -> running = false;
                default -> System.out.println("Invaild choice.");
            }

        }

        System.out.println("Bye.");
        scanner.close();
    }
}