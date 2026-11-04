import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BankManager {
    private final List<Account> accounts = new ArrayList<>();
    private int nextId = 1001;

    public Account openAccount(String owner, BigDecimal openingBalance) {
        Account account = new Account(nextId, owner, openingBalance);
        nextId++;
        accounts.add(account);
        return account;
    }

    public Account findById(int id) {
        for (Account account : accounts) {
            if (account.getId() == id) {
                return account;
            }
        }
        throw new IllegalArgumentException("No account with ID " + id);
    }

    public void deposit(int id, BigDecimal amount) {
        findById(id).deposit(amount);
    }

    public List<Account> getAccounts() {
        return List.copyOf(accounts);
    }

    public int count() {
        return accounts.size();
    }
}