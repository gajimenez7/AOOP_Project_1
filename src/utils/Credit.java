package src.utils;

public class Credit extends Account {
  private double creditLimit;

  public Credit(String acctNum, double creditLim, double creditStart, String type) {
    this.setAccountNumber(acctNum);
    this.creditLimit = creditLim;
    this.setBalance(creditStart);
    this.type = type;
  }

  
  /** 
   * @param amount
   * @return boolean
   */
  public boolean withdraw(double amount) {
    if (this.getBalance() >= creditLimit) {
      return false;
    } else {
      return super.withdraw(amount);
    }
  }

  /**
   * Transfer from credit accounts
   */
  public boolean transfer(Account account, double amount) {
    if (this.withdraw(amount)) {
      account.deposit(amount);
      return true;
    } else {
      return false;
    }
  }

  /**
   * Modify credit limit
   * @param creditLimit
   */
  public void setCreditLimit(double creditLimit) {
    this.creditLimit = creditLimit;
  }

  /**
   * Access credit limit
   * @return
   */
  public double getCreditLimit() {
    return creditLimit;
  }

}
