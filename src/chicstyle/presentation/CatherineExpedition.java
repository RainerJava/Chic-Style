package chicstyle.presentation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import chicstyle.abstraction.ChicEtStyle;
import chicstyle.abstraction.ChicEtStyleJDBC;
import chicstyle.abstraction.Principal;
import chicstyle.controle.ControlJButtonExpedier;
import chicstyle.controle.ControlJButtonNext;
import chicstyle.controle.ControlJButtonOkExpedition;
import chicstyle.tableaux.TabExpedition1;
import chicstyle.tableaux.TabExpedition2;

public class CatherineExpedition extends JFrame{

	private static final long serialVersionUID = 1L;
	static final int LABEL_SIZE = 18;
	private static final String String = null;
	private TabExpedition2 modele = new TabExpedition2(); //tableau
	private JTable tableau; //tableau
	private ImageChicEtStyle imageCetS;
	private ComboBoxes combobox;
	private JTextField texteType;
	private CatherineAccueil mere;
	private ChicEtStyle cets;

	public ChicEtStyle getCets() {
		return cets;
	}

	public void setCets(ChicEtStyle cets) {
		this.cets = cets;
	}

	public CatherineAccueil getMere() {
		return mere;
	}

	public void setMere(CatherineAccueil mere) {
		this.mere = mere;
	}

	public JTextField getTexteType() {
		return texteType;
	}

	public void setTexteType(JTextField texteType) {
		this.texteType = texteType;
	}

	public CatherineExpedition(CatherineAccueil mere, ChicEtStyle cets, boolean creation) {
		super("Chic & Style");
		this.mere = mere;
		this.cets=cets;
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		Dimension tailleEcran = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize((int)(tailleEcran.getWidth()*3/4),(int)(tailleEcran.getHeight()*3/4));
		this.setLocation((int)(tailleEcran.getWidth()/8),(int)(tailleEcran.getHeight()/8));

		Container conteneur = this.getContentPane();
		conteneur.setLayout(new BorderLayout());

		conteneur.add(creerWest(), BorderLayout.WEST);

		JPanel util = new JPanel();
		util.setLayout(new BorderLayout());
		util.add(creerHaut(), BorderLayout.NORTH);

		tableau = new JTable(modele); //tableau
		util.add(new JScrollPane(tableau), BorderLayout.CENTER); //tableau
		// Pour empêcher de pouvoir déplacer les colonnes
		tableau.getTableHeader().setReorderingAllowed(false);
		// Pour empêcher de pouvoir modifier la taille des colonnes
		tableau.getTableHeader().setResizingAllowed(false);
		JPanel boutons = new JPanel(); //tableau

		JButton accueil = new JButton("Accueil");
		ControlJButtonNext controlacc = new ControlJButtonNext(this, new CatherineAccueil(this.getMere().getMere(),this.cets,true)); //BOUTON
		accueil.addActionListener(controlacc); //BOUTON
		boutons.add(accueil);

		JButton expedier = new JButton("Expedier");
		ControlJButtonExpedier controlEx = new ControlJButtonExpedier(this.cets, this, new CatherineAccueil(this.getMere().getMere(),this.cets,true));
		expedier.addActionListener(controlEx);
		boutons.add(expedier);

		util.add(boutons, BorderLayout.SOUTH); //tableau

		conteneur.add(util, BorderLayout.CENTER);

	} 

	public JPanel creerWest(){

		JPanel panneauImage = new JPanel();
		panneauImage.setBackground(Color.BLACK);
		panneauImage.setLayout(new BoxLayout(panneauImage, BoxLayout.Y_AXIS));
		panneauImage.add(Box.createRigidArea(new Dimension(0,120)));

		int pixel = this.getWidth();
		ImageIcon icon = new ImageIcon("images"+File.separator+"logo_C&S.jpg");
		//Image zoom = scaleImage(icon.getImage(), 0.5d);//facteur
		Image zoom = imageCetS.scaleImage(icon.getImage(), pixel/3);//taille en pixels
		Icon iconScaled = new ImageIcon(zoom);
		JLabel image = new JLabel(iconScaled);

		panneauImage.add(Box.createRigidArea(new Dimension(0,this.getHeight()/8)));
		panneauImage.add(image, BorderLayout.CENTER);
		return panneauImage;
	}

	public JPanel creerHaut() {
		JPanel panneauHaut = new JPanel();
		panneauHaut.setLayout(new BoxLayout(panneauHaut, BoxLayout.Y_AXIS));

		panneauHaut.add(Box.createRigidArea(new Dimension(0,50)));
		JLabel fonction = new JLabel("EXPEDITION");
		fonction.setFont(new Font("Helvetica",Font.ITALIC, 48));
		fonction.setAlignmentX(CENTER_ALIGNMENT);
		panneauHaut.add(fonction);
		panneauHaut.add(Box.createRigidArea(new Dimension(0,30)));

		combobox = new ComboBoxes(cets,0);
		panneauHaut.add(combobox.getBox());
		panneauHaut.add(Box.createRigidArea(new Dimension(0,20)));


		JButton ok = new JButton("Ok");
		ControlJButtonOkExpedition controlOk = new ControlJButtonOkExpedition(cets, this, modele);
		ok.addActionListener(controlOk);
		panneauHaut.add(ok);
		panneauHaut.add(Box.createRigidArea(new Dimension(0,20)));


		return panneauHaut;	
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

	//POUR LES TABLEAUX
	public static void main(String[] args) {
		ChicEtStyle cets = new ChicEtStyleJDBC();
		Principal principal = new Principal();
		MainWindow grandmere = new MainWindow(principal);
		CatherineAccueil mere = new CatherineAccueil(grandmere,cets, true);
		new CatherineExpedition(mere, cets, true).setVisible(true);
	}
}
