package chicstyle.controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import chicstyle.abstraction.ChicEtStyle;
import chicstyle.abstraction.Piece;
import chicstyle.presentation.ClaudieReception;

public class ControlJComboBoxRefClaudieRecep implements ActionListener {

	private ChicEtStyle cets;
	private ClaudieReception mere;

	public ControlJComboBoxRefClaudieRecep(ChicEtStyle cets, ClaudieReception mere) {
		super();
		this.cets = cets;
		this.mere = mere;
	}

	public void actionPerformed(ActionEvent e) {
		Piece p = cets.findPiece(mere.getCombobox().getComboClients().getSelectedItem().toString(),
				mere.getCombobox().getComboModeles().getSelectedItem().toString(),
				mere.getCombobox().getComboTissus().getSelectedItem().toString(),
				mere.getCombobox().getComboCouleurs().getSelectedItem().toString()
				);
		JComboBox<String> comboRefs = (JComboBox<String>)(e.getSource());
		Object test = comboRefs.getSelectedItem();
		if(test != null){
			String refCourant = comboRefs.getSelectedItem().toString();
			if (cets.findRef(refCourant)!=null){
				double laize_marlene = cets.findLaizePatronRef(p.getId_piece(), refCourant);
				String type = cets.findTypeRef(refCourant);
				mere.getTypeTissu().setText(type);
				mere.getLaizeMarlene().setText(laize_marlene+"");
				mere.getStock().setText(cets.findLRouleauStock(refCourant)+"");
			}
		}
	}
}
