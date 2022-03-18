package lab1;

import java.util.ArrayList;

public class Speciality {
	private String name;
	private ArrayList<Worker> workers;
	
	public void SetName(String _name) { name = _name; }
	public String GetName() { return name; }
	public void SetWorkers(ArrayList<Worker> _workers) { workers = _workers; }
	public ArrayList<Worker> GetWorkers() { return workers; }
}
