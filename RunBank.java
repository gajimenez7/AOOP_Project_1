import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.ReadOnlyBufferException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.io.File;
import java.util.Random;


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
          System.out.println("You Selected: Create an Account\n");
          acountCreation(customers);
          break;
        case "5":
          System.out.println("You Selected: Bank Manager\n");
          bankManager(customers);
          break;
          
        case "6":
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
private static boolean hasNumber(String str){
  for (char c : str.toCharArray()) {
    if (Character.isDigit(c)) {
       return true;
    }
 }
 return false;
}
private static boolean hasLetter(String num){
  boolean valid = false;
  for (char c : num.toCharArray()) {
    if (Character.isDigit(c)) {
       valid = false;
    }
    else{
      return true;
    }
 }
 return valid;
}
  private static void acountCreation(List<Customer> customers){
    Scanner scnr = new Scanner(System.in);
    //String [] newCustInfo = new String[13];

    System.out.println("Enter your first name: \n");
    String firstName= scnr.nextLine();
    firstName = checkName(scnr, firstName);

    System.out.println("Enter your last name:\n");
    String lastName = scnr.nextLine();
    lastName = checkName(scnr, lastName);

    System.out.println("Enter your address:\n");
    String address = scnr.nextLine();

    System.out.println("Enter your phone number (with no spaces or dashes):\n");
    String phoneNum = scnr.nextLine();
    phoneNum = checkNum(scnr, phoneNum);

    System.out.println("Enter your Date of Birth (in the format DDMMYY)");
    String dob = scnr.nextLine();
    dob = checkDOB(scnr, dob);

    System.out.println("What is your credit score?");
    String creditScore = scnr.nextLine();
    creditScore = checkCreditScore(scnr, creditScore);
    double creditLimit = assignCreditLimit(creditScore);
    

    int _newID = Integer.parseInt(highestID(customers)) + 1;
    String newID = Integer.toString(_newID);
    System.out.println("Your ID has been assigned. It is " + newID + ".\n");

    String checkingAcctID = Integer.toString(highestCheckingAcctNum(customers));
    System.out.println("Your Checking Account Number is " + checkingAcctID +".\n");

    String savingsAcctID = Integer.toString(highestSavingsAcctNum(customers));
    System.out.println(" Your Savings Account Number is " + savingsAcctID + ".\n");
    
    String creditAcctID = Integer.toString(highestCreditAcctNum(customers));
    System.out.println(" Your Credit Account Number is " + creditAcctID + ".\n");
    System.out.printf("Your credit limit is $$%.2f.\n" , creditLimit );

    Customer newCustomer = new Person(newID, firstName, lastName, dob, address, phoneNum);
    customers.add(newCustomer);

        Account checking = new Checking(checkingAcctID, 0.00);
        Account savings = new Saving(savingsAcctID, 0.00);
        Account credit = new Credit(creditAcctID, creditLimit, 0.00);

        newCustomer.addAccount(credit);
        newCustomer.addAccount(checking);
        newCustomer.addAccount(savings);
  
  }
  private static int highestCreditAcctNum(List<Customer> customers){
    Customer highestCustomer = customers.get(0);
    Account highestAccount = highestCustomer.accounts.get(0);
    for (Customer temp : customers) {
      if(Integer.parseInt(highestAccount.getAccountNumber()) < Integer.parseInt(temp.accounts.get(0).getAccountNumber()) ){
        highestAccount = temp.accounts.get(0);
      }
    }
    return Integer.parseInt(highestAccount.getAccountNumber()) + 1;
  }
  private static int highestCheckingAcctNum(List<Customer> customers){
    Customer highestCustomer = customers.get(0);
    Account highestAccount = highestCustomer.accounts.get(1);
    for (Customer temp : customers) {
      if(Integer.parseInt(highestAccount.getAccountNumber()) < Integer.parseInt(temp.accounts.get(1).getAccountNumber()) ){
        highestAccount = temp.accounts.get(1);
      }
    }
    return Integer.parseInt(highestAccount.getAccountNumber()) + 1;
  }
  private static int highestSavingsAcctNum(List<Customer> customers){
    Customer highestCustomer = customers.get(0);
    Account highestAccount = highestCustomer.accounts.get(2);
    for (Customer temp : customers) {
      if(Integer.parseInt(highestAccount.getAccountNumber()) < Integer.parseInt(temp.accounts.get(2).getAccountNumber()) ){
        highestAccount = temp.accounts.get(2);
      }
    }
    return Integer.parseInt(highestAccount.getAccountNumber()) + 1;
  }
  private static String highestID(List<Customer> customers){
    Customer highestCustomer = customers.get(0);
    for (Customer temp : customers) {
      if(Integer.parseInt(highestCustomer.getID()) < Integer.parseInt(temp.getID()) ){
        highestCustomer = temp;
      }
    }
    return highestCustomer.getID();
  }

  
  private static String checkCreditScore(Scanner scnr, String _creditScore){
    int creditScore = Integer.parseInt(_creditScore);
    while(creditScore < 0 || creditScore > 850 || hasLetter(_creditScore) == true){
      System.out.println("Enter a valid credit score:\n");
      creditScore = Integer.parseInt(scnr.nextLine());
    }
    return Integer.toString(creditScore);
  }

