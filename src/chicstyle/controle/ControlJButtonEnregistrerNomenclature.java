package chicstyle.controle;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import chicstyle.abstraction.ChicEtStyle;
import chicstyle.abstraction.Nomenclature;
import chicstyle.abstraction.Piece;
import chicstyle.abstraction.Referencage;
import chicstyle.presentation.CatherineNomenclature;
import chicstyle.presentation.CatherineNouvelleCommande;

public class ControlJButtonEnregistrerNomenclature implements ActionListener{
	
	private ChicEtStyle cets;
	private CatherineNomenclature mere; 
	private Frame fille;
	
	public ControlJButtonEnregistrerNomenclature(ChicEtStyle cets, CatherineNomenclature mere,
			Frame fille) {
		super();
		this.cets = cets;
		this.mere = mere;
		this.fille = fille;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		JTable tab = mere.getTableau();
		int nbrLignes = tab.getRowCount();
		for (int i=0; i < nbrLignes; i++){
			String ref = tab.getValueAt(i,0).toString();
			String type = tab.getValueAt(i,1).toString();
			boolean estTissu = (boolean)(tab.getValueAt(i,4));
			Referencage r = new Referencage(ref, type, estTissu);
			cets.saveRef(r, true);
			
			String client = mere.getCombobox().getComboClients().getSelectedItem()+"";
			String modele = mere.getCombobox().getComboModeles().getSelectedItem()+"";
			String tissu = mere.getCombobox().getComboTissus().getSelectedItem()+"";
			String couleur = mere.getCombobox().getComboCouleurs().getSelectedItem()+"";
			
			Piece p = cets.findPiece(client, modele, tissu, couleur);
			Double qte_unit = Double.parseDouble(tab.getValueAt(i, 2).toString());
			boolean fact = (boolean)(tab.getValueAt(i,3));
			Nomenclature nom = new Nomenclature(p.getId_piece(), ref, qte_unit, fact);
			cets.saveNomenclature(nom, true);
		}
		JOptionPane.showMessageDialog(mere, "Nomenclature Enregistrée", "info", JOptionPane.PLAIN_MESSAGE); 
		fille.setVisible(true);
		mere.dispose();
	}

}
