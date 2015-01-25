package chicstyle.controle;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlJButtonPrec implements ActionListener{
	
	private Frame mere;
	private Frame fille;

	public ControlJButtonPrec(Frame mere, Frame fille) {
		this.mere = mere;
		this.fille = fille;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		fille.setVisible(true);
		mere.dispose();
	}

}
