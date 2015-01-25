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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;

import chicstyle.abstraction.ChicEtStyle;
import chicstyle.abstraction.ChicEtStyleJDBC;
import chicstyle.abstraction.Principal;
import chicstyle.controle.ControlJButtonEnregistrerCommande;
import chicstyle.controle.ControlJButtonNext;
import chicstyle.controle.ControlJButtonOkMarleneAccueil;
import chicstyle.controle.ControlJButtonValiderPatronnage;
import chicstyle.controle.ControlJComboBoxRefMarleneAcc;
import chicstyle.tableaux.TabMarlene2;

public class MarleneAccueil extends JFrame{

	private static final long serialVersionUID = 1L;
	static final int LABEL_SIZE = 18;
	private TabMarlene2 modele = new TabMarlene2(); //tableau
	private JTable tableau; //tableau
	private ComboBoxes comboBoxes;
	private JComboBox<String> comboRef;
	private JTextField lon;
	private MainWindow mere;
	private ChicEtStyle cets;
	private ImageChicEtStyle imageCetS;
	
	public MainWindow getMere() {
		return mere;
	}

	public void setMere(MainWindow mere) {
		this.mere = mere;
	}

	public ChicEtStyle getCets() {
		return cets;
	}

	public void setCets(ChicEtStyle cets) {
		this.cets = cets;
	}

	public JTextField getLon() {
		return lon;
	}

	public void setLon(JTextField lon) {
		this.lon = lon;
	}

	public JComboBox<String> getComboRef() {
		return comboRef;
	}

	public void setComboRef(JComboBox<String> comboRef) {
		this.comboRef = comboRef;
	}

	private JTextField laizeField;
	
	//Getter et Setters
	public ComboBoxes getComboBoxes() {
		return comboBoxes;
	}

	public JTable getTableau() {
		return tableau;
	}

	public void setTableau(JTable tableau) {
		this.tableau = tableau;
	}

	public void setComboBoxes(ComboBoxes comboBoxes) {
		this.comboBoxes = comboBoxes;
	}

	public JTextField getLaizeField() {
		return laizeField;
	}

	public void setLaizeField(JTextField laizeField) {
		this.laizeField = laizeField;
	}

	public MarleneAccueil(MainWindow mere, ChicEtStyle cets, boolean creation) {
		super("Chic & Style");
		this.mere=mere;
		this.cets=cets;
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		Dimension tailleEcran = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize((int)(tailleEcran.getWidth()*3/4),(int)(tailleEcran.getHeight()*3/4));
		this.setLocation((int)(tailleEcran.getWidth()/8),(int)(tailleEcran.getHeight()/8));
		
		Container conteneur = this.getContentPane();
		conteneur.setLayout(new BorderLayout());
		
		JPanel droite = new JPanel();
		droite.setLayout(new BoxLayout(droite,BoxLayout.Y_AXIS));
		droite.add(creerHaut());
		
		tableau = new JTable(modele); //tableau
        droite.add(new JScrollPane(tableau)); //tableau
        
        JPanel boutons = new JPanel(); //tableau
        JButton validate = new JButton("Valider");
		ControlJButtonValiderPatronnage controlvalid = new ControlJButtonValiderPatronnage(cets, this, this.mere);
		validate.addActionListener(controlvalid);
        boutons.add(validate);
        
        JButton alerte = new JButton("ALERTE");
        ControlJButtonNext controlFournitures = new ControlJButtonNext(this, new MarleneAlerte(this, cets, true));
		alerte.addActionListener(controlFournitures);
        boutons.add(alerte);
        
        JButton precedent = new JButton("Déconnexion");
        ControlJButtonNext prec = new ControlJButtonNext(this, mere); //BOUTON
		precedent.addActionListener(prec); //BOUTON
		boutons.add(precedent);
        
        droite.add(boutons); //tableau
        
        conteneur.add(creerWest(), BorderLayout.WEST);
        conteneur.add(droite);
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

	public JPanel creerHaut() {
		JPanel panneauHaut = new JPanel();
		panneauHaut.setLayout(new BoxLayout(panneauHaut, BoxLayout.Y_AXIS));
		
		panneauHaut.add(Box.createRigidArea(new Dimension(0,30)));
		JLabel fonction = new JLabel("PATRONNAGE");
		fonction.setFont(new Font("Helvetica",Font.ITALIC, 48));
		fonction.setAlignmentX(CENTER_ALIGNMENT);
		panneauHaut.add(fonction);
		panneauHaut.add(Box.createRigidArea(new Dimension(0,30)));
		
		ComboBoxes combobox = new ComboBoxes(cets,0);
		panneauHaut.add(combobox.getBox());
		this.comboBoxes=combobox;
		panneauHaut.add(Box.createRigidArea(new Dimension(0,20)));
		
		JButton ok = new JButton("OK");
		ok.addActionListener(new ControlJButtonOkMarleneAccueil(cets, this));
		panneauHaut.add(ok);
		panneauHaut.add(Box.createRigidArea(new Dimension(0,20)));

		JPanel refPanel = new JPanel();
		refPanel.setLayout(new BoxLayout(refPanel, BoxLayout.X_AXIS));
		JLabel reference = new JLabel("Référence(s): ");
		comboRef = new JComboBox<String>();
		comboRef.setMaximumSize(new Dimension(150,25));
		comboRef.setAlignmentX(CENTER_ALIGNMENT);
		comboRef.setAlignmentY(CENTER_ALIGNMENT);
		comboRef.setEditable(false);
		ControlJComboBoxRefMarleneAcc controlComboRef = new ControlJComboBoxRefMarleneAcc(cets, this);
		comboRef.addActionListener(controlComboRef);
		refPanel.add(reference);
		refPanel.add(comboRef);
		panneauHaut.add(refPanel);
		panneauHaut.add(Box.createRigidArea(new Dimension(0,20)));
		
		JPanel laizePanel = new JPanel();
		laizePanel.add(new JLabel("laize utilisée"));
		JTextField laizeField = new JTextField(10);
		this.laizeField=laizeField;
		laizePanel.add(laizeField);
		laizePanel.add(Box.createRigidArea(new Dimension(50,0)));
		laizePanel.add(new JLabel("longueur annoncée par le client :"));
		lon = new JTextField(10);
		lon.setEditable(false);
		laizePanel.add(lon);
		panneauHaut.add(laizePanel);
		panneauHaut.add(Box.createRigidArea(new Dimension(0,20)));
		return panneauHaut;	
	}

	public static void main(String[] args) {
		ChicEtStyle cets = new ChicEtStyleJDBC();
		Principal principal = new Principal();
		MainWindow mere = new MainWindow(principal);
		new MarleneAccueil(mere, cets, true).setVisible(true);
	}

}
