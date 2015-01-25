package chicstyle.controle;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import chicstyle.abstraction.Alertes;
import chicstyle.abstraction.ChicEtStyle;
import chicstyle.presentation.MarleneAccueil;
import chicstyle.presentation.MarleneAlerte;

public class ControlJButtonEnregistrerAlerteMarlene implements ActionListener{
	
	private ChicEtStyle cets;
	private MarleneAlerte mere; 
	private Frame fille;
	
	public ControlJButtonEnregistrerAlerteMarlene(ChicEtStyle cets, MarleneAlerte mere,
			Frame fille) {
		super();
		this.cets = cets;
		this.mere = mere;
		this.fille = fille;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		String source = "Patronnage";
		String message = mere.getMessage().getText();
		
		char[] test = message.toCharArray();
		String s = "'";
		int i = 0;
		boolean trouver = false;
		while (i<test.length  && trouver==false){
			if (test[i] == s.charAt(0)){
				JOptionPane.showMessageDialog(mere, "Interdit ! Votre message contient une ' ", "info", JOptionPane.WARNING_MESSAGE);
				trouver = true;
			}
			i++;
		}
		if (trouver==false){
			Alertes al = new Alertes(source, message);
			cets.saveAlerte(al);

			JOptionPane.showMessageDialog(mere, "Alerte Envoyée", "info", JOptionPane.PLAIN_MESSAGE); 
			fille = new MarleneAccueil(mere.getMere().getMere(),cets,true);
			fille.setVisible(true);
			mere.dispose();
		}
	}


}
