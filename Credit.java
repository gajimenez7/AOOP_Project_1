public class Credit extends Account {
  private double creditLimit;

  public Credit() {
  }

  public Credit(double limit) {
    this.creditLimit = limit;
  }

  public boolean withdraw(double amount) {
    if (this.getBalance() >= creditLimit) {
      return false;
    } else {
      return super.withdraw(amount);
    }
  }

  public boolean transfer(Account account, double amount) {
    if (this.withdraw(amount)) {
      account.deposit(amount);
      return true;
    } else {
      return false;
    }
  }
}
