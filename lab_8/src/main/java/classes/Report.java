package classes;

import javax.persistence.*;

/**
 * @author Осипцов Никита, группа 0305
 *	<p>Класс отчёта</p>
 */

@Entity
@Table(name = "report")
public class Report {
	@Id
	@Column(name = "report_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@OneToOne(fetch = FetchType.LAZY)
    @MapsId
	private Car car;
	@OneToOne(fetch = FetchType.LAZY)
    @MapsId
	private Worker worker;
	@Column(name = "repair_date")
	private String repairDate;
	
	public Report() {}
	public Report(int _id) {id = _id;}
	
	public int GetId() { return id;}
	public void SetId(int _id) { id = _id;}
	
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
	public String toString() {
		return id + "||" + car.GetId() + "||" + worker.GetId() + "||" + repairDate;
	}
}
