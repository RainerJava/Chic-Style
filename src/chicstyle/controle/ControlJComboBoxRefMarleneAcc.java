package chicstyle.controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import chicstyle.abstraction.ChicEtStyle;
import chicstyle.abstraction.Piece;
import chicstyle.presentation.ClaudieReception;
import chicstyle.presentation.MarleneAccueil;

public class ControlJComboBoxRefMarleneAcc implements ActionListener {

	private ChicEtStyle cets;
	private MarleneAccueil mere;

	public ControlJComboBoxRefMarleneAcc(ChicEtStyle cets, MarleneAccueil mere) {
		super();
		this.cets = cets;
		this.mere = mere;
	}

	public void actionPerformed(ActionEvent e) {
		Piece p = cets.findPiece(mere.getComboBoxes().getComboClients().getSelectedItem().toString(),
				mere.getComboBoxes().getComboModeles().getSelectedItem().toString(),
				mere.getComboBoxes().getComboTissus().getSelectedItem().toString(),
				mere.getComboBoxes().getComboCouleurs().getSelectedItem().toString()
				);
		JComboBox<String> comboRefs = (JComboBox<String>)(e.getSource());
		Object test = comboRefs.getSelectedItem();
		if(test != null){
			String refCourant = comboRefs.getSelectedItem().toString();
			if (cets.findRef(refCourant)!=null){
				double longueurAnnoncee = cets.findNomenclature(p.getId_piece(), refCourant).getQte_unit();
				mere.getLon().setText(longueurAnnoncee+"");
			}
		}
	}
}
