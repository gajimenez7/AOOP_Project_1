
public class LogBuilder {
    Account account1;
    Account account2;
    Customer customer1;
    Customer customer2;
    String transaction;
    String amount;

    public static LogBuilder newInstance() {
        return new LogBuilder();
    }

    /**
     * Set first account
     * 
     * @param account1
     */
    public LogBuilder account1(Account a) {
        this.account1 = a;
        return this;
    }

    /**
     * Set second account
     * 
     * @param account2
     */
    public LogBuilder account2(Account a) {
        this.account2 = a;
        return this;
    }

    /**
     * Set first customer
     * 
     * @param customer1
     */
    public LogBuilder person1(Customer c) {
        this.customer1 = c;
        return this;
    }

    /**
     * Set second customer
     * 
     * @param customer2
     */
    public LogBuilder person2(Customer c) {
        this.customer2 = c;
        return this;
    }

    /**
     * Set the transaction type
     * 
     * @param transaction
     */
    public LogBuilder transaction(String transaction) {
        this.transaction = transaction;
        return this;
    }

    /**
     * Set amount in transaction made
     * 
     * @param amount
     */
    public LogBuilder amount(String amount) {
        this.amount = amount;
        return this;
    }

    public Log build() {
        return new Log(this);
    }
}