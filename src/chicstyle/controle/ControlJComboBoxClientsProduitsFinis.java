package chicstyle.controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JTable;

import chicstyle.abstraction.ChicEtStyle;

public class ControlJComboBoxClientsProduitsFinis implements ActionListener{

	private ChicEtStyle cets;
	private JComboBox suivant;
	private JTable tableau;

	public ControlJComboBoxClientsProduitsFinis(ChicEtStyle cets, JComboBox suivant, JTable tableau) {
		super();
		this.cets = cets;
		this.suivant = suivant;
		this.tableau = tableau;
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
		for (int i=0; i<cets.findProduitsFinisClient(clientCourant).size(); i++){
			String[] resultat =(String[]) cets.findProduitsFinisClient(clientCourant).get(i);
			
			tableau.setValueAt(resultat[0], i, 0);
			tableau.setValueAt(resultat[1], i, 1);
			tableau.setValueAt(resultat[2], i, 2);
			tableau.setValueAt(resultat[3], i, 3);
			tableau.setValueAt(resultat[4], i, 4);
			tableau.setValueAt(resultat[5], i, 5);
			tableau.setValueAt(resultat[6], i, 6);
			tableau.setValueAt(Integer.parseInt(resultat[6])-Integer.parseInt(resultat[5]), i, 7);

		}
	
	}


}
