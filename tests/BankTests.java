import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import src.utils.Checking;
import src.utils.Credit;
import src.utils.Customer;
import src.utils.Saving;

import static org.junit.jupiter.api.Assertions.*;

public class BankTests {

    private Saving savingAccount;
    private Checking checkingAccount;
    private Credit creditAccount;
    private Customer customer;

    @BeforeEach
    public void setUp() {
        // Assuming Saving, Checking, and Credit constructors take account number and initial balance
        checkingAccount = new Checking(1001, 1928.58);
        savingAccount = new Saving(2001, 3582.37);
        creditAccount = new Credit(3001, -1513.57); // Assuming negative balance represents credit debt

        // Creating a Customer object with information for "Donald Duck"
        customer = new Customer("Donald", "Duck", "1313 Disneyland Dr, Anaheim, CA 92802", "(714) 781-4636");
    }

    // Test 1: Check initial balance of the checking account
    @Test
    public void testInitialCheckingBalance() {
        assertEquals(1928.58, checkingAccount.getBalance(), "Initial balance for checking should be 1928.58");
    }

    // Test 2: Deposit money into the savings account
    @Test
    public void testDepositIntoSavings() {
        savingAccount.deposit(500.0);
        assertEquals(4082.37, savingAccount.getBalance(), "Balance should be 4082.37 after depositing 500.0 into savings");
    }

    // Test 3: Withdraw money from the checking account
    @Test
    public void testWithdrawFromChecking() {
        boolean success = checkingAccount.withdraw(500.0);
        assertTrue(success, "Withdrawal of 500.0 from checking should be successful");
        assertEquals(1428.58, checkingAccount.getBalance(), "Balance should be 1428.58 after withdrawal of 500.0");
    }

    // Test 4: Attempt to withdraw more than the available balance in the checking account (should fail)
    @Test
    public void testWithdrawInsufficientFundsInChecking() {
        boolean success = checkingAccount.withdraw(2500.0);
        assertFalse(success, "Withdrawal of 2500.0 from checking should fail due to insufficient funds");
        assertEquals(1928.58, checkingAccount.getBalance(), "Balance should remain 1928.58 after failed withdrawal");
    }

    // Test 5: Check customer information for Donald Duck
    @Test
    public void testCustomerInformation() {
        assertEquals("Donald", customer.getFirstName(), "First name should be Donald");
        assertEquals("Duck", customer.getLastName(), "Last name should be Duck");
        assertEquals("1313 Disneyland Dr, Anaheim, CA 92802", customer.getAddress(), "Address should be '1313 Disneyland Dr, Anaheim, CA 92802'");
        assertEquals("(714) 781-4636", customer.getPhoneNumber(), "Phone number should be '(714) 781-4636'");
    }
}

