import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class RunBank {
  public static void main(String[] args) throws FileNotFoundException {
    boolean exitFlag = false;
    while (!exitFlag) {
      String userInput = Prompt();
      switch (userInput) {
        case "1":
          System.out.println("You Selected: Make individual transaction\n");
          break;
        case "2":
          System.out.println("You Selected: Make transaction between two users\n");
          break;
        case "3":
          System.out.println("You Selected: Bank Manager\n");
          break;
        case "4":
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
    ParseFile();
  }

  private static String Prompt() {
    String input = "";
    Scanner scnr = new Scanner(System.in);
    System.out.println("WELCOME TO MINERS BANK!\n");
    System.out.println("Please make a selection: ");
    System.out.println("[1] Make individual transaction");
    System.out.println("[2] Make transaction between two users");
    System.out.println("[3] Bank Manager");
    System.out.println("[4] EXIT");
    input = scnr.next();
    return input;
  }

  private static void ParseFile() throws FileNotFoundException {
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
  }
}
