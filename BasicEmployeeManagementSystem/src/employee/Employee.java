package employee;

import java.util.ArrayList;
import java.util.List;
import abstractEmployee.Employeeinf;

public class Employee extends Employeeinf {
	protected List<Employee> employees;
	 public Employee() {
	        employees = new ArrayList<>();
	       
	    }

	    public void setId(String ID) {
	        this.ID = ID;
	    }

	    public void setFirstName(String firstName) {
	        this.firstName = firstName;
	    }

	    public void setLastName(String lastName) {
	        this.lastName = lastName;
	    }

	    public void setPhoneNumber(String phoneNumber) {
	        this.phoneNumber = phoneNumber;
	    }

	    public void setDepartment(String department) {
	        this.department = department;
	    }

	    public void setPosition(String position) {
	        this.position = position;
	    }

	    public void setGender(String gender) {
	        this.gender = gender;
	    }

	    public void setSalary(double salary) {
	        this.salary = salary;
	    }

	    public String getId() {
	        return ID;
	    }

	    public String getFirstName() {
	        return firstName;
	    }

	    public String getLastName() {
	        return lastName;
	    }

	    public String getPhoneNumber() {
	        return phoneNumber;
	    }

	    public String getDepartment() {
	        return department;
	    }

	    public String getPosition() {
	        return position;
	    }

	    public String getGender() {
	        return gender;
	    }

	    public double getSalary() {
	        return salary;
	    }
   
}

