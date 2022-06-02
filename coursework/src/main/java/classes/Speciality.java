package classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.*;

import main.Main;

/**
 * @author Осипцов Никита, группа 0305
 *	<p>Класс специальности</p>
 */

@Entity
@Table(name = "speciality")
public class Speciality implements TableFriendly {
	@Id
	@Column(name = "spec_id")
	private int id;
	@Column(name = "name")
	private String name;
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
        name = "worker_speciality", 
        joinColumns = @JoinColumn(name = "spec_id"), 
        inverseJoinColumns = @JoinColumn(name = "person_id"))
	private List<Worker> workers;
	
	public Speciality() { workers = new ArrayList<Worker>(); }
	public Speciality(int _id) {
		id =  Integer.parseInt("4" + _id);
		workers = new ArrayList<Worker>();
		}
	
	public int GetId() { return id;}
	public void SetId(int _id) {id = _id; }
	
	public String GetName() { return name; }
	public boolean SetName(String _name) {
		if (_name != null && _name.length() > 0) {
			name = _name;
			return true;
		}
		return false;
	}
	
	public List<Worker> GetWorkers() { return workers; }
	public boolean AddWorker(Worker worker) {
		if (worker != null && !workers.contains(worker)) {
			workers.add(worker);
			return true;
		}
		else return false;
	}
	public boolean RemoveWorker(Worker worker) {
		if (worker != null && workers.remove(worker))
			return true;
		else return false;
	}
	
	@Override
	public String toString() { return "'" + Integer.toString(id) + "' '" + name +"'"; }
	
	@Override
	public boolean equals(Object other) {
		if(other == null) return false;
		if(other instanceof Speciality)
			return ((Speciality) other).id == id;
		else return false;
	}
	
	@Override
	public void cascadeRemove() {
		for(var wkr : Main.megaList.get(4))
			((Worker)wkr).RemoveSpeciality(this);
		
		workers = null;
		Main.megaList.get(3).remove(this);
	}
	
	@Override
	public boolean isRelated() { return !workers.isEmpty(); }
	
	public Object[] toRow() {
		var result = new Object[3];
		
		result[0] = Integer.valueOf(id);
		result[1] = name;
		result[2] = Arrays.toString(workers.stream().map(wkr -> wkr.GetId()).toArray());
		
		return result;
	}
}
