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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import chicstyle.abstraction.ChicEtStyle;
import chicstyle.abstraction.ChicEtStyleJDBC;
import chicstyle.abstraction.Principal;
import chicstyle.controle.ControlJButtonEnregistrerAlerteMarlene;
import chicstyle.controle.ControlJButtonNext;
import chicstyle.controle.ControlJButtonPrec;
import chicstyle.tableaux.TabMarleneAlerte2;

public class MarleneAlerte extends JFrame {

	private static final long serialVersionUID = 1L;
	static final int LABEL_SIZE = 18;
	private ImageChicEtStyle imageCetS;
	private ChicEtStyle cets;
	private JTextField message;
	private MarleneAccueil mere;

	public ChicEtStyle getCets() {
		return cets;
	}

	public void setCets(ChicEtStyle cets) {
		this.cets = cets;
	}

	public MarleneAccueil getMere() {
		return mere;
	}

	public void setMere(MarleneAccueil mere) {
		this.mere = mere;
	}

	public MarleneAlerte(MarleneAccueil mere, ChicEtStyle cets, boolean creation) {
		super("Chic & Style");
		this.mere=mere;
		this.cets = cets;
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		Dimension tailleEcran = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize((int)(tailleEcran.getWidth()*3/4),(int)(tailleEcran.getHeight()*3/4));
		this.setLocation((int)(tailleEcran.getWidth()/8),(int)(tailleEcran.getHeight()/8));
		Container conteneur = this.getContentPane();
		conteneur.setLayout(new BorderLayout());

		conteneur.add(creerWest(), BorderLayout.WEST);

		JPanel util = new JPanel();
		util.setLayout(new BorderLayout());

		JPanel boutons = new JPanel();
		JButton valider = new JButton("Valider");
		ControlJButtonEnregistrerAlerteMarlene controleValider = new ControlJButtonEnregistrerAlerteMarlene(cets, this, this.mere);
		valider.addActionListener(controleValider);
		boutons.add(valider);

		JButton precedent = new JButton("Retour");
		ControlJButtonPrec controlDeconnexion = new ControlJButtonPrec(this, this.mere); //BOUTON
		precedent.addActionListener(controlDeconnexion); //BOUTON
		boutons.add(precedent);

		util.add(creerHaut(), BorderLayout.NORTH);
		util.add(creerCentre(), BorderLayout.CENTER);
		util.add(boutons, BorderLayout.SOUTH);

		conteneur.add(util, BorderLayout.CENTER);


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
		JLabel fonction = new JLabel("ALERTE");
		fonction.setFont(new Font("Helvetica",Font.ITALIC, 48));
		fonction.setAlignmentX(CENTER_ALIGNMENT);
		panneauHaut.add(fonction);
		panneauHaut.add(Box.createRigidArea(new Dimension(0,20)));

		return panneauHaut;	
	}

	public JPanel creerCentre(){
		JPanel panneauCentre = new JPanel();
		panneauCentre.setLayout(new BoxLayout(panneauCentre, BoxLayout.Y_AXIS));
		
		panneauCentre.add(Box.createRigidArea(new Dimension(0,20)));

		JLabel mess = new JLabel("Tapez votre message ci-dessous");
		mess.setAlignmentX(CENTER_ALIGNMENT);
		
		message = new JTextField(100);
		message.setAlignmentX(CENTER_ALIGNMENT);
		message.setMaximumSize(new Dimension(600,70));
		panneauCentre.add(mess);
		panneauCentre.add(message);
		
		panneauCentre.add(Box.createRigidArea(new Dimension(0,40)));

		JTextField consigne = new JTextField(" ATTENTION ! On ne pas écrire d'apostrophe !");
		consigne.setAlignmentX(CENTER_ALIGNMENT);
		consigne.setEditable(false);
		consigne.setMaximumSize(new Dimension(250,30));
		panneauCentre.add(consigne);
		return panneauCentre;
	}
	
	
	public JTextField getMessage() {
		return message;
	}

	public void setMessage(JTextField message) {
		this.message = message;
	}

	public static void main(String[] args) {
		ChicEtStyle cets = new ChicEtStyleJDBC();
		Principal principal = new Principal();
		MainWindow grandmere = new MainWindow(principal);
		MarleneAccueil mere = new MarleneAccueil(grandmere, cets, true);
		new MarleneAlerte(mere, cets, true).setVisible(true);
	}
}
