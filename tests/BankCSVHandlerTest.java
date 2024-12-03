import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class BankCSVHandlerTest {

    @Test
    void testParseFileValid() throws Exception {
        List<Customer> customers = BankCSVHandler.parseFile();
        assertNotNull(customers, "Parsed customer list should not be null.");
        assertFalse(customers.isEmpty(), "Customer list should contain data.");
    }

    @Test
    void testParseFileMalformed() {
        assertThrows(IllegalStateException.class, () -> {
            BankCSVHandler.parseFile("InvalidFile.csv");
        }, "Should throw exception for malformed CSV.");
    }
}
