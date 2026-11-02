import java.util.Scanner;

public class Atm {

    static final int CORRECT_PIN = 1234;

    static void showMenu() {
        System.out.println();
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Exit");
        System.out.print("Choose an option: ");
    }

    static boolean login(Scanner scanner) {
        for (int attempt = 1; attempt <= 3; attempt++) {
            System.out.print("Enter your PIN: ");
            int enteredPin = scanner.nextInt();

            if (enteredPin == CORRECT_PIN) {
                return true;
            }
            System.out.println("Wrong PIN. Attempt " + attempt + " of 3.");
        }
        return false;
    }

    static double deposit(Scanner scanner, double balance) {
        System.out.print("Enter deposit amount: ");
        double amount = scanner.nextDouble();

        if (amount <= 0) {
            System.out.println("Deposit must be more than 0.");
            return balance;
        }

        balance += amount;
        System.out.printf("Deposited $%.2f. New balance: $%.2f%n", amount, balance);
        return balance;
    }

    static double withdraw(Scanner scanner, double balance) {
        System.out.print("Enter withdrawal amount: ");
        double amount = scanner.nextDouble();

        if (amount <= 0) {
            System.out.println("Withdrawal must be more than 0.");
        } else if (amount > balance) {
            System.out.println("Insufficient funds.");
        } else {
            balance -= amount;
            System.out.printf("Withdrew $%.2f. New balance: $%.2f%n", amount, balance);
        }
        return balance;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double balance = 1000.00;

        System.out.println("=== Welcome to Java ATM ===");

        if (!login(scanner)) {
            System.out.println("Card locked.");
            scanner.close();
            return;
        }

        boolean running = true;
        while (running) {
            showMenu();
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> System.out.printf("Your balance: $%.2f%n", balance);
                case 2 -> balance = deposit(scanner, balance);
                case 3 -> balance = withdraw(scanner, balance);
                case 4 -> {
                    System.out.println("Goodbye!");
                    running = false;
                }
                default -> System.out.println("Invalid option.");
            }
        }

        scanner.close();
    }
}