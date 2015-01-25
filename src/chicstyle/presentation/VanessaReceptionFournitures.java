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

import chicstyle.abstraction.ChicEtStyle;
import chicstyle.abstraction.ChicEtStyleJDBC;
import chicstyle.abstraction.Principal;
import chicstyle.controle.ControlJButtonEnregistrerReceptionFourniture;
import chicstyle.controle.ControlJButtonNext;

public class VanessaReceptionFournitures extends JFrame{
	
	private static final long serialVersionUID = 1L;
	static final int LABEL_SIZE = 18;
	private JTextField texteType;	
	private ComboBoxesReceptionFourniture combobox;
	private JTextField quantite;
	private VanessaAccueil mere;
	private ChicEtStyle cets;
	private ImageChicEtStyle imageCetS;
	
	public VanessaAccueil getMere() {
		return mere;
	}

	public void setMere(VanessaAccueil mere) {
		this.mere = mere;
	}

	public ChicEtStyle getCets() {
		return cets;
	}

	public void setCets(ChicEtStyle cets) {
		this.cets = cets;
	}

	public JTextField getQuantite() {
		return quantite;
	}

	public void setQuantite(JTextField quantite) {
		this.quantite = quantite;
	}

	public ComboBoxesReceptionFourniture getCombobox() {
		return combobox;
	}

	public void setCombobox(ComboBoxesReceptionFourniture combobox) {
		this.combobox = combobox;
	}

	public JTextField getTexteType() {
		return texteType;
	}

	public void setTexteType(JTextField texteType) {
		this.texteType = texteType;
	}

	public VanessaReceptionFournitures(VanessaAccueil mere, ChicEtStyle cets, boolean creation) {
		super("Chic & Style");
		this.mere=mere;
		this.cets=cets;
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		Dimension tailleEcran = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize((int)(tailleEcran.getWidth()*3/4),(int)(tailleEcran.getHeight()*3/4));
		this.setLocation((int)(tailleEcran.getWidth()/8),(int)(tailleEcran.getHeight()/8));
		
		
		Container conteneur = this.getContentPane();
		conteneur.setLayout(new BorderLayout());
		conteneur.add(creerWest(), BorderLayout.WEST);
		
		JPanel util = new JPanel();
		util.setLayout(new BoxLayout(util, BoxLayout.Y_AXIS));
		
		util.add(creerHaut());
		JPanel boutons = new JPanel(); //
		
		JButton validate = new JButton("Valider");
		ControlJButtonEnregistrerReceptionFourniture controlValiderNouvelleFourniture = new ControlJButtonEnregistrerReceptionFourniture(cets,this, mere); //BOUTON
		validate.addActionListener(controlValiderNouvelleFourniture); //BOUTON
		boutons.add(validate);
		
		JButton retour = new JButton("Retour");
		ControlJButtonNext ret = new ControlJButtonNext(this, mere); //BOUTON
		retour.addActionListener(ret); //BOUTON
		boutons.add(retour);

		util.add(boutons);
		
		conteneur.add(util);
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
	
	
	
	public JPanel creerHaut() {
		JPanel panneauHaut = new JPanel();
		panneauHaut.setLayout(new BoxLayout(panneauHaut, BoxLayout.Y_AXIS));
		panneauHaut.add(Box.createRigidArea(new Dimension(0,20)));
		JLabel titre = new JLabel("RECEPTION");
		titre.setFont(new Font("Helvetica",Font.ITALIC, 40));
		titre.setAlignmentX(CENTER_ALIGNMENT);
		panneauHaut.add(titre);
		JLabel fonction = new JLabel("FOURNITURES");
		fonction.setFont(new Font("Helvetica",Font.ITALIC, 40));
		fonction.setAlignmentX(CENTER_ALIGNMENT);
		panneauHaut.add(fonction);
		
		panneauHaut.add(Box.createRigidArea(new Dimension(0,20)));
		
		combobox = new ComboBoxesReceptionFourniture(cets);
		panneauHaut.add(combobox.getBox());
		
		panneauHaut.add(Box.createRigidArea(new Dimension(0,20)));

				
		JPanel panelQuantite = new JPanel();
		JLabel nomQuantite = new JLabel("Quantité reçue");
		quantite = new JTextField("");
		quantite.setPreferredSize(new Dimension(150,20));
		quantite.setEnabled(true);
		panelQuantite.add(nomQuantite);
		panelQuantite.add(quantite);
		panneauHaut.add(panelQuantite);
		
		panneauHaut.add(Box.createRigidArea(new Dimension(0,50)));

		return panneauHaut;
	}

		public static void main(String[] args) {
			ChicEtStyle cets = new ChicEtStyleJDBC();
			Principal principal = new Principal();
			MainWindow grandmere = new MainWindow(principal);
			VanessaAccueil mere = new VanessaAccueil(grandmere, cets, true);
			new VanessaReceptionFournitures(mere, cets, true).setVisible(true);
		}
		
}
	
	
