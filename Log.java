
public class Log {
  private Account account1;
  private Account account2;
  private Customer customer1;
  private Customer customer2;
  private String transaction;
  private String amount;

  public Log() {
  }

  public Log(Account a1, Account a2, Customer c1, Customer c2, String transaction, String amount) {
    this.account1 = a1;
    this.account2 = a2;
    this.customer1 = c1;
    this.customer2 = c2;
    this.transaction = transaction;
    this.amount = amount;
  }

  public void setAccount1(Account a) {
    this.account1 = a;
  }

  public void setAccount2(Account a) {
    this.account2 = a;
  }

  public void setPerson1(Customer c) {
    this.customer1 = c;
  }

  public void setPerson2(Customer c) {
    this.customer2 = c;
  }

  public void setTransaction(String transaction) {
    this.transaction = transaction;
  }

  public void setAmount(String amount){
    this.amount = amount;
  }

  public String parseTransaction() {
    String logHistory = "";
    switch (this.transaction) {
      case "deposit":
      logHistory = "TRANSACTION: " + this.transaction + "\n" + 
                    "ACCOUNT: " + this.account1.getAccountNumber() + "\n" + 
                    "CUSTOMER: " + this.customer1.getFirstName() + " " + this.customer1.getLastName() + "\n" +
                    "AMOUNT: " + this.amount + "\n" +
                    "PREVIOUS BALANCE: " + (this.account1.getBalance() - Double.parseDouble(this.amount)) + "\n" + 
                    "NEW BALANCE: " + this.account1.getBalance() + "\n\n";
      break;
      case "withdraw":
      logHistory = "TRANSACTION: " + this.transaction + "\n" + 
                   "ACCOUNT: " + this.account1.getAccountNumber() + "\n" + 
                   "CUSTOMER: " + this.customer1.getFirstName() + " " + this.customer1.getLastName() + "\n" +
                   "AMOUNT: $" + this.amount + "\n" +
                   "PREVIOUS BALANCE: $" + (this.account1.getBalance() + Double.parseDouble(this.amount)) + "\n" + 
                   "NEW BALANCE: $" + this.account1.getBalance() + "\n\n";
        break;
      case "transfer":
      logHistory = "TRANSACTION: " + this.transaction + "\n" + 
                   "FROM ACCOUNT: " + this.account1.getAccountNumber() + "\n" + 
                   "TO ACCOUNT: " + this.account2.getAccountNumber() + "\n" +
                   "CUSTOMER: " + this.customer1.getFirstName() + " " + this.customer1.getLastName() + "\n" +
                   "AMOUNT: " + this.amount + "\n" + 
                   "FROM ACCOUNT BALANCE: " + this.account1.getBalance() + "\n" + 
                   "TO ACCOUNT BALANCE: " + this.account2.getBalance() + "\n\n";
        break;
      case "payment":
      logHistory = "TRANSACTION: " + this.transaction + "\n" + 
                   "FROM ACCOUNT: " + this.account1.getAccountNumber() + "\n" + 
                   "TO ACCOUNT: " + this.account2.getAccountNumber() + "\n" +
                   "FROM CUSTOMER: " + this.customer1.getFirstName() + " " + this.customer1.getLastName() + "\n" +
                   "TO CUSTOMER: " + this.customer2.getFirstName() + " " + this.customer2.getLastName() + "\n" +
                   "AMOUNT: " + this.amount + "\n\n";
        break;
    }
    return logHistory;
  }
}
