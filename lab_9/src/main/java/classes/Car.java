package classes;

import javax.persistence.*;

/**
 * @author Осипцов Никита, группа 0305
 * <p>Класс машины</p>
 */

@Entity
@Table(name = "car")
public class Car {
	@Id
	@Column(name = "car_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "person_id")
	private Owner owner;
	@Column(name = "brand")
	private String brand;
	@Column(name = "problem_description")
	private String problemDescription;
	@Column(name = "release_year")
	private int releaseYear;
	@Column(name = "mileage")
	private int mileage;
	
	public Car() {}
	public Car(int _id) {id = _id;}
	
	public int GetId() { return id;}
	public void SetId(int _id) { id = _id;}
	
	public Owner GetOwner() { return owner; }
	public void SetOwner(Owner _owner) { owner = _owner;}
	
	public String GetBrand() { return brand; }
	public boolean SetBrand(String _brand) {
		if (_brand != null && _brand.length() > 0 && _brand.matches("^[a-zA-Zа-яА-Я]*$")){
			brand = _brand;
			return true;
		}
		return false;
	}
	
	public String GetProblemDescription() { return problemDescription; }
	public boolean SetProblemDescription(String descr) {
		if (descr != null && descr.length() > 0 && !descr.contains("||")) {
			problemDescription = descr;
			return true;
		}
		return false;
	}
	
	public int GetReleaseYear() { return releaseYear; }
	public boolean SetReleaseYear(String year) {
		int _year = 0;
		try { 
			_year = Integer.parseInt(year);
			if(_year >= 1950) releaseYear = _year; 
		}
		catch (Exception e) { return false; } 
		
		return _year >= 1950;
	}
	
	public int GetMileage() { return mileage; }
	public boolean SetMileage(String _mileage) {
		try { int mileage = Integer.parseInt(_mileage); this.mileage = mileage; }
		catch (Exception e) { return false; } 
		
		return true;
	}
	
	@Override
	public String toString() {
		return id + "||" + brand + "||" + problemDescription + "||" + releaseYear + "||" + mileage;
	}
}
