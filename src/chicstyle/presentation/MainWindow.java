package chicstyle.presentation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import chicstyle.abstraction.ChicEtStyle;
import chicstyle.abstraction.ChicEtStyleJDBC;
import chicstyle.abstraction.Principal;
import chicstyle.controle.ControlJButtonOkMainWindow;


public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	public static final int LABEL_SIZE = 18;
	private JTextField login;
	private JPasswordField password;
	private ChicEtStyle cets;
	private Principal modele;
	private ImageChicEtStyle imageCetS;

		
	public Principal getModele() {
		return modele;
	}

	public void setModele(Principal modele) {
		this.modele = modele;
	}

	public MainWindow(Principal modele) {
		super("Chic & Style");
		this.cets = new ChicEtStyleJDBC();
		this.modele = modele;
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		Dimension tailleEcran = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize((int)(tailleEcran.getWidth()*3/4),(int)(tailleEcran.getHeight()*3/4));
		this.setLocation((int)(tailleEcran.getWidth()/8),(int)(tailleEcran.getHeight()/8));
		Container conteneur = this.getContentPane();
		conteneur.setLayout(new BorderLayout());
		
		conteneur.add(creerWest(), BorderLayout.WEST);
		
		JPanel util = new JPanel();
		util.setLayout(new BoxLayout(util, BoxLayout.Y_AXIS));
		util.add(Box.createRigidArea(new Dimension(0,this.getHeight()/5)));
		util.add(creerHaut());
		util.add(Box.createRigidArea(new Dimension(0,30)));
		util.add(creerCentre());
		util.add(Box.createRigidArea(new Dimension(0,this.getHeight()/4)));

		conteneur.add(util);
	} 
	
public JPanel creerWest(){
		
		JPanel panneauImage = new JPanel();
		panneauImage.setBackground(Color.BLACK);
		panneauImage.setLayout(new BoxLayout(panneauImage, BoxLayout.Y_AXIS));
		panneauImage.add(Box.createRigidArea(new Dimension(0,120)));

		ImageIcon icon = new ImageIcon("images"+File.separator+"logo_C&S.jpg");
		//Image zoom = scaleImage(icon.getImage(), 0.5d);//facteur
		int pixels = this.getWidth();
		Image zoom = imageCetS.scaleImage(icon.getImage(), pixels/2);//taille en pixels
		Icon iconScaled = new ImageIcon(zoom);
		JLabel image = new JLabel(iconScaled);
		
		panneauImage.add(image, BorderLayout.CENTER);
		return panneauImage;
	}
	
	public JLabel creerHaut() {
		JLabel panneauHaut = new JLabel("BIENVENUE");
//		panneauHaut.setPreferredSize(new Dimension(100,100));
		panneauHaut.setFont(new Font("Helvetica",Font.ITALIC, 48));
		panneauHaut.setAlignmentX(CENTER_ALIGNMENT);
		return panneauHaut;
		
	}
	
	public JPanel creerCentre() {
		JPanel panneauCentre = new JPanel();
		panneauCentre.setLayout(new BoxLayout(panneauCentre, BoxLayout.Y_AXIS));
		
		JPanel log = new JPanel();
		JLabel identifiant = new JLabel("Identifiant: ");
		this.login = new JTextField(15);
		log.add(identifiant);
		log.add(login);
		
		JPanel pass = new JPanel();
		JLabel mdp = new JLabel("Mot de passe: ");
		password = new JPasswordField(15);
		pass.add(mdp);
		pass.add(password);
		
		JButton ok = new JButton("OK");
		ControlJButtonOkMainWindow controlOk = new ControlJButtonOkMainWindow(cets, this);
		ok.addActionListener(controlOk);
		
		panneauCentre.add(log);
		panneauCentre.add(pass);
		panneauCentre.add(ok); // TODO ajouter un action listener
		return panneauCentre;
	}
	
	public JTextField getLogin() {
		return login;
	}

	public void setLogin(JTextField login) {
		this.login = login;
	}

	public JPasswordField getPassword() {
		return password;
	}

	public void setPassword(JPasswordField password) {
		this.password = password;
	}

	public static void main(String[] args) {
		Principal modele = new Principal();
		MainWindow fen = new MainWindow(modele);
		fen.setVisible(true);
	}
}
