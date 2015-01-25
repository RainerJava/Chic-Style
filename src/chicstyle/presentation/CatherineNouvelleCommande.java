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
import java.util.Vector;

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
import chicstyle.controle.ControlJButtonEnregistrerCommande;
import chicstyle.controle.ControlJButtonNext;

public class CatherineNouvelleCommande extends JFrame{
	
	private static final long serialVersionUID = 1L;
	static final int LABEL_SIZE = 18;
	private ImageChicEtStyle imageCetS;
	private JComboBox<String> comboClients;
	private JTextField nom;
	private JTextField tis;
	private JTextField cou;
	private JTextField ty;
	private CatherineAccueil mere;
	private ChicEtStyle cets;
	
	public CatherineAccueil getMere() {
		return mere;
	}

	public void setMere(CatherineAccueil mere) {
		this.mere = mere;
	}

	public JComboBox<String> getComboClients() {
		return comboClients;
	}

	public void setComboClients(JComboBox<String> comboClients) {
		this.comboClients = comboClients;
	}

	public JTextField getNom() {
		return nom;
	}

	public void setNom(JTextField nom) {
		this.nom = nom;
	}

	public JTextField getTis() {
		return tis;
	}

	public void setTis(JTextField tis) {
		this.tis = tis;
	}

	public JTextField getCou() {
		return cou;
	}

	public void setCou(JTextField cou) {
		this.cou = cou;
	}

	public JTextField getTy() {
		return ty;
	}

	public void setTy(JTextField ty) {
		this.ty = ty;
	}

	public CatherineNouvelleCommande(CatherineAccueil mere, ChicEtStyle cets, boolean creation) {
		super("Chic & Style");
		this.cets=cets;
		this.mere=mere;
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		Dimension tailleEcran = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize((int)(tailleEcran.getWidth()*3/4),(int)(tailleEcran.getHeight()*3/4));
		this.setLocation((int)(tailleEcran.getWidth()/8),(int)(tailleEcran.getHeight()/8));
		
		Container conteneur = this.getContentPane();
		conteneur.setLayout(new BorderLayout());
		
		JPanel util = new JPanel();
		util.setLayout(new BorderLayout());
		util.add(creerHaut(), BorderLayout.NORTH);
		util.add(creerCentre(), BorderLayout.CENTER);
		util.add(creerBas(), BorderLayout.SOUTH);
		
		conteneur.add(creerWest(), BorderLayout.WEST);
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
		Image zoom = imageCetS.scaleImage(icon.getImage(), pixel/2);//taille en pixels
		Icon iconScaled = new ImageIcon(zoom);
		JLabel image = new JLabel(iconScaled);
		
		panneauImage.add(image, BorderLayout.CENTER);
		return panneauImage;
	}
	
	public JPanel creerHaut() {
		JPanel panneauHaut = new JPanel();
		panneauHaut.setLayout(new BoxLayout(panneauHaut, BoxLayout.Y_AXIS));
		panneauHaut.add(Box.createRigidArea(new Dimension(0,80)));

		JLabel fonction = new JLabel("NOUVELLE COMMANDE");
		fonction.setFont(new Font("Helvetica",Font.ITALIC, 48));
		fonction.setAlignmentX(CENTER_ALIGNMENT);
		panneauHaut.add(fonction);
		panneauHaut.add(Box.createRigidArea(new Dimension(0,60)));

		return panneauHaut;	
	}
	
	public JPanel creerCentre() {
		JPanel panneauCentre = new JPanel();
		panneauCentre.setLayout(new BoxLayout(panneauCentre, BoxLayout.Y_AXIS));
		
		JPanel nomClient = new JPanel();
		JLabel client = new JLabel("Client: ");
		Vector<String> clients = cets.findAllClients();
		comboClients=new JComboBox<String>(clients);
		comboClients.setEditable(true);
		comboClients.setMaximumSize(new Dimension(170,25));
		comboClients.setAlignmentX(CENTER_ALIGNMENT);
		comboClients.setAlignmentY(CENTER_ALIGNMENT);
		nomClient.add(client);
		nomClient.add(comboClients);
		
		JPanel nomModele = new JPanel();
		JLabel modele = new JLabel("Modèle: ");
		nom = new JTextField(15);
		nomModele.setAlignmentX(CENTER_ALIGNMENT);
		nomModele.setAlignmentY(CENTER_ALIGNMENT);
		nomModele.add(modele);
		nomModele.add(nom);
		
		JPanel nomTissu = new JPanel();
		JLabel tissu = new JLabel("   Tissu: ");
		tis = new JTextField(15);
		nomTissu.setAlignmentX(CENTER_ALIGNMENT);
		nomTissu.setAlignmentY(CENTER_ALIGNMENT);
		nomTissu.add(tissu);
		nomTissu.add(tis);
		
		JPanel nomCouleur = new JPanel();
		JLabel couleur = new JLabel("Couleur: ");
		cou = new JTextField(15);
		nomCouleur.setAlignmentX(CENTER_ALIGNMENT);
		nomCouleur.setAlignmentY(CENTER_ALIGNMENT);
		nomCouleur.add(couleur);
		nomCouleur.add(cou);
		
		JPanel nomType = new JPanel();
		JLabel type = new JLabel("     Type: ");
		ty = new JTextField(15);
		nomType.setAlignmentX(CENTER_ALIGNMENT);
		nomType.setAlignmentY(CENTER_ALIGNMENT);
		nomType.add(type);
		nomType.add(ty);
		
		JButton valider = new JButton("VALIDER");
		ControlJButtonEnregistrerCommande controlvalid = new ControlJButtonEnregistrerCommande(cets, this, new CatherineAccueil(this.getMere().getMere(),cets,true));
		valider.addActionListener(controlvalid);
		valider.setAlignmentX(CENTER_ALIGNMENT);
		valider.setAlignmentY(CENTER_ALIGNMENT);
		valider.setMaximumSize(new Dimension(120,30));
		
		panneauCentre.add(nomClient);
		panneauCentre.add(nomModele);
		panneauCentre.add(nomTissu);
		panneauCentre.add(nomCouleur);
		panneauCentre.add(nomType);
		panneauCentre.add(Box.createRigidArea(new Dimension(0,100)));
		panneauCentre.add(valider);
		panneauCentre.add(Box.createRigidArea(new Dimension(0,150)));
		return panneauCentre;
	}
	
	public JPanel creerBas(){
		JPanel panneauBas = new JPanel();
		panneauBas.setLayout(new BoxLayout(panneauBas, BoxLayout.Y_AXIS));
		
		JButton precedent = new JButton("Précédent");
		ControlJButtonNext controlprec = new ControlJButtonNext(this, new CatherineAccueil(this.getMere().getMere(),cets,true)); //BOUTON
		precedent.addActionListener(controlprec); //BOUTON
		precedent.setAlignmentX(CENTER_ALIGNMENT);
		precedent.setAlignmentY(CENTER_ALIGNMENT);
		
		panneauBas.add(precedent);
		panneauBas.add(Box.createRigidArea(new Dimension(0,20)));
		return panneauBas;
	}
	
	public static void main(String[] args) {
		ChicEtStyle cets = new ChicEtStyleJDBC();
		Principal principal = new Principal();
		MainWindow grandmere = new MainWindow(principal);
		CatherineAccueil mere = new CatherineAccueil(grandmere, cets, true);
		new CatherineNouvelleCommande(mere, cets, true).setVisible(true);
	}
}
