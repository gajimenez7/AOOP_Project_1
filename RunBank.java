import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;

/**
 * Runner class that uses all helper classes
 * 
 * @author
 * @author
 * @author George Jimenez
 * 
 */
public class RunBank {

  /**
   * Main contains the interface for user interaction
   * 
   * @param args
   * @throws FileNotFoundException
   */
  public static void main(String[] args) throws FileNotFoundException {
    List<Customer> customers = ParseFile();
    createFile();

    boolean exitFlag = false;
    while (!exitFlag) {
      String userInput = Prompt();
      switch (userInput) {
        case "1":
          System.out.println("You Selected: Make individual transaction\n");
          transaction(customers);
          break;
        case "2":
          System.out.println("You Selected: Make transaction between two accounts\n");
          transfer(customers);
          break;
        case "3":
          System.out.println("You Selected: Pay another user\n");
          pay(customers);
          break;
        case "4":
          System.out.println("You Selected: Bank Manager\n");
          bankManager(customers);
          break;
        case "5":
          System.out.println("Exiting...Goodbye!");
          exitFlag = true;
          break;
        case "exit":
          System.out.println("Exiting...Goodbye!");
          exitFlag = true;
          break;
        default:
          System.out.println("Invalid input!\n");
          break;
      }
    }
    ParseFile();
  }

  /**
   * Get valid customer from list of customers and user input
   * 
   * @param scnr
   * @param customers
   * @return Customer
   */
  private static Customer getValidCustomer(Scanner scnr, List<Customer> customers) {
    System.out.println("Whose account is the request?");
    String input = scnr.nextLine();
    Customer curr = isValidCustomer(input, customers);
    while (curr == null) {
      System.out.println("Not a valid user. Try again.");
      input = scnr.nextLine();
      curr = isValidCustomer(input, customers);
    }
    return curr;
  }

  /**
   * Simulates a bank manager option
   * 
   * @param customers
   */
  private static void bankManager(List<Customer> customers) {
    Scanner scnr = new Scanner(System.in);
    System.out.println("Would you like to 1. Display user info, 2. show account info");
    String input = scnr.nextLine();

    switch (input) {
      case "1":
        System.out.println("Search user by A. Name or B. ID number?");
        String input2 = scnr.nextLine();
        while (!input2.equals("A") && !input2.equals("B")) {
          System.out.println("Invalid option. Try again");
          input2 = scnr.nextLine();
        }
        if (input2.equals("A")) {
          Customer curr = getValidCustomer(scnr, customers);
          curr.showCustomerDetails();
        } else {
          System.out.println("Enter ID number: ");
          String id = scnr.nextLine();
          Customer cust = searchCustomer(id, customers);
          while (cust == null) {
            System.out.println("Not a valid ID. Try again");
            id = scnr.nextLine();
            cust = searchCustomer(id, customers);
          }
          cust.showCustomerDetails();
        }
        break;
      case "2":
        Customer showAcctInfo = getValidCustomer(scnr, customers);

        System.out.println("/Checking Account/\nBalance: $" + showAcctInfo.accounts.get(1).getBalance()
            + "\nAccount Number: " + showAcctInfo.accounts.get(1).getAccountNumber());
        System.out.println("/Savings Account/\nBalance: $" + showAcctInfo.accounts.get(2).getBalance()
            + "\nAccount Number: " + showAcctInfo.accounts.get(2).getAccountNumber());

        System.out.println("/Checking Account/\nBalance: $" + showAcctInfo.accounts.get(0).getBalance()
            + "\nAccount Number: " + showAcctInfo.accounts.get(0).getAccountNumber());
      default:
        break;
    }
  }

  /**
   * Searches requested customer from list
   * 
   * @param id
   * @param customers
   * @return
   */
  private static Customer searchCustomer(String id, List<Customer> customers) {
    for (Customer temp : customers) {
      if (temp.getID().equals(id)) {
        return temp;
      }
    }
    return null;
  }

