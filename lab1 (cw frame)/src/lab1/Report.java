package lab1;

public class Report {
	private Car car;
	private Worker worker;
	private String repairDate;
	
	public void SetCar(Car _car) { car = _car; }
	public Car GetCar() { return car; }
	public void SetWorker(Worker _worker) { worker = _worker; }
	public Worker GetWorker() { return worker; }
	public boolean SetDate(int day, int month, int year) {
		boolean flag = day > 0 && day < 32 && month > 0 && month < 13 && year > 1950;
		if(flag) 
			repairDate = day + "." + month + "." + year;
		return flag;
	}
	public String GetDate() { return repairDate; }
}
