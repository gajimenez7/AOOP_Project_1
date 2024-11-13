
/**
 * Log class that handles logging transactions using a Log Builder
 */
public class Log {
  private Account account1;
  private Account account2;
  private Customer customer1;
  private Customer customer2;
  private String transaction;
  private String amount;
  
  /**
   * Log Constructor
   * 
   * @param a1
   * @param a2
   * @param c1
   * @param c2
   * @param transaction
   * @param amount
   */
  public Log(Account a1, Account a2, Customer c1, Customer c2, String transaction, String amount) {
    this.account1 = a1;
    this.account2 = a2;
    this.customer1 = c1;
    this.customer2 = c2;
    this.transaction = transaction;
    this.amount = amount;
  }

  /**
   * Parse object attributes to return information to log
   * depending on transaction type
   * 
   * @return
   */
  public String parseTransaction() {
    String logHistory = "";
    switch (this.transaction) {
      case "deposit":
        logHistory = logDeposit();
        break;
      case "withdraw":
        logHistory = logWithdraw();
        break;
      case "transfer":
        logHistory = logTransfer();
        break;
      case "payment":
        logHistory = logPayement();
        break;
      case "invalid":
        logHistory = logInvalid();
    }
    return logHistory;
  }

  public String logDeposit() {
    String log;
    log = "TRANSACTION: " + this.transaction + "\n" +
        "ACCOUNT: " + this.account1.getAccountNumber() + "\n" +
        "CUSTOMER: " + this.customer1.getFirstName() + " " + this.customer1.getLastName() + "\n" +
        "AMOUNT: " + this.amount + "\n" +
        "PREVIOUS BALANCE: " + (this.account1.getBalance() - Double.parseDouble(this.amount)) + "\n" +
        "NEW BALANCE: " + this.account1.getBalance() + "\n\n";
    return log;
  }

  public String logWithdraw() {
    String log;
    log = "TRANSACTION: " + this.transaction + "\n" +
        "ACCOUNT: " + this.account1.getAccountNumber() + "\n" +
        "CUSTOMER: " + this.customer1.getFirstName() + " " + this.customer1.getLastName() + "\n" +
        "AMOUNT: $" + this.amount + "\n" +
        "PREVIOUS BALANCE: $" + (this.account1.getBalance() + Double.parseDouble(this.amount)) + "\n" +
        "NEW BALANCE: $" + this.account1.getBalance() + "\n\n";
    return log;
  }

  public String logTransfer() {
    String log;
    log = "TRANSACTION: " + this.transaction + "\n" +
        "FROM ACCOUNT: " + this.account1.getAccountNumber() + "\n" +
        "TO ACCOUNT: " + this.account2.getAccountNumber() + "\n" +
        "CUSTOMER: " + this.customer1.getFirstName() + " " + this.customer1.getLastName() + "\n" +
        "AMOUNT: " + this.amount + "\n" +
        "FROM ACCOUNT BALANCE: " + this.account1.getBalance() + "\n" +
        "TO ACCOUNT BALANCE: " + this.account2.getBalance() + "\n\n";
    return log;
  }

  public String logPayement() {
    String log;
    log = "TRANSACTION: " + this.transaction + "\n" +
        "FROM ACCOUNT: " + this.account1.getAccountNumber() + "\n" +
        "TO ACCOUNT: " + this.account2.getAccountNumber() + "\n" +
        "FROM CUSTOMER: " + this.customer1.getFirstName() + " " + this.customer1.getLastName() + "\n" +
        "TO CUSTOMER: " + this.customer2.getFirstName() + " " + this.customer2.getLastName() + "\n" +
        "AMOUNT: " + this.amount + "\n\n";
    return log;
  }

  public String logInvalid() {
    String log;
    log = "TRANSACTION: " + this.transaction + "\n" +
        "ACCOUNT: " + this.account1.getAccountNumber() + "\n" +
        "FROM CUSTOMER: " + this.customer1.getFirstName() + " " + this.customer1.getLastName() + "\n\n";
    return log;
  }
  
}
