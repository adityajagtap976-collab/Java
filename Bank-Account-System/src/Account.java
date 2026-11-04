import java.math.BigDecimal;

public class Account {
    private final int id;
    private final String owner;
    private BigDecimal balance;

    public Account(int id, String owner, BigDecimal openingBalance) {
        if (owner == null || owner.isBlank()) {
            throw new IllegalArgumentException("Owner name cannot be blank.");
        }
        BigDecimal money = toMoney(openingBalance);
        if (money.signum() < 0) {
            throw new IllegalArgumentException("Opening balance cannot be negative.");
        }
        this.id = id;
        this.owner = owner.trim();
        this.balance = money;
    }

    public int getId() {
        return id;
    }

    public String getOwner() {
        return owner;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void deposit(BigDecimal amount) {
        BigDecimal money = toMoney(amount);
        if (money.signum() <= 0) {
            throw new IllegalArgumentException("Deposit must be greater than 0");
        }
        balance = balance.add(money);
    }

    private static BigDecimal toMoney(BigDecimal amount) {
        if (amount.scale() > 2) {
            throw new IllegalArgumentException("Max 2 decimal places.");
        }
        return amount.setScale(2);
    }

    @Override
    public String toString() {
        return String.format("#%d | %s | %s", id, owner, balance);
    }
}
