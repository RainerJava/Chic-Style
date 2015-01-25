package chicstyle.controle;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import chicstyle.abstraction.ChicEtStyle;
import chicstyle.abstraction.Housser;
import chicstyle.abstraction.LongueurCoupee;
import chicstyle.abstraction.Piece;
import chicstyle.presentation.ClaudieAccueil;
import chicstyle.presentation.ClaudieDecoupe;

public class ControlJButtonEnregistrerDecoupe implements ActionListener {

	private ChicEtStyle cets;
	private ClaudieDecoupe mere; 
	private Frame fille;

	public ControlJButtonEnregistrerDecoupe(ChicEtStyle cets, ClaudieDecoupe mere,
			Frame fille) {
		super();
		this.cets = cets;
		this.mere = mere;
		this.fille = fille;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		String client = mere.getCombobox().getComboClients().getSelectedItem()+"";
		String modele = mere.getCombobox().getComboModeles().getSelectedItem()+"";
		String tissu = mere.getCombobox().getComboTissus().getSelectedItem()+"";
		String couleur = mere.getCombobox().getComboCouleurs().getSelectedItem()+"";
		Piece p = cets.findPiece(client, modele, tissu, couleur);
		String ref = mere.getComboRef().getSelectedItem()+"";
		String text = mere.getTexteDecoupe().getText();
		int ancien_longueur = cets.findLongueurCoupee(p.getId_piece(), ref);
		int nouveau_longueur = ancien_longueur + Integer.parseInt(text);

		LongueurCoupee longueur = new LongueurCoupee(p.getId_piece(),ref,nouveau_longueur);
		cets.saveLongueurCoupee(longueur, true);

		JOptionPane.showMessageDialog(mere, "Découpe enregistrée", "info", JOptionPane.PLAIN_MESSAGE); 
		fille = new ClaudieAccueil(mere.getMere().getMere(),cets,true);
		fille.setVisible(true);
		mere.dispose();
	}
}
