package chicstyle.controle;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import chicstyle.abstraction.ChicEtStyle;
import chicstyle.presentation.DirectionAccueil;
import chicstyle.presentation.DirectionAlerte;
import chicstyle.presentation.MainWindow;
import chicstyle.presentation.VanessaReceptionFournitures;

public class ControlJButtonSupprimerDirAlertes implements ActionListener{

	private DirectionAlerte mere;
	private Frame fille;
	private ChicEtStyle cets;
	
	public ControlJButtonSupprimerDirAlertes(ChicEtStyle cets, DirectionAlerte mere, Frame fille){
		this.cets = cets;
		this.mere = mere;
		this.fille = fille;
	}
	
	public void actionPerformed(ActionEvent e){
		int j = mere.getTabAlertes().size();
		
		for (int i=0; i<j; i++){
			boolean effacer = mere.getTabAlertes().get(i).getSupprimer().isSelected();
			if (effacer){
				String source = mere.getTabAlertes().get(i).getTextSource().getText();
				String message = mere.getTabAlertes().get(i).getTextMessage().getText();
				cets.deleteAlerte(source, message);
			}	
		}
		
		JOptionPane.showMessageDialog(mere, "Alertes Supprimées", "info", JOptionPane.PLAIN_MESSAGE);
		MainWindow grandMere = mere.getMere().getMere();
		fille = new DirectionAccueil(grandMere,cets, true );
		fille.setVisible(true);
		mere.dispose();
	}
}
