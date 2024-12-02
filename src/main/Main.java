import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import src.utils.AllTransactions;
import src.utils.BankCSVHandler;
import src.utils.BankManager;
import src.utils.CreateAcct;
import src.utils.Customer;
import src.utils.Log;


public class Main { 

    public static void main(String[] args) throws FileNotFoundException {
        List<Customer> customers = BankCSVHandler.parseFile();

        AllTransactions.readTransactions("Transactions.csv", customers);
        Log.createFile();
        
        boolean exitFlag = false;
        while (!exitFlag) {
            String userInput = Prompt();
            switch (userInput) {
                case "1":
                    System.out.println("You Selected: Make individual transaction\n");
                    AllTransactions.transaction(customers);
                    break;
                case "2":
                    System.out.println("You Selected: Make transaction between two accounts\n");
                    AllTransactions.transferPrompt(customers);
                    break;
                case "3":
                    System.out.println("You Selected: Pay another user\n");
                    AllTransactions.payPrompt(customers);
                    break;
                case "4":
                    System.out.println("You Selected: Create an Account\n");
                    CreateAcct.accountCreation(customers);
                    break;
                case "5":
                    System.out.println("You Selected: User Info\n");
                    BankManager.bankManager(customers);
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

    }

    /**
     * Prints the prompt for user to give input
     *
     * @return user input as a String
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
        System.out.println("[5] User Info");
        System.out.println("[6] Exit");

        input = scnr.nextLine();  
        return input;
    }
}
