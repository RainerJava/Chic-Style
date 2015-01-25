package chicstyle.controle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import chicstyle.abstraction.ChicEtStyle;
import chicstyle.abstraction.Piece;
import chicstyle.presentation.MarleneAccueil;

public class ControlJButtonOkMarleneAccueil implements ActionListener{

	private ChicEtStyle cets;
	private MarleneAccueil mere;

	public ControlJButtonOkMarleneAccueil(ChicEtStyle cets, MarleneAccueil mere) {
		super();
		this.cets = cets;
		this.mere = mere;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Piece p = cets.findPiece(mere.getComboBoxes().getComboClients().getSelectedItem().toString(),
				mere.getComboBoxes().getComboModeles().getSelectedItem().toString(),
				mere.getComboBoxes().getComboTissus().getSelectedItem().toString(),
				mere.getComboBoxes().getComboCouleurs().getSelectedItem().toString()
				);

		JComboBox<String> comboRef = mere.getComboRef();
		
		if(p==null){
			JOptionPane.showMessageDialog(mere, "Attention mauvaise saisie pièce!", "alerte", JOptionPane.WARNING_MESSAGE);
		}
		else{
			Vector<String> refs = cets.findRefPieceEstTissu(p.getId_piece(), true);

			comboRef.removeAllItems();
			comboRef.addItem("-");
			for (int i=0; i<refs.size(); i++){
				comboRef.addItem(refs.get(i));
			}
		}

	}


}