  /**
   * Searches for requested account associated to customer
   * 
   * @param scnr
   * @param customer
   * @return
   */
  private static Account getValidAccount(Scanner scnr, Customer customer) {
    System.out.println("Enter the account number:");
    String input = scnr.nextLine();
    Account account = customer.getAccount(input);
    while (account == null) {
      System.out.println("Not a valid account number. Try again.");
      input = scnr.nextLine();
      account = customer.getAccount(input);
    }
    return account;
  }

  /**
   * Transfer user interface
   * 
   * @param customers
   */

  private static void transfer(List<Customer> customers) {
    Scanner scnr = new Scanner(System.in);

    Log logger = new Log();
    
    double amount = 0.00;

    Customer curr = getValidCustomer(scnr, customers);
    Account fromAcct = getValidAccount(scnr, curr);

    System.out.println("How much will you be transferring?");
    amount = Double.parseDouble(scnr.nextLine());
    
    while(amount < 0){
      System.out.println("Invalid Input");
      System.out.println("How much will you be transferring?");
      amount = Double.parseDouble(scnr.next());
    }
    
    if (fromAcct.getBalance() < amount) {
      System.out.println("You do not have sufficient funds.");
      return;
    }

    Account toAcct = getValidAccount(scnr, curr);

    fromAcct.setBalance(fromAcct.getBalance() - amount);
    toAcct.setBalance(toAcct.getBalance() + amount);

    logger.setAccount1(fromAcct);
    logger.setAccount2(toAcct);
    logger.setPerson1(curr);
    logger.setAmount(Double.toString(amount));
    logger.setTransaction("transfer");
    toFile(logger.parseTransaction());

    System.out.println("You transferred $" + amount + " from account " + fromAcct.getAccountNumber() +
        " to account " + toAcct.getAccountNumber() + ".");
  }

  /**
   * Transaction user interface
   * 
   * @param customers
   */
  private static void transaction(List<Customer> customers) {
    Scanner scnr = new Scanner(System.in);

    double amount = 0.00;

    Customer curr = getValidCustomer(scnr, customers);
    Account currAcc = getValidAccount(scnr, curr);

    Log logger = new Log();

    System.out.println("How much would you like to deposit/withdraw?");
    amount = Double.parseDouble(scnr.nextLine());

    while(amount < 0){
      System.out.println("Invalid Input");
      System.out.println("How much would you like to deposit/withdraw?");
      amount = Double.parseDouble(scnr.nextLine());
    }

    System.out.println("Would you like to\n1. Deposit\n2. Withdraw");
    String input = scnr.nextLine();

    switch (input) {
      case "1":
        currAcc.deposit(amount);

        logger.setAccount1(currAcc);
        logger.setPerson1(curr);
        logger.setTransaction("deposit");
        logger.setAmount(Double.toString(amount));
        toFile(logger.parseTransaction());

        System.out.println("Your balance is now $" + currAcc.getBalance() + "\n");
        break;
      case "2":
        if (currAcc.withdraw(amount)) {
          System.out.println("Your balance is now $" + currAcc.getBalance() + "\n");

          logger.setAccount1(currAcc);
          logger.setPerson1(curr);
          logger.setTransaction("withdraw");
          logger.setAmount(Double.toString(amount));
          toFile(logger.parseTransaction());
        } else {
          logger.setAccount1(currAcc);
          logger.setPerson1(curr);
          logger.setTransaction("invalid");
          toFile(logger.parseTransaction());

          System.out.println("You do not have sufficient funds.\n");
        }
        break;
      default:
        System.out.println("Invalid option. Transaction cancelled.");
    }
  }

