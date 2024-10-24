import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Log {
  private Account account1;
  private Account account2;
  private Person person1;
  private Person person2;
  private String transaction;

  public Log() {
  }

  public Log(Account a1, Account a2, Person p1, Person p2, String transaction) {
    this.account1 = a1;
    this.account2 = a2;
    this.person1 = p1;
    this.person2 = p2;
    this.transaction = transaction;
  }

  public void setAccount1(Account a) {
    this.account1 = a;
  }

  public void setAccount2(Account a) {
    this.account2 = a;
  }

  public void setPerson1(Person p) {
    this.person1 = p;
  }

  public void setPerson2(Person p) {
    this.person2 = p;
  }

  public void setTransaction(String transaction) {
    this.transaction = transaction;
  }

  public String parseTransaction() {
    String logHistory = "";
    switch (this.transaction) {
      case "deposit":
        logHistory = this.transaction;
        break;
      case "withdraw":
        logHistory = this.transaction;
        break;
      case "transfer":
        logHistory = this.transaction;
        break;
    }
    return logHistory;
  }
}
