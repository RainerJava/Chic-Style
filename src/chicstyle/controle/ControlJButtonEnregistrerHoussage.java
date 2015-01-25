package chicstyle.controle;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import chicstyle.abstraction.ChicEtStyle;
import chicstyle.abstraction.Housser;
import chicstyle.abstraction.Lancement;
import chicstyle.abstraction.Piece;
import chicstyle.presentation.VanessaHoussage;

public class ControlJButtonEnregistrerHoussage implements ActionListener {

	private ChicEtStyle cets;
	private VanessaHoussage mere; 
	private Frame fille;
	
	public ControlJButtonEnregistrerHoussage(ChicEtStyle cets, VanessaHoussage mere,
			Frame fille) {
		super();
		this.cets = cets;
		this.mere = mere;
		this.fille = fille;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		JTable tab = mere.getTableau();
		int nbrLignes = tab.getRowCount();
		
		String client = mere.getCombobox().getComboClients().getSelectedItem()+"";
		String modele = mere.getCombobox().getComboModeles().getSelectedItem()+"";
		String tissu = mere.getCombobox().getComboTissus().getSelectedItem()+"";
		String couleur = mere.getCombobox().getComboCouleurs().getSelectedItem()+"";
		Piece p = cets.findPiece(client, modele, tissu, couleur);
		
		for (int i=0; i < nbrLignes; i++){
			int taille = (int)(tab.getValueAt(i,0));
			int qte = Integer.parseInt(tab.getValueAt(i,1).toString());
			Housser houss = new Housser(p.getId_piece(),taille,qte);
			cets.saveHoussage(houss, true);
		}
		JOptionPane.showMessageDialog(mere, "Houssage Enregistré", "info", JOptionPane.PLAIN_MESSAGE); 
		fille.setVisible(true);
		mere.dispose();
	}
}
