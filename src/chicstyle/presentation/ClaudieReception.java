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
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import chicstyle.abstraction.ChicEtStyle;
import chicstyle.abstraction.ChicEtStyleJDBC;
import chicstyle.abstraction.Principal;
import chicstyle.controle.ControlJButtonEnregistrerRecepTissu;
import chicstyle.controle.ControlJButtonNext;
import chicstyle.controle.ControlJButtonOkClaudieReception;
import chicstyle.controle.ControlJComboBoxRefClaudieRecep;

public class ClaudieReception extends JFrame{

	private static final long serialVersionUID = 1L;
	static final int LABEL_SIZE = 18;
	private ComboBoxes combobox;
	JComboBox<String> comboRef;
	private ImageChicEtStyle imageCetS;
	private JTextField texteType;
	private JTextField texteStock;
	private JTextField quantite;
	private JTextField laize;
	private JTextField laizeMarlene;
	private ChicEtStyle cets;
	private ClaudieAccueil mere;

	public ClaudieAccueil getMere() {
		return mere;
	}

	public void setMere(ClaudieAccueil mere) {
		this.mere = mere;
	}

	public ClaudieReception(ClaudieAccueil mere, ChicEtStyle cets, boolean creation) {
		super("Chic & Style");
		this.cets = cets;
		this.mere=mere;
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		Dimension tailleEcran = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize((int)(tailleEcran.getWidth()*3/4),(int)(tailleEcran.getHeight()*3/4));
		this.setLocation((int)(tailleEcran.getWidth()/8),(int)(tailleEcran.getHeight()/8));
		Container conteneur = this.getContentPane();
		conteneur.setLayout(new BorderLayout());
		
		conteneur.add(creerWest(), BorderLayout.WEST);
		
		JPanel util = new JPanel();
		util.setLayout(new BorderLayout());
		util.add(creerHaut(cets), BorderLayout.NORTH);
		util.add(creerCentre(), BorderLayout.CENTER);
		util.add(creerBas(), BorderLayout.SOUTH);
		
		conteneur.add(util);
	} 

	public JPanel creerWest(){
		
		JPanel panneauImage = new JPanel();
		panneauImage.setBackground(Color.BLACK);
		panneauImage.setLayout(new BoxLayout(panneauImage, BoxLayout.Y_AXIS));
		
		ImageIcon icon = new ImageIcon("images"+File.separator+"logo_C&S.jpg");
		//Image zoom = scaleImage(icon.getImage(), 0.5d);//facteur
		int pixels = this.getWidth();
		Image zoom = imageCetS.scaleImage(icon.getImage(), pixels/2);//taille en pixels
		Icon iconScaled = new ImageIcon(zoom);
		JLabel image = new JLabel(iconScaled);
		
		panneauImage.add(Box.createRigidArea(new Dimension(0,this.getHeight()/6)));

		panneauImage.add(image, BorderLayout.CENTER);
		return panneauImage;
	}
	
	public JPanel creerHaut(ChicEtStyle cets) {
		JPanel panneauHaut = new JPanel();
		panneauHaut.setLayout(new BoxLayout(panneauHaut, BoxLayout.Y_AXIS));
		JLabel fonction = new JLabel("RECEPTION TISSUS");
		fonction.setFont(new Font("Helvetica",Font.ITALIC, 48));
		fonction.setAlignmentX(CENTER_ALIGNMENT);
		panneauHaut.add(Box.createRigidArea(new Dimension(this.getWidth()/4,0)));
		panneauHaut.add(fonction, BorderLayout.NORTH);
		panneauHaut.add(Box.createRigidArea(new Dimension(0,20)));

		
		JPanel ut = new JPanel();
		combobox = new ComboBoxes(cets,0);
		ut.add(combobox.getBox());
				
		JButton ok = new JButton("OK");
		ControlJButtonOkClaudieReception controlOk = new ControlJButtonOkClaudieReception(cets,this);
		ok.addActionListener(controlOk);
		ok.setPreferredSize(new Dimension(60, 100));
		ut.add(ok);
		
		panneauHaut.add(ut);
		panneauHaut.add(Box.createRigidArea(new Dimension(0,20)));

		
		String[] refs = {"-"};
		comboRef = new JComboBox<String>(refs);
		comboRef.setEditable(false);
		comboRef.setPreferredSize(new Dimension(150, 20));
		ControlJComboBoxRefClaudieRecep controlComboRef = new ControlJComboBoxRefClaudieRecep(cets, this);
		comboRef.addActionListener(controlComboRef);
		
		
		JPanel re = new JPanel();
		JLabel labRe = new JLabel("Reference :");
		re.add(labRe);
		re.add(comboRef);
		
		panneauHaut.add(re, BorderLayout.SOUTH);
		
		return panneauHaut;	
	}
	
