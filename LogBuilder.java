
public class LogBuilder {
    private Account account1;
    private Account account2;
    private Customer customer1;
    private Customer customer2;
    private String transaction;
    private String amount;

    public static LogBuilder newInstance() {
        return new LogBuilder();
    }

    /**
     * Set first account
     * 
     * @param account1
     */
    public LogBuilder setAccount1(Account a) {
        this.account1 = a;
        return this;
    }

    /**
     * Set second account
     * 
     * @param account2
     */
    public LogBuilder setAccount2(Account a) {
        this.account2 = a;
        return this;
    }

    /**
     * Set first customer
     * 
     * @param customer1
     */
    public LogBuilder setPerson1(Customer c) {
        this.customer1 = c;
        return this;
    }

    /**
     * Set second customer
     * 
     * @param customer2
     */
    public LogBuilder setPerson2(Customer c) {
        this.customer2 = c;
        return this;
    }

    /**
     * Set the transaction type
     * 
     * @param transaction
     */
    public LogBuilder setTransaction(String transaction) {
        this.transaction = transaction;
        return this;
    }

    /**
     * Set amount in transaction made
     * 
     * @param amount
     */
    public LogBuilder setAmount(String amount) {
        this.amount = amount;
        return this;
    }

    public Log build() {
        return new Log(account1, account2, customer1, customer2, transaction, amount);
    }
}