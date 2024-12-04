import org.junit.jupiter.api.Test;
import src.utils.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

public class BankManagerTest {

    @Test
    void testRetrieveCustomerByID() {
        List<Customer> customers = new ArrayList<>();
        Customer customer = new Person("1", "John", "1", "1", "1", "1"); 
        customer.setID("2062"); // Assuming setId method
        customers.add(customer);

        // Update Bank Manager
        Customer retrieved = new Person(null, null, null, null, null, null); 
        //BankManager.findCustomerById(customers, "2062")
        assertNotNull(retrieved, "Customer should be found by ID.");
        assertEquals("John", retrieved.getFirstName(), "Customer name should match.");
        assertEquals("Doe", retrieved.getLastName(), "Customer name should match.");
    }
}
