package chicstyle.presentation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Toolkit;
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

import chicstyle.abstraction.ChicEtStyle;
import chicstyle.abstraction.ChicEtStyleJDBC;
import chicstyle.abstraction.Principal;
import chicstyle.controle.ControlJButtonEnregistrerDecoupe;
import chicstyle.controle.ControlJButtonEnregistrerRecepTissu;
import chicstyle.controle.ControlJButtonNext;
import chicstyle.controle.ControlJButtonOkClaudieDecoupe;
import chicstyle.controle.ControlJButtonOkClaudieReception;
import chicstyle.controle.ControlJComboBoxRefClaudieDecoupe;
import chicstyle.controle.ControlJComboBoxRefClaudieRecep;

public class ClaudieDecoupe extends JFrame {

	private static final long serialVersionUID = 1L;
	static final int LABEL_SIZE = 18;
	private ComboBoxes combobox;
	JComboBox<String> comboRef;
	private ImageChicEtStyle imageCetS;
	private JTextField texteType;
	private JTextField texteTotal;
	private JTextField texteReste;
	private JTextField texteStock;
	private JTextField texteDecoupe;
	private ChicEtStyle cets;
	private ClaudieAccueil mere;

	public ChicEtStyle getCets() {
		return cets;
	}

	public void setCets(ChicEtStyle cets) {
		this.cets = cets;
	}

	public ClaudieAccueil getMere() {
		return mere;
	}

	public void setMere(ClaudieAccueil mere) {
		this.mere = mere;
	}

	public JTextField getTexteType() {
		return texteType;
	}

	public void setTexteType(JTextField texteType) {
		this.texteType = texteType;
	}

	public JTextField getTexteTotal() {
		return texteTotal;
	}

	public void setTexteTotal(JTextField texteTotal) {
		this.texteTotal = texteTotal;
	}

	public JTextField getTexteReste() {
		return texteReste;
	}

	public void setTexteReste(JTextField texteReste) {
		this.texteReste = texteReste;
	}

	public JTextField getTexteStock() {
		return texteStock;
	}

	public void setTexteStock(JTextField texteStock) {
		this.texteStock = texteStock;
	}

	public JTextField getTexteDecoupe() {
		return texteDecoupe;
	}

	public void setTexteDecoupe(JTextField texteDecoupe) {
		this.texteDecoupe = texteDecoupe;
	}

	public ClaudieDecoupe(ClaudieAccueil mere, ChicEtStyle cets, boolean creation) {
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
		util.add(creerHaut(), BorderLayout.NORTH);
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
		Image zoom = imageCetS.scaleImage(icon.getImage(), pixels/3);//taille en pixels
		Icon iconScaled = new ImageIcon(zoom);
		JLabel image = new JLabel(iconScaled);
		
		panneauImage.add(Box.createRigidArea(new Dimension(0,this.getHeight()/5)));

		panneauImage.add(image, BorderLayout.CENTER);
		return panneauImage;
	}
	
