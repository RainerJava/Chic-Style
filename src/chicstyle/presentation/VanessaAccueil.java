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
import chicstyle.controle.ControlJButtonNextVanessaBoite;
import chicstyle.controle.ControlJButtonNextVanessaFournitures;
import chicstyle.controle.ControlJButtonNextVanessaHoussage;
import chicstyle.controle.ControlJButtonNextVanessaStock;

public class VanessaAccueil extends JFrame{
	
	private static final long serialVersionUID = 1L;
	static final int LABEL_SIZE = 18;
	ImageChicEtStyle imageCetS;
	private MainWindow mere;
	private ChicEtStyle cets;
		
	public ChicEtStyle getCets() {
		return cets;
	}

	public void setCets(ChicEtStyle cets) {
		this.cets = cets;
	}

	public MainWindow getMere() {
		return mere;
	}

	public void setMere(MainWindow mere) {
		this.mere = mere;
	}

	
	public VanessaAccueil(MainWindow mere , ChicEtStyle cets, boolean creation){
		super("Chic&Style");
		this.mere=mere;
		this.cets=cets;
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		Dimension tailleEcran = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize((int)(tailleEcran.getWidth()*3/4),(int)(tailleEcran.getHeight()*3/4));
		this.setLocation((int)(tailleEcran.getWidth()/8),(int)(tailleEcran.getHeight()/8));
		
		Container conteneur = this.getContentPane();
		conteneur.setLayout(new BorderLayout());
		conteneur.add(creerWest(), BorderLayout.WEST);
		conteneur.add(creerHaut(),BorderLayout.CENTER);
	}
	
	public JPanel creerWest(){
		
		JPanel panneauImage = new JPanel();
		panneauImage.setBackground(Color.BLACK);
		panneauImage.setLayout(new BoxLayout(panneauImage, BoxLayout.Y_AXIS));

		int pixel = this.getWidth();
		ImageIcon icon = new ImageIcon("images"+File.separator+"logo_C&S.jpg");
		//Image zoom = scaleImage(icon.getImage(), 0.5d);//facteur
		Image zoom = imageCetS.scaleImage(icon.getImage(), pixel/2);//taille en pixels
		Icon iconScaled = new ImageIcon(zoom);
		JLabel image = new JLabel(iconScaled);
		
		panneauImage.add(Box.createRigidArea(new Dimension(0,this.getHeight()/6)));
		panneauImage.add(image, BorderLayout.CENTER);
		return panneauImage;
	}
	
	public JPanel creerHaut(){
		JPanel panneauHaut = new JPanel();
		panneauHaut.setLayout(new BoxLayout(panneauHaut, BoxLayout.Y_AXIS));
		panneauHaut.add(Box.createRigidArea(new Dimension(0,80)));
		JLabel fonction = new JLabel("HOUSSAGE ET FOURNITURES");
		fonction.setFont(new Font("Helvetica",Font.ITALIC, 40));
		fonction.setAlignmentX(CENTER_ALIGNMENT);
		panneauHaut.add(fonction);
		panneauHaut.add(Box.createRigidArea(new Dimension(0,50)));
		
		JButton houssage = new JButton("HOUSSAGE");
		houssage.setAlignmentX(CENTER_ALIGNMENT);
		houssage.setAlignmentY(CENTER_ALIGNMENT);
		houssage.setToolTipText("entrer les vêtements houssés");
		houssage.setMaximumSize(new Dimension(200,40));
		ControlJButtonNextVanessaHoussage controlHoussage = new ControlJButtonNextVanessaHoussage(this);
		houssage.addActionListener(controlHoussage);
		panneauHaut.add(houssage);
		panneauHaut.add(Box.createRigidArea(new Dimension(0,30)));

		JButton fournitures = new JButton("FOURNITURES");
		fournitures.setAlignmentX(CENTER_ALIGNMENT);
		fournitures.setAlignmentY(CENTER_ALIGNMENT);
		fournitures.setToolTipText("entrer une réception de fourniture");
		fournitures.setMaximumSize(new Dimension(200,40));
		ControlJButtonNextVanessaFournitures controlFournitures = new ControlJButtonNextVanessaFournitures(this);
		fournitures.addActionListener(controlFournitures);
		panneauHaut.add(fournitures);
		panneauHaut.add(Box.createRigidArea(new Dimension(0,30)));
		
		JButton boite = new JButton("BOITE");
		boite.setAlignmentX(CENTER_ALIGNMENT);
		boite.setAlignmentY(CENTER_ALIGNMENT);
		boite.setToolTipText("réaliser une boîte");
		boite.setMaximumSize(new Dimension(200,40));
		ControlJButtonNextVanessaBoite controlBoite = new ControlJButtonNextVanessaBoite(this);
		boite.addActionListener(controlBoite);
		panneauHaut.add(boite);
		panneauHaut.add(Box.createRigidArea(new Dimension(0,30)));
		
		JButton stock = new JButton("STOCK");
		stock.setAlignmentX(CENTER_ALIGNMENT);
		stock.setAlignmentY(CENTER_ALIGNMENT);
		stock.setToolTipText("réaliser une boîte");
		stock.setMaximumSize(new Dimension(200,40));
		ControlJButtonNextVanessaStock controlStock = new ControlJButtonNextVanessaStock(this);
		stock.addActionListener(controlStock);
		panneauHaut.add(stock);
		panneauHaut.add(Box.createRigidArea(new Dimension(0,300)));
	
		JButton deconnexion = new JButton("Déconnexion");
		deconnexion.setMaximumSize(new Dimension(150,30));
		deconnexion.setAlignmentX(CENTER_ALIGNMENT);
		ControlJButtonNext controlDeconnexion = new ControlJButtonNext(this, mere); //BOUTON
		deconnexion.addActionListener(controlDeconnexion); //BOUTON
		panneauHaut.add(deconnexion);
		
		return panneauHaut;
	}	
	
	public static void main(String[] args) {
		ChicEtStyle cets = new ChicEtStyleJDBC();
		Principal principal = new Principal();
		MainWindow mere = new MainWindow(principal);
		new VanessaAccueil(mere, cets, true).setVisible(true);
	}
	
}