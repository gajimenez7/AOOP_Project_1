import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
    @Test
    public void testDeposit() {
        double initialBalance = balance.getBalance();
        saving.deposit(200.00);
        assertEquals(initialBalance + 200.00, saving.getBalance(), "Balance should be updated after deposit.");
    }