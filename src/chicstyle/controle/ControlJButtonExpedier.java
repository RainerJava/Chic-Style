package chicstyle.controle;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import chicstyle.abstraction.ChicEtStyle;
import chicstyle.abstraction.Expedier;
import chicstyle.abstraction.Housser;
import chicstyle.abstraction.Piece;
import chicstyle.presentation.CatherineExpedition;

public class ControlJButtonExpedier implements  ActionListener{

	private ChicEtStyle cets;
	private CatherineExpedition mere; 
	private Frame fille;

	public ControlJButtonExpedier(ChicEtStyle cets, CatherineExpedition mere,
			Frame fille) {
		super();
		this.cets = cets;
		this.mere = mere;
		this.fille = fille;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		JTable tab = mere.getTableau();
		Piece p = cets.findPiece(mere.getCombobox().getComboClients().getSelectedItem().toString(),
				mere.getCombobox().getComboModeles().getSelectedItem().toString(),
				mere.getCombobox().getComboTissus().getSelectedItem().toString(),
				mere.getCombobox().getComboCouleurs().getSelectedItem().toString()
				);

		int nbrLignes = tab.getRowCount();
		for (int i=0; i < nbrLignes; i++){
			String id_piece = p.getId_piece();
			int taille = (int)tab.getValueAt(i, 0);
			int qte = (int)tab.getValueAt(i,2);
			Expedier e = new Expedier(id_piece, taille, qte);
			cets.saveExpedier(e, true);
			Housser h = new Housser(p.getId_piece(), taille, ((int)(tab.getValueAt(i,1))) - qte);
			cets.saveHoussage(h, true);
			
		}
		JOptionPane.showMessageDialog(mere, "Expedié", "info", JOptionPane.PLAIN_MESSAGE); 
		fille.setVisible(true);
		mere.dispose();
	}


}
