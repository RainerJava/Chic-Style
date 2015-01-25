package chicstyle.controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import chicstyle.abstraction.ChicEtStyle;
import chicstyle.abstraction.Piece;
import chicstyle.presentation.ClaudieDecoupe;

public class ControlJButtonOkClaudieDecoupe implements ActionListener{

	private ChicEtStyle cets;
	private ClaudieDecoupe mere;

	public ControlJButtonOkClaudieDecoupe(ChicEtStyle cets, ClaudieDecoupe mere) {
		super();
		this.cets = cets;
		this.mere = mere;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Piece p = cets.findPiece(mere.getCombobox().getComboClients().getSelectedItem().toString(),
				mere.getCombobox().getComboModeles().getSelectedItem().toString(),
				mere.getCombobox().getComboTissus().getSelectedItem().toString(),
				mere.getCombobox().getComboCouleurs().getSelectedItem().toString()
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
