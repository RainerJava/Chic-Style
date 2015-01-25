package chicstyle.tableaux;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class TabPret2 extends AbstractTableModel{

	private final List<TabPret1> infos = new ArrayList<TabPret1>();
	 
    private final String[] entetes = {"Client", "Modèle", "Tissu", "Couleur", "Taille","Quantité"};
 
    public TabPret2() {
        super();
 
        infos.add(new TabPret1("", "" , "", "", "", ""));
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false; //Toutes les cellules ï¿½ditables
    }
     
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if(aValue != null){
            TabPret1 info = infos.get(rowIndex);
     
            switch(columnIndex){
                case 0:
                    info.setClient((String)aValue);
                    break;
                case 1:
                    info.setModele((String)aValue);
                    break;
                case 2:
                    info.setTissu((String)aValue);
                    break;
                case 3:
                    info.setCouleur((String)aValue);
                    break;
                case 4:
                    info.setTaille((String)aValue);
                case 5:
                	info.setQuantite((String)aValue);
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
                return infos.get(rowIndex).getClient();
            case 1:
                return infos.get(rowIndex).getModele();
            case 2:
                return infos.get(rowIndex).getTissu();
            case 3:
                return infos.get(rowIndex).getCouleur();
            case 4:
                return infos.get(rowIndex).getTaille();
            case 5:
            	return infos.get(rowIndex).getQuantite();
            default:
                return null; //Ne devrait jamais arriver
        }
    }
 
    public void addAmi(TabPret1 info) {
        infos.add(info);
 
        fireTableRowsInserted(infos.size() -1, infos.size() -1);
    }
 
    public void removeAmi(int rowIndex) {
        infos.remove(rowIndex);
 
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

}
