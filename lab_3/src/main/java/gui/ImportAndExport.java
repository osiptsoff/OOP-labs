package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

import main.Main;

class ImportButtonListener implements ActionListener {
	Hulk hulk;
	ClosedTable table;
	BufferedReader reader;
	
	public ImportButtonListener(Hulk _hulk) { hulk = _hulk; }
	@Override
	public void actionPerformed(ActionEvent e) {
		table = hulk.tables[hulk.currentEntity];
		if (hulk.currentEntity != Constants.Entities.length - 1 && JOptionPane.showConfirmDialog(hulk.mainPart, 
				"Вы действительно хотите загрузить вариант этой таблицы с диска?"
				+ "\nОткрытая в приложении таблица будет перезаписана.") == JOptionPane.OK_OPTION) {
			
			try {
				reader = new BufferedReader(new FileReader(Constants.csvPaths[hulk.currentEntity]));
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(hulk.mainPart, "Неудача при открытии файла. Возможно, вы его удалили"
						+ "\nили ни разу не сохраняли эту таблицу на диск.");
				return;
			}
			
			table.selectAll();
			hulk.removeButton.getActionListeners()[0].actionPerformed(null);
			
			String string;
			try {
				string = reader.readLine();
			} catch (IOException e2) {
				JOptionPane.showMessageDialog(hulk.mainPart, "Неудача при записи строки. Остановлено.");
				return;
			}
			
			try {
				do {
					var obj = Constants.Constructors[hulk.currentEntity].Instantiate(Main.lastId);
					
					table.addRow();
					table.startEdittingRow(table.rowCount - 1);
					ObjectEditor.editors[hulk.currentEntity].func(obj, string.split(Constants.separator), table);
					table.stopEditingRow();
					
					Main.megaList.get(hulk.currentEntity).add(obj);
					++Main.lastId;
					
					string = reader.readLine();
				} while (string != null);
			reader.close();
			JOptionPane.showMessageDialog(hulk.mainPart, "Чтение выполнено успешно.");
				
		} catch (Exception e1) {
			table.stopEditingRow();
			table.deleteRow(table.rowCount - 1);
			JOptionPane.showMessageDialog(hulk.mainPart, "Неудача при записи очередной строки. Остановлено.");
			return;
		}
	}
	
} }

class ExportButtonListener implements ActionListener {
	Hulk hulk;
	BufferedWriter writer;
		
	public ExportButtonListener(Hulk _hulk) { hulk = _hulk; }
	@Override
	public void actionPerformed(ActionEvent e) {
		if (hulk.currentEntity != Constants.Entities.length - 1 && JOptionPane.showConfirmDialog(hulk.mainPart, 
				"Вы действительно хотите сохранить эту таблицу?"
				+ "\nСуществующий на диске вариант будет перезаписан.") == JOptionPane.OK_OPTION) {
			
			try {
				writer = new BufferedWriter(new FileWriter(Constants.csvPaths[hulk.currentEntity]));
				for(var obj : Main.megaList.get(hulk.currentEntity)) {
					writer.write(obj.toString());
					writer.write("\n");
				}
				writer.close();
				JOptionPane.showMessageDialog(hulk.mainPart, "Запись выполнена успешно.");
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(hulk.mainPart, "Неудача при записи строки. Остановлено.");
				
			}
			
		}
		
	}

}