package chicstyle.controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import chicstyle.abstraction.ChicEtStyle;
import chicstyle.abstraction.Piece;
import chicstyle.presentation.DirectionStock;
import chicstyle.tableaux.TabBoite1;
import chicstyle.tableaux.TabDirectionStock1;
import chicstyle.tableaux.TabDirectionStock2;

public class ControlJButtonOkDirection implements ActionListener{

	private ChicEtStyle cets;
	private DirectionStock mere;
	private TabDirectionStock2 modele;

	public ControlJButtonOkDirection(ChicEtStyle cets, DirectionStock mere, TabDirectionStock2 modele) {
		super();
		this.cets = cets;
		this.mere = mere;
		this.modele = modele;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JTable tab = mere.getTableau();
		int nbrLignes = tab.getRowCount();
		
		Piece p = cets.findPiece(mere.getCombobox().getComboClients().getSelectedItem().toString(),
				mere.getCombobox().getComboModeles().getSelectedItem().toString(),
				mere.getCombobox().getComboTissus().getSelectedItem().toString(),
				mere.getCombobox().getComboCouleurs().getSelectedItem().toString()
				);
		
		if(p==null){
			JOptionPane.showMessageDialog(mere, "Attention mauvaise saisie pièce!", "alerte", JOptionPane.WARNING_MESSAGE);
		}
		else{
			Vector<String> ref = cets.findRefPiece(p.getId_piece());
			
			for(int j=0; j<nbrLignes; j++){
				modele.removeAmi(0);
			}
			
			for (int i=0; i < ref.size(); i++){
				String refCourant = ref.get(i);
				String type = cets.findTypeRef(refCourant);
				boolean test = cets.findEstTissuRef(refCourant);
				if(test){
					int total=0;
					for(int j=0; j<10; j++){
						int k = 30+2*j ;
						total = total + cets.findTotalClaudieDecoupe(p.getId_piece(),refCourant,k);
					}
					int stock = (int)(cets.findLRouleauStock(refCourant));
					int diff = total - stock;
					modele.addAmi(new TabDirectionStock1(refCourant,type,total,stock,diff));
				}
				else{
					Object[] o = cets.findRealisationBoiteRef(p.getId_piece(), refCourant);
						int besoin = (int)(o[1]);
						int dispo = (int)(o[2]);
						int diff = besoin - dispo;
						modele.addAmi(new TabDirectionStock1(refCourant,type,besoin,dispo,diff));
				}
			}
			
			mere.getDate().setText(cets.findDateLancement(p.getId_piece()));
		}
	}
}
