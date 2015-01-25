package chicstyle.controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import chicstyle.abstraction.ChicEtStyle;
import chicstyle.abstraction.Piece;
import chicstyle.presentation.CatherineAccueil;
import chicstyle.presentation.ClaudieAccueil;
import chicstyle.presentation.DirectionAccueil;
import chicstyle.presentation.MainWindow;
import chicstyle.presentation.MarleneAccueil;
import chicstyle.presentation.VanessaAccueil;
import chicstyle.presentation.VanessaHoussage;
import chicstyle.tableaux.TabVanessaHoussage1;
import chicstyle.tableaux.TabVanessaHoussage2;

public class ControlJButtonOkMainWindow implements ActionListener {
	
	private ChicEtStyle cets;
	private MainWindow mere;

	public ControlJButtonOkMainWindow(ChicEtStyle cets, MainWindow mere) {
		super();
		this.cets = cets;
		this.mere = mere;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String login = mere.getLogin().getText();
		String mdpSaisi = mere.getPassword().getText();
		String mdpReel = cets.findMdp(login);
		boolean comparaison = mdpReel.equals(mdpSaisi);
		if (comparaison){
			boolean[] res = cets.findPermissions(login);
			JFrame fille = new JFrame();
			if (res[0]){
				fille = new DirectionAccueil(mere, cets, true);
				fille.setVisible(true);
				mere.dispose();
			}
			else if (res[1]){
				fille = new CatherineAccueil(mere, cets, true);
				fille.setVisible(true);
				mere.dispose();
			}
			else if (res[2]){
				fille = new VanessaAccueil(mere, cets, true);
				fille.setVisible(true);
				mere.dispose();
			}
			else if (res[3]){
				fille = new ClaudieAccueil(mere, cets, true);
				fille.setVisible(true);
				mere.dispose();
			}
			else if (res[4]){
				fille = new MarleneAccueil(mere, cets, true);
				fille.setVisible(true);
				mere.dispose();
			}
		}
		else{
			JOptionPane.showMessageDialog(mere, "Erreur de login ou mot de passe", "Attention", JOptionPane.WARNING_MESSAGE);
		}
	}

}
