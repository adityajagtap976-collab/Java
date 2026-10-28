final Map<String, Account> accounts = new HashMap<>();

void main() {
    seedAccounts();
    IO.println("=== WELCOME TO JAVA BANK ATM ===");

    while (true) {
        String card = IO.readln("\nInsert card (card number, or 'off' to power off): ");
        if (card == null || card.trim().equalsIgnoreCase("off")) {
            IO.println("ATM powered off.");
            return;
        }
        Account account = login(card.trim());
        if (account != null) {
            runSession(account);
        }
    }
}

Account login(String cardNumber) {
    Account account = accounts.get(cardNumber);
    if (account == null) {
        IO.println("Card not recognized.");
        return null;
    }
    if (account.isLocked()) {
        IO.println("This card is locked. Contact your bank.");
        return null;
    }
    while (!account.isLocked()) {
        String pin = IO.readln("Enter 4-digit PIN: ");
        if (pin == null) {
            return null;
        }
        if (account.checkPin(pin)) {
            IO.println("PIN accepted.");
            return account;
        }
        if (account.isLocked()) {
            IO.println("Wrong PIN. Card locked. Contact your bank.");
        } else {
            IO.println("Wrong PIN. Attempts left: " + account.attemptsLeft());
        }
    }
    return null;
}

void runSession(Account account) {
    IO.println("Hello, " + account.getHolderName() + "!");
    while (true) {
        showMenu();
        String choice = IO.readln("Choose an option: ");
        if (choice == null) {
            return;
        }
        switch (choice.trim()) {
            case "1" -> IO.println("Balance: " + formatMoney(account.getBalanceInCents()));
            case "2" -> {
                IO.println("Card returned. Goodbye, " + account.getHolderName() + ".");
                return;
            }
            default -> IO.println("Invalid option. Enter 1 or 2.");
        }
    }
}

void seedAccounts() {
    accounts.put("1001", new Account("1001", "Aisha", "1234", 100_000L));
    accounts.put("1002", new Account("1002", "Ben", "4321", 25_050L));
    accounts.put("1003", new Account("1003", "Chen", "0042", 50_000L));
}

void showMenu() {
    IO.print("""

            --- MENU ---
            1) Check balance
            2) Exit
            """);
}

String formatMoney(long cents) {
    return "$%d.%02d".formatted(cents / 100, cents % 100);
}