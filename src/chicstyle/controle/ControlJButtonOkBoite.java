package chicstyle.controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import chicstyle.abstraction.ChicEtStyle;
import chicstyle.abstraction.Piece;
import chicstyle.presentation.VanessaRealisationBoite;
import chicstyle.tableaux.TabBoite1;
import chicstyle.tableaux.TabBoite2;

public class ControlJButtonOkBoite implements ActionListener {

	private ChicEtStyle cets;
	private VanessaRealisationBoite mere;
	private TabBoite2 modele = new TabBoite2(); //tableau

	public ControlJButtonOkBoite(ChicEtStyle cets, VanessaRealisationBoite mere, TabBoite2 modele) {
		super();
		this.cets = cets;
		this.mere = mere;
		this.modele = modele;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Piece p = cets.findPiece(mere.getCombobox().getComboClients().getSelectedItem().toString(),
				mere.getCombobox().getComboModeles().getSelectedItem().toString(),
				mere.getCombobox().getComboTissus().getSelectedItem().toString(),
				mere.getCombobox().getComboCouleurs().getSelectedItem().toString()
				);

		JTable tab = mere.getTableau();
		
		if(p==null){
			JOptionPane.showMessageDialog(mere, "Attention mauvaise saisie pièce!", "alerte", JOptionPane.WARNING_MESSAGE);
		}
		else{
			Vector<Object> o = cets.findRealisationBoite(p.getId_piece());

			int nbrLignes = tab.getRowCount();
			for(int j=0; j<nbrLignes; j++){
				modele.removeAmi(0);
			}

			for (int i=0; i<o.size();i++){
				Object[] temp = (Object[])o.get(i);
				if(!cets.findOkBoite(p.getId_piece(), temp[0].toString())){
					String ref = temp[0].toString();
					String type = temp[1].toString();
					int besoin = Integer.parseInt(temp[2].toString());
					int qte = Integer.parseInt(temp[3].toString());
					boolean ok = cets.findOkBoite(p.getId_piece(), temp[0].toString());
					modele.addAmi(new TabBoite1(ref,type,besoin,qte,ok));
				}
			}

			mere.getTextedate().setText(cets.findDateLancement(p.getId_piece()));

		}
	}
}