	public JPanel creerHaut() {
		JPanel panneauHaut = new JPanel();
		panneauHaut.setLayout(new BoxLayout(panneauHaut, BoxLayout.Y_AXIS));
		JLabel fonction = new JLabel("DECOUPE");
		fonction.setFont(new Font("Helvetica",Font.ITALIC, 48));
		fonction.setAlignmentX(CENTER_ALIGNMENT);
		panneauHaut.add(Box.createRigidArea(new Dimension(this.getWidth()/4,0)));
		panneauHaut.add(fonction, BorderLayout.NORTH);
		panneauHaut.add(Box.createRigidArea(new Dimension(0,20)));

		
		JPanel ut = new JPanel();
		combobox = new ComboBoxes(cets,0);
		ut.add(combobox.getBox());
		
		JButton ok = new JButton("OK");
		ControlJButtonOkClaudieDecoupe controlOk = new ControlJButtonOkClaudieDecoupe(cets,this);
		ok.addActionListener(controlOk);
		ok.setPreferredSize(new Dimension(60, 100));
		ut.add(ok);
		
		panneauHaut.add(ut);
		
		panneauHaut.add(Box.createRigidArea(new Dimension(0,30)));
		
		String[] refs = {"-"};
		comboRef = new JComboBox<String>(refs);
		comboRef.setEditable(false);
		comboRef.setPreferredSize(new Dimension(150, 20));
		ControlJComboBoxRefClaudieDecoupe controlComboRef = new ControlJComboBoxRefClaudieDecoupe(cets, this);
		comboRef.addActionListener(controlComboRef);
		
		JPanel re = new JPanel();
		JLabel labRe = new JLabel("Reference :");
		re.add(labRe);
		re.add(comboRef);
		
		JPanel panelType = new JPanel();
		JLabel nomType = new JLabel("Type: ");
		texteType = new JTextField("");
		texteType.setPreferredSize(new Dimension(150,20));
		texteType.setEditable(false);
		panelType.add(nomType);
		panelType.add(texteType);
		re.add(panelType);
		
		panneauHaut.add(re);
		
		return panneauHaut;	
	}
	
	public JPanel creerCentre(){
		
		JPanel globalCentre = new JPanel();
		globalCentre.setLayout(new BoxLayout(globalCentre, BoxLayout.Y_AXIS));
		globalCentre.add(Box.createRigidArea(new Dimension(0,20)));

		JPanel panneauCentre = new JPanel();
		panneauCentre.setLayout(new BoxLayout(panneauCentre, BoxLayout.X_AXIS));
		
		panneauCentre.add(Box.createRigidArea(new Dimension(0,50)));
		
		JPanel panelTotal = new JPanel();
		JLabel nomTotal = new JLabel("Total nécessaire: ");
		texteTotal = new JTextField("");
		texteTotal.setPreferredSize(new Dimension(150,20));
		texteTotal.setEditable(false);
		panelTotal.add(nomTotal);
		panelTotal.add(texteTotal);
		panneauCentre.add(panelTotal);
		
		JPanel panelReste = new JPanel();
		JLabel nomReste = new JLabel("Tissu restant à découper: ");
		texteReste = new JTextField("");
		texteReste.setPreferredSize(new Dimension(150,20));
		texteReste.setEditable(false);
		panelReste.add(nomReste);
		panelReste.add(texteReste);
		panneauCentre.add(panelReste);
		
		JPanel panelStock = new JPanel();
		JLabel nomStock = new JLabel("Stock disponible: ");
		texteStock = new JTextField("");
		texteStock.setPreferredSize(new Dimension(150,20));
		texteStock.setEditable(false);
		panelStock.add(nomStock);
		panelStock.add(texteStock);
		panneauCentre.add(panelStock);
		
		globalCentre.add(panneauCentre);
		globalCentre.add(Box.createRigidArea(new Dimension(0,20)));

		
		JPanel panelDecoupe = new JPanel();
		JLabel nomDecoupe = new JLabel("Découpage réalisé: ");
		texteDecoupe = new JTextField("");
		texteDecoupe.setPreferredSize(new Dimension(150,20));
		texteDecoupe.setEditable(true);
		panelDecoupe.add(nomDecoupe);
		panelDecoupe.add(texteDecoupe);
		globalCentre.add(panelDecoupe);
		
		globalCentre.add(Box.createRigidArea(new Dimension(0,110)));
		
		return globalCentre;
	}

	public JPanel creerBas() {
		JPanel boutons = new JPanel();
		JButton valider = new JButton("Valider");
		ControlJButtonEnregistrerDecoupe controlValider = new ControlJButtonEnregistrerDecoupe(cets, this, mere);
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

	public static void main(String[] args) {
		ChicEtStyle cets = new ChicEtStyleJDBC();
		Principal principal = new Principal();
		MainWindow grandmere = new MainWindow(principal);
		ClaudieAccueil mere = new ClaudieAccueil(grandmere, cets, true);
		new ClaudieDecoupe(mere, cets, true).setVisible(true);
	}
}