	public JPanel creerCentre(){
		
		JPanel panneauCentre = new JPanel();
		panneauCentre.setLayout(new BoxLayout(panneauCentre, BoxLayout.Y_AXIS));
		
		panneauCentre.add(Box.createRigidArea(new Dimension(0,20)));
		
		JPanel panelType = new JPanel();
		JLabel nomType = new JLabel("Type: ");
		texteType = new JTextField("");
		texteType.setPreferredSize(new Dimension(150,20));
		texteType.setEditable(false);
		panelType.add(nomType);
		panelType.add(texteType);
		panneauCentre.add(panelType);
		
		JPanel panelStock = new JPanel();
		JLabel nomStock = new JLabel("Stock");
		texteStock = new JTextField("");
		texteStock.setPreferredSize(new Dimension(150,20));
		texteStock.setEditable(false);
		panelStock.add(nomStock);
		panelStock.add(texteStock);
		panneauCentre.add(panelStock);
		
		panneauCentre.add(Box.createRigidArea(new Dimension(0,20)));
		
		JPanel panelLaizeMarlene = new JPanel();
		JLabel nomLaizeMarlene = new JLabel("Laize utilisé pour modele");
		laizeMarlene = new JTextField("");
		laizeMarlene.setPreferredSize(new Dimension(150,20));
		laizeMarlene.setEnabled(false);
		panelLaizeMarlene.add(nomLaizeMarlene);
		panelLaizeMarlene.add(laizeMarlene);
		panneauCentre.add(panelLaizeMarlene);
		
		JPanel panelLaize = new JPanel();
		JLabel nomLaize = new JLabel("                 Laize tissu reçu");
		laize = new JTextField("");
		laize.setPreferredSize(new Dimension(150,20));
		laize.setEnabled(true);
		panelLaize.add(nomLaize);
		panelLaize.add(laize);
		panneauCentre.add(panelLaize);
		
		JPanel panelQuantite = new JPanel();
		JLabel nomQuantite = new JLabel("                   Quantité reçue");
		quantite = new JTextField("");
		quantite.setPreferredSize(new Dimension(150,20));
		quantite.setEnabled(true);
		panelQuantite.add(nomQuantite);
		panelQuantite.add(quantite);
		panneauCentre.add(panelQuantite);
		
		panneauCentre.add(Box.createRigidArea(new Dimension(0,110)));
		
		return panneauCentre;
	}

	public JPanel creerBas() {
		JPanel boutons = new JPanel();
		JButton valider = new JButton("Valider");
		ControlJButtonEnregistrerRecepTissu controlValider = new ControlJButtonEnregistrerRecepTissu(cets, this, mere);
		valider.addActionListener(controlValider);
		boutons.add(valider);
		
		JButton retour = new JButton("Retour");
		ControlJButtonNext controlDeconnexion = new ControlJButtonNext(this, mere); //BOUTON
		retour.addActionListener(controlDeconnexion); //BOUTON
		boutons.add(retour);
				
		return boutons;
	}
	

	public ComboBoxes getCombobox() {
		return combobox;
	}

	public void setCombobox(ComboBoxes combobox) {
		this.combobox = combobox;
	}
	
	

	public JComboBox<String> getComboRef() {
		return comboRef;
	}

	public void setComboRef(JComboBox<String> comboRef) {
		this.comboRef = comboRef;
	}
		
	public JTextField getStock() {
		return texteStock;
	}

	public void setStock(JTextField stock) {
		this.texteStock = stock;
	}

	public JTextField getTypeTissu() {
		return texteType;
	}

	public void setTypeTissu(JTextField type) {
		this.texteType = type;
	}

	public JTextField getQuantite() {
		return quantite;
	}

	public void setQuantite(JTextField quantite) {
		this.quantite = quantite;
	}

	public JTextField getLaize() {
		return laize;
	}

	public void setLaize(JTextField laize) {
		this.laize = laize;
	}

	public JTextField getLaizeMarlene() {
		return laizeMarlene;
	}

	public void setLaizeMarlene(JTextField laizeMarlene) {
		this.laizeMarlene = laizeMarlene;
	}

	public static void main(String[] args) {
		ChicEtStyle cets = new ChicEtStyleJDBC();
		Principal principal = new Principal();
		MainWindow grandmere = new MainWindow(principal);
		ClaudieAccueil mere = new ClaudieAccueil(grandmere, cets, true);
		new ClaudieReception(mere, cets, true).setVisible(true);
	}
}
