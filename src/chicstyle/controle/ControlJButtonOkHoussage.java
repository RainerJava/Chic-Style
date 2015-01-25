package chicstyle.controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import chicstyle.abstraction.ChicEtStyle;
import chicstyle.abstraction.Housser;
import chicstyle.abstraction.Lancement;
import chicstyle.abstraction.Piece;
import chicstyle.presentation.VanessaHoussage;
import chicstyle.tableaux.TabBoite1;
import chicstyle.tableaux.TabVanessaHoussage1;
import chicstyle.tableaux.TabVanessaHoussage2;

public class ControlJButtonOkHoussage implements ActionListener {
	
	private ChicEtStyle cets;
	private VanessaHoussage mere;
	private TabVanessaHoussage2 modele;

	public ControlJButtonOkHoussage(ChicEtStyle cets, VanessaHoussage mere, TabVanessaHoussage2 modele) {
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
		
		JTextField texteType = mere.getTexteType();
		
		if(p==null){
			JOptionPane.showMessageDialog(mere, "Attention mauvaise saisie pièce!", "alerte", JOptionPane.WARNING_MESSAGE);
		}
		else{
			texteType.setText(p.getType()); 

			for(int j=0; j<nbrLignes; j++){
				modele.removeAmi(0);
			}
			
			for (int i=0; i < nbrLignes; i++){
				int j = 30+2*i;
				int total = cets.findLancement(p.getId_piece(),j).getQte();
				int qte_exp = 0;
				if(cets.findExpedier(p.getId_piece(), j)!=null){qte_exp=cets.findExpedier(p.getId_piece(), j).getQte();}
				int qte_hous = 0;
				if(cets.findHousser(p.getId_piece(), j)!=null){qte_hous=cets.findHousser(p.getId_piece(), j).getQte();}
				int reste = total - qte_exp - qte_hous;
				modele.addAmi(new TabVanessaHoussage1(j,0,total,reste));
			}
			mere.getTextedate().setText(cets.findDateLancement(p.getId_piece()));
		}
	}
}
