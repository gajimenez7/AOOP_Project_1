import java.io.IOException;
import java.time.LocalDate;
import java.io.File;
import java.io.FileWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;

/**
 * Object to store and retrieve account information
 * of a specific customer
 */
public class UserTransaction {
  private Customer customer;
  private Account account;
  private Double startBalance;
  private Double endBalance;
  private ArrayList<String> transactions;
  private LocalDate date;

  public UserTransaction() {
    this.date = LocalDate.now();
    this.transactions = new ArrayList<>();
  }

  /**
   * @param customer
   * @param account
   * @param startBalance
   * @param endBalance
   * @param transactions
   * @param date
   */
  public UserTransaction(Customer customer, Account account, Double startBalance, Double endBalance) {
    this.customer = customer;
    this.account = account;
    this.startBalance = startBalance;
    this.endBalance = endBalance;
    this.date = LocalDate.now();
    this.transactions = new ArrayList<>();
  }


  /**
   * Set account information
   */
  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  /**
   * Set account information
   */
  public void setAccount(Account account) {
    this.account = account;
  }

  /**
   * Set Starting Balance
   */
  public void setStartBalance(Double startBalance) {
    //
    this.startBalance = startBalance;
  }

  /**
   * Set Ending Balance at time of file generation
   */
  public void setEndBalance(Double endBalance) {
    this.endBalance = endBalance;
  }

  /**
   * Set all transactions of requested customer
   */
  public void addTransaction(String transaction) {
    this.transactions.add(transaction);
  }

  /**
   * Set the date which file was generated
   */
  public void setDate(LocalDate date) {
    this.date = date;
  }

  /**
   * Generate transaction file for specified user
   */
  public void generateTransactionFile() {
    String dir = "UserTransactionFiles";
    String fileExtension = ".txt";
    String fileName = customer.getLastName() + "_" + customer.getFirstName() + fileExtension;
    makeFile(dir, fileName);
    writeToFile(dir, fileName);
  }

  /**
   * Create transaction file
   */
  private void makeFile(String dir, String fileName) {
    
    try {
      // make directory
      File fd = new File(dir);
      if(fd.mkdir());
      else
        System.out.println("Error making directory");
      
      // make file
      File f = new File(dir + "\\" +  fileName);
      f.createNewFile();
    } catch (IOException e) {
      System.out.println("An error occurred");
      e.printStackTrace();
    }
  }

  /**
   * Write Customer transactions to transaction text file
   * which includes
   * - Customer Name
   * - Account Number
   * - Start and End Balance
   * - List of transactions
   * - Date generated
   * 
   * @param fileName
   */
  private void writeToFile(String dir, String fileName) {
    String title = customer.getFirstName() + " " + customer.getLastName() + " Transaction File: \n";

    try {
      FileWriter fw = new FileWriter(dir + "\\" +  fileName, true);
      // title with customer name
      fw.write(title + "\n");
      // account number
      fw.write(account.getAccountNumber() + "\n");
      // end (current) balance
      fw.write(String.valueOf(account.getBalance()) + "\n");

      // transactions
      if (!transactions.isEmpty()) {
        for (String transaction : transactions) {
          fw.write(transaction + "\n");
        }
      }
      // date of generation
      fw.write(date.toString() + "\n");
      // close writer
      fw.close();
    } catch (IOException e) {
      System.out.println("An error occurred");
      e.printStackTrace();
    }
  }
}