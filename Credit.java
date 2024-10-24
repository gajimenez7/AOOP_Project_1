public class Credit extends Account {
  private double creditLimit;

  public Credit(String acctNum, double creditLim, double creditStart) {
    this.setAccountNumber(acctNum);
    this.creditLimit = creditLim;
    this.setBalance(creditStart);
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
