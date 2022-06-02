package classes;

import javax.persistence.*;

import main.Main;

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
	@Column(name = "carinfo")
	private String carInfo;
	@Column(name = "workerinfo")
	private String workerInfo;
	@Column(name = "day")
	private int day;
	@Column(name = "month")
	private int month;
	@Column(name = "year")
	private int year;
	
	public Report() {};
	public Report(int _id) {id =  Integer.parseInt("3" + _id);}
	
	public int GetId() { return id;}
	public void SetId(int _id) {id = _id; }
	
	public String getCarInfo() { return carInfo; }
	public void setCarInfo(Car _car) { carInfo = _car.toString(); }
	
	public String getWorkerInfo() { return workerInfo; }
	public void SetWorkerInfo(Worker _worker) { workerInfo = _worker.toString(); }
	
	public int getDay() { return day; }
	public boolean setDay(int _day) {
		boolean flag = (_day > 0) && (_day < 32);
		if(flag) day = _day;
		return flag;
	}
	
	public int getMonth() { return month; }
	public boolean setMonth(int _month) {
		boolean flag = (_month > 0) && (_month < 13);
		if(flag) month = _month;
		return flag;
	}
	
	public int getYear() { return year; }
	public boolean setYear(int _year) {
		boolean flag = (_year > 1950);
		if(flag) year = _year;
		return flag;
	}
	
	@Override
	public String toString() {return "\\{" + workerInfo+ "\\} repaired\n"
			+ "\\{" + carInfo+ "\\}\n at " + day + "\\." + month + "\\." + year ; }
	
	@Override
	public boolean equals(Object other) {
		if(other == null) return false;
		if(other instanceof Report)
			return ((Report) other).id == id;
		else return false;
	}

	@Override
	public void cascadeRemove() { Main.megaList.get(2).remove(this); }
	
	@Override
	public boolean isRelated() { return carInfo != null && workerInfo != null; }
	
	@Override
	public Object[] toRow() {
		var result = new Object[6];
		
		result[0] = Integer.valueOf(id);
		if(carInfo != null && carInfo != "") result[1] = carInfo.substring(1, carInfo.indexOf("'", 1));
		if(workerInfo != null && workerInfo != "") result[2] = workerInfo.substring(1, workerInfo.indexOf("'", 1));
		result[3] = Integer.valueOf(day);
		result[4] = Integer.valueOf(month);
		result[5] = Integer.valueOf(year);
		
		return result;
	}
}
