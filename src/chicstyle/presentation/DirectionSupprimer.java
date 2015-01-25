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
import javax.swing.JTable;

import chicstyle.abstraction.ChicEtStyle;
import chicstyle.abstraction.ChicEtStyleJDBC;
import chicstyle.abstraction.Principal;
import chicstyle.controle.ControlJButtonNext;
import chicstyle.controle.ControlJButtonValiderSuppression;
import chicstyle.tableaux.TabDirectionProduitsFinis2;

public class DirectionSupprimer extends JFrame{

	private static final long serialVersionUID = 1L;
	static final int LABEL_SIZE = 18;
	private ComboBoxes combobox;
	private ImageChicEtStyle imageCetS;
	private ChicEtStyle cets;
	private DirectionAccueil mere;	

	public DirectionAccueil getMere() {
		return mere;
	}

	public void setMere(DirectionAccueil mere) {
		this.mere = mere;
	}

	public ComboBoxes getCombobox() {
		return combobox;
	}

	public void setCombobox(ComboBoxes combobox) {
		this.combobox = combobox;
	}

	public DirectionSupprimer(DirectionAccueil mere, ChicEtStyle cets, boolean creation) {
		//INITIALISATIONS
		super("Chic & Style");
		this.cets = cets;
		this.mere = mere;
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
		panneauImage.add(Box.createRigidArea(new Dimension(0,120)));

		ImageIcon icon = new ImageIcon("images"+File.separator+"logo_C&S.jpg");
		//Image zoom = scaleImage(icon.getImage(), 0.5d);//facteur
		int pixels = (int)this.getSize().getWidth();
		Image zoom = imageCetS.scaleImage(icon.getImage(), pixels/2);//taille en pixels
		Icon iconScaled = new ImageIcon(zoom);
		JLabel image = new JLabel(iconScaled);

		panneauImage.add(image, BorderLayout.CENTER);
		return panneauImage;
	}

	public JPanel creerCentre(){
	JPanel panneauCentre = new JPanel();
	panneauCentre.setLayout(new BoxLayout(panneauCentre, BoxLayout.Y_AXIS));
//	panneauCentre.add(Box.createRigidArea(new Dimension(0,60)));

	panneauCentre.add(Box.createRigidArea(new Dimension(0,80)));

	JLabel fonction = new JLabel("SUPPRESSION");
	fonction.setFont(new Font("Helvetica",Font.ITALIC, 48));
	fonction.setAlignmentX(CENTER_ALIGNMENT);
	panneauCentre.add(fonction);

	panneauCentre.add(Box.createRigidArea(new Dimension(0,50)));

	this.combobox = new ComboBoxes(cets, 0);
	combobox.getTi().setVisible(false);
	combobox.getCo().setVisible(false);
	panneauCentre.add(combobox.getBox());
	
	JButton validerSuppr = new JButton("Valider Suppression");
	ControlJButtonValiderSuppression controlValSuppr = new ControlJButtonValiderSuppression(cets,this, mere);
	validerSuppr.addActionListener(controlValSuppr);
	validerSuppr.setAlignmentX(CENTER_ALIGNMENT);
	panneauCentre.add(validerSuppr);
	
	panneauCentre.add(Box.createRigidArea(new Dimension(0,20)));

	JButton prec = new JButton("Accueil");
    ControlJButtonNext precedent = new ControlJButtonNext(this, mere); //BOUTON
	prec.addActionListener(precedent);
	prec.setAlignmentX(CENTER_ALIGNMENT);
	panneauCentre.add(prec);
	
	panneauCentre.add(Box.createRigidArea(new Dimension(0,250)));
	
	return panneauCentre;
}

public static void main(String[] args) {
	ChicEtStyle cets = new ChicEtStyleJDBC();
	Principal principal = new Principal();
	MainWindow grandmere = new MainWindow(principal);
	DirectionAccueil mere = new DirectionAccueil(grandmere, cets, true);
	new DirectionSupprimer(mere, cets, true).setVisible(true);
}


}
