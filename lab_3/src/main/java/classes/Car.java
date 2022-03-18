package classes;

import javax.persistence.*;

@Entity
@Table(name = "car")
public class Car {
	private int ownerId;
	private String brand;
	private String problemDescription;
	private int releaseYear;
	private int mileage;
	
	@Id
	@Column(name = "car_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	public int GetId() { return id;}
	public void SetId(int _id) { id = _id;}
	
	@Column(name = "owner_id")
	public int GetOwnerId() { return ownerId; }
	public void SetOwnerId(int _ownerId) { ownerId = _ownerId;}
	
	@Column(name = "brand")
	public String GetBrand() { return brand; }
	public void SetBrand(String _brand) { brand = _brand;}
	
	@Column(name = "problem_description")
	public String GetProblemDescription() { return problemDescription; }
	public void SetProblemDescription(String descr) { problemDescription = descr;}
	
	@Column(name = "release_year")
	public int GetReleaseYear() { return releaseYear; }
	public boolean SetReleaseYear(int year) {
		boolean flag = year >= 1950;
		if(flag) releaseYear = year;
		return flag;
	}
	
	@Column(name = "mileage")
	public int GetMileage() { return mileage; }
	public boolean SetMileage(int _mileage) {
		boolean flag = _mileage >= 0;
		if(flag) mileage = _mileage;
		return flag;
	}
}
