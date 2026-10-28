static final int MAX_ATTEMPTS = 3;
static final String CORRECT_PIN = "1234";

long balanceInCents = 100_000L; // $1,000.00

void main() {
    IO.println("=== WELCOME TO JAVA BANK ATM ===");

    if (!authenticate()) {
        IO.println("card blocked. contact your bank.");
        return;
    }
    while (true) {
        showMenu();
        String choice = IO.readln("choose an option: ");
        if (choice == null) {
            return;
        }
        switch (choice.trim()) {
            case "1" -> IO.println("Balance: " + formatMoney(balanceInCents));
            case "2" -> {
                IO.println("Thankyou for using Java Bank. Goodbye.");
                return;
            }
            default -> IO.println("Invalid option. Enter 1 or 2.");
        }
    }
}

boolean authenticate() {
    for (int attempt = 1; attempt <= MAX_ATTEMPTS; attempt++) {
        String pin = IO.readln("Enter 4-digit PIN: ");
        if (CORRECT_PIN.equals(pin)) {
            IO.println("PIN accepted.");
            return true;
        }
    }
    return false;
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