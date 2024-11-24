package src.resources;
import java.io.*;
import java.util.*;

public class BankCSVHandler {

    private static final String fileName = "Bank Users.csv";
    private static Map<String, Integer> headerMap;

    static {
        headerMap = getHeaderMap(fileName);
        if (headerMap.isEmpty()) {
            throw new IllegalStateException("Error");
        }
    }


    public static List<Customer> parseFile() throws FileNotFoundException {
        List<Customer> customerList = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(fileName))) {

            if (scanner.hasNextLine()) scanner.nextLine();


            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] accountData = splitLine(line);


                String id = getValue(accountData, "Identification Number");
                String firstName = getValue(accountData, "First Name");
                String lastName = getValue(accountData, "Last Name");
                String dob = getValue(accountData, "Date of Birth");
                String address = getValue(accountData, "Address");
                String phoneNumber = getValue(accountData, "Phone Number");

                Customer customer = new Person(id, firstName, lastName, dob, address, phoneNumber);

                String checkAcctNum = getValue(accountData, "Checking Account Number");
                double checkStart = parseDouble(getValue(accountData, "Checking Starting Balance"), 0.0);
                if (!checkAcctNum.isEmpty()) customer.addAccount(new Checking(checkAcctNum, checkStart));

                String savAcctNum = getValue(accountData, "Savings Account Number");
                double savStart = parseDouble(getValue(accountData, "Savings Starting Balance"), 0.0);
                if (!savAcctNum.isEmpty()) customer.addAccount(new Saving(savAcctNum, savStart));

                String creditAcctNum = getValue(accountData, "Credit Account Number");
                double creditLimit = parseDouble(getValue(accountData, "Credit Max"), 0.0);
                double creditStart = parseDouble(getValue(accountData, "Credit Starting Balance"), 0.0);
                if (!creditAcctNum.isEmpty()) customer.addAccount(new Credit(creditAcctNum, creditLimit, creditStart));

                customerList.add(customer);
            }
        }

        return customerList;
    }
    private static String[] splitLine(String line) {
        List<String> result = new ArrayList<>();
        StringBuilder currentField = new StringBuilder();
        boolean inQuotes = false;
    
        for (char c : line.toCharArray()) {
          if (c == '"') {
            inQuotes = !inQuotes; 
          } else if (c == ',' && !inQuotes) {
            result.add(currentField.toString().trim());
            currentField.setLength(0); 
          } else {
            currentField.append(c);
          }
        }

        result.add(currentField.toString().trim());
    
        return result.toArray(new String[0]);
      }

    public static void appendUserToCSV(Customer customer) {
        String[] row = new String[headerMap.size()];
        Arrays.fill(row, ""); 


        row[headerMap.get("Identification Number")] = customer.getID();
        row[headerMap.get("First Name")] = customer.getFirstName();
        row[headerMap.get("Last Name")] = customer.getLastName();
        row[headerMap.get("Date of Birth")] = customer.getDOB();
        row[headerMap.get("Address")] = quoter(customer.getAddress());
        row[headerMap.get("Phone Number")] = customer.getPhoneNum();
        row[headerMap.get("Checking Account Number")] = customer.accounts.get(1).getAccountNumber();
        row[headerMap.get("Checking Starting Balance")] = String.valueOf(customer.accounts.get(1).getBalance());
        row[headerMap.get("Savings Account Number")] = customer.accounts.get(2).getAccountNumber();
        row[headerMap.get("Savings Starting Balance")] = String.valueOf(customer.accounts.get(2).getBalance());
        row[headerMap.get("Credit Account Number")] = customer.accounts.get(0).getAccountNumber();
        row[headerMap.get("Credit Max")] = String.valueOf(((Credit) customer.accounts.get(0)).getCreditLimit());
        row[headerMap.get("Credit Starting Balance")] = String.valueOf(customer.accounts.get(0).getBalance());
        

        // Write the row to the file
        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write(String.join(",", row) + "\n");
        } catch (IOException e) {
            System.err.println("Error appending user to CSV file: " + e.getMessage());
        }
    }

    private static String quoter(String line) {
        if (line.contains(",") || line.contains("\"") || line.contains(" ")) {
            line = line.replace("\"", "\"\"");
            return "\"" + line + "\"";
        }
        return line;
    }
    // Helper method to create a header map
    private static Map<String, Integer> getHeaderMap(String fileName) {
        Map<String, Integer> headerMap = new LinkedHashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String headerLine = reader.readLine();
            if (headerLine != null) {
                String[] headers = headerLine.split(",");
                for (int i = 0; i < headers.length; i++) {
                    headerMap.put(headers[i].trim(), i);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
        }
        return headerMap;
    }


    private static String getValue(String[] accountData, String key) {
        int index = headerMap.getOrDefault(key, -1);
        return (index >= 0 && index < accountData.length) ? accountData[index] : "";
    }

    private static double parseDouble(String value, double defaultValue) {
         return Double.parseDouble(value);
    }

  
}
