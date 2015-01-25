package chicstyle.controle;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import chicstyle.abstraction.ChicEtStyle;
import chicstyle.abstraction.Lancement_Prod;
import chicstyle.abstraction.Piece;
import chicstyle.presentation.DirectionStock;

public class ControlJButtonOkProd implements ActionListener {

	private ChicEtStyle cets;
	private DirectionStock mere;
	private Frame fille;
	
	public ControlJButtonOkProd(ChicEtStyle cets, DirectionStock mere, Frame fille) {
		super();
		this.cets = cets;
		this.mere = mere;
		this.fille = fille;
	}
	
	public void actionPerformed(ActionEvent e) {
		
		Piece p = cets.findPiece(mere.getCombobox().getComboClients().getSelectedItem().toString(),
				mere.getCombobox().getComboModeles().getSelectedItem().toString(),
				mere.getCombobox().getComboTissus().getSelectedItem().toString(),
				mere.getCombobox().getComboCouleurs().getSelectedItem().toString()
				);
		
		String date = mere.getDate().getText();
		Lancement_Prod lancement = new Lancement_Prod(p.getId_piece().toString(),date);
		cets.saveDateLancement(lancement, true);
		
		JOptionPane.showMessageDialog(mere, "Date de lancement enregistrée", "info", JOptionPane.PLAIN_MESSAGE); 
		fille.setVisible(true);
		mere.dispose();
	}
}
