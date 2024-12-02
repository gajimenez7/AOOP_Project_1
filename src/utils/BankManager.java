package src.utils;

import java.util.List;
import java.util.Scanner;

public class BankManager {
  /**
   * Simulates a bank manager option
   * 
   * @param customers
   */
  public static void bankManager(List<Customer> customers) {
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
          Customer curr = ErrorHandler.getValidCustomer(scnr, customers);
          curr.showCustomerDetails();
        } else {
          System.out.println("Enter ID number: ");
          String id = scnr.nextLine();
          Customer cust = ErrorHandler.searchCustomer(id, customers);
          while (cust == null) {
            System.out.println("Not a valid ID. Try again");
            id = scnr.nextLine();
            cust = ErrorHandler.searchCustomer(id, customers);
          }

        }

        break;
      case "2":
        Customer showAcctInfo = ErrorHandler.getValidCustomer(scnr, customers);

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

}
