package src.utils;

/**
 * Account with account number and balance
 * @author George Jimenez
 */

public abstract class Account {
  protected String accountNumber;
  protected double balance;
  protected String type;

  /**
   * adds the amount of the deposit to balance
   * 
   * @param amount
   */
  public void deposit(double amount) {
    this.balance += amount;
  }

  /** 
   *  @return double balance 
   * */
  public double getBalance() {
    return this.balance;
  }

  /**
   * sets balance
   * 
   * @param balance
   */
  public void setBalance(double balance) {
    this.balance = balance;
  }

  /**
   * Sets account number
   * and returns true if successful 
   * @param accountNum
   */
  public boolean setAccountNumber(String accountNum) {
    this.accountNumber = accountNum;
    return true;
  }

  /**
   * Account number getter
   * @return account number
   */
  public String getAccountNumber() {
    return this.accountNumber;
  }

  /**
   * Type getter
   * @return type
   */
  public String getType() {
    return type;
  }

  /**
   * if amount is less than balance, subtracts amount from balance
   * returns false if otherwise
   * 
   * @param amount
   */
  public boolean withdraw(double amount) {
    if (amount <= this.balance) {
      this.balance -= amount;
      return true;
    } else
      return false;
  }

  /**
   * Abstract method for transferring
   * @param account
   * @param amount
   * @return
   */
  abstract boolean transfer(Account account, double amount);

}