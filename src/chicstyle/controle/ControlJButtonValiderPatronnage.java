package chicstyle.controle;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import chicstyle.abstraction.ChicEtStyle;
import chicstyle.abstraction.Patron;
import chicstyle.abstraction.Piece;
import chicstyle.presentation.CatherineAccueil;
import chicstyle.presentation.CatherineNouvelleCommande;
import chicstyle.presentation.MarleneAccueil;

public class ControlJButtonValiderPatronnage implements ActionListener {

	private ChicEtStyle cets;
	private MarleneAccueil mere; 
	private Frame fille;


	public ControlJButtonValiderPatronnage(ChicEtStyle cets,
			MarleneAccueil mere, Frame fille) {
		this.cets = cets;
		this.mere = mere;
		this.fille = fille;
	}

	public void actionPerformed(ActionEvent e) {
		Piece p = cets.findPiece(mere.getComboBoxes().getComboClients().getSelectedItem().toString(),
				mere.getComboBoxes().getComboModeles().getSelectedItem().toString(),
				mere.getComboBoxes().getComboTissus().getSelectedItem().toString(),
				mere.getComboBoxes().getComboCouleurs().getSelectedItem().toString()
				);
		
		for (int i =0;i<10;i++){
			String longueur = mere.getTableau().getValueAt(i,1).toString();
			if (longueur.equals("")==false){
				Patron patron = new Patron(
						p.getId_piece(),
						mere.getComboRef().getSelectedItem().toString(),
						30+2*i,
						Double.parseDouble(mere.getLaizeField().getText()), 
						Double.parseDouble(longueur)) 
				;
				try {
					cets.savePatron(patron, true);
				} catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(mere, "On ne peut pas!", "Erreur!",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		try{
			JOptionPane.showMessageDialog(mere, "Patronnages enregistrés", "Validation de votre saisie", JOptionPane.PLAIN_MESSAGE); 
			new MarleneAccueil(mere.getMere(), cets, true).setVisible(true);
			mere.dispose();
		}catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(mere, "On ne peut pas!", "Erreur!",
					JOptionPane.ERROR_MESSAGE); 
		}
	}
}

