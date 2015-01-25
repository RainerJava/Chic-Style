package chicstyle.controle;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import chicstyle.abstraction.ChicEtStyle;
import chicstyle.abstraction.Recep_Fournitures;
import chicstyle.abstraction.Recep_Tissu;
import chicstyle.presentation.ClaudieAccueil;
import chicstyle.presentation.ClaudieReception;
import chicstyle.presentation.VanessaReceptionFournitures;

public class ControlJButtonEnregistrerRecepTissu implements ActionListener {

	private ChicEtStyle cets;
	private ClaudieReception mere; 
	private Frame fille;

	public ControlJButtonEnregistrerRecepTissu(ChicEtStyle cets, ClaudieReception mere,
			Frame fille) {
		super();
		this.cets = cets;
		this.mere = mere;
		this.fille = fille;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String text = mere.getQuantite().getText();

		String ref = mere.getComboRef().getSelectedItem()+"";
		double laize = Double.parseDouble(mere.getLaize().getText());
		
		double ancien_stock = cets.findLongueurRouleauRecep_Tissu(ref, laize);
		double nouveau_stock = ancien_stock + Double.parseDouble(text);
		
		Recep_Tissu recep = new Recep_Tissu(ref, laize, nouveau_stock);

		int choix = JOptionPane.showConfirmDialog(mere, "Confirmez-vous la reception de ce tissu ?", "Validation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		switch (choix){
		case JOptionPane.YES_OPTION :
			cets.saveRecep_Tissu(recep, true);
			JOptionPane.showMessageDialog(mere, "Stock tissu mis à jour", "info", JOptionPane.PLAIN_MESSAGE); 
			fille = new ClaudieAccueil(mere.getMere().getMere(),cets,true) ;
			fille.setVisible(true);
			mere.dispose();
			break;
		}
	}
}