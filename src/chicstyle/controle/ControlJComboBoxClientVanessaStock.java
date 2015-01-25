package chicstyle.controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JTable;

import chicstyle.abstraction.ChicEtStyle;
import chicstyle.abstraction.Piece;
import chicstyle.presentation.VanessaStock;
import chicstyle.tableaux.TabStockVanessa1;
import chicstyle.tableaux.TabStockVanessa2;


public class ControlJComboBoxClientVanessaStock implements ActionListener {

	private ChicEtStyle cets;
	private VanessaStock mere;
	private TabStockVanessa2 modele;

	public ControlJComboBoxClientVanessaStock(ChicEtStyle cets, VanessaStock mere, TabStockVanessa2 modele) {
		super();
		this.cets = cets;
		this.mere = mere;
		this.modele = modele;
	}

	public void actionPerformed(ActionEvent e) {
		JTable tab = mere.getTableau();
		int nbrLignes = tab.getRowCount();

		String client = mere.getComboClients().getSelectedItem().toString();

		Vector<String> ref = cets.findRefPiecePasTissu(client);

		for(int j=0; j<nbrLignes; j++){
			modele.removeAmi(0);
		}

		for (int i=0; i < ref.size(); i++){
			String refCourant = ref.get(i);
			String type = cets.findTypeRef(refCourant);
			int total = cets.findRecep_Fournitures(refCourant);
			modele.addAmi(new TabStockVanessa1(type,refCourant,total));
		}
	}
}
