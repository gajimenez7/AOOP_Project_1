package src.utils;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class CreateAcct {

  public static void accountCreation(List<Customer> customers) {
    Scanner scnr = new Scanner(System.in);

    System.out.println("Enter your first name: \n");
    String firstName = scnr.nextLine();
    firstName = ErrorHandler.checkName(scnr, firstName);

    System.out.println("Enter your last name:\n");
    String lastName = scnr.nextLine();
    lastName = ErrorHandler.checkName(scnr, lastName);

    System.out.println("Enter your address:\n");
    String address = scnr.nextLine();

    System.out.println("Enter your phone number (with no spaces or dashes):\n");
    String phoneNum = scnr.nextLine();
    phoneNum = ErrorHandler.checkNum(scnr, phoneNum);

    System.out.println("Enter your Date of Birth (in the format DDMMYY)");
    String dob = scnr.nextLine();
    dob = ErrorHandler.checkDOB(scnr, dob);

    System.out.println("What is your credit score?");
    String creditScore = scnr.nextLine();
    creditScore = ErrorHandler.checkCreditScore(scnr, creditScore);
    double creditLimit = assignCreditLimit(creditScore);

    int _newID = Integer.parseInt(highestID(customers)) + 1;
    String newID = Integer.toString(_newID);
    System.out.println("Your ID has been assigned. It is " + newID + ".\n");

    String checkingAcctID = Integer.toString(highestCheckingAcctNum(customers));
    System.out.println("Your Checking Account Number is " + checkingAcctID + ".\n");

    String savingsAcctID = Integer.toString(highestSavingsAcctNum(customers));
    System.out.println(" Your Savings Account Number is " + savingsAcctID + ".\n");

    String creditAcctID = Integer.toString(highestCreditAcctNum(customers));
    System.out.println(" Your Credit Account Number is " + creditAcctID + ".\n");
    System.out.printf("Your credit limit is $%.2f.\n", creditLimit);

    Customer newCustomer = new Person(newID, firstName, lastName, dob, address, phoneNum);
    customers.add(newCustomer);

    Account checking = new Checking(checkingAcctID, 0.00, "checking");
    Account savings = new Saving(savingsAcctID, 0.00, "savings");
    Account credit = new Credit(creditAcctID, creditLimit, 0.00, "credit");

    newCustomer.addAccount(credit);
    newCustomer.addAccount(checking);
    newCustomer.addAccount(savings);
    BankCSVHandler.appendUserToCSV(newCustomer);
  }

  private static int highestCreditAcctNum(List<Customer> customers) {
    Customer highestCustomer = customers.get(0);
    Account highestAccount = highestCustomer.accounts.get(0);
    for (Customer temp : customers) {
      if (Integer.parseInt(highestAccount.getAccountNumber()) < Integer
          .parseInt(temp.accounts.get(0).getAccountNumber())) {
        highestAccount = temp.accounts.get(0);
      }
    }
    return Integer.parseInt(highestAccount.getAccountNumber()) + 1;
  }

  private static int highestCheckingAcctNum(List<Customer> customers) {
    Customer highestCustomer = customers.get(0);
    Account highestAccount = highestCustomer.accounts.get(1);
    for (Customer temp : customers) {
      if (Integer.parseInt(highestAccount.getAccountNumber()) < Integer
          .parseInt(temp.accounts.get(1).getAccountNumber())) {
        highestAccount = temp.accounts.get(1);
      }
    }
    return Integer.parseInt(highestAccount.getAccountNumber()) + 1;
  }

  private static int highestSavingsAcctNum(List<Customer> customers) {
    Customer highestCustomer = customers.get(0);
    Account highestAccount = highestCustomer.accounts.get(2);
    for (Customer temp : customers) {
      if (Integer.parseInt(highestAccount.getAccountNumber()) < Integer
          .parseInt(temp.accounts.get(2).getAccountNumber())) {
        highestAccount = temp.accounts.get(2);
      }
    }
    return Integer.parseInt(highestAccount.getAccountNumber()) + 1;
  }

  private static String highestID(List<Customer> customers) {
    Customer highestCustomer = customers.get(0);
    for (Customer temp : customers) {
      if (Integer.parseInt(highestCustomer.getID()) < Integer.parseInt(temp.getID())) {
        highestCustomer = temp;
      }
    }
    return highestCustomer.getID();
  }

  private static double assignCreditLimit(String _creditScore) {
    double creditScore = Double.parseDouble(_creditScore);
    Random random = new Random();

    if (creditScore <= 580) {
      return random.nextInt(699 - 100) + 100;
    } else if (creditScore > 580 && creditScore < 670) {
      return random.nextInt(4999 - 700) + 700;
    } else if (creditScore >= 670 && creditScore < 740) {
      return random.nextInt(7499 - 5000) + 5000;
    } else if (creditScore >= 740 && creditScore < 800) {
      return random.nextInt(15999 - 7500) + 7500;
    } else if (creditScore >= 800 && creditScore <= 850) {
      return random.nextInt(25000 - 16000) + 16000;
    } else {
      return -1;
    }
  }
}