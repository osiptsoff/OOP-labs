package gui;

import classes.*;
import main.Main;
import org.apache.log4j.Logger;

final class ObjectEditor {
	private static final Logger log = Logger.getLogger("ObjectEditor.class");

	public static void editCar(Object _car, Object[] row, Hulk hulk, boolean reledit) throws IncorrectDataException {
		ClosedTable table = hulk.tables[hulk.currentEntity];
		Car car = (Car)_car;
		boolean succsess;
		//
		log.info("Editting started");
		//
		succsess = car.SetBrand((String)row[1])
				&& car.SetProblemDescription((String)row[2])
				&& car.SetReleaseYear((String)row[3])
				&& car.SetMileage((String)row[4]);
		//
		log.info("Primary data got");
		//
		if(succsess && reledit) {
			//
			log.info("Relations establishing started");
			//
			int related = Constants.relatedEntities[hulk.currentEntity][0];
			var selectedRows = hulk.tables[related].getSelectedRows();
			
			if(selectedRows.length > 0)
				car.SetOwner((Owner)Main.ownerList.get(selectedRows[selectedRows.length - 1]));
			
			if(car.GetOwner() != null) {
				//
				log.info("Relations established");
				//
				table.setValueAt(Integer.toString(car.GetOwner().GetId()), table.editedRow, 5);
			}
			else {
				//
				log.info("Relations not established");
				//
				table.setValueAt("", table.editedRow, 5);
			}
		}
		
		if (succsess) {
			//
			log.info("All done succsessfully");
			//
			table.setValueAt(Integer.toString(car.GetId()), table.editedRow, 0);
			table.setValueAt(car.GetBrand(), table.editedRow, 1);
			table.setValueAt(car.GetProblemDescription(), table.editedRow, 2);
			table.setValueAt(Integer.toString(car.GetReleaseYear()), table.editedRow, 3);
			table.setValueAt(Integer.toString(car.GetMileage()), table.editedRow, 4);
		}
		else { 
			//
			log.info("Error occured");
			//
			throw new IncorrectDataException();
		}
	}
	
	public static void editOwner(Object _owner, Object[] row, Hulk hulk, boolean reledit) throws IncorrectDataException {
		ClosedTable table = hulk.tables[hulk.currentEntity];
		Owner owner = (Owner)_owner;
		boolean succsess;	
		
		succsess = owner.SetName((String)row[1])
				&& owner.SetLastName((String)row[2])
				&& owner.SetPhoneNumber((String)row[3]);
		
		if(succsess && reledit) {
			int related = Constants.relatedEntities[hulk.currentEntity][0];
			var selectedRows = hulk.tables[related].getSelectedRows();
			
			if(selectedRows.length > 0) {
				owner.GetCars().removeAll(owner.GetCars());
				for(var rw : selectedRows)
					owner.AddCar((Car)Main.carList.get(rw));
			}
			
			if(!owner.GetCars().isEmpty()) {
				String cars = "";
				for(var car : owner.GetCars())
					cars += Integer.toString(car.GetId()) + " ";
				table.setValueAt(cars, table.editedRow, 4);
			}
			else
				table.setValueAt("", table.editedRow, 4);
		}
		
		if(succsess) {
			table.setValueAt(Integer.toString(owner.GetId()), table.editedRow, 0);
			table.setValueAt(owner.GetName(), table.editedRow, 1);
			table.setValueAt(owner.GetLastName(), table.editedRow, 2);
			table.setValueAt(owner.GetPhoneNumber(), table.editedRow, 3);
		}
		else throw new IncorrectDataException();
		
	}
	
