import java.util.List;

public class Person {
    protected String firstName;
    protected  String lastName;
    protected  String id;
    protected String dob;
    protected  String phoneNum;
    protected  String address;

    public Person(String id, String firstName, String lastName,String dob, String address, String phoneNum){
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        this.phoneNum = phoneNum;
        this.address = address;
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
    

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    public void setLastName(String lastName){
        this.lastName=lastName;
    }
    public void setID(String id){
        this.id = id;
    }
    public void setPhoneNum(String phoneNum){
        this.phoneNum = phoneNum;
    }
    public void setAddress(String address){
        this.address = address;
    }
    public void setDOB(String dob){
        this.dob = dob;
    }
}
