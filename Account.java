public abstract class Account {
  protected String accountNumber;
  protected double balance;

  public void deposit(double amount) {
    this.balance += amount;
  }

  public double getBalance() {
    return this.balance;
  }

  public void setBalance(double balance){
    this.balance = balance;
  }

  public boolean setAccountNumber(String accountNum) {
    this.accountNumber = accountNum;
    return true;
  }

  public String getAccountNumber() {
    return this.accountNumber;
  }

  public boolean withdraw(double amount) {
    if (amount <= this.balance) {
      this.balance -= amount;
      return true;
    } else
      return false;
  }

  abstract boolean transfer(Account account, double ammount);
  /*
   * if(this.withdraw(ammount)){
   * account.deposit(ammount);
   * return true;
   * } else{
   * return false;
   * }
   */
}
