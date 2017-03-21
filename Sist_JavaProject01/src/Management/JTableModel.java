package Management;

import javax.swing.table.AbstractTableModel;

public class JTableModel extends AbstractTableModel {
	private Object[][] data;
	private String[] column = { "ID", "AREA", "NAME", "BIRTH", "EMAIL", "PHONE" };

	public JTableModel(Object[][] data) {
		this.data = data;
	}

	public Object[][] getData() {
		return data;
	}

	public String[] getColumn() {
		return column;
	}

	public void setColumn(String[] column) {
		this.column = column;
	}

	public void setData(Object[][] data) {
		this.data = data;
	}

	@Override
	public String getColumnName(int col) {
		// TODO Auto-generated method stub
		return column[col];
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		data[rowIndex][columnIndex] = aValue;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return data.length;
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return column.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return data[rowIndex][columnIndex];
	}

}
