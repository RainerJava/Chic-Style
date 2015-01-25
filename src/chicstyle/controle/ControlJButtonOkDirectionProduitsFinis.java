package chicstyle.controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JTable;

import chicstyle.abstraction.ChicEtStyle;
import chicstyle.abstraction.ChicEtStyleJDBC;
import chicstyle.abstraction.Piece;
import chicstyle.presentation.CatherineNomenclature;
import chicstyle.presentation.DirectionProduitsFinis;
import chicstyle.controle.ControlJButtonOkDirectionProduitsFinis;
import chicstyle.tableaux.TabDirectionProduitsFinis1;
import chicstyle.tableaux.TabDirectionProduitsFinis2;
import chicstyle.tableaux.TabNomenclature1;
import chicstyle.tableaux.TabNomenclature2;
import chicstyle.tableaux.TabVanessaHoussage1;

public class ControlJButtonOkDirectionProduitsFinis implements ActionListener{

	private ChicEtStyle cets;
	private DirectionProduitsFinis mere;
	private TabDirectionProduitsFinis2 modele; //tableau

	public ControlJButtonOkDirectionProduitsFinis(ChicEtStyle cets, DirectionProduitsFinis mere, TabDirectionProduitsFinis2 modele) {
		super();
		this.cets = cets;
		this.mere = mere;
		this.modele = modele;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String clientSelect = (mere.getCombobox().getComboClients().getSelectedItem().toString());
		String modeleSelect = (mere.getCombobox().getComboModeles().getSelectedItem().toString());
		String tissuSelect 	= (mere.getCombobox().getComboTissus().getSelectedItem().toString());
		String couleurSelect = (mere.getCombobox().getComboCouleurs().getSelectedItem().toString());
		JTable tab = mere.getTableau();
		int nbrLignes = tab.getRowCount();
		Vector<Object> objet= new Vector<Object>();

		for(int j=0; j<nbrLignes; j++){
			modele.removeAmi(0);
		}

		if ((clientSelect!="-")
				&& (modeleSelect=="-")
				&& (tissuSelect=="-")
				&& (couleurSelect=="-")){
			Vector<Object> objet1 = cets.findProduitsFinisClient(clientSelect);
			objet=objet1;

			for (int i=0; i<objet.size();i++){
				String[] resultat = (String[]) objet.get(i);
				int int7=(Integer.parseInt(resultat[6])-Integer.parseInt(resultat[5]));
				String string7 = new String(""+int7);

				modele.addAmi(new TabDirectionProduitsFinis1(resultat[0],
															resultat[1],
															resultat[2],
															Integer.parseInt(resultat[3]),
															Integer.parseInt(resultat[4]),
															Integer.parseInt(resultat[5]),
															Integer.parseInt(resultat[6]),
															Integer.parseInt(string7)));
			}

		}else if  ((clientSelect!="-")
				&& (modeleSelect!="-")
				&& (tissuSelect=="-")
				&& (couleurSelect=="-")){
			Vector<Object> objet2 = cets.findProduitsFinisModele(clientSelect,modeleSelect);
			objet=objet2;
			if (objet!=null){
				for (int i=0; i<objet.size();i++){
					String[] resultat = (String[]) objet.get(i);
					int int7=(Integer.parseInt(resultat[6])-Integer.parseInt(resultat[5]));
					String string7 = new String(""+int7);

					modele.addAmi(new TabDirectionProduitsFinis1(resultat[0],
							resultat[1],
							resultat[2],
							Integer.parseInt(resultat[3]),
							Integer.parseInt(resultat[4]),
							Integer.parseInt(resultat[5]),
							Integer.parseInt(resultat[6]),
							Integer.parseInt(string7)));

				}

			}

		}else if  ((clientSelect!="-")
				&& (modeleSelect!="-")
				&& (tissuSelect!="-")
				&& (couleurSelect=="-")){
			Vector<Object> objet3 = cets.findProduitsFinisTissu(clientSelect,modeleSelect,tissuSelect);
			objet=objet3;
			if (objet!=null){
				for (int i=0; i<objet.size();i++){
					String[] resultat = (String[]) objet.get(i);
					int int7=(Integer.parseInt(resultat[6])-Integer.parseInt(resultat[5]));
					String string7 = new String(""+int7);

					modele.addAmi(new TabDirectionProduitsFinis1(resultat[0],
							resultat[1],
							resultat[2],
							Integer.parseInt(resultat[3]),
							Integer.parseInt(resultat[4]),
							Integer.parseInt(resultat[5]),
							Integer.parseInt(resultat[6]),
							Integer.parseInt(string7)));

				}

			}

		}else if  ((clientSelect!="-")
				&& (modeleSelect!="-")
				&& (tissuSelect!="-")
				&& (couleurSelect!="-")){
			Vector<Object> objet4 = cets.findProduitsFinisCouleur(clientSelect,modeleSelect,tissuSelect,couleurSelect);
			objet=objet4;
			if (objet!=null){
				for (int i=0; i<objet.size();i++){
					String[] resultat = (String[]) objet.get(i);
					int int7=(Integer.parseInt(resultat[6])-Integer.parseInt(resultat[5]));
					String string7 = new String(""+int7);

					modele.addAmi(new TabDirectionProduitsFinis1(resultat[0],
							resultat[1],
							resultat[2],
							Integer.parseInt(resultat[3]),
							Integer.parseInt(resultat[4]),
							Integer.parseInt(resultat[5]),
							Integer.parseInt(resultat[6]),
							Integer.parseInt(string7)));

				}

			}
		}
	}
}
