public class Savings extends Account {
  public Savings(String accountNumber, double initialBalance) {
    this.setAccountNumber(accountNumber);
    this.deposit(initialBalance);
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
