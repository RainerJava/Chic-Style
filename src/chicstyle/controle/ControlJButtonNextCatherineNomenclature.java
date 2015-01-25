package chicstyle.controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import chicstyle.presentation.CatherineAccueil;
import chicstyle.presentation.CatherineNomenclature;

public class ControlJButtonNextCatherineNomenclature implements ActionListener {

	private CatherineAccueil mere;

	public ControlJButtonNextCatherineNomenclature(CatherineAccueil mere) {
		this.mere = mere;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		CatherineNomenclature fille = new CatherineNomenclature(mere, mere.getCets(),true);
		fille.setVisible(true);
		mere.setVisible(false);
	}
}
