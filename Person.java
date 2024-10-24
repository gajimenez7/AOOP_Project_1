
/**creates a person class that has info of customers
 * @author Stephanie Reyes
 */
public class Person extends Customer {

    /**creates a person object
     */
    public Person(String id, String firstName, String lastName,String dob, String address, String phoneNum){
        super(id, firstName, lastName,dob, address, phoneNum);
    }

    /**displays info about customer
     * @return void
     */
    public void showCustomerDetails() {
        System.out.println("Customer ID: " + getID());
        System.out.println("Name: " + getFirstName() + " " + getLastName());
        System.out.println("Date of Birth: " + getDOB());
        System.out.println("Address: " + getAddress());
        System.out.println("Phone Number: " + getPhoneNum());
    }
    
}
