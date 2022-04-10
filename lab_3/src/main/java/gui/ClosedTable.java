package gui;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public final class ClosedTable extends JTable{
	private static final long serialVersionUID = 1L;
	
	protected DefaultTableModel model;
	protected int editedRow;
	protected int rowCount;
	
	public ClosedTable(String[] headers) {
		super(new DefaultTableModel(headers, 0));
		this.getSelectionModel().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION );
		model = (DefaultTableModel) this.getModel();
		rowCount = model.getRowCount();
		}
	
	@Override
	public boolean isCellEditable(int row, int col) {
		return row == editedRow;
	}
	
	
	public void startEdittingRow(int row) { editedRow = row; }
	public void stopEditingRow() { editedRow = -1; }
	public void addRow() { 
		model.addRow(Constants.FieldsNames[Constants.FieldsNames.length - 1]);
		++rowCount;
	}
	public void deleteRow(int row) {
		this.clearSelection();
		this.editCellAt(-1, -1);
		stopEditingRow();
		--rowCount;
		model.removeRow(row);
	}
	
	public int getColumnIndex(String name) {
		for(int i = 0; i < this.getColumnCount(); ++i)
			if(name.equals(this.getColumnName(i)))
				return i;
		return -1;
	}
	
	public Object[] getRow(int index) {
		var row = new Object[this.getColumnCount()];
		
		for(int i = 0; i < row.length; ++i)
			row[i] = this.getValueAt(index, i);
		
		return row;
	}
}
