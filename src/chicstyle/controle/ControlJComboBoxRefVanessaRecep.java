package chicstyle.controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import chicstyle.abstraction.ChicEtStyle;
import chicstyle.presentation.VanessaReceptionFournitures;

public class ControlJComboBoxRefVanessaRecep implements ActionListener {

	private ChicEtStyle cets;
	private JTextField suivant;
	private JTextField stock;

	public ControlJComboBoxRefVanessaRecep(ChicEtStyle cets, JTextField suivant, JTextField stock) {
		super();
		this.cets = cets;
		this.suivant = suivant;
		this.stock = stock;
	}

	public void actionPerformed(ActionEvent e) {
		JComboBox<String> comboRefs = (JComboBox<String>)(e.getSource());
		Object test = comboRefs.getSelectedItem();
		if(test != null){
			String refCourant = comboRefs.getSelectedItem().toString();
			suivant.setText(cets.findTypeRef(refCourant));
			stock.setText(cets.findRecep_Fournitures(refCourant)+"");
		}
	}
}
