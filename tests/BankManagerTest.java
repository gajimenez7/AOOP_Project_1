import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

public class BankManagerTest {

    @Test
    void testRetrieveCustomerByID() {
        List<Customer> customers = new ArrayList<>();
        Customer customer = new Customer("John Doe"); 
        customer.setId("2062"); // Assuming setId method
        customers.add(customer);

        Customer retrieved = BankManager.findCustomerById(customers, "2062"); 
        assertNotNull(retrieved, "Customer should be found by ID.");
        assertEquals("John Doe", retrieved.getName(), "Customer name should match.");
    }
}
