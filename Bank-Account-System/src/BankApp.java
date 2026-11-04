import java.math.BigDecimal;
import java.util.Scanner;

public class BankApp {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        BankManager bank = new BankManager();

        while (true) {
            System.out.println("\n1. Open account");
            System.out.println("2. Deposit");
            System.out.println("3. List accounts");
            System.out.println("4. Find account");
            System.out.println("0. Exit");
            String choice = prompt("Choose: ");

            try {
                switch (choice) {
                    case "1" -> openAccount(bank);
                    case "2" -> deposit(bank);
                    case "3" -> listAccounts(bank);
                    case "4" -> findAccount(bank);
                    case "0" -> {
                        System.out.println("Bye.");
                        return;
                    }
                    default -> System.out.println("Inavlid choice.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void openAccount(BankManager bank) {
        String owner = prompt("Owner name: ");
        BigDecimal opening = promptMoney("Opening balance: ");
        Account account = bank.openAccount(owner, opening);
        System.out.println("Opened: " + account);
    }

    private static void deposit(BankManager bank) {
        int id = promptInt("Account ID: ");
        BigDecimal amount = promptMoney("Amount: ");
        bank.deposit(id, amount);
        System.out.println("Done. " + bank.findById(id));
    }

    private static void listAccounts(BankManager bank) {
        if (bank.count() == 0) {
            System.out.println("No accounts.");
            return;
        }
        for (Account account : bank.getAccounts()) {
            System.out.println(account);
        }
    }

    private static void findAccount(BankManager bank) {
        int id = promptInt("Account ID: ");
        System.out.println(bank.findById(id));
    }

    private static String prompt(String message) {
        System.out.print(message);
        return scanner.nextLine().trim();
    }

    private static int promptInt(String message) {
        while (true) {
            try {
                return Integer.parseInt(prompt(message));
            } catch (NumberFormatException e) {
                System.out.println("Enter a whole number.");
            }
        }
    }

    private static BigDecimal promptMoney(String message) {
        while (true) {
            try {
                return new BigDecimal(prompt(message));
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid amount like 250.50");
            }
        }
    }
}