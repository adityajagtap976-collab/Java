import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TodoApp {

    static final String FILE_NAME = "tasks.txt";

    static ArrayList<Task> loadTasks() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        Path path = Path.of(FILE_NAME);

        if (!Files.exists(path)) {
            return tasks;
        }

        List<String> lines = Files.readAllLines(path);
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            String[] parts = line.split("\\|", 3);
            if (parts.length < 3) {
                System.out.println("Skipping bad line: " + line);
                continue;
            }
            try {
                Priority priority = Priority.valueOf(parts[1]);
                Task t = new Task(parts[2], priority);
                if (Boolean.parseBoolean(parts[0])) {
                    t.markDone();
                }
                tasks.add(t);
            } catch (IllegalArgumentException e) {
                System.out.println("Skipping bad priority: " + line);
            }
        }
        return tasks;
    }

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

    static Priority askForPriority(Scanner scanner) {
        System.out.print("Priority (1=LOW, 2=MEDIUM, 3=HIGH): ");
        String input = scanner.nextLine();
        return switch (input) {
            case "1" -> Priority.LOW;
            case "2" -> Priority.MEDIUM;
            case "3" -> Priority.HIGH;
            default -> null;
        };
    }

    static void addTask(Scanner scanner, ArrayList<Task> tasks) {
        System.out.print("Task: ");
        String text = scanner.nextLine().trim();
        if (text.isBlank()) {
            System.out.println("Title can't be empty.");
            return;
        }
        Priority priority = askForPriority(scanner);
        if (priority == null) {
            System.out.println("Invalid priority. Task not added.");
            return;
        }
        tasks.add(new Task(text, priority));
        saveTasks(tasks);
        System.out.println("Added. Total: " + tasks.size());
    }

    static void listTasks(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("No tasks yet.");
            return;
        }
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    static void listHighPriority(ArrayList<Task> tasks) {
        int shown = 0;
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            if (t.getPriority() == Priority.HIGH) {
                System.out.println((i + 1) + ". " + t);
                shown++;
            }
        }
        if (shown == 0) {
            System.out.println("No HIGH priority tasks.");
        }
    }

    // Returns a 0-based index, or -1 if nothing valid was chosen
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
            System.out.println("That's not a number: \"" + input + "\"");
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
        if (t.isDone()) {
            System.out.println("Already done.");
        } else {
            t.markDone();
            saveTasks(tasks);
            System.out.println("Completed: " + t.getTitle());
        }
    }

    static void deleteTask(Scanner scanner, ArrayList<Task> tasks) {
        int index = askForIndex(scanner, tasks, "Delete");
        if (index == -1) {
            return;
        }
        Task removed = tasks.remove(index);
        saveTasks(tasks);
        System.out.println("Deleted: " + removed.getTitle());
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayList<Task> tasks;
        try {
            tasks = loadTasks();
        } catch (IOException e) {
            System.out.println("Could not load " + FILE_NAME + ": " + e.getMessage());
            System.out.println("Exiting so your file is not overwritten.");
            scanner.close();
            return;
        }
        System.out.println("Loaded " + tasks.size() + " task(s).");
        boolean running = true;

        while (running) {
            System.out.println("\n=== TO-DO LIST ===");
            System.out.println("1. Add task");
            System.out.println("2. List tasks");
            System.out.println("3. Complete task");
            System.out.println("4. Delete task");
            System.out.println("5. List HIGH priority only");
            System.out.println("6. Exit");
            System.out.print("Choose: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> addTask(scanner, tasks);
                case "2" -> listTasks(tasks);
                case "3" -> completeTask(scanner, tasks);
                case "4" -> deleteTask(scanner, tasks);
                case "5" -> listHighPriority(tasks);
                case "6" -> running = false;
                default -> System.out.println("Invaild choice.");
            }

        }

        System.out.println("Bye.");
        scanner.close();
    }
}