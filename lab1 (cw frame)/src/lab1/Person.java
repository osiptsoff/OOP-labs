package lab1;

class Person {
	protected String name;
	protected String lastName;
	protected String phoneNumber;
	
	public boolean SetName(String _name) {
		boolean flag = _name.matches("^[a-zA-Z]*$") && _name.length() > 1 && _name.length() < 50; 
		if(flag) {
			name = _name.toLowerCase();
			name = name.substring(0, 1).toUpperCase() + name.substring(1);
		}
		
		return flag;
	}
	
	public String GetName() {return name;}
	
	public boolean SetLastName(String _lastName) {
		boolean flag = _lastName.matches("^[a-zA-Z]*$") && _lastName.length() > 1 && _lastName.length() < 50;
		if(flag) {
			lastName = _lastName.toLowerCase();
			lastName = _lastName.substring(0, 1).toUpperCase() + _lastName.substring(1);
		}
		
		return flag;
	}
	
	public String GetLastName() {return lastName;}
	
	public boolean SetPhoneNumber(String _phoneNumber) {
		boolean flag = _phoneNumber.charAt(0) == '+' && _phoneNumber.substring(1).matches("^[0-9]*$");
		if(flag) 
			phoneNumber = _phoneNumber;
		return flag;
	}
	
	public String GetPhoneNumber() {return phoneNumber;}
}
