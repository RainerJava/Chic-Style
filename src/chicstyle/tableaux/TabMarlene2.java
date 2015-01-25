package chicstyle.tableaux;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class TabMarlene2 extends AbstractTableModel {

	private final List<TabMarlene1> infos = new ArrayList<TabMarlene1>();
	 
    private final String[] entetes = {"Taille","Longueur nécessaire"};
 
    public TabMarlene2() {
        super();
 
        infos.add(new TabMarlene1("30",""));
        infos.add(new TabMarlene1("32",""));
        infos.add(new TabMarlene1("34",""));
        infos.add(new TabMarlene1("36",""));
        infos.add(new TabMarlene1("38",""));
        infos.add(new TabMarlene1("40",""));
        infos.add(new TabMarlene1("42",""));
        infos.add(new TabMarlene1("44",""));
        infos.add(new TabMarlene1("46",""));
        infos.add(new TabMarlene1("48",""));
        
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
    	if(columnIndex == 0) return false;
    	return true;
    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if(aValue != null){
            TabMarlene1 info = infos.get(rowIndex);
     
            switch(columnIndex){
                case 0:
                    info.setTaille((String)aValue);
                    break;
                case 1:
                    info.setLongueur((String)aValue);
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
                return infos.get(rowIndex).getLongueur();
            default:
                return null; //Ne devrait jamais arriver
        }
    }
 
    public void addAmi(TabMarlene1 info) {
        infos.add(info);
 
        fireTableRowsInserted(infos.size() -1, infos.size() -1);
    }
 
    public void removeAmi(int rowIndex) {
        infos.remove(rowIndex);
 
        fireTableRowsDeleted(rowIndex, rowIndex);
    }
}
