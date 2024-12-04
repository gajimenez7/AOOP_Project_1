package src.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class AllTransactions {

  // log output directory
  final static String logDir = "output/log/log.txt";

  // user transfer bank statement directory
  final static String utDir = "output/UserTransactionFiles/";

  /**
   * Transfer user interface
   * 
   * @param customers
   */
  public static void transferPrompt(List<Customer> customers) {
    Scanner scnr = new Scanner(System.in);

    double amount = 0.00;

    Customer curr = ErrorHandler.getValidCustomer(scnr, customers);
    Account fromAcct = ErrorHandler.getValidAccount(scnr, curr);

    // prompt transfer amount
    System.out.println("How much will you be transferring?");
    amount = Double.parseDouble(scnr.nextLine());

    while (amount < 0) {
      System.out.println("Invalid Input");
      System.out.println("How much will you be transferring?");
      amount = Double.parseDouble(scnr.next());
    }

    // validate amount
    if (fromAcct.getBalance() < amount) {
      System.out.println("You do not have sufficient funds.");
      return;
    }

    Account toAcct = ErrorHandler.getValidAccount(scnr, curr);
    System.out.println("You transferred $" + amount + " from account " + fromAcct.getAccountNumber() +
        " to account " + toAcct.getAccountNumber() + ".");

    // begin transfer process
    transfer(toAcct, fromAcct, curr, amount);
  }

  /**
   * Single user transfer from one account to another
   * 
   * @param toAcct
   * @param fromAcct
   * @param curr
   * @param amount
   */
  public static void transfer(Account toAcct, Account fromAcct, Customer curr, double amount) {

    Log logger;

    UserTransaction ut1, ut2;
    fromAcct.withdraw(amount);
    toAcct.deposit(amount);

    // build bank statement
    ut1 = new Builder()
        .customer(curr)
        .account1(fromAcct)
        .startBalance(fromAcct.getBalance())
        .endBalance(fromAcct.getBalance())
        .buildTransaction();

    ut2 = new Builder()
        .customer(curr)
        .account1(toAcct)
        .startBalance(toAcct.getBalance())
        .endBalance(toAcct.getBalance())
        .buildTransaction();

    // build log
    logger = new Builder()
        .account1(fromAcct)
        .account2(toAcct)
        .person1(curr)
        .amount(Double.toString(amount))
        .transaction("transfer")
        .buildLog();

    // log write to file
    History.writeToFile(logger.parseTransaction(), logDir);

    ut1.addTransaction(logger.parseTransaction());
    ut2.addTransaction(logger.parseTransaction());

    // User Transactions write to file
    History.writeToFile(ut1.fileOutput(utDir, ut1.fileName()), utDir + ut1.fileName());
    History.writeToFile(ut2.fileOutput(utDir, ut2.fileName()), utDir + ut2.fileName());

  }

  /**
   * Transaction user interface
   * 
   * @param customers
   */
  public static void transaction(List<Customer> customers) {
    Scanner scnr = new Scanner(System.in);

    double amount = 0.00;

    Customer curr = ErrorHandler.getValidCustomer(scnr, customers);

    Account currAcc = ErrorHandler.getValidAccount(scnr, curr);
    Log logger;

    UserTransaction ut;

    System.out.println("How much would you like to deposit/withdraw?");
    amount = Double.parseDouble(scnr.nextLine());

    while (amount < 0) {
      System.out.println("Invalid Input");
      System.out.println("How much would you like to deposit/withdraw?");
      amount = Double.parseDouble(scnr.nextLine());
    }

    System.out.println("Would you like to\n1. Deposit\n2. Withdraw");
    String input = scnr.nextLine();

    // logic based on transaction type
    switch (input) {
      case "1":
        currAcc.deposit(amount);
        depositLog(curr, currAcc, amount, "deposit");

        System.out.println("Your balance is now $" + currAcc.getBalance() + "\n");
        break;
      case "2":
        if (currAcc.withdraw(amount)) {

          depositLog(curr, currAcc, amount, "withdraw");
          System.out.println("Your balance is now $" + currAcc.getBalance() + "\n");
        } else {

          ut = new Builder()
              .customer(curr)
              .account1(currAcc)
              .startBalance(currAcc.getBalance())
              .endBalance(currAcc.getBalance())
              .buildTransaction();

          logger = new Builder()
              .account1(currAcc)
              .person1(curr)
              .transaction("invalid")
              .buildLog();

          // log write to file
          History.writeToFile(logger.parseTransaction(), logDir);

          ut.addTransaction(logger.parseTransaction());

          // User Transactions write to file
          History.writeToFile(ut.fileOutput(utDir, ut.fileName()), utDir + ut.fileName());

          System.out.println("You do not have sufficient funds.\n");
        }
        break;
      default:
        System.out.println("Invalid option. Transaction cancelled.");
    }

  }

  /**
   * Log deposit
   * 
   * @param curr
   * @param currAcc
   * @param amount
   * @param transaction
   */
  public static void depositLog(Customer curr, Account currAcc, double amount, String transaction) {
    Log logger;

    UserTransaction ut;
    if (transaction.equals("deposit")) {
      ut = new Builder()
          .customer(curr)
          .account1(currAcc)
          .startBalance(currAcc.getBalance())
          .endBalance(currAcc.getBalance())
          .buildTransaction();

      logger = new Builder()
          .account1(currAcc)
          .person1(curr)
          .amount(Double.toString(amount))
          .transaction("deposit")
          .buildLog();
    } else {
      ut = new Builder()
          .customer(curr)
          .account1(currAcc)
          .startBalance(currAcc.getBalance())
          .endBalance(currAcc.getBalance())
          .buildTransaction();

      logger = new Builder()
          .account1(currAcc)
          .person1(curr)
          .amount(Double.toString(amount))
          .transaction("withdraw")
          .buildLog();

    }

    // log write to file
    History.writeToFile(logger.parseTransaction(), logDir);

    ut.addTransaction(logger.parseTransaction());

    // User Transactions write to file
    History.writeToFile(ut.fileOutput(utDir, ut.fileName()), utDir + ut.fileName());
  }

  /**
   * Payment user interface
   * 
   * @param customers
   */
  public static void payPrompt(List<Customer> customers) {

    double amount = 0.00;

    Scanner scnr = new Scanner(System.in);
    Customer curr = ErrorHandler.getValidCustomer(scnr, customers);
    Account fromAcct = ErrorHandler.getValidAccount(scnr, curr);

    while (amount <= 0.00) {
      System.out.println("Invalid Input");
      System.out.println("How much will you be paying?");
      amount = Double.parseDouble(scnr.nextLine());
    }

    while (amount > fromAcct.balance) {
      System.out.println("Not enough funds.");
      amount = Double.parseDouble(scnr.nextLine());
    }
    
    System.out.println("Now input information of the person you'd like to pay");
    Customer paid = ErrorHandler.getValidCustomer(scnr, customers);

    Account payAcct = ErrorHandler.getValidAccount(scnr, paid);

    pay(paid, payAcct, curr, fromAcct, amount);

    System.out.println("You just paid " + paid.getFirstName() + " " + paid.getLastName() + " $" + amount
        + ". Your new balance is " + fromAcct.getBalance());
  }

  /**
   * Payment method between customers from one account to another
   * 
   * @param paid
   * @param toAcct
   * @param curr
   * @param fromAcct
   * @param amount
   */
  public static void pay(Customer paid, Account toAcct, Customer curr, Account fromAcct, double amount) {
    Log logger;

    UserTransaction ut1, ut2;

    fromAcct.withdraw(amount);
    toAcct.deposit(amount);

    ut1 = new Builder()
        .customer(curr)
        .account1(fromAcct)
        .startBalance(fromAcct.getBalance())
        .endBalance(fromAcct.getBalance())
        .buildTransaction();

    ut2 = new Builder()
        .customer(curr)
        .account1(toAcct)
        .startBalance(toAcct.getBalance())
        .endBalance(toAcct.getBalance())
        .buildTransaction();

    logger = new Builder()
        .account1(fromAcct)
        .account2(toAcct)
        .person1(curr)
        .person2(paid)
        .transaction("payment")
        .amount(Double.toString(amount))
        .buildLog();

    // log write to file
    History.writeToFile(logger.parseTransaction(), logDir);

    ut1.addTransaction(logger.parseTransaction());
    ut2.addTransaction(logger.parseTransaction());

    // User Transactions write to file
    History.writeToFile(ut1.fileOutput(utDir, ut1.fileName()), utDir + ut1.fileName());
    History.writeToFile(ut2.fileOutput(utDir, ut2.fileName()), utDir + ut2.fileName());
  }

  /**
   * Read and transactions from csv file
   * 
   * @param filePath
   * @param customers
   */
  public static void readTransactions(String filePath, List<Customer> customers) {
    try (Scanner scanner = new Scanner(new File(filePath))) {
      String firstLine = scanner.nextLine();
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        if (line.equals(firstLine)) {
          scanner.nextLine();
        } else {
          processTransaction(line, customers);
        }

      }
    } catch (FileNotFoundException e) {
      System.out.println("Transaction file not found: " + e.getMessage());
    }
  }

  /**
   * Process transactions from csv file
   * 
   * @param transaction
   * @param customers
   */
  public static void processTransaction(String transaction, List<Customer> customers) {
    String[] parts = transaction.split(",", -1);
    String fromFirstName = parts[0];
    String fromLastName = parts[1];
    // String fromFullName = fromFirstName + " " + fromLastName;

    Account toAcct;
    Customer curr;
    if (fromFirstName.isEmpty() && fromLastName.isEmpty()) {
      curr = null;
    } else {
      curr = ErrorHandler.isValidCustomer2(fromFirstName, fromLastName, customers);
    }

    String fromWhere = parts[2].trim();
    Account fromAcct;
    String action = parts[3].trim().toLowerCase();
    String toFirstName = parts[4].trim();
    String toLastName = parts[5].trim();
    // String toFullName = toFirstName + " " + toLastName;
    Customer curr2;
    if (toFirstName.isEmpty() && toLastName.isEmpty()) {
      curr2 = null;
    } else {
      curr2 = ErrorHandler.isValidCustomer2(toFirstName, toLastName, customers);
    }

    String toWhere = parts[6].trim();
    toAcct = ErrorHandler.getAccount(curr2, toWhere);
    fromAcct = ErrorHandler.getAccount(curr, fromWhere);
    Log logger;

    UserTransaction ut;
    double amount;
    if (parts[7].isEmpty()) {
      amount = 0;
    } else {
      amount = Double.parseDouble(parts[7].trim());
    }
    switch (action) {
      case "pays":

        if (curr2 == null) {
          return;
        }

        pay(curr2, toAcct, curr, fromAcct, amount);

        break;
      case "transfers":
        String transferToWhere = parts[6].trim();
        toAcct = ErrorHandler.getAccount(curr2, transferToWhere);
        transfer(toAcct, fromAcct, curr, amount);
        break;
      case "inquires":
        ut = new Builder()
            .customer(curr)
            .account1(fromAcct)
            .startBalance(null)
            .endBalance(null)
            .buildTransaction();

        logger = new Builder()
            .account1(fromAcct)
            .person1(curr)
            .amount(null)
            .transaction("inquire")
            .buildLog();

        // log write to file
        History.writeToFile(logger.parseTransaction(), logDir);

        ut.addTransaction(logger.parseTransaction());

        // User Transactions write to file
        History.writeToFile(ut.fileOutput(utDir, ut.fileName()), utDir + ut.fileName());
        break;
      case "withdraws":

        fromAcct.withdraw(amount);
        depositLog(curr, fromAcct, amount, "withdraw");
        break;
      case "deposits":
        toAcct.deposit(amount);
        depositLog(curr2, toAcct, amount, "deposit");

        break;
      default:
        System.out.println("Unknown transaction type: " + action);
        break;
    }
  }
}