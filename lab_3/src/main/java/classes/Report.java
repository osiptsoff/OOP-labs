package classes;

import javax.persistence.*;

@Entity
@Table(name = "report")
public class Report {
	@Id
	@Column(name = "report_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "car_id")
	private int carId;
	@Column(name = "worker_id")
	private int workerId;
	@Column(name = "repair_date")
	private String repairDate;
	
	public int GetId() { return id;}
	public void SetId(int _id) { id = _id;}
	
	public int GetCarId() { return carId; }
	public void SetCarId(int _carId) { carId = _carId; }
	
	public int GetWorkerId() { return workerId; }
	public void SetWorkerId(int _workerId) { workerId = _workerId; }
	
	public String GetDate() { return repairDate; }
	public boolean SetDate(int day, int month, int year) {
		boolean flag = day > 0 && day < 32 && month > 0 && month < 13 && year > 1950;
		if(flag) 
			repairDate = day + "." + month + "." + year;
		return flag;
	}
}
