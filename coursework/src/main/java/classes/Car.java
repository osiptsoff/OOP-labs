package classes;

import javax.persistence.*;

import org.hibernate.Hibernate;

import main.Main;

/**
 * @author Осипцов Никита, группа 0305
 * <p>Класс машины</p>
 */

@Entity
@Table(name = "car")
public class Car implements TableFriendly {
	@Id
	@Column(name = "car_id")
	private int id;
	@ManyToOne(targetEntity = Owner.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
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
	
	public Car() {};
	public Car(int _id) {id =  Integer.parseInt("1" + _id);}
	
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
		return "'" + Integer.toString(id) + "' '" + brand + "' '" + problemDescription +
				"' '" + Integer.toString(releaseYear) + "' '" + Integer.toString(mileage) + "'";
	}
	
	@Override
	public boolean equals(Object other) {
		other = Hibernate.unproxy(other);
		if(other == null) return false;
		if(other instanceof Car)
			return ((Car) other).id == id;
		else return false;
	}
	
	@Override
	public void remove() {
		for(var report : Main.megaList.get(2))
			if( ((Report)report).GetCar() != null && ((Report)report).GetCar().equals(this) )
				((Report)report).SetCar(null);
		
		if(owner != null)
			for(var ownr : Main.megaList.get(1))
				((Owner)ownr).RemoveCar(this);
	}
	
	@Override
	public Object[] toRow() {
		var result = new Object[6];
		
		result[0] = Integer.valueOf(id);
		result[1] = brand;
		result[2] = problemDescription;
		result[3] = Integer.valueOf(releaseYear);
		result[4] = Integer.valueOf(mileage);
		if(owner != null) result[5] = Integer.valueOf(owner.GetId());
		
		return result;
	}
}
