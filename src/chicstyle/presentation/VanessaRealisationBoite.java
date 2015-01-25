package chicstyle.presentation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;

import chicstyle.abstraction.ChicEtStyle;
import chicstyle.abstraction.ChicEtStyleJDBC;
import chicstyle.abstraction.Principal;
import chicstyle.controle.ControlJButtonEnregistrerReceptionFourniture;
import chicstyle.controle.ControlJButtonNext;
import chicstyle.controle.ControlJButtonOkBoite;
import chicstyle.controle.ControlJButtonOkNomenclature;
import chicstyle.controle.ControlJButtonValiderBoite;
import chicstyle.tableaux.TabBoite1;
import chicstyle.tableaux.TabBoite2;

public class VanessaRealisationBoite extends JFrame{

	private static final long serialVersionUID = 1L;
	static final int LABEL_SIZE = 18;
	private TabBoite2 modele = new TabBoite2(); //
	private JTable tableau; //
	private ComboBoxes combobox;	
	private JTextField textedate;
	private ChicEtStyle cets;
	private VanessaAccueil mere;
	private ImageChicEtStyle imageCetS;

	public VanessaAccueil getMere() {
		return mere;
	}

	public void setMere(VanessaAccueil mere) {
		this.mere = mere;
	}

	public ChicEtStyle getCets() {
		return cets;
	}

	public void setCets(ChicEtStyle cets) {
		this.cets = cets;
	}


	public JTextField getTextedate() {
		return textedate;
	}

	public void setTextedate(JTextField textedate) {
		this.textedate = textedate;
	}

	public JTable getTableau() {
		return tableau;
	}

	public void setTableau(JTable tableau) {
		this.tableau = tableau;
	}

	public ComboBoxes getCombobox() {
		return combobox;
	}

	public void setCombobox(ComboBoxes combobox) {
		this.combobox = combobox;
	}

	public VanessaRealisationBoite(VanessaAccueil mere, ChicEtStyle cets, boolean creation) {
		super("Chic & Style");
		this.mere=mere;
		this.cets=cets;
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		Dimension tailleEcran = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize((int)(tailleEcran.getWidth()*3/4),(int)(tailleEcran.getHeight()*3/4));
		this.setLocation((int)(tailleEcran.getWidth()/8),(int)(tailleEcran.getHeight()/8));

		Container conteneur = this.getContentPane();
		conteneur.setLayout(new BorderLayout());
		conteneur.add(creerHaut(), BorderLayout.NORTH);

		tableau = new JTable(modele); //
		getContentPane().add(new JScrollPane(tableau), BorderLayout.CENTER); //
		// Pour empêcher de pouvoir déplacer les colonnes
		tableau.getTableHeader().setReorderingAllowed(false);
		// Pour empêcher de pouvoir modifier la taille des colonnes
		tableau.getTableHeader().setResizingAllowed(false);
		JPanel boutons = new JPanel(); //
		JButton validate = new JButton("Valider");
		ControlJButtonValiderBoite controlValiderBoite = new ControlJButtonValiderBoite(cets,this, mere); //BOUTON
		validate.addActionListener(controlValiderBoite); //BOUTON
		boutons.add(validate);
		JButton precedent = new JButton("Précédent");
		ControlJButtonNext prec = new ControlJButtonNext(this, mere); //BOUTON
		precedent.addActionListener(prec); //BOUTON
		boutons.add(precedent);
		getContentPane().add(boutons, BorderLayout.SOUTH); //
	}

	public JPanel creerHaut() {

		JPanel panneauHaut = new JPanel();
		panneauHaut.setLayout(new BoxLayout(panneauHaut, BoxLayout.Y_AXIS));

		panneauHaut.add(Box.createRigidArea(new Dimension(0,60)));
		JLabel fonction = new JLabel("REALISATION BOITE");
		fonction.setFont(new Font("Helvetica",Font.ITALIC, 48));
		fonction.setAlignmentX(CENTER_ALIGNMENT);
		panneauHaut.add(fonction);
		panneauHaut.add(Box.createRigidArea(new Dimension(0,40)));

		combobox = new ComboBoxes(cets,0);
		panneauHaut.add(combobox.getBox());
		panneauHaut.add(Box.createRigidArea(new Dimension(0,20)));

		JButton ok = new JButton("OK");
		ok.setAlignmentX(CENTER_ALIGNMENT);
		ok.setAlignmentY(CENTER_ALIGNMENT);
		ControlJButtonOkBoite controlOk = new ControlJButtonOkBoite(cets, this, modele);
		ok.addActionListener(controlOk);
		panneauHaut.add(ok);
		panneauHaut.add(Box.createRigidArea(new Dimension(0,20)));

		JPanel nomDate = new JPanel();
		JLabel date = new JLabel("Date début de production: ");
		textedate = new JTextField("");
		nomDate.setAlignmentX(CENTER_ALIGNMENT);
		nomDate.setAlignmentY(CENTER_ALIGNMENT);
		textedate.setPreferredSize(new Dimension(150,20));
		textedate.setEditable(false);
		nomDate.add(date);
		nomDate.add(textedate);
		panneauHaut.add(nomDate);
		panneauHaut.add(Box.createRigidArea(new Dimension(0,30)));

		return panneauHaut;
	}

	public static void main(String[] args) {
		ChicEtStyle cets = new ChicEtStyleJDBC();
		Principal principal = new Principal();
		MainWindow grandmere = new MainWindow(principal);
		VanessaAccueil mere = new VanessaAccueil(grandmere, cets, true);
		new VanessaRealisationBoite(mere, cets, true).setVisible(true);
	}
}
