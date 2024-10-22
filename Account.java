public abstract class Account {
  private String accountNumber;
  private double balance;

  abstract void deposit(double amount);

  // this.balance += amount;

  abstract boolean withdraw(double amount);

  /*
   * if (amount <= this.balance) {
   * this.balance -= amount;
   * return true;
   * }else
   * return false;
   */
  abstract double getBalance();
  // return this.balance;

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
