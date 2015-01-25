package chicstyle.main;

import chicstyle.abstraction.Principal;
import chicstyle.presentation.MainWindow;

public class GestionPrincipal {

	public static void main(String[] args) {
		Principal principal = new Principal();

		MainWindow fen = new MainWindow(principal);
		fen.setVisible(true);
	}
}
