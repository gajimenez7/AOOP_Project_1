package src.resources;
public class Checking extends Account {

    public Checking(String accountNumber, double initialBalance) {
        this.setAccountNumber(accountNumber);
        this.setBalance(initialBalance);
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
