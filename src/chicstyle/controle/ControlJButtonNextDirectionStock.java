package chicstyle.controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import chicstyle.presentation.DirectionAccueil;
import chicstyle.presentation.DirectionStock;

public class ControlJButtonNextDirectionStock implements ActionListener {

	private DirectionAccueil mere;

	public ControlJButtonNextDirectionStock(DirectionAccueil mere) {
		this.mere = mere;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		DirectionStock fille = new DirectionStock(mere, mere.getCets(),true);
		fille.setVisible(true);
		mere.setVisible(false);
	}
}
