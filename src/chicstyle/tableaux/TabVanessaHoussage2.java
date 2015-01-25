package chicstyle.tableaux;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class TabVanessaHoussage2 extends AbstractTableModel{
	
	private final List<TabVanessaHoussage1> infos = new ArrayList<TabVanessaHoussage1>();
	 
    private final String[] entetes = {"Taille","Quantité houssée", "Total","Reste à housser"};
 
    public TabVanessaHoussage2() {
        super();
 
        infos.add(new TabVanessaHoussage1(30,0,0,0));
        infos.add(new TabVanessaHoussage1(32,0,0,0));
        infos.add(new TabVanessaHoussage1(34,0,0,0));
        infos.add(new TabVanessaHoussage1(36,0,0,0));
        infos.add(new TabVanessaHoussage1(38,0,0,0));
        infos.add(new TabVanessaHoussage1(40,0,0,0));
        infos.add(new TabVanessaHoussage1(42,0,0,0));
        infos.add(new TabVanessaHoussage1(44,0,0,0));
        infos.add(new TabVanessaHoussage1(46,0,0,0));
        infos.add(new TabVanessaHoussage1(48,0,0,0));
        
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
    	if(columnIndex == 0 || columnIndex == 2 || columnIndex == 3) return false;
    	return true;
    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if(aValue != null){
            TabVanessaHoussage1 info = infos.get(rowIndex);
     
            switch(columnIndex){
                case 0:
                    info.setTaille((int)aValue);
                    break;
                case 1:
                    info.setHousse(Integer.parseInt((String)aValue));
                    break;
                case 2:
                    info.setTotal((int)aValue);
                    break;
                case 3:
                    info.setReste((int)aValue);
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
                return infos.get(rowIndex).getTaille();
            case 1:
                return infos.get(rowIndex).getHousse();
            case 2:
                return infos.get(rowIndex).getTotal();
            case 3:
                return infos.get(rowIndex).getReste();
            default:
                return null; //Ne devrait jamais arriver
        }
    }
 
    public void addAmi(TabVanessaHoussage1 info) {
        infos.add(info);
 
        fireTableRowsInserted(infos.size() -1, infos.size() -1);
    }
 
    public void removeAmi(int rowIndex) {
        infos.remove(rowIndex);
 
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

}