private static double assignCreditLimit(String _creditScore){
  double creditScore = Double.parseDouble(_creditScore);
  Random random = new Random();

  if(creditScore <= 580){
    return random.nextInt(699 - 100) + 100;
  }
  else if(creditScore > 580 && creditScore < 670){
    return random.nextInt(4999 - 700) + 700;
  }
  else if(creditScore >= 670 && creditScore <740){
    return random.nextInt(7499 - 5000) + 5000;
  }
  else if(creditScore >= 740 && creditScore <800){
    return random.nextInt(15999 - 7500) + 7500;
  }
  else if(creditScore >= 800 && creditScore <= 850){
    return random.nextInt(25000 - 16000) + 16000;
  }
  else{
    return -1;
  }
}

  private static boolean checkDayToMonth(String day, String month){
    int dayNum = Integer.parseInt(day);
    int monthNum = Integer.parseInt(month);
    if(monthNum == 1 || monthNum == 3 || monthNum == 5 || monthNum == 7 || monthNum == 8 || monthNum == 10 || monthNum == 12){
      if(dayNum > 31){
        return false;
      }
    }
    else if(monthNum == 4 || monthNum == 6 || monthNum == 9 || monthNum == 11 ){
      if(dayNum > 30){
        return false;
      }
    }
    else if(monthNum == 2){
      if(dayNum > 29){
        return false;
      }
    }
    return true;
  }
  private static String monthStr(String monthNum){
    switch (monthNum) {
      case "01":
        return "Jan";

      case "02":
        return "Feb";

      case "03":
        return "Mar";

      case "04":
        return "Apr";

      case "05":
        return "May";

      case "06":
        return "Jun";

      case "07":
        return "Jul";

      case "08":
        return "Aug";

      case "09":
        return "Sep";

      case "10":
        return "Oct";

      case "11":
        return "Nov";
        
      case "12":
        return "Dec";

      default:
      return null;
      
    }
  }

  private static String checkDOB(Scanner scnr, String str) {
    while (true) {
      if (str.length() == 6 && !hasLetter(str)) {
        String day = str.substring(0, 2);
        String month = str.substring(2, 4);
            
        if (isValidMonth(month)) {
          if (checkDayToMonth(day, month)) {
            month = monthStr(month);  
            return String.format("%s-%s-%s", day, month, str.substring(4));
        } 
          else {
            System.out.println("The date you inputted is impossible for the given month, try again.\n");
        }
       } 
          else {
                System.out.println("Please enter a valid month (01–12).\n");
            }
      } else {
            System.out.println("Please enter a DOB in the correct format (DDMMYY) with a valid month (01–12).\n");
        }
        
        str = scnr.nextLine();
    }
}

