package chicstyle.controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import chicstyle.presentation.VanessaAccueil;
import chicstyle.presentation.VanessaHoussage;
import chicstyle.presentation.VanessaStock;

public class ControlJButtonNextVanessaStock implements ActionListener{

	private VanessaAccueil mere;

	public ControlJButtonNextVanessaStock(VanessaAccueil mere) {
		this.mere = mere;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		VanessaStock fille = new VanessaStock(mere, mere.getCets(),true);
		fille.setVisible(true);
		mere.setVisible(false);
	}
}

