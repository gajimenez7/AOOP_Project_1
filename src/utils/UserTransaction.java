package src.utils;

import java.time.LocalDate;
import java.io.File;
import java.util.ArrayList;

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

  /**
   * set parameters for builder
   * 
   * @param builder
   */
  UserTransaction(Builder builder) {
    this.customer = builder.customer;
    this.account = builder.account1;
    this.startBalance = builder.startBalance;
    this.endBalance = builder.endBalance;
    this.startBalance = 0.00;
    this.date = LocalDate.now();
    this.transactions = new ArrayList<>();
  }

  /**
   * get start balance
   */
  public double getStartBalance() {
    return this.startBalance;
  }

  /**
   * Set all transactions of requested customer
   * 
   * @param transaction
   */
  public void addTransaction(String transaction) {
    this.transactions.add(transaction);
  }

  /**
   * Set the date which file was generated
   * 
   * @param date
   */
  public void setDate(LocalDate date) {
    this.date = date;
  }

  /**
   * Generate transaction file name for specified user
   */
  public String fileName() {
    String fileExtension = ".txt";
    String fileName = customer.getLastName() + "_" + customer.getFirstName() + fileExtension;
    return fileName;
  }

  /**
   * Generate output to user file based on if it has already been created or not
   * 
   * @param dir
   * @param fileName
   * @return
   */
  public String fileOutput(String dir, String fileName) {
    String output = "";
    File outFile = new File(dir + fileName);
    if (outFile.isFile()) {
      output = transactions2(dir, fileName);
    } else {
      output = transactions1(dir, fileName);
    }
    return output;
  }

  /**
   * Write Customer transactions to transaction text file first time
   * which includes
   * - Customer Name
   * - Account Number
   * - Start and End Balance
   * - List of transactions
   * - Date generated
   * 
   * @param dir
   * @param fileName
   */
  private String transactions1(String dir, String fileName) {
    String title = customer.getFirstName() + " " + customer.getLastName() + " Transaction File: \n";
    String output = "";
    // title with customer name
    output += title + "\n";
    // account number
    output += "Account Number: " + account.getAccountNumber() + "\n";
    // starting balance
    output += "Starting Balance: " + String.valueOf(startBalance) + "\n";
    // end (current) balance
    output += "Current Balance: " + String.valueOf(endBalance) + "\n\n";

    // transactions
    if (!transactions.isEmpty()) {
      for (String transaction : transactions) {
        output += transaction;
      }
    }
    // date of generation
    output += "Date: " + date.toString() + "\n\n\n";

    return output;
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
   * @param dir
   * @param fileName
   */
  private String transactions2(String dir, String fileName) {
    String output = "";
    // account number
    output += "Account Number: " + account.getAccountNumber() + "\n";
    // starting balance
    output += "Starting Balance: " + String.valueOf(startBalance) + "\n";
    // end (current) balance
    output += "Current Balance: " + String.valueOf(endBalance) + "\n";

    // transactions
    if (!transactions.isEmpty()) {
      for (String transaction : transactions) {
        output += transaction;
      }
    }
    // date of generation
    output += "Date: " + date.toString() + "\n\n\n";

    return output;
  }
}