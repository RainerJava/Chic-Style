package chicstyle.tableaux;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class TabDirectionAlerte2 extends AbstractTableModel{

	private final List<TabDirectionAlerte1> infos = new ArrayList<TabDirectionAlerte1>();
	 
    private final String[] entetes = {"Source", "Message"};
 
    public TabDirectionAlerte2() {
        super();
 
        infos.add(new TabDirectionAlerte1("", ""));
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true; //Toutes les cellules éditables
    }
     
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if(aValue != null){
            TabDirectionAlerte1 info = infos.get(rowIndex);
     
            switch(columnIndex){
                case 0:
                    info.setSource((String)aValue);
                    break;
                case 1:
                    info.setMessage((String)aValue);
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
                return infos.get(rowIndex).getSource();
            case 1:
                return infos.get(rowIndex).getMessage();
            default:
                return null; //Ne devrait jamais arriver
        }
    }
 
    public void addAmi(TabDirectionAlerte1 info) {
        infos.add(info);
 
        fireTableRowsInserted(infos.size() -1, infos.size() -1);
    }
 
    public void removeAmi(int rowIndex) {
        infos.remove(rowIndex);
 
        fireTableRowsDeleted(rowIndex, rowIndex);
    }
}
