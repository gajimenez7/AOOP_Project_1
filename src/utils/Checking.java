package src.utils;

public class Checking extends Account {

    public Checking(String accountNumber, double initialBalance, String type) {
        this.setAccountNumber(accountNumber);
        this.setBalance(initialBalance);
        this.type = type;
    }

    @Override
    public boolean transfer(Account account, double amount) {
        if (this.withdraw(amount)) {
            account.deposit(amount);
            return true;
        } else {
            return false;
        }
    }
}
