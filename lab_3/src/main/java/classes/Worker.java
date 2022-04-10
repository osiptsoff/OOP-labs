package classes;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

/**
 * @author Осипцов Никита, группа 0305
 * <p>Класс рабочего</p>
 */

@Entity
@Table(name = "worker")
public class Worker extends Person {
	public boolean tasked;
	@ManyToMany(fetch = FetchType.LAZY ,cascade = CascadeType.ALL)
    @JoinTable(
        name = "worker_speciality", 
        joinColumns = @JoinColumn(name = "person_id"), 
        inverseJoinColumns = @JoinColumn(name = "spec_id"))
	private List<Speciality> specialities;
	
	public Worker() {
		super();
		specialities = new ArrayList<Speciality>();
	}
	public Worker(int _id) {
		super(_id);
		specialities = new ArrayList<Speciality>();
	}
	
	public List<Speciality> GetSpecialities() { return specialities; }
	public boolean AddSpeciality(Speciality speciality) {
		if (speciality != null && !specialities.contains(speciality)) {
			specialities.add(speciality);
			return true;
		}
		else return false;
	}
	
}