  /**
   * Payment user interface
   * 
   * @param customers
   */
  private static void pay(List<Customer> customers) {
    Log logger = new Log();

    double amount = 0.00;

    Scanner scnr = new Scanner(System.in);
    Customer curr = getValidCustomer(scnr, customers);
    Account fromAcct = getValidAccount(scnr, curr);

    while(amount < 0){
      System.out.println("Invalid Input");
      System.out.println("How much will you be paying?");
      amount = Double.parseDouble(scnr.nextLine());
    }

    while (amount > fromAcct.balance) {
      System.out.println("Not enough funds.");
      amount = Double.parseDouble(scnr.nextLine());
    }
    System.out.println("Now input information of the person you'd like to pay");
    Customer paid = getValidCustomer(scnr, customers);
    Account payAcct = getValidAccount(scnr, paid);

    fromAcct.setBalance(fromAcct.balance - amount);
    payAcct.setBalance(payAcct.balance + amount);

    logger.setAccount1(fromAcct);
    logger.setAccount2(payAcct);
    logger.setPerson1(curr);
    logger.setPerson2(paid);
    logger.setAmount(Double.toString(amount));
    logger.setTransaction("payment");
    toFile(logger.parseTransaction());

    System.out.println("You just paid " + paid.getFirstName() + " " + paid.getLastName() + " $" + amount
        + ". Your new balance is " + fromAcct.getBalance());
  }

  /**
   * Prints the prompt for user to give input
   * 
   * @return
   */
  private static String Prompt() {
    String input = "";
    Scanner scnr = new Scanner(System.in);
    System.out.println("WELCOME TO MINERS BANK!\n");
    System.out.println("Please make a selection: ");
    System.out.println("[1] Make individual transaction");
    System.out.println("[2] Make transaction between two accounts");
    System.out.println("[3] Pay another user");
    System.out.println("[4] Bank Manager");
    System.out.println("[5] EXIT");
    input = scnr.nextLine();
    return input;
  }

  /**
   * Checks that input is a valid customer from list
   * 
   * @param name
   * @param customers
   * @return
   */

  public static Customer isValidCustomer(String name, List<Customer> customers) {
    String[] fullName = name.split(" ");
    for (Customer temp : customers) {
      if (temp.getFirstName().equals(fullName[0]) && temp.getLastName().equals(fullName[1])) {
        return temp;
      }
    }
    return null;
  }

  /**
   * Parse csv file
   * 
   * @return
   * @throws FileNotFoundException
   */

  public static List<Customer> ParseFile() throws FileNotFoundException {
    String line = "";
    List<Customer> customerList = new ArrayList<Customer>();
    try (Scanner scanner = new Scanner(new File("Bank Users.csv"))) {
      scanner.nextLine();
      while (scanner.hasNextLine()) {
        line = scanner.nextLine();
        String[] accountData = line.split(",");
        String id = accountData[0];
        String firstName = accountData[1];
        String lastName = accountData[2];
        String dob = accountData[3];
        String address = accountData[4];
        String phoneNumber = accountData[6];
        String checkAcctNum = accountData[8];
        double checkStart = Double.parseDouble(accountData[9]);
        String savAcctNum = accountData[10];
        double savStart = Double.parseDouble(accountData[11]);
        String creditAcctNum = accountData[12];
        double creditLim = Double.parseDouble(accountData[13]);
        double creditStart = Double.parseDouble(accountData[14]);

        Customer customer = new Person(id, firstName, lastName, dob, address, phoneNumber);
        customerList.add(customer);

        Account checking = new Checking(checkAcctNum, checkStart);
        Account savings = new Saving(savAcctNum, savStart);
        Account credit = new Credit(creditAcctNum, creditLim, creditStart);

        customer.addAccount(credit);
        customer.addAccount(checking);
        customer.addAccount(savings);
      }

    } catch (FileNotFoundException e) {
      System.out.println("File not found: " + e.getMessage());
    } catch (Exception e) {
      System.out.println("Error");
    }
    return customerList;
  }

  /**
   * Create logging text file
   */
  public static void createFile() {
    try {
      File f = new File("log.txt");
      f.createNewFile();
    } catch (IOException e) {
      System.out.println("An error occurred");
      e.printStackTrace();
    }
  }

  /**
   * Write transactions to logging text file
   * 
   * @param transaction
   */
  public static void toFile(String transaction) {
    try {
      FileWriter fw = new FileWriter("log.txt", true);
      fw.write(transaction);
      fw.close();
    } catch (IOException e) {
      System.out.println("An error occurred");
      e.printStackTrace();
    }
  }
}
