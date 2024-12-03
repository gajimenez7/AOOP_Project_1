import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.ArrayList;

public class AllTransactionsTest {

    @Test
    void testTransferInsufficientFunds() {
        Customer customer1 = new Customer("John Doe"); 
        Customer customer2 = new Customer("Jane Doe");

        Account fromAccount = new Checking();
        fromAccount.deposit(100.0); // Initial balance
        customer1.addAccount(fromAccount);

        Account toAccount = new Checking();
        customer2.addAccount(toAccount);

        // Simulate a transfer
        String result = AllTransactions.transfer(fromAccount, toAccount, 150.0);

        assertEquals(100.0, fromAccount.getBalance(), "From account balance should remain unchanged.");
        assertEquals(0.0, toAccount.getBalance(), "To account balance should remain unchanged.");
        assertTrue(result.contains("Insufficient funds"), "Should return error for insufficient funds.");
    }
}
