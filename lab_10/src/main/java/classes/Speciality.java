package classes;

import java.util.ArrayList;
import javax.persistence.*;

/**
 * @author ������� ������, ������ 0305
 *	<p>����� �������������</p>
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
	
	public Speciality(int _id) {
		id =  Integer.parseInt("4" + _id);
		workers = new ArrayList<Worker>();
		}
	
	public int GetId() { return id;}
	
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
	public boolean RemoveWorker(Worker worker) {
		if (worker != null && workers.contains(worker)) {
			workers.remove(worker);
			return true;
		}
		else return false;
	}
}
