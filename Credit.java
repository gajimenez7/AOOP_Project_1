public class Credit extends Account {
  private double creditLimit;

  public Credit() {
  }

  public Credit(double limit) {
    this.creditLimit = limit;
  }

  public void deposit(double ammount) {

  }

  public boolean withdraw(double ammount) {
    return false;
  }

  public double getBalance() {
    return this.balance; // add balance attribute to Account class
  }

  public boolean transfer(Account account, double ammount) {
    if (this.withdraw(ammount)) {
      account.deposit(ammount);
      return true;
    } else {
      return false;
    }
  }
}
