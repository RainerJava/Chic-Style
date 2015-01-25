package chicstyle.controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JComboBox;

import chicstyle.abstraction.ChicEtStyle;

public class ControlJComboBoxRefClientVanessaRecep implements ActionListener {

	private ChicEtStyle cets;
	private JComboBox suivant;

	public ControlJComboBoxRefClientVanessaRecep(ChicEtStyle cets,JComboBox suivant) {
		super();
		this.cets = cets;
		this.suivant = suivant;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JComboBox<String> comboClients = (JComboBox<String>)(e.getSource());
		Object test = comboClients.getSelectedItem();
		if(test != null){
			String clientCourant = comboClients.getSelectedItem().toString();
			Vector<String> refs = cets.findRefPiecePasTissu(clientCourant);
			suivant.removeAllItems();
			suivant.addItem("-");
			for (int i=0; i<refs.size(); i++){
				suivant.addItem(refs.get(i));
			}
		}
	}
}
