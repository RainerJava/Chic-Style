package chicstyle.controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import chicstyle.abstraction.ChicEtStyle;
import chicstyle.presentation.DirectionAccueil;
import chicstyle.presentation.DirectionProduitsFinis;
import chicstyle.presentation.DirectionSupprimer;
import chicstyle.tableaux.TabDirectionProduitsFinis1;
import chicstyle.tableaux.TabDirectionProduitsFinis2;

public class ControlJButtonValiderSuppression implements ActionListener{

	private ChicEtStyle cets;
	private DirectionSupprimer mere;
	private DirectionAccueil fille;
	
	public ControlJButtonValiderSuppression(ChicEtStyle cets, DirectionSupprimer mere, DirectionAccueil fille) {
		super();
		this.cets = cets;
		this.mere = mere;
		this.fille = fille;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String clientSelect = (mere.getCombobox().getComboClients().getSelectedItem().toString());
		String modeleSelect = (mere.getCombobox().getComboModeles().getSelectedItem().toString());
		String tissuSelect 	= (mere.getCombobox().getComboTissus().getSelectedItem().toString());
		String couleurSelect = (mere.getCombobox().getComboCouleurs().getSelectedItem().toString());

		if ((clientSelect!="-")
				&& (modeleSelect=="-")
				&& (tissuSelect=="-")
				&& (couleurSelect=="-")){
			Vector<String> idsPieces = cets.findIdPieceClient(clientSelect);
			for (int i=0; i<idsPieces.size(); i++){
				cets.deletePiece(idsPieces.get(i));
			}
//			Vector<String> refs = cets.findAllRefClient(clientSelect);
//			for (int j=0; j<idsPieces.size(); j++){
//				cets.deleteRreferencage(refs.get(j));
//			}

		}else if  ((clientSelect!="-")
				&& (modeleSelect!="-")
				&& (tissuSelect=="-")
				&& (couleurSelect=="-")){
			Vector<String> idsPieces = cets.findIdPieceClientModele(clientSelect, modeleSelect);
			for (int i=0; i<idsPieces.size(); i++){
				cets.deletePiece(idsPieces.get(i));
				
//				Vector<String> refs = cets.findRefPiece(idsPieces.get(i));
//				for (int j=0; j<idsPieces.size(); j++){
//					cets.deleteRreferencage(refs.get(j));
//				}
				
			}
		}
		
		JOptionPane.showMessageDialog(mere, "Données Supprimées", "info", JOptionPane.PLAIN_MESSAGE); 
		fille = new DirectionAccueil(mere.getMere().getMere(),cets,true);
		fille.setVisible(true);
		mere.dispose();
	}
}
