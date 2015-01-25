package chicstyle.tableaux;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class TabStockVanessa2 extends AbstractTableModel{


	private final List<TabStockVanessa1> infos = new ArrayList<TabStockVanessa1>();

	private final String[] entetes = {"Type", "Référence", "Stock"};

	public TabStockVanessa2() {
		super();

		infos.add(new TabStockVanessa1("", "" ,0));
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {	
	return false;
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		if(aValue != null){
			TabStockVanessa1 info = infos.get(rowIndex);

			switch(columnIndex){
			case 0:
				info.setType((String)aValue);
				break;
			case 1:
				info.setReference((String)aValue);
				break;
			case 2:
				info.setStock(Integer.parseInt((String)aValue));
				break;
			}
		}
	}
	@Override
	public Class getColumnClass(int columnIndex){
		switch(columnIndex){
		default:
			return Object.class;
		}
	}

	public int getRowCount() {
		return infos.size();
	}

	public int getColumnCount() {
		return entetes.length;
	}

	public String getColumnName(int columnIndex) {
		return entetes[columnIndex];
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		switch(columnIndex){
		case 0:
			return infos.get(rowIndex).getType();
		case 1:
			return infos.get(rowIndex).getReference();
		case 2:
			return infos.get(rowIndex).getStock();
		default:
			return null; //Ne devrait jamais arriver
		}
	}

	public void addAmi(TabStockVanessa1 info) {
		infos.add(info);

		fireTableRowsInserted(infos.size() -1, infos.size() -1);
	}

	public void removeAmi(int rowIndex) {
		infos.remove(rowIndex);

		fireTableRowsDeleted(rowIndex, rowIndex);
	}


}
