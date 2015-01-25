package chicstyle.tableaux;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class TabNomenclature2 extends AbstractTableModel{

	private final List<TabNomenclature1> infos = new ArrayList<TabNomenclature1>();
	 
    private final String[] entetes = {"Référence", "Type", "Quantité Unitaire", "Facturé?", "Tissu?"};
 
    public TabNomenclature2() {
        super();
 
        infos.add(new TabNomenclature1("", "" , 0, false, false));
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true; //Toutes les cellules éditables
    }
     
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if(aValue != null){
            TabNomenclature1 info = infos.get(rowIndex);
     
            switch(columnIndex){
                case 0:
                    info.setReference((String)aValue);
                    break;
                case 1:
                    info.setType((String)aValue);
                    break;
                case 2:
                    info.setqteUnitaire((Double.parseDouble(aValue+"")));
                    break;
                case 3:
                    info.setFacture((Boolean)aValue);
                    break;
                case 4:
                    info.setTissu((Boolean)aValue);
                    break;
            }
        }
    }
    @Override
    public Class getColumnClass(int columnIndex){
        switch(columnIndex){
            case 2:
                return Double.class;
            case 3:
                return Boolean.class;
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
                return infos.get(rowIndex).getqteUnitaire();
            case 3:
                return infos.get(rowIndex).isFacture();
            case 4:
                return infos.get(rowIndex).isTissu();
            default:
                return null; //Ne devrait jamais arriver
        }
    }
 
    public void addAmi(TabNomenclature1 info) {
        infos.add(info);
 
        fireTableRowsInserted(infos.size() -1, infos.size() -1);
    }
 
    public void removeAmi(int rowIndex) {
        infos.remove(rowIndex);
 
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

}