	public static void editReport(Object _report, Object[] row, Hulk hulk, boolean reledit) throws IncorrectDataException {
		ClosedTable table = hulk.tables[hulk.currentEntity];
		Report report = (Report)_report;
		boolean succsess = true;
		String repairDate = (String)row[3];
		
		try {
			var date = repairDate.split("\\.");
			
			succsess = report.SetDate(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]));
		}
		catch(Exception e) { succsess = false; }
		
		if(succsess && reledit) {
			int relatedCar = Constants.relatedEntities[hulk.currentEntity][0];
			int relatedWorker = Constants.relatedEntities[hulk.currentEntity][1];
			var selectedRowsCar = hulk.tables[relatedCar].getSelectedRows();
			var selectedRowsWorker = hulk.tables[relatedWorker].getSelectedRows();
			
			if(selectedRowsCar.length > 0)
				report.SetCar((Car)Main.carList.get(selectedRowsCar[selectedRowsCar.length - 1]));
			if(selectedRowsWorker.length > 0)
				report.SetWorker((Worker)Main.workerList.get(selectedRowsWorker[selectedRowsWorker.length - 1]));
			
			if(report.GetCar() != null)
				table.setValueAt(Integer.toString(report.GetCar().GetId()), table.editedRow, 1);
			else
				table.setValueAt("", table.editedRow, 1);
			if(report.GetWorker() != null)
				table.setValueAt(Integer.toString(report.GetWorker().GetId()), table.editedRow, 2);
			else
				table.setValueAt("", table.editedRow, 2);
		}
		
		if(succsess) {
			table.setValueAt(Integer.toString(report.GetId()), table.editedRow, 0);
			table.setValueAt(report.GetDate(), table.editedRow, 3);
		}
		else throw new IncorrectDataException();
	}
	
	public static void editSpeciality(Object _speciality, Object[] row, Hulk hulk, boolean reledit) throws IncorrectDataException {
		ClosedTable table = hulk.tables[hulk.currentEntity];
		Speciality speciality = (Speciality)_speciality;
		
		if(reledit) {
			int related = Constants.relatedEntities[hulk.currentEntity][0];
			var selectedRows = hulk.tables[related].getSelectedRows();
			
			if(selectedRows.length > 0) {
				speciality.GetWorkers().removeAll(speciality.GetWorkers());
				for(var rw : selectedRows)
					speciality.AddWorker((Worker)Main.workerList.get(rw));
			}
			
			if(!speciality.GetWorkers().isEmpty()) {
				String workers = "";
				for(var worker : speciality.GetWorkers())
					workers += Integer.toString(worker.GetId()) + " ";
				table.setValueAt(workers, table.editedRow, 2);
			}
			else
				table.setValueAt("", table.editedRow, 2);
		}
		
		if(speciality.SetName((String)row[1])) {
			table.setValueAt(Integer.toString(speciality.GetId()), table.editedRow, 0);
			table.setValueAt(speciality.GetName(), table.editedRow, 1);
		}
		else throw new IncorrectDataException();
	}
	
	public static void editWorker(Object _worker, Object[] row, Hulk hulk, boolean reledit) throws IncorrectDataException {
		ClosedTable table = hulk.tables[hulk.currentEntity];
		Worker worker = (Worker)_worker;
		boolean succsess;	
		
		succsess = worker.SetName((String)row[1])
				&& worker.SetLastName((String)row[2])
				&& worker.SetPhoneNumber((String)row[3]);
		
		if(succsess && reledit) {
			int related = Constants.relatedEntities[hulk.currentEntity][0];
			var selectedRows = hulk.tables[related].getSelectedRows();
			
			if(selectedRows.length > 0) {
				worker.GetSpecialities().removeAll(worker.GetSpecialities());
				for(var rw : selectedRows)
					worker.AddSpeciality(((Speciality)Main.specList.get(rw)));
			}
			
			if(!worker.GetSpecialities().isEmpty()) {
				String specs = "";
				for(var spec : worker.GetSpecialities())
					specs += Integer.toString(spec.GetId()) + " ";
				table.setValueAt(specs, table.editedRow, 4);
			}
			else
				table.setValueAt("", table.editedRow, 4);
		}
		
		if(succsess) {
			table.setValueAt(Integer.toString(worker.GetId()), table.editedRow, 0);
			table.setValueAt(worker.GetName(), table.editedRow, 1);
			table.setValueAt(worker.GetLastName(), table.editedRow, 2);
			table.setValueAt(worker.GetPhoneNumber(), table.editedRow, 3);
		}
		else throw new IncorrectDataException();
		
	}
	
	interface Editor{
		void func(Object obj, Object[] objList, Hulk hulk, boolean reledit) throws IncorrectDataException;
	}
	public static final Editor[] editors = {
			(obj, objList, hulk, re) -> {editCar(obj, objList, hulk, re);},
			(obj, objList, hulk, re) -> {editOwner(obj, objList, hulk, re);},
			(obj, objList, hulk, re) -> {editReport(obj, objList, hulk, re);},
			(obj, objList, hulk, re) -> {editSpeciality(obj, objList, hulk, re);},
			(obj, objList, hulk, re) -> {editWorker(obj, objList, hulk, re);},
	};
	
	private ObjectEditor() {}
}
