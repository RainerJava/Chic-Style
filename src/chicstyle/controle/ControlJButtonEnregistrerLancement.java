package chicstyle.controle;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import chicstyle.abstraction.ChicEtStyle;
import chicstyle.abstraction.Lancement;
import chicstyle.abstraction.Piece;
import chicstyle.presentation.CatherineLancement;

public class ControlJButtonEnregistrerLancement implements ActionListener {

	private ChicEtStyle cets;
	private CatherineLancement mere; 
	private Frame fille;
	
	public ControlJButtonEnregistrerLancement(ChicEtStyle cets, CatherineLancement mere,
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
		
		if(p==null){
			JOptionPane.showMessageDialog(mere, "Attention mauvaise saisie pièce!", "alerte", JOptionPane.WARNING_MESSAGE);
		}
		else{
			for (int i=0; i < nbrLignes; i++){
				int taille = Integer.parseInt(tab.getValueAt(i,0).toString());
				int qte = (int)(tab.getValueAt(i,1));
				Lancement lanc = new Lancement(p.getId_piece(),taille,qte);
				cets.saveLancement(lanc, true);
			}
			JOptionPane.showMessageDialog(mere, "Lancement Enregistré", "info", JOptionPane.PLAIN_MESSAGE); 
			fille.setVisible(true);
			mere.dispose();
		}
	}
}
