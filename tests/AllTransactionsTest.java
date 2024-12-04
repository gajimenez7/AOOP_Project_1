import src.utils.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AllTransactionsTest {

    @Test
    void testTransferInsufficientFunds() {
        Customer customer1 = new Person("1", "John", "1", "1", "1", "1"); 
        Customer customer2 = new Person("1", "Jane", "1", "1", "1", "1");

        Account fromAccount = new Checking(null, 0, null);
        fromAccount.deposit(100.0); // Initial balance
        customer1.addAccount(fromAccount);

        Account toAccount = new Checking(null, 0, null);
        customer2.addAccount(toAccount);

        // Simulate a transfer
        AllTransactions.transfer(fromAccount, toAccount, customer2, 150.0);

        assertEquals(100.0, fromAccount.getBalance(), "From account balance should remain unchanged.");
        assertEquals(0.0, toAccount.getBalance(), "To account balance should remain unchanged.");
    }
}
