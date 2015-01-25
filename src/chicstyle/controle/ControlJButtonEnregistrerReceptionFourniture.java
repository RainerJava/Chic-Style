package chicstyle.controle;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import chicstyle.abstraction.ChicEtStyle;
import chicstyle.abstraction.Housser;
import chicstyle.abstraction.Piece;
import chicstyle.abstraction.Recep_Fournitures;
import chicstyle.presentation.VanessaHoussage;
import chicstyle.presentation.VanessaReceptionFournitures;
import chicstyle.tableaux.TabNomenclature1;

public class ControlJButtonEnregistrerReceptionFourniture implements ActionListener {

	private ChicEtStyle cets;
	private VanessaReceptionFournitures mere; 
	private Frame fille;

	public ControlJButtonEnregistrerReceptionFourniture(ChicEtStyle cets, VanessaReceptionFournitures mere,
			Frame fille) {
		super();
		this.cets = cets;
		this.mere = mere;
		this.fille = fille;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String text = mere.getQuantite().getText();

		String client = mere.getCombobox().getComboClients().getSelectedItem()+"";
		String ref = mere.getCombobox().getComboRefs().getSelectedItem()+"";
		
		int ancien_stock = cets.findRecep_Fournitures(ref);
		int nouveau_stock = ancien_stock + Integer.parseInt(text);
		
		Recep_Fournitures recep = new Recep_Fournitures(ref, nouveau_stock);
		cets.saveRecep_Fournitures(recep, true);

		int choix = JOptionPane.showConfirmDialog(mere, "Confirmez-vous l'ajout de cette fourniture ?", "Validation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		switch (choix){
		case JOptionPane.YES_OPTION :
			JOptionPane.showMessageDialog(mere, "Fourniture Enregistrée", "info", JOptionPane.PLAIN_MESSAGE); 
			fille.setVisible(true) ;
			mere.dispose();
			break;
		}
	}
}