private static boolean isValidMonth(String month) {
    try {
        int monthInt = Integer.parseInt(month);
        return monthInt >= 1 && monthInt <= 12;
    } catch (NumberFormatException e) {
        return false;
    }
}


  private static String checkNum(Scanner scnr, String str){
    while(hasLetter(str) == true || str.length() != 10){
      System.out.println("Please enter a valid number:\n");
      str = scnr.nextLine();
    }
    str = str.trim();
    return String.format("(%s) %s-%s", 
      str.substring(0, 3), 
      str.substring(3, 6), 
      str.substring(6));
  }
  private static String checkName(Scanner scnr, String str){
    while(hasNumber(str) == true){
      System.out.println("Please enter a valid name:\n");
      str = scnr.nextLine();
    }
    str = str.trim();
    Character.toUpperCase(str.charAt(0));
    return str;
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

        System.out.println("/Credit Account/\nBalance: $" + showAcctInfo.accounts.get(0).getBalance()
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
  public static void readTransactions(String filePath, List<Customer> customers) {
    try (Scanner scanner = new Scanner(new File(filePath))) {
        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            processTransaction(line, customers);
        }
    } catch (FileNotFoundException e) {
        System.out.println("Transaction file not found: " + e.getMessage());
    }
}

public static Customer isValidCustomer2(String firstName, String lastName, List<Customer> customers) {
  for (Customer temp : customers) {
    if (temp.getFirstName().equals(firstName) && temp.getLastName().equals(lastName)) {
      return temp;
    }
  }
  return null;
}
public static void processTransaction(String transaction, List<Customer> customers) {
    String[] parts = transaction.split(",");
    String fromFirstName = parts[0].trim() ;
    String fromLastName = parts[1].trim();
    
    Customer curr = isValidCustomer2(fromFirstName, fromLastName, customers);
    
    if(curr == null ){
      return;
    }
    String fromWhere = parts[2].trim();
    String action = parts[3].trim().toLowerCase();

    switch (action) {
        case "pays":
            String toFirstName = parts[4].trim();
            String toLastName = parts[5].trim();
            Customer curr2 = isValidCustomer2(toFirstName, toLastName, customers);
            if(curr2 == null){
              return;
            }
            String toWhere = parts[6].trim();
            double payAmount = Double.parseDouble(parts[7].trim());
            handlePay(fromFirstName, fromLastName, fromWhere, toFirstName, toLastName, toWhere, payAmount);
            break;
        case "transfers":
            String transferToWhere = parts[6].trim();
            double transferAmount = Double.parseDouble(parts[7].trim());
            handleTransfer(fromFirstName, fromLastName, fromWhere, transferToWhere, transferAmount);
            break;
        case "inquires":
            handleInquire(fromFirstName, fromLastName, fromWhere);
            break;
        case "withdraws":
            double withdrawAmount = Double.parseDouble(parts[7].trim());
            handleWithdraw(fromFirstName, fromLastName, fromWhere, withdrawAmount);
            break;
        case "deposits":
            String depositToFirstName = parts[4].trim();
            String depositToLastName = parts[5].trim();
            String depositToWhere = parts[6].trim();
            double depositAmount = Double.parseDouble(parts[7].trim());
            handleDeposit(depositToFirstName, depositToLastName, depositToWhere, depositAmount);
            break;
        default:
            System.out.println("Unknown transaction type: " + action);
            break;
    }
}

private static void handlePay(String fromFirst, String fromLast, String fromWhere,
                              String toFirst, String toLast, String toWhere, double amount) {
    System.out.println(fromFirst + " " + fromLast + " pays " + amount + " to " + toFirst + " " + toLast);
}

private static void handleTransfer(String fromFirst, String fromLast, String fromWhere,
                                   String toWhere, double amount) {
    System.out.println(fromFirst + " " + fromLast + " transfers " + amount + " from " + fromWhere + " to " + toWhere);
}

private static void handleInquire(String fromFirst, String fromLast, String fromWhere) {
    System.out.println(fromFirst + " " + fromLast + " inquires about " + fromWhere + " account.");
}

private static void handleWithdraw(String fromFirst, String fromLast, String fromWhere, double amount) {
    System.out.println(fromFirst + " " + fromLast + " withdraws " + amount + " from " + fromWhere);
}

private static void handleDeposit(String toFirst, String toLast, String toWhere, double amount) {
    System.out.println("Depositing " + amount + " to " + toFirst + " " + toLast + "'s " + toWhere + " account.");
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
    System.out.println("[4] Create an Account");
    System.out.println("[5] Bank Manager");
    System.out.println("[6] EXIT");
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

   /**
   * Parse csv file
   * 
   * @return
   * @throws FileNotFoundException
   */

   public static List<Customer> ParseFile() throws FileNotFoundException {
    List<Customer> customerList = new ArrayList<>();
    Map<String, Integer> headerMap = new HashMap<>();

    try (Scanner scanner = new Scanner(new File("Bank Users.csv"))) {
        // Read the header row and create a map for column indices
        if (scanner.hasNextLine()) {
            String headerLine = scanner.nextLine();
            String[] headers = splitLine(headerLine);
            for (int i = 0; i < headers.length; i++) {
                headerMap.put(headers[i].trim(), i);
            }
        }

        // Retrieve column indices dynamically
        int idIndex = headerMap.getOrDefault("Identification Number", -1);
        int firstNameIndex = headerMap.getOrDefault("First Name", -1);
        int lastNameIndex = headerMap.getOrDefault("Last Name", -1);
        int dobIndex = headerMap.getOrDefault("Date of Birth", -1);
        int addressIndex = headerMap.getOrDefault("Address", -1);
        int phoneNumberIndex = headerMap.getOrDefault("Phone Number", -1);
        int checkingAccountIndex = headerMap.getOrDefault("Checking Account Number", -1);
        int checkingBalanceIndex = headerMap.getOrDefault("Checking Starting Balance", -1);
        int savingsAccountIndex = headerMap.getOrDefault("Savings Account Number", -1);
        int savingsBalanceIndex = headerMap.getOrDefault("Savings Starting Balance", -1);
        int creditAccountIndex = headerMap.getOrDefault("Credit Account Number", -1);
        int creditMaxIndex = headerMap.getOrDefault("Credit Max", -1);
        int creditBalanceIndex = headerMap.getOrDefault("Credit Starting Balance", -1);

        // Read each data row and parse it based on header indices
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] accountData = splitLine(line);

            // Retrieve values dynamically using header map indices
            String id = idIndex >= 0 ? accountData[idIndex] : "";
            String firstName = firstNameIndex >= 0 ? accountData[firstNameIndex] : "";
            String lastName = lastNameIndex >= 0 ? accountData[lastNameIndex] : "";
            String dob = dobIndex >= 0 ? accountData[dobIndex] : "";
            String address = addressIndex >= 0 ? accountData[addressIndex] : "";
            String phoneNumber = phoneNumberIndex >= 0 ? accountData[phoneNumberIndex] : "";
            String checkAcctNum = checkingAccountIndex >= 0 ? accountData[checkingAccountIndex] : "";
            double checkStart = checkingBalanceIndex >= 0 ? Double.parseDouble(accountData[checkingBalanceIndex]) : 0.0;
            String savAcctNum = savingsAccountIndex >= 0 ? accountData[savingsAccountIndex] : "";
            double savStart = savingsBalanceIndex >= 0 ? Double.parseDouble(accountData[savingsBalanceIndex]) : 0.0;
            String creditAcctNum = creditAccountIndex >= 0 ? accountData[creditAccountIndex] : "";
            double creditLim = creditMaxIndex >= 0 ? Double.parseDouble(accountData[creditMaxIndex]) : 0.0;
            double creditStart = creditBalanceIndex >= 0 ? Double.parseDouble(accountData[creditBalanceIndex]) : 0.0;

            // Create Customer and Accounts
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
        System.out.println("Error: " + e.getMessage());
    }

    return customerList;
}

// Method to handle splitting lines with quoted fields
private static String[] splitLine(String line) {
    List<String> result = new ArrayList<>();
    StringBuilder currentField = new StringBuilder();
    boolean inQuotes = false;

    for (char c : line.toCharArray()) {
        if (c == '"') {
            inQuotes = !inQuotes; // Toggle quotes flag
        } else if (c == ',' && !inQuotes) {
            result.add(currentField.toString().trim());
            currentField.setLength(0); // Clear the current field
        } else {
            currentField.append(c);
        }
    }
    // Add the last field
    result.add(currentField.toString().trim());

    return result.toArray(new String[0]);
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
