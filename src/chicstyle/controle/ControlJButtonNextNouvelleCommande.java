package chicstyle.controle;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import chicstyle.presentation.CatherineAccueil;
import chicstyle.presentation.CatherineNouvelleCommande;

public class ControlJButtonNextNouvelleCommande implements ActionListener {

	private CatherineAccueil mere;

	public ControlJButtonNextNouvelleCommande(CatherineAccueil mere) {
		this.mere = mere;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		CatherineNouvelleCommande fille = new CatherineNouvelleCommande(mere, mere.getCets(),true);
		fille.setVisible(true);
		mere.setVisible(false);
	}
}

