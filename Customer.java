import java.util.ArrayList;
import java.util.List;

public abstract class Customer extends Person{
    protected List<Account> accounts;

    public Customer(String firstName, String lastName, String id, String phoneNum, String address){
        super(firstName,lastName,id,phoneNum,address);
        this.accounts = new ArrayList<>();
    }

    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public String getID(){
        return id;
    }
    public String getPhoneNum(){
        return phoneNum;
    }
    public String getAddress(){
        return address;
    }
    public void addAccount(Account account){
        accounts.add(account);
    }

    public Account getAccount(String acctNum){
        for(Account temp :accounts){
            if(temp.getAccountNumber().equals(acctNum)){
                return temp;
            }
        }
        return null;
    }

    public void listAccounts(){
        System.out.println("All accounts for " + firstName + " " + lastName + ":\n");
        for(Account temp: accounts){
            System.out.println("Account " + temp.getAccountNumber() + "\nBalance "+ temp.getBalance());
        }
    }


}
