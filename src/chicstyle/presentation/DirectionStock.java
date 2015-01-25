package chicstyle.presentation;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Box;
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
import chicstyle.controle.ControlJButtonEnregistrerReceptionFourniture;
import chicstyle.controle.ControlJButtonNext;
import chicstyle.controle.ControlJButtonOkDirection;
import chicstyle.controle.ControlJButtonOkHoussage;
import chicstyle.controle.ControlJButtonOkProd;
import chicstyle.tableaux.TabDirectionStock1;
import chicstyle.tableaux.TabDirectionStock2;


public class DirectionStock extends JFrame{

	private static final long serialVersionUID = 1L;
	static final int LABEL_SIZE = 18;
	private TabDirectionStock2 modele = new TabDirectionStock2(); //tableau
	private JTable tableau;
	private ComboBoxes combobox;
	private JTextField date;
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

	public JTextField getDate() {
		return date;
	}

	public void setDate(JTextField date) {
		this.date = date;
	}

	public ComboBoxes getCombobox() {
		return combobox;
	}

	public void setCombobox(ComboBoxes combobox) {
		this.combobox = combobox;
	}

	public JTable getTableau() {
		return tableau;
	}

	public void setTableau(JTable tableau) {
		this.tableau = tableau;
	}

	public DirectionStock(DirectionAccueil mere, ChicEtStyle cets, boolean creation) {
		super("Chic & Style");
		this.mere=mere;
		this.cets=cets;
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		Dimension tailleEcran = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize((int)(tailleEcran.getWidth()*3/4),(int)(tailleEcran.getHeight()*3/4));
		this.setLocation((int)(tailleEcran.getWidth()/8),(int)(tailleEcran.getHeight()/8));
		Container conteneur = this.getContentPane();
		conteneur.setLayout(new BorderLayout());
		conteneur.add(creerHaut(), BorderLayout.NORTH);
		
		tableau = new JTable(modele); //tableau
        getContentPane().add(new JScrollPane(tableau), BorderLayout.CENTER); //tableau
        
        JPanel sudHaut = new JPanel();
        sudHaut.setLayout(new BoxLayout(sudHaut, BoxLayout.Y_AXIS));
		JPanel dateProd = new JPanel();
		JLabel dateP = new JLabel("Date de lancement de la production: ");
		date = new JTextField(15);
		dateProd.add(dateP);
		dateProd.add(date);
		sudHaut.add(dateProd);
		JButton valider = new JButton("Valider");
		ControlJButtonOkProd controlValiderNouvelleFourniture = new ControlJButtonOkProd(cets,this, mere); //BOUTON
		valider.addActionListener(controlValiderNouvelleFourniture); //BOUTON
		valider.setAlignmentX(CENTER_ALIGNMENT);
        
        JPanel boutons = new JPanel(); //tableau
        JButton prec = new JButton("Accueil");
        ControlJButtonNext precedent = new ControlJButtonNext(this, mere); //BOUTON
		prec.addActionListener(precedent); //BOUTON
		prec.setAlignmentX(CENTER_ALIGNMENT);
        boutons.add(prec);
		boutons.add(Box.createRigidArea(new Dimension(10,0)));
        boutons.add(valider);
        
        JPanel panneauBas = new JPanel();
        panneauBas.setLayout(new BoxLayout(panneauBas, BoxLayout.Y_AXIS));
        panneauBas.add(sudHaut);
        panneauBas.add(boutons);

        conteneur.add(panneauBas, BorderLayout.SOUTH);    
	}

	public JPanel creerHaut() {
		JPanel panneauHaut = new JPanel();
		panneauHaut.setLayout(new BoxLayout(panneauHaut, BoxLayout.Y_AXIS));
	
		JLabel fonction = new JLabel("Stocks");
		fonction.setFont(new Font("Helvetica",Font.ITALIC, 48));
		fonction.setAlignmentX(CENTER_ALIGNMENT);
		panneauHaut.add(fonction);
		
		panneauHaut.add(Box.createRigidArea(new Dimension(0,20)));
		
		combobox = new ComboBoxes(cets,1);
		panneauHaut.add(combobox.getBox());
		
		panneauHaut.add(Box.createRigidArea(new Dimension(0,20)));
		
		JButton ok = new JButton("OK");
		ControlJButtonOkDirection controlOk = new ControlJButtonOkDirection(cets, this, modele);
		ok.addActionListener(controlOk);
		ok.setAlignmentX(CENTER_ALIGNMENT);
		panneauHaut.add(ok);
		panneauHaut.add(Box.createRigidArea(new Dimension(0,20)));

		
		return panneauHaut;	
	}
	
	//POUR LES TABLEAUX
    public static void main(String[] args) {
    	ChicEtStyle cets = new ChicEtStyleJDBC();
    	Principal modele = new Principal();
    	MainWindow grandmere = new MainWindow(modele);
		DirectionAccueil mere = new DirectionAccueil(grandmere, cets, true);
        new DirectionStock(mere, cets, true).setVisible(true);
    }
}
