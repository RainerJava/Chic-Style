package chicstyle.presentation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;

import javax.swing.*;

import chicstyle.abstraction.ChicEtStyle;
import chicstyle.abstraction.ChicEtStyleJDBC;
import chicstyle.abstraction.Principal;
import chicstyle.controle.ControlJButtonNext;
import chicstyle.controle.ControlJButtonNextDirectionProduitsFinis;
import chicstyle.controle.ControlJButtonNextDirectionStock;

public class DirectionAccueil extends JFrame {

	private static final long serialVersionUID = 1L;
	public static final int LABEL_SIZE = 18;
	
	private ImageChicEtStyle imageCetS;
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

	public DirectionAccueil(MainWindow mere, ChicEtStyle cets, boolean creation){
		super("Chic & Style Direction");
		this.mere = mere;
		this.cets=cets;
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		Dimension tailleEcran = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize((int)(tailleEcran.getWidth()*3/4),(int)(tailleEcran.getHeight()*3/4));
		this.setLocation((int)(tailleEcran.getWidth()/8),(int)(tailleEcran.getHeight()/8));
		
		Container conteneur = this.getContentPane();
		conteneur.setLayout(new BorderLayout());
		conteneur.add(creerWest(), BorderLayout.WEST);
		conteneur.add(creerCentre(), BorderLayout.CENTER);
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
	
	
	public JPanel creerCentre() {
		JPanel panneauCentre = new JPanel();
		panneauCentre.setLayout(new BoxLayout(panneauCentre, BoxLayout.Y_AXIS));
		panneauCentre.add(Box.createRigidArea(new Dimension(0,this.getHeight()/6)));
		
		JLabel fonction = new JLabel("DIRECTION");
		fonction.setFont(new Font("Helvetica",Font.ITALIC, 48));
		fonction.setAlignmentX(CENTER_ALIGNMENT);
		panneauCentre.add(fonction);
		
		panneauCentre.add(Box.createRigidArea(new Dimension(0,30)));

		
		JButton suiviC = new JButton("Suivi client");
		suiviC.setMaximumSize(new Dimension(160,30));
		suiviC.setAlignmentX(CENTER_ALIGNMENT);
		ControlJButtonNextDirectionProduitsFinis control = new ControlJButtonNextDirectionProduitsFinis(this); //BOUTON
		suiviC.addActionListener(control); //BOUTON
		panneauCentre.add(suiviC);
		
		panneauCentre.add(Box.createRigidArea(new Dimension(0,10)));

		JButton stocks = new JButton("Stocks");
		stocks.setMaximumSize(new Dimension(160,30));
		stocks.setAlignmentX(CENTER_ALIGNMENT);
		ControlJButtonNextDirectionStock controlstocks = new ControlJButtonNextDirectionStock(this); //BOUTON
		stocks.addActionListener(controlstocks); //BOUTON
		panneauCentre.add(stocks);
		
		panneauCentre.add(Box.createRigidArea(new Dimension(0,10)));

		int i = cets.findAllAlertes().size();
		JButton alertes = new JButton("ALERTES  ("+i+")");
		alertes.setMaximumSize(new Dimension(160,30));
		alertes.setAlignmentX(CENTER_ALIGNMENT);
		ControlJButtonNext controlalertes = new ControlJButtonNext(this, new DirectionAlerte(this, cets, true)); //BOUTON
		alertes.addActionListener(controlalertes); //BOUTON
		panneauCentre.add(alertes);

		panneauCentre.add(Box.createRigidArea(new Dimension(0,10)));

		JButton suppr = new JButton("Suppression Données");
		suppr.setMaximumSize(new Dimension(160,30));
		suppr.setAlignmentX(CENTER_ALIGNMENT);
		ControlJButtonNext controlsuppr = new ControlJButtonNext(this, new DirectionSupprimer(this, cets, true)); //BOUTON
		suppr.addActionListener(controlsuppr); //BOUTON
		panneauCentre.add(suppr);

		panneauCentre.add(Box.createRigidArea(new Dimension(0,50)));
		
		JButton deconnexion = new JButton("Déconnexion");
		deconnexion.setMaximumSize(new Dimension(160,30));
		deconnexion.setAlignmentX(CENTER_ALIGNMENT);
		ControlJButtonNext controlDeconnexion = new ControlJButtonNext(this, mere); //BOUTON
		deconnexion.addActionListener(controlDeconnexion); //BOUTON
		panneauCentre.add(deconnexion);
		
		return panneauCentre;
	}
	
	public static void main(String[] args) {
		ChicEtStyle cets = new ChicEtStyleJDBC();
		Principal modele = new Principal();
		MainWindow mere = new MainWindow(modele);
		new DirectionAccueil(mere, cets, true).setVisible(true);
	}
}
