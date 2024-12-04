package src.utils;

public class Saving extends Account {

  /**
   * Construct Saving object
   * @param accountNumber
   * @param initialBalance
   * @param type
   */
  public Saving(String accountNumber, double initialBalance, String type) {
    this.setAccountNumber(accountNumber);
    this.setBalance(initialBalance);
    this.type = type;
  }

  
  /** 
   * @param account
   * @param amount
   * @return boolean
   */
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