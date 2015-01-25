package chicstyle.presentation;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import chicstyle.abstraction.ChicEtStyle;
import chicstyle.abstraction.ChicEtStyleJDBC;
import chicstyle.abstraction.Principal;
import chicstyle.controle.ControlJButtonNext;
import chicstyle.controle.ControlJButtonOkDirectionProduitsFinis;
import chicstyle.controle.ControlJButtonOkNomenclature;
import chicstyle.tableaux.TabDirectionProduitsFinis1;
import chicstyle.tableaux.TabDirectionProduitsFinis2;


public class DirectionProduitsFinis extends JFrame{
	
	private static final long serialVersionUID = 1L;
	static final int LABEL_SIZE = 18;
	private TabDirectionProduitsFinis2 modele=new TabDirectionProduitsFinis2();; //tableau
	private JTable tableau; //tableau
	private DirectionAccueil mere;
	private ChicEtStyle cets;
	
	public DirectionAccueil getMere() {
		return mere;
	}

	public void setMere(DirectionAccueil mere) {
		this.mere = mere;
	}

	public ChicEtStyle getCets() {
		return cets;
	}

	public void setCets(ChicEtStyle cets) {
		this.cets = cets;
	}

	private ComboBoxes combobox;
	
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
	
	public DirectionProduitsFinis(DirectionAccueil mere, ChicEtStyle cets, boolean creation) {
		//INITIALISATIONS
		super("Chic & Style");
		this.mere=mere;
		this.cets=cets;
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		Dimension tailleEcran = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize((int)(tailleEcran.getWidth()*3/4),(int)(tailleEcran.getHeight()*3/4));
		this.setLocation((int)(tailleEcran.getWidth()/8),(int)(tailleEcran.getHeight()/8));
//		this.modele=new TabDirectionProduitsFinis2();
		this.tableau = new JTable(modele); //tableau
		//HAUT
		Container conteneur = this.getContentPane();
		conteneur.setLayout(new BorderLayout());
		conteneur.add(creerHaut(), BorderLayout.NORTH);
        //TABLEAU
		getContentPane().add(new JScrollPane(tableau), BorderLayout.CENTER); //tableau
		//BAS
        JPanel boutons = new JPanel(); //tableau
        JButton prec = new JButton("Accueil");
        ControlJButtonNext precedent = new ControlJButtonNext(this, new DirectionAccueil(this.getMere().getMere(),this.cets,true)); //BOUTON
		prec.addActionListener(precedent); //BOUTON
        boutons.add(prec);
        getContentPane().add(boutons, BorderLayout.SOUTH); //tableau
	} 

	public JPanel creerHaut() {
		JPanel panneauHaut = new JPanel();
		panneauHaut.setLayout(new BoxLayout(panneauHaut, BoxLayout.Y_AXIS));
		
		JLabel fonction = new JLabel("Produits Finis");
		fonction.setFont(new Font("Helvetica",Font.ITALIC, 48));
		fonction.setAlignmentX(CENTER_ALIGNMENT);
		
		panneauHaut.add(fonction);
		
		this.combobox = new ComboBoxes(cets, 1);
		panneauHaut.add(combobox.getBox());
		
		JButton ok = new JButton("OK");
		ControlJButtonOkDirectionProduitsFinis controlOk = new ControlJButtonOkDirectionProduitsFinis(cets, this, modele);
		ok.addActionListener(controlOk);
		panneauHaut.add(ok);
		
		return panneauHaut;	
	}
	
	//POUR LES TABLEAUX
    public static void main(String[] args) {
    	ChicEtStyle cets = new ChicEtStyleJDBC();
    	Principal principal = new Principal();
    	MainWindow grandmere = new MainWindow(principal);
		DirectionAccueil mere = new DirectionAccueil(grandmere, cets, true);
        new DirectionProduitsFinis(mere, cets, true).setVisible(true);
    }
}
