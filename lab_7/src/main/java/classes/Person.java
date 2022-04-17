package classes;

import javax.persistence.*;

/**
 * @author Осипцов Никита, группа 0305
 *	<p>Суперкласс для сущностей, представляющих людей</p>
 */

@MappedSuperclass
class Person {	
	@Id
	@Column(name = "person_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected int id;
	@Column(name = "name")
	protected String name;
	@Column(name = "last_name")
	protected String lastName;
	@Column(name = "phone_number")
	protected String phoneNumber;
	
	public Person() {}
	public Person(int _id) {id = _id;}
	
	public int GetId() { return id;}
	public void SetId(int _id) { id = _id;}
	
	public String GetName() {return name;}
	public boolean SetName(String _name) {
		if(_name == null) return false;
		_name = _name.toLowerCase();
		boolean flag = _name.matches("^[a-zа-я]*$") && _name.length() > 1 && _name.length() < 50; 
		if(flag) name = _name.substring(0, 1).toUpperCase() + _name.substring(1);
		
		return flag;
	}
	
	public String GetLastName() {return lastName;}
	public boolean SetLastName(String _lastName) {
		if(_lastName == null) return false;
		_lastName = _lastName.toLowerCase();
		boolean flag = _lastName.matches("^[a-zа-я]*$") && _lastName.length() > 1 && _lastName.length() < 50;
		if(flag) lastName = _lastName.substring(0, 1).toUpperCase() + _lastName.substring(1);
		
		return flag;
	}
	
	public String GetPhoneNumber() {return phoneNumber;}
	public boolean SetPhoneNumber(String _phoneNumber) {
		if(_phoneNumber == null) return false;
		boolean flag = _phoneNumber.charAt(0) == '+' && _phoneNumber.substring(1).matches("^[0-9]*$");
		if(flag) phoneNumber = _phoneNumber;
		return flag;
	}
	
	@Override
	public String toString() {
		return id + "||" +  name + "||" + lastName + "||" + phoneNumber;
	}
}
