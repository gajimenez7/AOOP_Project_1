import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class RunBank {
  public static void main(String[] args) throws FileNotFoundException {
    List<Customer> customers = parseFile();

    boolean exitFlag = false;
    Log logTransaction = new Log();

    while (!exitFlag) {
      String userInput = prompt();
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
        case "4":
          System.out.println("You Selected: Bank Manager\n");
          break;
        case "5":
          System.out.println("Exiting...Goodbye!\n");
          exitFlag = true;
          break;
        case "exit":
          System.out.println("Exiting...Goodbye!\n");
          exitFlag = true;
          break;
        default:
          System.out.println("Invalid input!\n");
          break;
      }
    }
    parseFile();
    toFile(logTransaction.parseTransaction());
  }
<<<<<<< HEAD

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

private static void bankManager(List <Customer> customers){
Scanner scnr = new Scanner(System.in);
System.out.println("Would you like to 1. Display user info, 2. show account info");
String input = scnr.nextLine();

switch (input) {
  case "1":
    System.out.println("Search user by A. Name or B. ID number?");
    String input2 = scnr.nextLine();
    while(!input2.equals("A") || !input2.equals("B")){
      System.out.println("Invalid option. Try again");
      input2 = scnr.nextLine();
    }
    if(input2.equals("A")){
      Customer curr = getValidCustomer(scnr, customers);
      curr.showCustomerDetails();
    }
    else{
      System.out.println("Enter ID number: ");
      String id = scnr.nextLine();
      Customer cust = searchCustomer(id, customers);
      while(cust == null){
        System.out.println("Not a valid ID. Try again");
        id = scnr.nextLine();
        cust = searchCustomer(id, customers);
      }
      cust.showCustomerDetails();
    }
    break;
  case "2":
  Customer showAcctInfo = getValidCustomer(scnr, customers);

  System.out.println("/Checking Account/\nBalance: $" + showAcctInfo.accounts.get(1).getBalance() + "\nAccount Number: " + showAcctInfo.accounts.get(1).getAccountNumber());
  System.out.println("/Savings Account/\nBalance: $" + showAcctInfo.accounts.get(2).getBalance() + "\nAccount Number: " + showAcctInfo.accounts.get(2).getAccountNumber());
 
  System.out.println("/Checking Account/\nBalance: $" + showAcctInfo.accounts.get(0).getBalance() + "\nAccount Number: " + showAcctInfo.accounts.get(0).getAccountNumber() );
  default:
    break;
}
}
private static Customer searchCustomer(String id, List <Customer> customers){
  for(Customer temp : customers){
    if(temp.getID().equals(id)){
      return temp;
    }
  }
  return null;
}
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
private static void transfer(List<Customer> customers) {
  Scanner scnr = new Scanner(System.in);
  
  Customer curr = getValidCustomer(scnr, customers);
  Account fromAcct = getValidAccount(scnr, curr);
  
  System.out.println("How much will you be transferring?");
  double amount = Double.parseDouble(scnr.nextLine());
  if (fromAcct.getBalance() < amount) {
      System.out.println("You do not have sufficient funds.");
      return;
  }

  Account toAcct = getValidAccount(scnr, curr);


  fromAcct.setBalance(fromAcct.getBalance() - amount);
  toAcct.setBalance(toAcct.getBalance() + amount);

  System.out.println("You transferred $" + amount + " from account " + fromAcct.getAccountNumber() + 
      " to account " + toAcct.getAccountNumber() + ".");
}
private static void transaction(List<Customer> customers) {
  Scanner scnr = new Scanner(System.in);


  Customer curr = getValidCustomer(scnr, customers);
  Account currAcc = getValidAccount(scnr, curr);

  System.out.println("How much would you like to deposit/withdraw?");
  double amount = Double.parseDouble(scnr.nextLine());
  
  System.out.println("Would you like to\n1. Deposit\n2. Withdraw");
  String input = scnr.nextLine();
  
  switch (input) {
      case "1":  
          currAcc.deposit(amount);
          System.out.println("Your balance is now $" + currAcc.getBalance());
          break;
          
      case "2":  
          if (currAcc.withdraw(amount)) {
              System.out.println("Your balance is now $" + currAcc.getBalance());
          } else {
              System.out.println("You do not have sufficient funds.");
          }
          break;
          
      default:
          System.out.println("Invalid option. Transaction cancelled.");
  }
}


  private static void pay(List <Customer> customers){
    Scanner scnr = new Scanner(System.in);
    Customer curr = getValidCustomer(scnr, customers);
    Account fromAcct = getValidAccount(scnr, curr);

    System.out.println("How much will you be paying?");
    double amount = Double.parseDouble(scnr.nextLine());
    while(amount > fromAcct.balance){
      System.out.println("Not enough funds.");
      amount = Double.parseDouble(scnr.nextLine());
    }
    System.out.println("Now input information of the person you'd like to pay");
    Customer paid = getValidCustomer(scnr, customers);
    Account payAcct = getValidAccount(scnr, paid);

    fromAcct.setBalance(fromAcct.balance - amount);
    payAcct.setBalance(payAcct.balance + amount);


    System.out.println("You just paid " + paid.getFirstName() + " " + paid.getLastName() + " $" + amount + ". Your new balance is " + fromAcct.getBalance());
  }

  
=======

  private static boolean transaction(List<Customer> customers) {
    Scanner scnr = new Scanner(System.in);
    System.out.println("Whose account is the transaction?");
    String input = scnr.nextLine();
    Customer curr = isValidCustomer(input, customers);
    while (isValidCustomer(input, customers) == null) {
      System.out.println("Not a valid user. Try again.");
      input = scnr.nextLine();
    }
    System.out.println("Enter the account number where the transaction will take place:\n");
    input = scnr.nextLine();
    while (curr.getAccount(input) != null) {
      System.out.println("Not a valid account number. Try again");
      input = scnr.nextLine();
    }
    Account currAcc = curr.getAccount(input);
    System.out.println("How much would you like to deposit/withdraw?");
    double amount = Double.parseDouble(scnr.nextLine());
    System.out.println("Would you like to\n 1. Deposit\n2.Withdraw\n");
    input = scnr.nextLine();
    switch (input) {
      case "1":
        currAcc.deposit(amount);
      case "2":
      default:
    }

    return false;
  }
>>>>>>> 54ec8bab2e86ce8587c609591649475ab57ba633

  private static String prompt() {
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

  public static Customer isValidCustomer(String name, List<Customer> customers) {
    String[] fullName = name.split(" ");
    for (Customer temp : customers) {
      if (temp.getFirstName().equals(fullName[0]) && temp.getLastName().equals(fullName[1])) {
        return temp;
      }
    }
    return null;
  }

<<<<<<< HEAD
  public static List <Customer>ParseFile() throws FileNotFoundException {
=======
  public static List parseFile() throws FileNotFoundException {
>>>>>>> 54ec8bab2e86ce8587c609591649475ab57ba633
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

  public void createFile() {
    try {
      File f = new File("o g.txt");
      f.createNewFile();
    } catch (IOException e) {
      System.out.println("An error occured");
      e.printStackTrace();
    }
  }

  public static void toFile(String transaction) {
    try {
      FileWriter fw = new FileWriter("log.txt", true);
      fw.write(transaction);
      fw.close();
    } catch (IOException e) {
      System.out.println("An error occured");
      e.printStackTrace();
    }
  }
}
