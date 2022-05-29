package classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.persistence.*;

import main.Main;

/**
 * @author Осипцов Никита, группа 0305
 * <p>Класс владельца машины</p>
 */

@Entity
@Table(name = "owner")
public class Owner extends Person implements TableFriendly {
	@OneToMany(	targetEntity = Car.class,
				mappedBy = "owner",
				cascade = CascadeType.ALL)
	private List<Car> cars;
	
	public Owner() {
		super();
		cars = new ArrayList<Car>();
	}
	public Owner(int _id) {
		super("2", _id);
		cars = new ArrayList<Car>();
	}
	
	public List<Car> GetCars() { return cars; }
	public boolean AddCar(Car car) {
		if (car != null && !cars.contains(car)) {
			cars.add(car);
			return true;
		}
		else return false;
	}
	public boolean RemoveCar(Car car) {
		if (car != null) {
			cars.remove(car);
			return true;
		}
		else return false;
	}

	@Override
	public void remove() {
		for(var car : Main.megaList.get(0))
			if(((Car)car).GetOwner().equals(this))
				((Car)car).SetOwner(null);
		cars = null;
	}
	
	@Override
	public Object[] toRow() {
		var result = new Object[5];
		
		result[0] = Integer.valueOf(id);
		result[1] = name;
		result[2] = lastName;
		result[3] = phoneNumber;
		result[4] = Arrays.toString(cars.stream().map(car -> car.GetId()).toArray());
		
		return result;
	}
}
