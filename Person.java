
public class Person extends Customer {

  public Person(String id, String firstName, String lastName, String dob, String address, String phoneNum) {
    super(id, firstName, lastName, dob, address, phoneNum);
  }

  public void showCustomerDetails() {
    System.out.println("Customer ID: " + getID());
    System.out.println("Name: " + getFirstName() + " " + getLastName());
    System.out.println("Date of Birth: " + getDOB());
    System.out.println("Address: " + getAddress());
    System.out.println("Phone Number: " + getPhoneNum());
  }

}
