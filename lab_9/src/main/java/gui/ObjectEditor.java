package gui;

import classes.*;
import main.Main;

public final class ObjectEditor {
	
	public static void editCar(Object _car, Object[] row, ClosedTable table) throws IncorrectDataException {
		Car car = (Car)_car;
		boolean succsess;
		
		succsess = car.SetBrand((String)row[1])
				&& car.SetProblemDescription((String)row[2])
				&& car.SetReleaseYear((String)row[3])
				&& car.SetMileage((String)row[4]);
		
		if (succsess) {
			table.setValueAt(car.GetId(), table.editedRow, 0);
			table.setValueAt(car.GetBrand(), table.editedRow, 1);
			table.setValueAt(car.GetProblemDescription(), table.editedRow, 2);
			table.setValueAt(Integer.toString(car.GetReleaseYear()), table.editedRow, 3);
			table.setValueAt(Integer.toString(car.GetMileage()), table.editedRow, 4);
		}
		else throw new IncorrectDataException();
	}
	
	public static void editOwner(Object _owner, Object[] row, ClosedTable table) throws IncorrectDataException {
		Owner owner = (Owner)_owner;
		boolean succsess;	
		
		succsess = owner.SetName((String)row[1])
				&& owner.SetLastName((String)row[2])
				&& owner.SetPhoneNumber((String)row[3]);
		
		if(succsess) {
			table.setValueAt(owner.GetId(), table.editedRow, 0);
			table.setValueAt(owner.GetName(), table.editedRow, 1);
			table.setValueAt(owner.GetLastName(), table.editedRow, 2);
			table.setValueAt(owner.GetPhoneNumber(), table.editedRow, 3);
		}
		else throw new IncorrectDataException();
		
	}
	
	public static void editReport(Object _report, Object[] row, ClosedTable table) throws IncorrectDataException {
		Report report = (Report)_report;
		boolean succsess = true;
		String repairDate = (String)row[3];
		int workerId = -1, carId = -1;
		
		try {
			var date = repairDate.split("\\.");
			
			succsess = report.SetDate(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]));
			carId = Integer.parseInt((String)row[1]);
			workerId = Integer.parseInt((String)row[2]);
		}
		catch(Exception e) { succsess = false; }
		
		if(succsess) {
			for(var worker : Main.workerList)
				if(((Worker)worker).GetId() == workerId) {
					report.SetWorker((Worker)worker);
					break;
				}
			for(var car : Main.carList)
				if(((Car)car).GetId() == carId) {
					report.SetCar((Car)car);
					break;
				}
			if( report.GetWorker() == null || report.GetCar() == null)
				succsess = false;
		}
		
		if(succsess) {
			table.setValueAt(report.GetId(), table.editedRow, 0);
			table.setValueAt(Integer.toString(report.GetCar().GetId()), table.editedRow, 1);
			table.setValueAt(Integer.toString(report.GetWorker().GetId()), table.editedRow, 2);
			table.setValueAt(report.GetDate(), table.editedRow, 3);
		}
		else throw new IncorrectDataException();
	}
	
	public static void editSpeciality(Object _speciality, Object[] row, ClosedTable table) throws IncorrectDataException {
		Speciality speciality = (Speciality)_speciality;
		
		if(speciality.SetName((String)row[1])) {
			table.setValueAt(speciality.GetId(), table.editedRow, 0);
			table.setValueAt(speciality.GetName(), table.editedRow, 1);
		}
		else throw new IncorrectDataException();
	}
	
	public static void editWorker(Object _worker, Object[] row, ClosedTable table) throws IncorrectDataException {
		Worker worker = (Worker)_worker;
		boolean succsess;	
		
		succsess = worker.SetName((String)row[1])
				&& worker.SetLastName((String)row[2])
				&& worker.SetPhoneNumber((String)row[3]);
		
		if(succsess) {
			table.setValueAt(worker.GetId(), table.editedRow, 0);
			table.setValueAt(worker.GetName(), table.editedRow, 1);
			table.setValueAt(worker.GetLastName(), table.editedRow, 2);
			table.setValueAt(worker.GetPhoneNumber(), table.editedRow, 3);
		}
		else throw new IncorrectDataException();
		
	}
	
	interface Editor{
		void func(Object obj, Object[] objList, ClosedTable table) throws IncorrectDataException;
	}
	public static final Editor[] editors = {
			(obj, objList, table) -> {editCar(obj, objList, table);},
			(obj, objList, table) -> {editOwner(obj, objList, table);},
			(obj, objList, table) -> {editReport(obj, objList, table);},
			(obj, objList, table) -> {editSpeciality(obj, objList, table);},
			(obj, objList, table) -> {editWorker(obj, objList, table);},
	};
	
	private ObjectEditor() {}
}
