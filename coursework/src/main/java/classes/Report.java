package classes;

import javax.persistence.*;

/**
 * @author Осипцов Никита, группа 0305
 *	<p>Класс отчёта</p>
 */

@Entity
@Table(name = "report")
public class Report implements TableFriendly {
	@Id
	@Column(name = "report_id")
	private int id;
	@OneToOne(targetEntity = Car.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "car_id")
	private Car car;
	@OneToOne(targetEntity = Worker.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "person_id")
	private Worker worker;
	@Column(name = "repair_date")
	private String repairDate;
	
	public Report() {};
	public Report(int _id) {id =  Integer.parseInt("3" + _id);}
	
	public int GetId() { return id;}
	public void SetId(int _id) {id = _id; }
	
	public Car GetCar() { return car; }
	public void SetCar(Car _car) { car = _car; }
	
	public Worker GetWorker() { return worker; }
	public void SetWorker(Worker _worker) { worker = _worker; }
	
	public String GetDate() { return repairDate; }
	public boolean SetDate(int day, int month, int year) {
		boolean flag = (day > 0) && (day < 32) && (month > 0) && (month < 13) && (year > 1950);
		if(flag) 
			repairDate = day + "." + month + "." + year;
		return flag;
	}
	
	@Override
	public String toString() {return "'" + repairDate + "'"; }

	@Override
	public void remove() { }
	
	@Override
	public Object[] toRow() {
		var result = new Object[4];
		
		result[0] = Integer.valueOf(id);
		if(car != null) result[1] = Integer.valueOf(car.GetId());
		if(worker != null) result[2] = Integer.valueOf(worker.GetId());
		result[3] = repairDate;
		
		return result;
	}
}
