package src.resources;
import java.time.LocalDate;
import java.util.ArrayList;

public class Builder {
    Account account1;
    Account account2;
    Customer customer1;
    Customer customer2;
    String transaction;
    String amount;

    Customer customer;
    Double startBalance = 0.00;
    Double endBalance;
    ArrayList<String> transactions;
    LocalDate date;

    public static Builder newInstance() {
        return new Builder();
    }

    /**
     * Set first account
     * 
     * @param account1
     */
    public Builder account1(Account a) {
        this.account1 = a;
        return this;
    }

    /**
     * Set second account
     * 
     * @param account2
     */
    public Builder account2(Account a) {
        this.account2 = a;
        return this;
    }

    /**
     * Set first customer
     * 
     * @param customer1
     */
    public Builder person1(Customer c) {
        this.customer1 = c;
        return this;
    }

    /**
     * Set second customer
     * 
     * @param customer2
     */
    public Builder person2(Customer c) {
        this.customer2 = c;
        return this;
    }

    /**
     * Set the transaction type
     * 
     * @param transaction
     */
    public Builder transaction(String transaction) {
        this.transaction = transaction;
        return this;
    }

    /**
     * Set amount in transaction made
     * 
     * @param amount
     */
    public Builder amount(String amount) {
        this.amount = amount;
        return this;
    }

    /**
     * Set account information
     * 
     * @param customer
     */
    public Builder customer(Customer customer) {
        this.customer = customer;
        return this;
    }

    /**
     * Set Starting Balance
     * 
     * @param startBalance
     */
    public Builder startBalance(Double startBalance) {
        if(this.startBalance == 0.00)
            this.startBalance = startBalance;
        return this;
    }

    /**
     * Set Ending Balance at time of file generation
     * 
     * @param endBalance
     */
    public Builder endBalance(Double endBalance) {
        this.endBalance = endBalance;
        return this;
    }

    public Log buildLog() {
        return new Log(this);
    }

    public UserTransaction buildTransaction() {
        return new UserTransaction(this);
    }
}