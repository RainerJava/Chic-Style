package chicstyle.controle;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JComboBox;

import chicstyle.abstraction.ChicEtStyle;

public class ControlJComboBoxTissus implements ActionListener{

	private ChicEtStyle cets;
	private JComboBox suivant;

	public ControlJComboBoxTissus(ChicEtStyle cets,JComboBox suivant) {
		super();
		this.cets = cets;
		this.suivant = suivant;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JComboBox<String> comboTissus = (JComboBox<String>)(e.getSource());
		Object test = comboTissus.getSelectedItem();
		if(test != null){
			String tissuCourant = comboTissus.getSelectedItem().toString();
			Vector<String> couleurs = cets.findAllCouleursTissu(tissuCourant);
			suivant.removeAllItems();
			suivant.addItem("-");
			for (int i=0; i<couleurs.size(); i++){
				suivant.addItem(couleurs.get(i));
			}
		}
	}
}