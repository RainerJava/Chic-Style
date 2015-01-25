package chicstyle.controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import chicstyle.presentation.CatherineAccueil;
import chicstyle.presentation.CatherinePret;

public class ControlJButtonNextCatherinePret implements ActionListener {

	private CatherineAccueil mere;

	public ControlJButtonNextCatherinePret(CatherineAccueil mere) {
		this.mere = mere;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		CatherinePret fille = new CatherinePret(mere, mere.getCets(),true);
		fille.setVisible(true);
		mere.setVisible(false);
	}
}
