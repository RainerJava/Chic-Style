package chicstyle.controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import chicstyle.abstraction.ChicEtStyle;
import chicstyle.abstraction.Piece;
import chicstyle.presentation.CatherineExpedition;
import chicstyle.tableaux.TabExpedition1;
import chicstyle.tableaux.TabExpedition2;
import chicstyle.tableaux.TabVanessaHoussage1;
import chicstyle.tableaux.TabVanessaHoussage2;

public class ControlJButtonOkExpedition implements ActionListener{

	private ChicEtStyle cets;
	private CatherineExpedition mere;
	private TabExpedition2 modele;


	public ControlJButtonOkExpedition(ChicEtStyle cets, CatherineExpedition mere, TabExpedition2 modele) {
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
			for(int j=0; j<nbrLignes; j++){
				modele.removeAmi(0);
			}

			for (int i=0; i < nbrLignes; i++){
				int j = 30+2*i;
				int pret = cets.findHousser(p.getId_piece(), j).getQte();
				int total = cets.findLancement(p.getId_piece(),j).getQte();
				int qte_exp = 0;
				if(cets.findExpedier(p.getId_piece(), j)!=null){qte_exp=cets.findExpedier(p.getId_piece(), j).getQte();}
				int reste = total - qte_exp;
				modele.addAmi(new TabExpedition1(j,pret,0,reste,total));
			}	
		}
	}
}
