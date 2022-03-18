package lab1;

import java.util.ArrayList;

public class Worker extends Person {
	private ArrayList<Speciality> specialities;
	public boolean tasked;
	
	public Worker() { super(); }
	public void SetSpecialities(ArrayList<Speciality> _specialities) {specialities = _specialities; }
	public ArrayList<Speciality> GetSpecialities() { return specialities; }
}
