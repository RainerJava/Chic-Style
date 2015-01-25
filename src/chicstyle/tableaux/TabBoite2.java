package chicstyle.tableaux;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class TabBoite2 extends AbstractTableModel{


	private final List<TabBoite1> infos = new ArrayList<TabBoite1>();

	private final String[] entetes = {"Référence", "Type", "Besoin", "Stock", "Mis en boîte"};

	public TabBoite2() {
		super();

		infos.add(new TabBoite1("", "" ,0,0, false));
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {	
		if(columnIndex==0 || columnIndex==1 || columnIndex==3){
			return false;
		}
		else{
			return true;
		}
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		if(aValue != null){
			TabBoite1 info = infos.get(rowIndex);

			switch(columnIndex){
			case 0:
				info.setReference((String)aValue);
				break;
			case 1:
				info.setType((String)aValue);
				break;
			case 2:
				info.setqte(Integer.parseInt((String)aValue));
				break;
			case 3:
				info.setStock(Integer.parseInt((String)aValue));
				break;
			case 4:
				info.setFait((Boolean)aValue);
				break;
			}
		}
	}
	@Override
	public Class getColumnClass(int columnIndex){
		switch(columnIndex){
		case 4:
			return Boolean.class;
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
			return infos.get(rowIndex).getReference();
		case 1:
			return infos.get(rowIndex).getType();
		case 2:
			return infos.get(rowIndex).getqte();
		case 3:
			return infos.get(rowIndex).getStock();
		case 4:
			return infos.get(rowIndex).isFait();
		default:
			return null; //Ne devrait jamais arriver
		}
	}

	public void addAmi(TabBoite1 info) {
		infos.add(info);

		fireTableRowsInserted(infos.size() -1, infos.size() -1);
	}

	public void removeAmi(int rowIndex) {
		infos.remove(rowIndex);

		fireTableRowsDeleted(rowIndex, rowIndex);
	}


}
