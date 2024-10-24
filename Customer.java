import java.util.ArrayList;
import java.util.List;

public abstract class Customer {
  protected List<Account> accounts;
  private String id;
  private String firstName;
  private String lastName;
  private String dob;
  private String address;
  private String phoneNum;

  public Customer(String id, String firstName, String lastName, String dob, String address, String phoneNumber) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.dob = dob;
    this.address = address;
    this.phoneNum = phoneNumber;
    this.accounts = new ArrayList<>();
  }

  public abstract void showCustomerDetails();

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getID() {
    return id;
  }

  public String getDOB() {
    return dob;
  }

  public String getPhoneNum() {
    return phoneNum;
  }

  public String getAddress() {
    return address;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public void setID(String id) {
    this.id = id;
  }

  public void setPhoneNum(String phoneNum) {
    this.phoneNum = phoneNum;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public void setDOB(String dob) {
    this.dob = dob;
  }

  public void addAccount(Account account) {
    accounts.add(account);
  }

  public Account getAccount(String acctNum) {
    for (Account temp : accounts) {
      if (temp.getAccountNumber().equals(acctNum)) {
        return temp;
     }

    }
    return null;
  }

}
