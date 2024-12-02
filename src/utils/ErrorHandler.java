package src.utils;

import java.util.List;
import java.util.Scanner;

public class ErrorHandler {
  public static Account getValidAccount(Scanner scnr, Customer customer) {
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

  public static Customer getValidCustomer(Scanner scnr, List<Customer> customers) {
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

  public static Account getAccount(Customer curr, String _acctType) {
    if (curr == null) {
      return null;
    }
    String acctType = _acctType.toLowerCase();
    for (Account temp : curr.accounts) {
      if (temp.getType().equals(acctType)) {
        return temp;
      }
    }
    return null;
  }

  private static boolean checkDayToMonth(String day, String month) {
    int dayNum = Integer.parseInt(day);
    int monthNum = Integer.parseInt(month);
    if (monthNum == 1 || monthNum == 3 || monthNum == 5 || monthNum == 7 || monthNum == 8 || monthNum == 10
        || monthNum == 12) {
      if (dayNum > 31) {
        return false;
      }
    } else if (monthNum == 4 || monthNum == 6 || monthNum == 9 || monthNum == 11) {
      if (dayNum > 30) {
        return false;
      }
    } else if (monthNum == 2) {
      if (dayNum > 29) {
        return false;
      }
    }
    return true;
  }

  private static String monthStr(String monthNum) {
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

  public static String checkDOB(Scanner scnr, String str) {
    while (true) {
      if (str.length() == 6 && !hasLetter(str)) {
        String day = str.substring(0, 2);
        String month = str.substring(2, 4);

        if (isValidMonth(month)) {
          if (checkDayToMonth(day, month)) {
            month = monthStr(month);
            return String.format("%s-%s-%s", day, month, str.substring(4));
          } else {
            System.out.println("The date you inputted is impossible for the given month, try again.\n");
          }
        } else {
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

  public static String checkNum(Scanner scnr, String str) {
    while (hasLetter(str) == true || str.length() != 10) {
      System.out.println("Please enter a valid number:\n");
      str = scnr.nextLine();
    }
    str = str.trim();
    return String.format("(%s) %s-%s",
        str.substring(0, 3),
        str.substring(3, 6),
        str.substring(6));
  }

  public static String checkName(Scanner scnr, String str) {
    while (hasNumber(str) == true) {
      System.out.println("Please enter a valid name:\n");
      str = scnr.nextLine();
    }
    str = str.trim();
    Character.toUpperCase(str.charAt(0));
    return str;
  }

  public static String checkCreditScore(Scanner scnr, String _creditScore) {
    int creditScore = Integer.parseInt(_creditScore);
    while (creditScore < 0 || creditScore > 850 || hasLetter(_creditScore) == true) {
      System.out.println("Enter a valid credit score:\n");
      creditScore = Integer.parseInt(scnr.nextLine());
    }
    return Integer.toString(creditScore);
  }

  private static boolean hasNumber(String str) {
    for (char c : str.toCharArray()) {
      if (Character.isDigit(c)) {
        return true;
      }
    }
    return false;
  }

  private static boolean hasLetter(String num) {
    boolean valid = false;
    for (char c : num.toCharArray()) {
      if (Character.isDigit(c)) {
        valid = false;
      } else {
        return true;
      }
    }
    return valid;
  }

  public static Customer isValidCustomer2(String firstName, String lastName, List<Customer> customers) {
    for (Customer temp : customers) {
      if (temp.getFirstName().equals(firstName) && temp.getLastName().equals(lastName)) {
        return temp;
      }
    }
    return null;
  }

  public static Customer isValidCustomer(String name, List<Customer> customers) {
    System.out.println(name);
    if (name.contains(" ") && name.length() > 1) {
      String[] fullName = name.split(" ");
      for (Customer temp : customers) {
        if (temp.getFirstName().equals(fullName[0]) && temp.getLastName().equals(fullName[1])) {
          return temp;
        }

      }
    }

    return null;
  }

  /**
   * Searches requested customer from list
   * 
   * @param id
   * @param customers
   * @return
   */
  public static Customer searchCustomer(String id, List<Customer> customers) {
    for (Customer temp : customers) {
      if (temp.getID().equals(id)) {
        return temp;
      }
    }
    return null;
  }
}
