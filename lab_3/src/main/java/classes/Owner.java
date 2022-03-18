package classes;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "owner")
public class Owner extends Person {
	@ElementCollection
	@CollectionTable(name = "owned_cars", joinColumns=@JoinColumn(name="id"))
	@Column(name = "car_id")
	private List<Integer> carsIds;
	
	public Owner() {
		super();
		carsIds = new ArrayList<Integer>();
	}
	
	public List<Integer> GetCarsIds() { return carsIds; }
	public void SetCarsIds(ArrayList<Integer> _carsIds) { carsIds = _carsIds;}
	
	
}
