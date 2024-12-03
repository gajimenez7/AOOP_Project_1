import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AccountTest {

    @Test
    void testDeposit() {
        Account account = new Checking(); 
        account.deposit(100.0);
        assertEquals(100.0, account.getBalance(), "Balance should update correctly.");
    }
}
