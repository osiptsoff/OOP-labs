package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import classes.*;

import main.Main;

final class RelinfoButtonListener implements ActionListener {
	Hulk hulk;
	
	public RelinfoButtonListener(Hulk _hulk) { hulk = _hulk; }
	
	@Override
	public void actionPerformed(ActionEvent e) {
		var selectedRows = hulk.tables[hulk.currentEntity].getSelectedRows();
		if(selectedRows.length != 0) {
			int selected = selectedRows[0];
			var packedObj = Main.megaList.get(hulk.currentEntity).get(selected);
			String message = "";
			
			switch (hulk.currentEntity) {
			case 0:
				var car = (Car)packedObj;
				if(car.GetOwner() != null)
					message = car.GetOwner().toString();
				JOptionPane.showMessageDialog(hulk.mainPart, "Владелец машины:\n" + message);
				break;
			case 1:
				var owner = (Owner)packedObj;
				message = "Машины владельца:\n";
				for(var cr : owner.GetCars())
					message += cr.toString() + "\n";
				JOptionPane.showMessageDialog(hulk.mainPart, message);
				break;
			case 2:
				var report = (Report)packedObj;
				message = "Отремонтированная машина: " ;
				message += report.getCarInfo();
				message += "\nРемонт выполнил: " ;
				message += report.getWorkerInfo();
				JOptionPane.showMessageDialog(hulk.mainPart, message);
				break;
			case 3:
				var spec = (Speciality)packedObj;
				message = "Рабочие данной специальности:\n";
				for(var wkr : spec.GetWorkers())
					message += wkr.toString() + "\n";
				JOptionPane.showMessageDialog(hulk.mainPart, message);
				break;
			case 4:
				var worker = (Worker)packedObj;
				message = "Специальности данного рабочего:\n";
				for(var spc : worker.GetSpecialities())
					message += spc.toString() + "\n";
				JOptionPane.showMessageDialog(hulk.mainPart, message);
				break;
			}
		}
	}

}
