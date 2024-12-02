package src.utils;

/**
 * @author George Jimenez
 *         creates an account with account number and balance
 */

public abstract class Account {
  // NumberFormat moneyFormat = NumberFormat.getCurrencyInstance();
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

  /** gets balance */
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
   * gets and sets account number
   * 
   * @param accountNum
   */
  public boolean setAccountNumber(String accountNum) {
    this.accountNumber = accountNum;
    return true;
  }

  public String getAccountNumber() {
    return this.accountNumber;
  }

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

  abstract boolean transfer(Account account, double amount);

}