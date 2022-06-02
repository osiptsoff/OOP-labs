package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;

import classes.IncorrectDataException;

/**
 * @author ������� ������, ������ 0305
 * <p>����� ��������� ������ �������������� </p>
 */
final class EditButtonListener implements ActionListener {
	Hulk hulk;
	ClosedTable table;
	
	public EditButtonListener(Hulk _hulk) { hulk = _hulk; }

	@Override
	/**
	 * <p>�� ������� �� ������ ���������� ������ �������� ���� ��������� �����. ���� ����� ���� �� ����,
	 * ���������� ���� �������������. ����� ������������� �������� ����� ������, ���� �����������
	 * ����������������� ������ � ��������� ����� ������. ����� ���� � ������� �������� ��������� ����������.</p>
	 */
	public void actionPerformed(ActionEvent e) {
		table = hulk.tables[hulk.currentEntity];
		var selectedRows = table.getSelectedRows();
		if(selectedRows.length > 0 && JOptionPane.showConfirmDialog(hulk.mainPart, "���������� � �������������� ������ � ID " + table.getValueAt(selectedRows[0], 0) + "?") == JOptionPane.OK_OPTION) {
			var backupRow = hulk.currentEntityShownRows.get(selectedRows[0]).toRow();
			
			boolean redactRelations = JOptionPane.showConfirmDialog(hulk.mainPart, "������������� �����?") == JOptionPane.OK_OPTION;
			if(redactRelations) {
				hulk.sfPrepare();
				hulk.selectionFrame.setVisible(true);
			}
			
			table.startEdittingRow(selectedRows[0]);
			var editted = hulk.currentEntityShownRows.get(selectedRows[0]);
			hulk.setContentEnabled(false);
			
			// ��������� ����������
			table.addKeyListener(new KeyListener() {
				@Override
				public void keyTyped(KeyEvent e) {	}
				@Override
				public void keyPressed(KeyEvent e) { }

				@Override
				public void keyReleased(KeyEvent e) {
					/**
					 * <p>���� ����� Enter, ������ �� ������ (����� ������ �� ���������) ���������
					 * ��������� ����� ������. � ������ ������ �� ���������� ��������������� ��� ������ � ������.</p>
					 */
					if(e.getKeyCode() == KeyEvent.VK_ENTER) {
						try {		
							ObjectEditor.editors[hulk.currentEntity].func(editted, table.getRow(table.editedRow), backupRow, hulk, redactRelations);
							hulk.setContentEnabled(true);
							hulk.tables[hulk.currentEntity].stopEditingRow();
							hulk.tables[hulk.currentEntity].removeKeyListener(this);
							hulk.selectionFrame.setVisible(false);
							if(redactRelations)
								hulk.revalidateTables();
							
						} catch (IncorrectDataException ex) {
							JOptionPane.showMessageDialog(hulk.mainPart, ex.getMessage());
						}
						/**
						 * ��� ������� Esc ������ ������ ����������� ����������, � ���������� ������ �����������
						 * ��������� ����� ��������� ������.
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
