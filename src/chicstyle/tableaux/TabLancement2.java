package chicstyle.tableaux;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class TabLancement2 extends AbstractTableModel{

	private final List<TabLancement1> infos = new ArrayList<TabLancement1>();
	 
    private final String[] entetes = {"Taille","Quantité"};
 
    public TabLancement2() {
        super();
 
        infos.add(new TabLancement1("30",0));
        infos.add(new TabLancement1("32",0));
        infos.add(new TabLancement1("34",0));
        infos.add(new TabLancement1("36",0));
        infos.add(new TabLancement1("38",0));
        infos.add(new TabLancement1("40",0));
        infos.add(new TabLancement1("42",0));
        infos.add(new TabLancement1("44",0));
        infos.add(new TabLancement1("46",0));
        infos.add(new TabLancement1("48",0));
        
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
    	if (columnIndex == 0) return false;
    	return true; //Toutes les cellules éditables
    }
     
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if(aValue != null){
            TabLancement1 info = infos.get(rowIndex);
     
            switch(columnIndex){
                case 0:
                    info.setTaille((String)aValue);
                    break;
                case 1:
                    info.setQte(Integer.parseInt((String)aValue));
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
                return infos.get(rowIndex).getQte();
            default:
                return null; //Ne devrait jamais arriver
        }
    }
 
    public void addAmi(TabLancement1 info) {
        infos.add(info);
 
        fireTableRowsInserted(infos.size() -1, infos.size() -1);
    }
 
    public void removeAmi(int rowIndex) {
        infos.remove(rowIndex);
 
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

}
