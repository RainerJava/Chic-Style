package chicstyle.controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import chicstyle.presentation.VanessaAccueil;
import chicstyle.presentation.VanessaHoussage;

public class ControlJButtonNextVanessaHoussage implements ActionListener{

	private VanessaAccueil mere;

	public ControlJButtonNextVanessaHoussage(VanessaAccueil mere) {
		this.mere = mere;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		VanessaHoussage fille = new VanessaHoussage(mere, mere.getCets(),true);
		fille.setVisible(true);
		mere.setVisible(false);
	}
}
