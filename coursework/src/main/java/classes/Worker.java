package classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.*;

import main.Main;

/**
 * @author Осипцов Никита, группа 0305
 * <p>Класс рабочего</p>
 */

@Entity
@Table(name = "worker")
public class Worker extends Person implements TableFriendly {
	@ManyToMany(mappedBy = "workers")
	private List<Speciality> specialities;
	
	public Worker() { specialities = new ArrayList<Speciality>(); }
	public Worker(int _id) {
		super("5", _id);
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
	public boolean RemoveSpeciality(Speciality speciality) {
		if (speciality != null) {
			specialities.remove(speciality);
			return true;
		}
		else return false;
	}

	@Override
	public void remove() {
		for(var report : Main.megaList.get(2))
			if(((Report)report).GetWorker() != null && ((Report)report).GetWorker().equals(this) )
				((Report)report).SetWorker(null);
		
		for(var spec : Main.megaList.get(3))
			((Speciality)spec).RemoveWorker(this);
		specialities = null;
	}
	
	@Override
	public Object[] toRow() {
		var result = new Object[5];
		
		result[0] = Integer.valueOf(id);
		result[1] = name;
		result[2] = lastName;
		result[3] = phoneNumber;
		result[4] = Arrays.toString(specialities.stream().map(spec -> spec.GetId()).toArray());
		
		return result;
	}
}
