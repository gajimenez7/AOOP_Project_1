import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.List;

public class RunBank {
    public static void main(String[] args) throws FileNotFoundException {
        String line = "";
        try (Scanner scanner = new Scanner(new File("Bank Users.csv"))) {
        line = scanner.nextLine();
        while (scanner.hasNextLine()) {
            String[] accountData = line.split(",");
            
        }
}
    }
}
