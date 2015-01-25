package chicstyle.controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import chicstyle.presentation.DirectionAccueil;
import chicstyle.presentation.DirectionProduitsFinis;

public class ControlJButtonNextDirectionProduitsFinis implements ActionListener {

	private DirectionAccueil mere;

	public ControlJButtonNextDirectionProduitsFinis(DirectionAccueil mere) {
		this.mere = mere;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		DirectionProduitsFinis fille = new DirectionProduitsFinis(mere, mere.getCets(),true);
		fille.setVisible(true);
		mere.setVisible(false);
	}
}
