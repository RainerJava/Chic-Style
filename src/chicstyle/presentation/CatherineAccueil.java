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

import chicstyle.abstraction.ChicEtStyle;
import chicstyle.abstraction.ChicEtStyleJDBC;
import chicstyle.abstraction.Principal;
import chicstyle.controle.ControlJButtonNext;
import chicstyle.controle.ControlJButtonNextCatherineNomenclature;
import chicstyle.controle.ControlJButtonNextCatherinePret;
import chicstyle.controle.ControlJButtonNextLancement;
import chicstyle.controle.ControlJButtonNextNouvelleCommande;
import chicstyle.tableaux.TabNomenclature1;

public class CatherineAccueil extends JFrame {

	private static final long serialVersionUID = 1L;
	static final int LABEL_SIZE = 18;
	private TabNomenclature1 modele;
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

	public CatherineAccueil(MainWindow mere, ChicEtStyle cets, boolean creation) {
		super("Chic & Style");
		this.mere=mere;
		this.cets=cets;
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		Dimension tailleEcran = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize((int)(tailleEcran.getWidth()*3/4),(int)(tailleEcran.getHeight()*3/4));
		this.setLocation((int)(tailleEcran.getWidth()/8),(int)(tailleEcran.getHeight()/8));
		
		Container conteneur = this.getContentPane();
		conteneur.setLayout(new BorderLayout());
		conteneur.add(creerCentre(), BorderLayout.CENTER);
		conteneur.add(creerWest(), BorderLayout.WEST);
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
	
	public JPanel creerCentre() {
		JPanel panneauCentre = new JPanel();
		panneauCentre.setLayout(new BoxLayout(panneauCentre, BoxLayout.Y_AXIS));
		
		panneauCentre.add(Box.createRigidArea(new Dimension(0,100)));
		JLabel fonction = new JLabel("SECRETARIAT");
		fonction.setFont(new Font("Helvetica",Font.ITALIC, 48));
		fonction.setAlignmentX(CENTER_ALIGNMENT);
		panneauCentre.add(fonction);
		panneauCentre.add(Box.createRigidArea(new Dimension(0,50)));
		
		JButton newCommande = new JButton("NOUVELLE COMMANDE");
		newCommande.setAlignmentX(CENTER_ALIGNMENT);
		newCommande.setAlignmentY(CENTER_ALIGNMENT);
		newCommande.setToolTipText("entrer une nouvelle commande");
		newCommande.setMaximumSize(new Dimension(200,40));
		ControlJButtonNextNouvelleCommande control = new ControlJButtonNextNouvelleCommande(this); //BOUTON
		newCommande.addActionListener(control); //BOUTON
		panneauCentre.add(newCommande);
		panneauCentre.add(Box.createRigidArea(new Dimension(0,30)));
		
		JButton expe = new JButton("PRODUITS FINIS");
		expe.setAlignmentX(CENTER_ALIGNMENT);
		expe.setAlignmentY(CENTER_ALIGNMENT);
		expe.setToolTipText("contrôler ce qui est prêt à expédier");
		expe.setMaximumSize(new Dimension(200,40));
		ControlJButtonNextCatherinePret controlexpe = new ControlJButtonNextCatherinePret(this); //BOUTON
		expe.addActionListener(controlexpe); //BOUTON
		panneauCentre.add(expe);
		panneauCentre.add(Box.createRigidArea(new Dimension(0,30)));
		
		JButton nomenclature = new JButton("NOMENCLATURE");
		nomenclature.setAlignmentX(CENTER_ALIGNMENT);
		nomenclature.setAlignmentY(CENTER_ALIGNMENT);
		nomenclature.setToolTipText("entrer la nomenclature pour un produit");
		nomenclature.setMaximumSize(new Dimension(200,40));
		ControlJButtonNextCatherineNomenclature controlnom = new ControlJButtonNextCatherineNomenclature(this); //BOUTON
		nomenclature.addActionListener(controlnom); //BOUTON
		panneauCentre.add(nomenclature);
		panneauCentre.add(Box.createRigidArea(new Dimension(0,30)));
		
		JButton lancement = new JButton("LANCEMENT");
		lancement.setAlignmentX(CENTER_ALIGNMENT);
		lancement.setAlignmentY(CENTER_ALIGNMENT);
		lancement.setToolTipText("entrer un lancement");
		lancement.setMaximumSize(new Dimension(200,40));
		ControlJButtonNextLancement controllanc = new ControlJButtonNextLancement(this); //BOUTON
		lancement.addActionListener(controllanc); //BOUTON
		panneauCentre.add(lancement);
		panneauCentre.add(Box.createRigidArea(new Dimension(0,130)));
		
		JButton deconnexion = new JButton("Déconnexion");
		deconnexion.setMaximumSize(new Dimension(200,40));
		deconnexion.setAlignmentX(CENTER_ALIGNMENT);
		ControlJButtonNext controlDeconnexion = new ControlJButtonNext(this, new MainWindow(this.getMere().getModele())); //BOUTON
		deconnexion.addActionListener(controlDeconnexion); //BOUTON
		panneauCentre.add(deconnexion);
		
		
		
		return panneauCentre;
	}
	
	public static void main(String[] args) {
		ChicEtStyle cets = new ChicEtStyleJDBC();
		Principal modele = new Principal();
		MainWindow mere = new MainWindow(modele);
		new CatherineAccueil(mere, cets, true).setVisible(true);;
	}
}
