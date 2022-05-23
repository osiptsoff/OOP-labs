package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;

import classes.IncorrectDataException;
import main.Main;

/**
 * @author Осипцов Никита, группа 0305
 * <p>Класс слушателя кнопки редактирования </p>
 */
final class EditButtonListener implements ActionListener {
	Hulk hulk;
	ClosedTable table;
	
	public EditButtonListener(Hulk _hulk) { hulk = _hulk; }

	@Override
	/**
	 * <p>По нажатии на кнопку получается список индексов всех выбранных строк. Если такая хотя бы одна,
	 * вызывается окно подтверждения. После подтверждения создаётся новый объект, куда сохраняются
	 * отредактированные данные и резервная копия строки. После чего к таблице крепится слушатель клавиатуры.</p>
	 */
	public void actionPerformed(ActionEvent e) {
		table = hulk.tables[hulk.currentEntity];
		var selectedRows = table.getSelectedRows();
		if(selectedRows.length > 0 && JOptionPane.showConfirmDialog(hulk.mainPart, "Приступить к редактированию строки с ID " + table.getValueAt(selectedRows[0], 0) + "?") == JOptionPane.OK_OPTION) {
			var newObject = Constants.Constructors[hulk.currentEntity].Instantiate(Integer.parseInt(((String) table.getValueAt(selectedRows[0], 0)).substring(1)));
			var backupRow = new Object[table.getColumnCount()];
			for(int i = 0; i < backupRow.length; ++i)
				backupRow[i] = table.getValueAt(selectedRows[0], i);
			
			boolean redactRelations = JOptionPane.showConfirmDialog(hulk.mainPart, "Редактировать связи?") == JOptionPane.OK_OPTION;
			if(redactRelations) {
				hulk.sfPrepare();
				hulk.selectionFrame.setVisible(true);
			}
			
			table.startEdittingRow(selectedRows[0]);
			hulk.setContentEnabled(false);
			
			// Слушатель клавиатуры
			table.addKeyListener(new KeyListener() {
				@Override
				public void keyTyped(KeyEvent e) {	}
				@Override
				public void keyPressed(KeyEvent e) { }

				@Override
				public void keyReleased(KeyEvent e) {
					/**
					 * <p>Если нажат Enter, данные из строки (самой первой из выбранных) заполняют
					 * созданный ранее объект. В случае успеха им заменяется соответствующий ему объект в списке.</p>
					 */
					if(e.getKeyCode() == KeyEvent.VK_ENTER) {
						try {		
							ObjectEditor.editors[hulk.currentEntity].func(newObject, table.getRow(table.editedRow), hulk, redactRelations);
							hulk.setContentEnabled(true);
							if(redactRelations)
								Main.megaList.get(hulk.currentEntity).set(selectedRows[0], newObject);
							else
								ObjectEditor.editors[hulk.currentEntity].func(Main.megaList.get(hulk.currentEntity).get(selectedRows[0]), table.getRow(table.editedRow), hulk, false);
							hulk.tables[hulk.currentEntity].stopEditingRow();
							hulk.tables[hulk.currentEntity].removeKeyListener(this);
							hulk.selectionFrame.setVisible(false);
							
						} catch (IncorrectDataException ex) {
							JOptionPane.showMessageDialog(hulk.mainPart, ex.getMessage());
						}
						/**
						 * При нажатии Esc старый объект сохраняется нетронутым, а изменяемая строка заполняется
						 * сделанной ранее резервной копией.
						 */
					} else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
						hulk.setContentEnabled(true);
						hulk.tables[hulk.currentEntity].stopEditingRow();
						table.editCellAt(-1, -1);
						for(int i = 0; i < backupRow.length; ++i)
							table.setValueAt(backupRow[i], selectedRows[0], i);
						hulk.tables[hulk.currentEntity].removeKeyListener(this);
						hulk.selectionFrame.setVisible(false);
					}
					
				}
				
			});
		}
	}
}
