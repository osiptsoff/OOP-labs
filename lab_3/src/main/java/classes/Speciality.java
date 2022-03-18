package classes;

import java.util.ArrayList;
import javax.persistence.*;

@Entity
@Table(name = "speciality")
public class Speciality {
	
	private String name;
	private ArrayList<Integer> workersIds;
	
	@Id
	@Column(name = "spec_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	public int GetId() { return id;}
	public void SetId(int _id) { id = _id;}
	
	@Column(name = "name")
	public String GetName() { return name; }
	public void SetName(String _name) { name = _name; }
	
	@ElementCollection
	@CollectionTable(name = "workers_of_speciality", joinColumns=@JoinColumn(name="id"))
	@Column(name = "worker_id")
	public ArrayList<Integer> GetWorkersIds() { return workersIds; }
	public void SetWorkersIds(ArrayList<Integer> _workersIds) { workersIds = _workersIds; }
}
