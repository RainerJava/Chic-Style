package chicstyle.controle;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import chicstyle.abstraction.ChicEtStyle;
import chicstyle.abstraction.Piece;
import chicstyle.presentation.CatherineAccueil;
import chicstyle.presentation.CatherineNouvelleCommande;


public class ControlJButtonEnregistrerCommande implements ActionListener{

	private ChicEtStyle cets;
	private CatherineNouvelleCommande mere; 
	private Frame fille;


	public ControlJButtonEnregistrerCommande(ChicEtStyle cets,CatherineNouvelleCommande mere, Frame fille) {
		this.cets = cets;
		this.mere = mere;
		this.fille = fille;
	}

	public void actionPerformed(ActionEvent e) {
		Piece p = new Piece(mere.getComboClients().getSelectedItem().toString(),
				mere.getNom().getText(),
				mere.getTis().getText(),
				mere.getCou().getText(),
				mere.getTy().getText()
				);
		try {
			cets.savePiece(p, true);
			JOptionPane.showMessageDialog(mere, "Commande Enregistrée", "Validation de votre saisie", JOptionPane.PLAIN_MESSAGE); 
			new CatherineAccueil(this.mere.getMere().getMere(), cets, true).setVisible(true);
			mere.dispose();
		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(mere, "On ne peut pas!", "Erreur!",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}