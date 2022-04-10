package classes;

import java.util.ArrayList;
import javax.persistence.*;

/**
 * @author Осипцов Никита, группа 0305
 *	<p>Класс специальности</p>
 */

@Entity
@Table(name = "speciality")
public class Speciality {
	@Id
	@Column(name = "spec_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "name")
	private String name;
	@ManyToMany(mappedBy = "specialities")
	private ArrayList<Worker> workers;
	
	public Speciality() {}
	public Speciality(int _id) {id = _id;}
	
	public int GetId() { return id;}
	public void SetId(int _id) { id = _id;}
	
	public String GetName() { return name; }
	public boolean SetName(String _name) {
		if (_name != null && _name.length() > 0 && !_name.contains("||")) {
			name = _name;
			return true;
		}
		return false;
	}
	
	public ArrayList<Worker> GetWorkers() { return workers; }
	public boolean AddWorker(Worker worker) {
		if (worker != null && !workers.contains(worker)) {
			workers.add(worker);
			return true;
		}
		else return false;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
