package chicstyle.controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import chicstyle.presentation.VanessaAccueil;
import chicstyle.presentation.VanessaHoussage;
import chicstyle.presentation.VanessaRealisationBoite;

public class ControlJButtonNextVanessaBoite implements ActionListener{

	private VanessaAccueil mere;

	public ControlJButtonNextVanessaBoite(VanessaAccueil mere) {
		this.mere = mere;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		VanessaRealisationBoite fille = new VanessaRealisationBoite(mere, mere.getCets(),true);
		fille.setVisible(true);
		mere.setVisible(false);
	}
}
