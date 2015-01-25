package chicstyle.controle;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import chicstyle.abstraction.Boite;
import chicstyle.abstraction.ChicEtStyle;
import chicstyle.abstraction.Piece;
import chicstyle.abstraction.Recep_Fournitures;
import chicstyle.presentation.VanessaRealisationBoite;

public class ControlJButtonValiderBoite implements ActionListener {

	private ChicEtStyle cets;
	private VanessaRealisationBoite mere; 
	private Frame fille;

	public ControlJButtonValiderBoite(ChicEtStyle cets, VanessaRealisationBoite mere,
			Frame fille) {
		super();
		this.cets = cets;
		this.mere = mere;
		this.fille = fille;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		Piece p = cets.findPiece(mere.getCombobox().getComboClients().getSelectedItem().toString(),
				mere.getCombobox().getComboModeles().getSelectedItem().toString(),
				mere.getCombobox().getComboTissus().getSelectedItem().toString(),
				mere.getCombobox().getComboCouleurs().getSelectedItem().toString()
				);
		
		JTable tab = mere.getTableau();
		int nbrLignes = tab.getRowCount();
		
		for(int i=0; i<nbrLignes; i++){
			Object ok = tab.getValueAt(i, 4);
			if(ok != null){
				int ancien_stock = cets.findRecep_Fournitures(tab.getValueAt(i, 0).toString());
				int nouveau_stock = ancien_stock - Integer.parseInt(tab.getValueAt(i, 2).toString());
				Recep_Fournitures recep = new Recep_Fournitures(tab.getValueAt(i, 0).toString(), nouveau_stock);
				cets.saveRecep_Fournitures(recep, true);
			}
			Boite b = new Boite(p.getId_piece(),tab.getValueAt(i, 0).toString(),(boolean)ok);
			cets.saveOkBoite(b, true);
		}

		int choix = JOptionPane.showConfirmDialog(mere, "Confirmez-vous la réalisation de cette boîte ?", "Validation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		switch (choix){
		case JOptionPane.YES_OPTION :
			JOptionPane.showMessageDialog(mere, "Fourniture Enregistrée", "info", JOptionPane.PLAIN_MESSAGE); 
			fille.setVisible(true) ;
			mere.dispose();
			break;
		}
	}
}
