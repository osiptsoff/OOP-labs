package classes;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/**
 * @author Осипцов Никита, группа 0305
 * <p>Класс владельца машины</p>
 */

@Entity
@Table(name = "owner")
public class Owner extends Person {
	@OneToMany(mappedBy = "owner",
				cascade = CascadeType.ALL,
				orphanRemoval = true)
	private List<Car> cars;
	
	public Owner() {
		super();
		cars = new ArrayList<Car>();
	}
	public Owner(int _id) {
		super(_id);
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
}
