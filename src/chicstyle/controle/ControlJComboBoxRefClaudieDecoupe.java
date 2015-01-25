package chicstyle.controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import chicstyle.abstraction.ChicEtStyle;
import chicstyle.abstraction.Piece;
import chicstyle.presentation.ClaudieDecoupe;

public class ControlJComboBoxRefClaudieDecoupe implements ActionListener{

	private ChicEtStyle cets;
	private ClaudieDecoupe mere;

	public ControlJComboBoxRefClaudieDecoupe(ChicEtStyle cets, ClaudieDecoupe mere) {
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
				String type = cets.findTypeRef(refCourant);
				mere.getTexteType().setText(type);
				int total=0;
				for(int i=0; i<10; i++){
					int j = 30+2*i ;
					total = total + cets.findTotalClaudieDecoupe(p.getId_piece(),refCourant,j);
				}
				mere.getTexteTotal().setText(total+"");
				int stock = (int)(cets.findLRouleauStock(refCourant));
				mere.getTexteStock().setText(cets.findLRouleauStock(refCourant)+"");
				int reste =total - cets.findLongueurCoupee(p.getId_piece(), refCourant);
				mere.getTexteReste().setText(reste+"");
			}
		}
	}
}
