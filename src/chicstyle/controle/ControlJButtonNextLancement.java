package chicstyle.controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import chicstyle.presentation.CatherineAccueil;
import chicstyle.presentation.CatherineLancement;


public class ControlJButtonNextLancement implements ActionListener{

	private CatherineAccueil mere;

	public ControlJButtonNextLancement(CatherineAccueil mere) {
		this.mere = mere;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		CatherineLancement fille = new CatherineLancement(mere, mere.getCets(),true);
		fille.setVisible(true);
		mere.setVisible(false);
	}
}
