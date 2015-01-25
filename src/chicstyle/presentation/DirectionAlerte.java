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

import javax.swing.*;

import chicstyle.abstraction.ChicEtStyle;
import chicstyle.abstraction.ChicEtStyleJDBC;
import chicstyle.abstraction.Principal;
import chicstyle.controle.ControlJButtonNext;
import chicstyle.controle.ControlJButtonSupprimerDirAlertes;
import chicstyle.tableaux.TabDirectionAlerte2;
import chicstyle.tableaux.TabDirectionStock2;


public class DirectionAlerte extends JFrame {

	private static final long serialVersionUID = 1L;
	static final int LABEL_SIZE = 18;
	private ChicEtStyle cets;
	private Vector<JPanelAlertesDirection> tabAlertes;
	private DirectionAccueil mere;
	private ImageChicEtStyle imageCetS;


	public DirectionAlerte(DirectionAccueil mere, ChicEtStyle cets, boolean creation) {
		super("Chic & Style");
		this.mere = mere;
		this.cets = cets;
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		Dimension tailleEcran = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize((int)(tailleEcran.getWidth()*3/4),(int)(tailleEcran.getHeight()*3/4));
		this.setLocation((int)(tailleEcran.getWidth()/8),(int)(tailleEcran.getHeight()/8));
		Container conteneur = this.getContentPane();
		conteneur.setLayout(new BorderLayout());
		
		JPanel util = new JPanel();
		util.setLayout(new BoxLayout(util, BoxLayout.Y_AXIS));
		util.add(creerHaut());
		util.add(creerCentre());
		
        JPanel boutons = new JPanel(); //tableau
        
        JButton prec = new JButton("Accueil");
        ControlJButtonNext precedent = new ControlJButtonNext(this, mere); //BOUTON
		prec.addActionListener(precedent); //BOUTON
        boutons.add(prec);
        
        JButton supprimer = new JButton("Supprimer");
        ControlJButtonSupprimerDirAlertes controlSuppr = new ControlJButtonSupprimerDirAlertes(cets, this, mere);
        supprimer.addActionListener(controlSuppr);
        boutons.add(supprimer);
        
        util.add(boutons); 
        
        conteneur.add(creerWest(), BorderLayout.WEST);
        conteneur.add(util, BorderLayout.CENTER);
	} 

	public JPanel creerHaut() {
		JPanel panneauHaut = new JPanel();
		panneauHaut.setLayout(new BoxLayout(panneauHaut, BoxLayout.Y_AXIS));
		JLabel fonction = new JLabel("ALERTES");
		fonction.setFont(new Font("Helvetica",Font.ITALIC, 48));
		fonction.setAlignmentX(CENTER_ALIGNMENT);
		panneauHaut.add(fonction);
		return panneauHaut;	
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
		
		panneauImage.add(Box.createRigidArea(new Dimension(0,this.getHeight()/4)));

		panneauImage.add(image, BorderLayout.CENTER);
		return panneauImage;
	}
	
	public JPanel creerCentre(){
		JPanel panneauCentre = new JPanel();
		panneauCentre.setLayout(new BoxLayout(panneauCentre, BoxLayout.Y_AXIS));
		
		Vector<String[]> alertes = cets.findAllAlertes();
		if (alertes.size()>=1){
			tabAlertes = new Vector<JPanelAlertesDirection>();
			for (int i=0; i<alertes.size(); i++){
				String[] al = alertes.get(i);
				String source = al[0];
				String message = al[1];
				JPanelAlertesDirection temp = new JPanelAlertesDirection(cets, source, message);
				tabAlertes.add(temp);
				JPanel panAlerte = temp.getPanel();
				panAlerte.setAlignmentX(CENTER_ALIGNMENT);
				panneauCentre.add(panAlerte);
				panneauCentre.add(Box.createRigidArea(new Dimension(0,10)));
			}
		}
		else{
			JTextField non = new JTextField("Vous n'avez aucune alerte");
			non.setEditable(false);
			non.setMaximumSize(new Dimension(200, 50));
			panneauCentre.add(non);
		}
		
		return panneauCentre;
	}
	
	public Vector<JPanelAlertesDirection> getTabAlertes() {
		return tabAlertes;
	}

	public void setTabAlertes(Vector<JPanelAlertesDirection> tabAlertes) {
		this.tabAlertes = tabAlertes;
	}

	public DirectionAccueil getMere() {
		return mere;
	}

	public void setMere(DirectionAccueil mere) {
		this.mere = mere;
	}

	public static void main(String[] args) {
		ChicEtStyle cets = new ChicEtStyleJDBC();
		Principal modele = new Principal();
    	MainWindow grandmere = new MainWindow(modele);
		DirectionAccueil mere = new DirectionAccueil(grandmere, cets, true);
        new DirectionAlerte(mere, cets, true).setVisible(true);
	}
	

}
