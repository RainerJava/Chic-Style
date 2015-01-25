package chicstyle.controle;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JComboBox;

import chicstyle.abstraction.ChicEtStyle;
import chicstyle.presentation.CatherineExpedition;
import chicstyle.presentation.CatherineNouvelleCommande;

public class ControlJComboBoxClients implements ActionListener{

	private ChicEtStyle cets;
	private JComboBox suivant;

	public ControlJComboBoxClients(ChicEtStyle cets,JComboBox suivant) {
		super();
		this.cets = cets;
		this.suivant = suivant;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		JComboBox<String> comboClients = (JComboBox<String>)(e.getSource());
		String clientCourant = comboClients.getSelectedItem().toString();
		Vector<String> modeles = cets.findAllModelesClient(clientCourant);
		suivant.removeAllItems();
		suivant.addItem("-");
		for (int i=0; i<modeles.size(); i++){
			suivant.addItem(modeles.get(i));
		}
	}


}
