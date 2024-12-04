package src.utils;

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

  /**
   * Access first name
   * 
   * @return firstName
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * access last name
   * 
   * @return lastName
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * Access ID
   * 
   * @return id
   */
  public String getID() {
    return id;
  }

  /**
   * Access DOB
   * 
   * @return dob
   */
  public String getDOB() {
    return dob;
  }

  /**
   * Access phone number
   * 
   * @return phone number
   */
  public String getPhoneNum() {
    return phoneNum;
  }

  /**
   * Access address
   * 
   * @return address
   */
  public String getAddress() {
    return address;
  }

  /**
   * Modify first name
   * 
   * @param firstName
   */
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  /**
   * Modify last name
   * 
   * @param lastName
   */
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  /**
   * Modify ID
   * 
   * @param id
   */
  public void setID(String id) {
    this.id = id;
  }

  /**
   * Modify phone number
   * 
   * @param phoneNum
   */
  public void setPhoneNum(String phoneNum) {
    this.phoneNum = phoneNum;
  }

  /**
   * Modify address
   * 
   * @param address
   */
  public void setAddress(String address) {
    this.address = address;
  }

  /**
   * Modify DOB
   * 
   * @param dob
   */
  public void setDOB(String dob) {
    this.dob = dob;
  }

  /**
   * Add account
   * 
   * @param account
   */
  public void addAccount(Account account) {
    accounts.add(account);
  }

  /**
   * Find and get account
   * 
   * @param acctNum
   * @return
   */
  public Account getAccount(String acctNum) {
    for (Account temp : accounts) {
      if (temp.getAccountNumber().equals(acctNum)) {
        return temp;
      }
    }
    return null;
  }

}
