package lab1;

public class Car {
	private String brand;
	private String problemDescription;
	private int releaseYear;
	private int mileage;
	private Owner owner;
	
	public void SetBrand(String _brand) { brand = _brand;}
	public String GetBrand() { return brand; }
	public void SetProblemDescription(String descr) { problemDescription = descr;}
	public String GetProblemDescription() { return problemDescription; }
	public boolean SetReleaseYear(int year) {
		boolean flag = year >= 1950;
		if(flag) releaseYear = year;
		return flag;
	}
	public int GetReleaseYear() { return releaseYear; }
	
	public boolean SetMileage(int _mileage) {
		boolean flag = _mileage >= 0;
		if(flag) mileage = _mileage;
		return flag;
	}
	public int GetMileage() { return mileage; }
	
	public void SetOwner(Owner _owner) { owner = _owner;}
	public Owner GetOwner() { return owner; }
}
