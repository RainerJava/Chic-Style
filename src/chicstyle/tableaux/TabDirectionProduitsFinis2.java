package chicstyle.tableaux;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class TabDirectionProduitsFinis2 extends AbstractTableModel{

	private final List<TabDirectionProduitsFinis1> infos = new ArrayList<TabDirectionProduitsFinis1>();
	 
    private final String[] entetes = {"Modèle", "Tissu", "Couleur", "Taille", "Quantité houssée", "Quantité expédiée", "Total", "Différence"};
 
    public TabDirectionProduitsFinis2() {
        super();
        infos.add(new TabDirectionProduitsFinis1("0", "0" , "0", 0, 0, 0, 0, 0));

        
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
    	return false;
    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if(aValue != null){
            TabDirectionProduitsFinis1 info = infos.get(rowIndex);
     
            switch(columnIndex){
            	case 0:
            		info.setModele((String)aValue);
            		break;
            	case 1:
            		info.setTissu((String)aValue);
            		break;
            	case 2:
            		info.setCouleur((String)aValue);
            		break;
                case 3:
                	info.setTaille(Integer.parseInt((String)aValue));
                    break;
                case 4:
                    info.setHousse(Integer.parseInt((String)aValue));
                    break;
                case 5:
                    info.setExpedie(Integer.parseInt((String)aValue));
                    break;
                case 6:
                    info.setTotal(Integer.parseInt((String)aValue));
                    break;
                case 7:
                    info.setDiff(Integer.parseInt((String)aValue));
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
        		return infos.get(rowIndex).getModele();
        	case 1:
        		return infos.get(rowIndex).getTissu();
        	case 2:
        		return infos.get(rowIndex).getCouleur();
            case 3:
                return infos.get(rowIndex).getTaille();
            case 4:
                return infos.get(rowIndex).getHousse();
            case 5:
                return infos.get(rowIndex).getExpedie();
            case 6:
                return infos.get(rowIndex).getTotal();
            case 7:
            	return infos.get(rowIndex).getDiff();
            default:
                return null; //Ne devrait jamais arriver
        }
    }
 
    public void addAmi(TabDirectionProduitsFinis1 info) {
        infos.add(info);
 
        fireTableRowsInserted(infos.size() -1, infos.size() -1);
    }
 
    public void removeAmi(int rowIndex) {
        infos.remove(rowIndex);
 
        fireTableRowsDeleted(rowIndex, rowIndex);
    }
}
