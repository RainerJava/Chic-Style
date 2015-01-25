package chicstyle.tableaux;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class TabExpedition2 extends AbstractTableModel{

	private final List<TabExpedition1> infos = new ArrayList<TabExpedition1>();
	 
    private final String[] entetes = {"Taille","Prêt pour expédition","Quantité à expédier","Reste à expédier","Total"};
 
    public TabExpedition2() {
        super();
 
        infos.add(new TabExpedition1(30,0,0,0,0));
        infos.add(new TabExpedition1(32,0,0,0,0));
        infos.add(new TabExpedition1(34,0,0,0,0));
        infos.add(new TabExpedition1(36,0,0,0,0));
        infos.add(new TabExpedition1(38,0,0,0,0));
        infos.add(new TabExpedition1(40,0,0,0,0));
        infos.add(new TabExpedition1(42,0,0,0,0));
        infos.add(new TabExpedition1(44,0,0,0,0));
        infos.add(new TabExpedition1(46,0,0,0,0));
        infos.add(new TabExpedition1(48,0,0,0,0));
        
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
    	if (columnIndex == 0 || columnIndex == 1 || columnIndex == 3 || columnIndex == 4) return false;
    	return true; //Toutes les cellules éditables
    }
     
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if(aValue != null){
            TabExpedition1 info = infos.get(rowIndex);
     
            switch(columnIndex){
                case 0:
                    info.setTaille((int)aValue);
                    break;
                case 1:
                    info.setPret((int)aValue);
                    break;
                case 2:
                	info.setQuantite(Integer.parseInt((String)aValue));
                    break;
                case 3:
                    info.setTotal((int)aValue);
                    break;
                case 4:
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
            	return infos.get(rowIndex).getPret();
            case 2:
            	return infos.get(rowIndex).getQuantite();                
            case 3:
            	return infos.get(rowIndex).getReste();
            case 4:
            	return infos.get(rowIndex).getTotal();
            default:
                return null; //Ne devrait jamais arriver
        }
    }
 
    public void addAmi(TabExpedition1 info) {
        infos.add(info);
 
        fireTableRowsInserted(infos.size() -1, infos.size() -1);
    }
 
    public void removeAmi(int rowIndex) {
        infos.remove(rowIndex);
 
        fireTableRowsDeleted(rowIndex, rowIndex);
    }
}
