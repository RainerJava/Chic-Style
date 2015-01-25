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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

import chicstyle.abstraction.ChicEtStyle;
import chicstyle.abstraction.ChicEtStyleJDBC;
import chicstyle.abstraction.Principal;
import chicstyle.controle.ControlJButtonNext;

public class ClaudieAccueil extends JFrame {

	private static final long serialVersionUID = 1L;
	static final int LABEL_SIZE = 18;
	private ImageChicEtStyle imageCetS;
	private MainWindow mere;
	
	public MainWindow getMere() {
		return mere;
	}

	public void setMere(MainWindow mere) {
		this.mere = mere;
	}

	public ClaudieAccueil(MainWindow mere, ChicEtStyle cets, boolean creation){
		super("Chic&Style");
		this.mere=mere;
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		Dimension tailleEcran = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize((int)(tailleEcran.getWidth()*3/4),(int)(tailleEcran.getHeight()*3/4));
		this.setLocation((int)(tailleEcran.getWidth()/8),(int)(tailleEcran.getHeight()/8));
		
		Container conteneur = this.getContentPane();
		conteneur.setLayout(new BorderLayout());
		conteneur.add(creerWest(), BorderLayout.WEST);
		
		JPanel est = new JPanel();
		est.setLayout(new BorderLayout());
		est.add(creerHaut(cets), BorderLayout.NORTH);
		est.add(creerBas(), BorderLayout.SOUTH);
		
		conteneur.add(est,BorderLayout.CENTER);
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

	public JPanel creerHaut(ChicEtStyle cets){
		JPanel panneauHaut = new JPanel();
		panneauHaut.setLayout(new BoxLayout(panneauHaut, BoxLayout.Y_AXIS));
		
		panneauHaut.add(Box.createRigidArea(new Dimension(0,this.getHeight()/6)));

		JLabel titre = new JLabel("Réception tissus");
		titre.setFont(new Font("Helvetica",Font.ITALIC, 48));
		titre.setAlignmentX(CENTER_ALIGNMENT);
		panneauHaut.add(titre);
		
		panneauHaut.add(Box.createRigidArea(new Dimension(0,30)));
		
		JButton houssage = new JButton("RECEPTION TISSUS");
		ControlJButtonNext controlHoussage = new ControlJButtonNext(this, new ClaudieReception(this, cets, true));
		houssage.addActionListener(controlHoussage);
		houssage.setAlignmentX(CENTER_ALIGNMENT);
		houssage.setMaximumSize(new Dimension(150,30));

		
		JButton decoupe = new JButton("DECOUPE");
		ControlJButtonNext controlDecoupe = new ControlJButtonNext(this, new ClaudieDecoupe(this, cets, true));
		decoupe.addActionListener(controlDecoupe);
		decoupe.setAlignmentX(CENTER_ALIGNMENT);
		decoupe.setMaximumSize(new Dimension(150,30));

		JButton fournitures = new JButton("ALERTE");
		ControlJButtonNext controlFournitures = new ControlJButtonNext(this, new ClaudieAlerte(this, cets,true));
		fournitures.addActionListener(controlFournitures);
		fournitures.setAlignmentX(CENTER_ALIGNMENT);
		fournitures.setMaximumSize(new Dimension(150,30));
		
		panneauHaut.add(houssage);
		panneauHaut.add(Box.createRigidArea(new Dimension(0,10)));
		panneauHaut.add(decoupe);
		panneauHaut.add(Box.createRigidArea(new Dimension(0,10)));
		panneauHaut.add(fournitures);
		
		return panneauHaut;
	}
	
	
	public JPanel creerBas(){
		JPanel panneauBas = new JPanel();
		JButton deconnexion = new JButton("Déconnexion");
		deconnexion.setMaximumSize(new Dimension(150,30));
		deconnexion.setAlignmentX(CENTER_ALIGNMENT);
		ControlJButtonNext controlDeconnexion = new ControlJButtonNext(this, mere); //BOUTON
		deconnexion.addActionListener(controlDeconnexion); //BOUTON
		panneauBas.add(deconnexion);
		panneauBas.add(Box.createRigidArea(new Dimension(0,20)));

		
		return panneauBas;
	}
	
	
	public static void main(String[] args) {
		ChicEtStyle cets = new ChicEtStyleJDBC();
		Principal principal = new Principal();
		MainWindow mere = new MainWindow(principal);
		new ClaudieAccueil(mere, cets, true).setVisible(true);
	}
}
