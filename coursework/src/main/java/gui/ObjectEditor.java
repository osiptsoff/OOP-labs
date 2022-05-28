package gui;

import classes.*;
import main.Main;

final class ObjectEditor {
	public static void editCar(Object _car, Object[] row, Object[] backup, Hulk hulk, boolean reledit) throws IncorrectDataException {
		ClosedTable table = hulk.tables[hulk.currentEntity];
		Car car = (Car)_car;
		
		boolean succsess;
		succsess = car.SetBrand((String)row[1])
				&& car.SetProblemDescription((String)row[2])
				&& car.SetReleaseYear((String)row[3])
				&& car.SetMileage((String)row[4]);
		if(succsess && reledit) {
			int related = Constants.relatedEntities[hulk.currentEntity][0];
			var selectedRows = hulk.tables[related].getSelectedRows();
			
			if(selectedRows.length > 0)
				car.SetOwner((Owner)Main.megaList.get(1).get(selectedRows[selectedRows.length - 1]));
		}
		
		if (succsess)
			table.setRow(car.toRow(), table.editedRow);
		else { 
			if(backup != null) {
				car.SetBrand((String)backup[1]);
				car.SetProblemDescription((String)backup[2]);
				car.SetReleaseYear((String)backup[3]);
				car.SetMileage((String)backup[4]);
			}
			throw new IncorrectDataException();
		}
	}
	
	public static void editOwner(Object _owner, Object[] row, Object[] backup, Hulk hulk, boolean reledit) throws IncorrectDataException {
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
					owner.AddCar((Car)Main.megaList.get(0).get(rw));
			}
		}
		
		if(succsess)
			table.setRow(owner.toRow(), table.editedRow);
		else {
			if(backup != null) {
				owner.SetName((String)backup[1]);
				owner.SetLastName((String)backup[2]);
				owner.SetPhoneNumber((String)backup[3]);
			}
			throw new IncorrectDataException();
		}
		
	}
	
	public static void editReport(Object _report, Object[] row, Object[] backup, Hulk hulk, boolean reledit) throws IncorrectDataException {
		ClosedTable table = hulk.tables[hulk.currentEntity];
		Report report = (Report)_report;
		boolean succsess = true;
		
		try {
			var date = ((String)row[3]).split("\\.");
			
			succsess = report.SetDate(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]));
		}
		catch(Exception e) { succsess = false; }
		
		if(succsess && reledit) {
			int relatedCar = Constants.relatedEntities[hulk.currentEntity][0];
			int relatedWorker = Constants.relatedEntities[hulk.currentEntity][1];
			var selectedRowsCar = hulk.tables[relatedCar].getSelectedRows();
			var selectedRowsWorker = hulk.tables[relatedWorker].getSelectedRows();
			
			if(selectedRowsCar.length > 0)
				report.SetCar((Car)Main.megaList.get(0).get(selectedRowsCar[selectedRowsCar.length - 1]));
			if(selectedRowsWorker.length > 0)
				report.SetWorker((Worker)Main.megaList.get(4).get(selectedRowsWorker[selectedRowsWorker.length - 1]));
		}
		
		if(succsess)
			table.setRow(report.toRow(), table.editedRow);
		else {
			if(backup != null) {
				var date = ((String)backup[3]).split("\\.");	
				succsess = report.SetDate(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]));
			}
			throw new IncorrectDataException();
		}
	}
	
	public static void editSpeciality(Object _speciality, Object[] row, Object[] backup, Hulk hulk, boolean reledit) throws IncorrectDataException {
		ClosedTable table = hulk.tables[hulk.currentEntity];
		Speciality speciality = (Speciality)_speciality;
		
		if(!speciality.SetName((String)row[1])) {
			if (backup != null) speciality.SetName((String)backup[1]);
			throw new IncorrectDataException();
		}
		
		if(reledit) {
			int related = Constants.relatedEntities[hulk.currentEntity][0];
			var selectedRows = hulk.tables[related].getSelectedRows();
			
			if(selectedRows.length > 0) {
				speciality.GetWorkers().removeAll(speciality.GetWorkers());
				for(var rw : selectedRows)
					speciality.AddWorker((Worker)Main.megaList.get(4).get(rw));
			}	
			table.setRow(speciality.toRow(), table.editedRow);
		}
	}
	
	public static void editWorker(Object _worker, Object[] row, Object[] backup, Hulk hulk, boolean reledit) throws IncorrectDataException {
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
					worker.AddSpeciality(((Speciality)Main.megaList.get(3).get(rw)));
			}
		}
		
		if(succsess)
			table.setRow(worker.toRow(), table.editedRow);
		else {
			if(backup != null) {
				worker.SetName((String)backup[1]);
				worker.SetLastName((String)backup[2]);
				worker.SetPhoneNumber((String)backup[3]);
			}
			throw new IncorrectDataException();
		}
		
	}
	
	interface Editor{
		void func(Object obj, Object[] newValues, Object[] backup, Hulk hulk, boolean reledit) throws IncorrectDataException;
	}
	public static final Editor[] editors = {
			(obj, newValues, backup, hulk, re) -> {editCar(obj, newValues, backup, hulk, re);},
			(obj, newValues, backup, hulk, re) -> {editOwner(obj, newValues, backup, hulk, re);},
			(obj, newValues, backup, hulk, re) -> {editReport(obj, newValues, backup, hulk, re);},
			(obj, newValues, backup, hulk, re) -> {editSpeciality(obj, newValues, backup, hulk, re);},
			(obj, newValues, backup, hulk, re) -> {editWorker(obj, newValues, backup, hulk, re);},
	};
	
	private ObjectEditor() {}
}
