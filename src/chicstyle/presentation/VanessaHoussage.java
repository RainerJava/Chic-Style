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
import chicstyle.controle.ControlJButtonEnregistrerHoussage;
import chicstyle.controle.ControlJButtonEnregistrerLancement;
import chicstyle.controle.ControlJButtonNext;
import chicstyle.controle.ControlJButtonOkHoussage;
import chicstyle.controle.ControlJButtonOkNomenclature;
import chicstyle.tableaux.TabDirectionProduitsFinis2;
import chicstyle.tableaux.TabVanessaHoussage2;

public class VanessaHoussage extends JFrame{
	private static final long serialVersionUID = 1L;
	static final int LABEL_SIZE = 18;
	private TabVanessaHoussage2 modele = new TabVanessaHoussage2(); //tableau
	private JTable tableau; //tableau
	private ImageChicEtStyle imageCetS;
	private ComboBoxes combobox;
	private JTextField texteType;
	private ChicEtStyle cets;
	private VanessaAccueil mere;
	private JTextField textedate;
	
	public JTextField getTextedate() {
		return textedate;
	}

	public void setTextedate(JTextField textedate) {
		this.textedate = textedate;
	}

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

	public JTable getTableau() {
		return tableau;
	}

	public void setTableau(JTable tableau) {
		this.tableau = tableau;
	}
	
	public ComboBoxes getCombobox() {
		return combobox;
	}

	public void setCombobox(ComboBoxes combobox) {
		this.combobox = combobox;
	}
	
	public JTextField getTexteType() {
		return texteType;
	}

	public void setTexteType(JTextField texteType) {
		this.texteType = texteType;
	}

	public VanessaHoussage(VanessaAccueil mere, ChicEtStyle cets, boolean creation) {
		super("Chic & Style");
		this.mere=mere;
		this.cets=cets;
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		Dimension tailleEcran = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize((int)(tailleEcran.getWidth()*3/4),(int)(tailleEcran.getHeight()*3/4));
		this.setLocation((int)(tailleEcran.getWidth()/8),(int)(tailleEcran.getHeight()/8));
		
		JPanel droite = new JPanel();
		droite.setLayout(new BorderLayout());
		
		Container conteneur = this.getContentPane();
		conteneur.setLayout(new BorderLayout());
		
		droite.add(creerHaut(), BorderLayout.NORTH);
		
		conteneur.add(creerWest(), BorderLayout.WEST);
		
		tableau = new JTable(modele); //tableau
        droite.add(new JScrollPane(tableau), BorderLayout.CENTER); //tableau
        
        JPanel boutons = new JPanel(); //tableau
        JButton validate = new JButton("Valider");
        ControlJButtonEnregistrerHoussage controlenregistrer = new ControlJButtonEnregistrerHoussage(cets,this,mere);
		validate.addActionListener(controlenregistrer);
        boutons.add(validate);
        
        JButton precedent = new JButton("Précédent");
        ControlJButtonNext prec = new ControlJButtonNext(this, mere); //BOUTON
		precedent.addActionListener(prec); //BOUTON
		boutons.add(precedent);
        
        droite.add(boutons, BorderLayout.SOUTH); //tableau
        
        conteneur.add(droite);
	}
	
	public JPanel creerHaut() {
		JPanel panneauHaut = new JPanel();
		panneauHaut.setLayout(new BoxLayout(panneauHaut, BoxLayout.Y_AXIS));
		
		panneauHaut.add(Box.createRigidArea(new Dimension(0,30)));
		JLabel fonction = new JLabel("HOUSSAGE");
		fonction.setFont(new Font("Helvetica",Font.ITALIC, 48));
		fonction.setAlignmentX(CENTER_ALIGNMENT);
		panneauHaut.add(fonction);
		panneauHaut.add(Box.createRigidArea(new Dimension(0,30)));
		
		combobox = new ComboBoxes(cets,1);
		panneauHaut.add(combobox.getBox());
		panneauHaut.add(Box.createRigidArea(new Dimension(0,20)));
		
		JButton ok = new JButton("OK");
		ok.setAlignmentX(CENTER_ALIGNMENT);
		ok.setAlignmentY(CENTER_ALIGNMENT);
		ControlJButtonOkHoussage controlOk = new ControlJButtonOkHoussage(cets, this, modele);
		ok.addActionListener(controlOk);
		panneauHaut.add(ok);
		panneauHaut.add(Box.createRigidArea(new Dimension(0,20)));
		
		JPanel nomType = new JPanel();
		JLabel type = new JLabel("Type: ");
		texteType = new JTextField("");
		nomType.setAlignmentX(CENTER_ALIGNMENT);
		nomType.setAlignmentY(CENTER_ALIGNMENT);
		texteType.setEditable(false);
		texteType.setPreferredSize(new Dimension(150,20));
		nomType.add(type);
		nomType.add(texteType);
		panneauHaut.add(nomType);
		panneauHaut.add(Box.createRigidArea(new Dimension(0,20)));
		
		return panneauHaut;	
	}
	
	public JPanel creerWest(){
		
		JPanel panneauImage = new JPanel();
		panneauImage.setBackground(Color.BLACK);
		panneauImage.setLayout(new BoxLayout(panneauImage, BoxLayout.Y_AXIS));
		panneauImage.add(Box.createRigidArea(new Dimension(0,120)));

		int pixel = this.getWidth();
		ImageIcon icon = new ImageIcon("images"+File.separator+"logo_C&S.jpg");
		//Image zoom = scaleImage(icon.getImage(), 0.5d);//facteur
		Image zoom = imageCetS.scaleImage(icon.getImage(), pixel/3);//taille en pixels
		Icon iconScaled = new ImageIcon(zoom);
		JLabel image = new JLabel(iconScaled);

		panneauImage.add(Box.createRigidArea(new Dimension(0,this.getHeight()/8)));
		panneauImage.add(image, BorderLayout.CENTER);
		return panneauImage;
	}

	public static void main(String[] args) {
		ChicEtStyle cets = new ChicEtStyleJDBC();
		Principal principal = new Principal();
		MainWindow grandmere = new MainWindow(principal);
		VanessaAccueil mere = new VanessaAccueil(grandmere, cets, true);
		new VanessaHoussage(mere, cets, true).setVisible(true);
	}
}
