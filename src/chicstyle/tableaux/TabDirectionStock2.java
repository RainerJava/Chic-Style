package chicstyle.tableaux;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class TabDirectionStock2 extends AbstractTableModel {
	
	private final List<TabDirectionStock1> infos = new ArrayList<TabDirectionStock1>();
	 
    private final String[] entetes = {"Référence", "Type", "Besoin", "Quantité disponible", "Différence"};
 
    public TabDirectionStock2() {
        super();
 
        infos.add(new TabDirectionStock1("", "" ,0,0,0));
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false; //Toutes les cellules éditables
    }
     
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if(aValue != null){
            TabDirectionStock1 info = infos.get(rowIndex);
     
            switch(columnIndex){
                case 0:
                    info.setReference((String)aValue);
                    break;
                case 1:
                    info.setType((String)aValue);
                    break;
                case 2:
                    info.setBesoin(Integer.parseInt((String)aValue));
                    break;
                case 3:
                    info.setDispo(Integer.parseInt((String)aValue));
                    break;
                case 4:
                	info.setDiff((int)aValue);
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
                return infos.get(rowIndex).getReference();
            case 1:
                return infos.get(rowIndex).getType();
            case 2:
                return infos.get(rowIndex).getBesoin();
            case 3:
                return infos.get(rowIndex).getDispo();
            case 4:
            	return infos.get(rowIndex).getDiff();
            default:
                return null; //Ne devrait jamais arriver
        }
    }
 
    public void addAmi(TabDirectionStock1 info) {
        infos.add(info);
 
        fireTableRowsInserted(infos.size() -1, infos.size() -1);
    }
 
    public void removeAmi(int rowIndex) {
        infos.remove(rowIndex);
 
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

}
