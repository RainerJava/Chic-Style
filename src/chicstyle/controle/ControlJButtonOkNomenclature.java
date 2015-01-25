package chicstyle.controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import chicstyle.abstraction.ChicEtStyle;
import chicstyle.abstraction.Piece;
import chicstyle.presentation.CatherineNomenclature;
import chicstyle.tableaux.TabBoite1;
import chicstyle.tableaux.TabNomenclature1;
import chicstyle.tableaux.TabNomenclature2;

public class ControlJButtonOkNomenclature implements ActionListener{

	private ChicEtStyle cets;
	private CatherineNomenclature mere;
	private TabNomenclature2 modele; //tableau

	public ControlJButtonOkNomenclature(ChicEtStyle cets, CatherineNomenclature mere, TabNomenclature2 modele) {
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
		
		if (p==null){
			JOptionPane.showMessageDialog(mere, "Attention mauvaise saisie pièce!", "alerte", JOptionPane.WARNING_MESSAGE);
		}
		else{
			Vector<Object> o = cets.findNomenclaturePiece(p.getId_piece());
			
			int nbrLignes = tab.getRowCount();
			for(int j=0; j<nbrLignes; j++){
				modele.removeAmi(0);
			}
			
			for (int i=0; i<o.size();i++){
				modele.addAmi(new TabNomenclature1("", "",0,false,false));
				Object[] temp = (Object[])o.get(i);
				tab.setValueAt(temp[0].toString(),i,0);
				tab.setValueAt(temp[1].toString(),i,1);
				tab.setValueAt(temp[2].toString(),i,2);
				tab.setValueAt((boolean)temp[3],i,3);
				tab.setValueAt((boolean)temp[4],i,4);
			}
		}

	}
}