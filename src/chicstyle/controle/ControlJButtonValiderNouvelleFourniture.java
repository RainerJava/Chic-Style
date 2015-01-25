package chicstyle.controle;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import chicstyle.abstraction.ChicEtStyle;
import chicstyle.presentation.VanessaReceptionFournitures;

public class ControlJButtonValiderNouvelleFourniture implements ActionListener {
	
	private VanessaReceptionFournitures mere;
	private Frame fille;
	private ChicEtStyle modele;
	
	public ControlJButtonValiderNouvelleFourniture(VanessaReceptionFournitures mere, Frame fille){
		this.mere = mere;
		this.fille = fille;
	}
	
	public void actionPerformed(ActionEvent e){
		int choix = JOptionPane.showConfirmDialog(mere, "Confirmez-vous l'ajout de cette fourniture ?", "Validation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		switch (choix){
		case JOptionPane.YES_OPTION :
				JOptionPane.showMessageDialog(mere, "Fourniture Enregistrée", "info", JOptionPane.PLAIN_MESSAGE); 
				fille.setVisible(true) ;
				mere.setVisible(false);
				break;
		}
	}
}
