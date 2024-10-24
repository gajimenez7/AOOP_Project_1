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
          break;
        case "3":
          System.out.println("You Selected: Pay another user\n");
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

  public static List parseFile() throws FileNotFoundException {
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
        String checkAcctNum = accountData[7];
        double checkStart = Double.parseDouble(accountData[8]);
        String savAcctNum = accountData[9];
        double savStart = Double.parseDouble(accountData[10]);
        String creditAcctNum = accountData[11];
        double creditLim = Double.parseDouble(accountData[12]);
        double creditStart = Double.parseDouble(accountData[13]);

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
