package lab1;

import java.util.ArrayList;

public class Owner extends Person {
	private ArrayList<Car> cars;
	
	public Owner() { super(); }
	public void SetCars(ArrayList<Car> _cars) { cars = _cars;}
	public ArrayList<Car> GetCars() { return cars; }
}
