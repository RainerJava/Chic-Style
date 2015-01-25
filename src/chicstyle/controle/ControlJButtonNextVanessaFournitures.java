package chicstyle.controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import chicstyle.presentation.VanessaAccueil;
import chicstyle.presentation.VanessaHoussage;
import chicstyle.presentation.VanessaReceptionFournitures;

public class ControlJButtonNextVanessaFournitures implements ActionListener {

	private VanessaAccueil mere;

	public ControlJButtonNextVanessaFournitures(VanessaAccueil mere) {
		this.mere = mere;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		VanessaReceptionFournitures fille = new VanessaReceptionFournitures(mere, mere.getCets(),true);
		fille.setVisible(true);
		mere.setVisible(false);
	}
}
