import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class RunBank {
    public static void main(String[] args) throws FileNotFoundException {
        String line = "";
        List <Customer> customerList = new ArrayList<Customer>();
        
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
                double  creditLim = Double.parseDouble(accountData[12]);
                double creditStart = Double.parseDouble(accountData[13]);
            
                Customer customer = new Person(id,firstName,lastName,dob,address,phoneNumber);
                customerList.add(customer);

                Account checking = new Checking(checkAcctNum, checkStart);
                Account savings = new Savings(savAcctNum, savStart);
                Account credit = new Credit(creditAcctNum,creditLim,creditStart);

                customer.addAccount(credit);
                customer.addAccount(checking);
                customer.addAccount(savings);
            }
            
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
        catch (Exception e) {
            System.out.println("Error");
        }
    }
}
