package classes;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "worker")
public class Worker extends Person {
	public boolean tasked;
	@ElementCollection
	@CollectionTable(name = "worker_specs", joinColumns=@JoinColumn(name="id"))
	@Column(name = "spec_id")
	private List<Integer> specialitiesIds;
	
	public Worker() {
		super();
		specialitiesIds = new ArrayList<Integer>();
	}
	
	public void SetSpecialitiesIds(ArrayList<Integer> _specialitiesIds) {specialitiesIds = _specialitiesIds; }
	public List<Integer> GetSpecialitiesIds() { return specialitiesIds; }
}
