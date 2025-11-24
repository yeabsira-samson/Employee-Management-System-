package abstractEmployee;




public abstract class Employeeinf {
	protected  String ID;
    protected  String firstName;
    protected  String lastName;
    protected  String phoneNumber;
    protected  String department;
    protected  String position;
    protected String gender;
    protected  double salary;
    

    public abstract void setId(String ID);
    public abstract void setFirstName (String firstName);
    public abstract void setLastName(String lastName);
    public abstract void setPhoneNumber(String phoneNumber);
    public abstract void setDepartment(String department);
    public abstract void setPosition(String position);
    public abstract void setGender(String gender);
   
    public abstract void getId(String ID);
    public abstract void getFirstName (String firstName);
    public abstract void getLastName(String lastName);
    public abstract void getPhoneNumber(String phoneNumber);
    public abstract void getDepartment(String department);
    public abstract void getPosition(String position);
    public abstract void getGender(String gender);
}
