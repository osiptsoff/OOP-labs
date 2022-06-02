package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;

import classes.*;
import main.Main;

/**
 * @author ������� ������, ������ 0305
 */
final class MakeAtEnter implements KeyListener {
	TableFriendly obj;
	ClosedTable table;
	Hulk hulk;
	
	/**
	 * @param _obj - ��������� ������ ������ T, ������� ������ ���� ��������
	 * @param _hulk - ����, �� ������� ���������� �������, ���� ����������� ������
	 */
	public MakeAtEnter(TableFriendly _obj, Hulk _hulk) {
		obj = _obj;
		hulk = _hulk;
		table = hulk.tables[hulk.currentEntity];
	}
	@Override
	public void keyTyped(KeyEvent e) {}
	@Override
	public void keyPressed(KeyEvent e) {}
	
	/**
	 * <p> ��� ������� �� Enter ����������� ������, �������� � ������. ���� ��� ���������, ������ 
	 * �����������. ��� ������� �� Esc ���������� ����������� </p>
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			try {
				ObjectEditor.editors[hulk.currentEntity].func(obj, table.getRow(table.editedRow), null, hulk, true);
				++Main.lastIds[hulk.currentEntity];
				Main.megaList.get(hulk.currentEntity).add(obj);
				hulk.currentEntityShownRows.add(obj);
				hulk.setContentEnabled(true);
				hulk.tables[hulk.currentEntity].stopEditingRow();
				hulk.tables[hulk.currentEntity].removeKeyListener(this);
				hulk.selectionFrame.setVisible(false);
				
				hulk.revalidateTables();
			}
			catch (IncorrectDataException ex) { JOptionPane.showMessageDialog(hulk.mainPart, ex.getMessage()); }
				
		}
		else if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			hulk.tables[hulk.currentEntity].deleteRow(hulk.tables[hulk.currentEntity].rowCount - 1);
			hulk.setContentEnabled(true);
			hulk.tables[hulk.currentEntity].removeKeyListener(this);
			hulk.selectionFrame.setVisible(false);
		}
	}
	
}



/**
 * @author 	������� ������, ������ 0305
 *	<p>����� ��������� ������ ����������. </p>
 */
final class AddButtonListener implements ActionListener {
	private Hulk hulk;
	private ClosedTable table;
	/**
	 * @param _hulk - ����, �� ������� ������������� ������, � ������� �������� ���������
	 */
	public AddButtonListener(Hulk _hulk) { hulk = _hulk; }
	/**
	 * <p> ��� ������� �� ������ � ������� �������� ������, ����� �������� ������
	 * ������ ���������������� ������. ����� ���������� ��������� ���������� ��� �������� ����� ������</p>
	 */
	public void actionPerformed(ActionEvent e) {
		table = hulk.tables[hulk.currentEntity];
		if(hulk.currentEntity != Constants.Entities.length - 1) {
			hulk.revertButton.getActionListeners()[0].actionPerformed(null);
			table.addRow();
			table.startEdittingRow(table.rowCount - 1);
			hulk.setContentEnabled(false);
			table.addKeyListener(new MakeAtEnter(Constants.Constructors[hulk.currentEntity].Instantiate(Main.lastIds[hulk.currentEntity]), hulk));
			hulk.sfPrepare();
			hulk.selectionFrame.setVisible(true);
		}
	}
}
