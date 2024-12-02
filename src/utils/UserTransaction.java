package src.utils;

import java.io.IOException;
import java.time.LocalDate;
import java.io.File;
import java.io.FileWriter;
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
   * Generate transaction file for specified user
   */
  public void generateTransactionFile() {
    String dir = "UserTransactionFiles";
    String fileExtension = ".txt";
    String fileName = customer.getLastName() + "_" + customer.getFirstName() + fileExtension;
    makeFile(dir, fileName);
  }

  /**
   * Create transaction file
   * 
   * @param dir
   * @param fileName
   */
  private void makeFile(String dir, String fileName) {

    try {
      // make directory
      File fd = new File(dir);
      if (fd.mkdir())
        ;
      else
        System.out.println("Error making directory");

      // make file
      File f = new File(dir + "\\" + fileName);
      if (f.createNewFile()) {
        writeToFile1(dir, fileName);
      } else {
        writeToFile2(dir, fileName);
      }

    } catch (IOException e) {
      System.out.println("An error occurred");
      e.printStackTrace();
    }
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
  private void writeToFile1(String dir, String fileName) {
    String title = customer.getFirstName() + " " + customer.getLastName() + " Transaction File: \n";

    try {
      FileWriter fw = new FileWriter(dir + "\\" + fileName, true);
      // title with customer name
      fw.write(title + "\n");
      // account number
      fw.write(account.getAccountNumber() + "\n");
      // starting balance
      fw.write(String.valueOf(startBalance) + "\n");
      // end (current) balance
      fw.write(String.valueOf(endBalance) + "\n");

      // transactions
      if (!transactions.isEmpty()) {
        for (String transaction : transactions) {
          fw.write(transaction + "\n");
        }
      }
      // date of generation
      fw.write(date.toString() + "\n\n");
      // close writer
      fw.close();
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
   * @param dir
   * @param fileName
   */
  private void writeToFile2(String dir, String fileName) {
    try {
      FileWriter fw = new FileWriter(dir + "\\" + fileName, true);
      // account number
      fw.write(account.getAccountNumber() + "\n");
      // starting balance
      fw.write(String.valueOf(startBalance) + "\n");
      // end (current) balance
      fw.write(String.valueOf(endBalance) + "\n");

      // transactions
      if (!transactions.isEmpty()) {
        for (String transaction : transactions) {
          fw.write(transaction + "\n");
        }
      }
      // date of generation
      fw.write(date.toString() + "\n\n");
      // close writer
      fw.close();
    } catch (IOException e) {
      System.out.println("An error occurred");
      e.printStackTrace();
    }
  }
}