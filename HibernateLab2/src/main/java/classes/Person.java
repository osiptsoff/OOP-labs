package classes;

import javax.persistence.*;

@MappedSuperclass
class Person {	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected int id;
	@Column(name = "name")
	protected String name;
	@Column(name = "last_name")
	protected String lastName;
	@Column(name = "phone_number")
	protected String phoneNumber;
	
	public int GetId() { return id;}
	public void SetId(int _id) { id = _id;}
	
	public String GetName() {return name;}
	public boolean SetName(String _name) {
		boolean flag = _name.matches("^[a-zA-Z]*$") && _name.length() > 1 && _name.length() < 50; 
		if(flag) {
			name = _name.toLowerCase();
			name = name.substring(0, 1).toUpperCase() + name.substring(1);
		}
		
		return flag;
	}
	
	public String GetLastName() {return lastName;}
	public boolean SetLastName(String _lastName) {
		boolean flag = _lastName.matches("^[a-zA-Z]*$") && _lastName.length() > 1 && _lastName.length() < 50;
		if(flag) {
			lastName = _lastName.toLowerCase();
			lastName = _lastName.substring(0, 1).toUpperCase() + _lastName.substring(1);
		}
		
		return flag;
	}
	
	public String GetPhoneNumber() {return phoneNumber;}
	public boolean SetPhoneNumber(String _phoneNumber) {
		boolean flag = _phoneNumber.charAt(0) == '+' && _phoneNumber.substring(1).matches("^[0-9]*$");
		if(flag) 
			phoneNumber = _phoneNumber;
		return flag;
	}
}
