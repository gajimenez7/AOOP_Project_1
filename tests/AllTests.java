import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
    AccountTest.class,
    AllTransactionsTest.class,
    BankCSVHandlerTest.class,
    BankManagerTest.class
})
public class AllTests {}
