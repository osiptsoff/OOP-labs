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
		if (speciality != null && specialities.remove(speciality))
			return true;
		else return false;
	}

	@Override
	public void cascadeRemove() {
		specialities.clear();
		for(var spec : Main.megaList.get(3)) 
			if( ((Speciality)spec).RemoveWorker(this) && ((Speciality)spec).GetWorkers().isEmpty())
				specialities.add((Speciality)spec);
		Main.megaList.get(3).removeAll(specialities);
		
		specialities = null;
		Main.megaList.get(4).remove(this);
	}
	
	@Override
	public boolean isRelated() { return true; }
	
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
