public class Account {
    private static final int MAX_FAILED_ATTEMPTS = 3;

    private final String cardNumber;
    private final String holderName;
    private final String pin;
    private long balanceInCents;
    private int failedAttempts;

    public Account(String cardNumber, String holderName, String pin, long balanceInCents) {
        this.cardNumber = cardNumber;
        this.holderName = holderName;
        this.pin = pin;
        this.balanceInCents = balanceInCents;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getHolderName() {
        return holderName;
    }

    public long getBalanceInCents() {
        return balanceInCents;
    }

    public boolean isLocked() {
        return failedAttempts >= MAX_FAILED_ATTEMPTS;
    }

    public int attemptsLeft() {
        return MAX_FAILED_ATTEMPTS - failedAttempts;
    }

    public boolean checkPin(String candidate) {
        if (isLocked()) {
            return false;
        }
        if (pin.equals(candidate)) {
            failedAttempts = 0;
            return true;
        }
        failedAttempts++;
        return false;
    }
}