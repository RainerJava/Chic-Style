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

import javax.swing.AbstractAction;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import chicstyle.abstraction.ChicEtStyle;
import chicstyle.abstraction.ChicEtStyleJDBC;
import chicstyle.abstraction.Piece;
import chicstyle.abstraction.Principal;
import chicstyle.controle.ControlJButtonEnregistrerNomenclature;
import chicstyle.controle.ControlJButtonNext;
import chicstyle.controle.ControlJButtonOkNomenclature;
import chicstyle.tableaux.TabNomenclature2;
import chicstyle.tableaux.TabNomenclature1;
import chicstyle.tableaux.TabPret1;

public class CatherineNomenclature extends JFrame{

	private static final long serialVersionUID = 1L;
	static final int LABEL_SIZE = 18;
	private TabNomenclature2 modele = new TabNomenclature2(); //tableau
	private JTable tableau; //tableau
	private ComboBoxes combobox;
	private CatherineAccueil mere;
	private ChicEtStyle cets;
	private ImageChicEtStyle imageCetS;

	public ChicEtStyle getCets() {
		return cets;
	}

	public void setCets(ChicEtStyle cets) {
		this.cets = cets;
	}

	public CatherineAccueil getMere() {
		return mere;
	}

	public void setMere(CatherineAccueil mere) {
		this.mere = mere;
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

	public CatherineNomenclature(CatherineAccueil mere, ChicEtStyle cets, boolean creation) {
		super();
		this.mere=mere;
		this.cets=cets;
		setTitle("Chic & Style");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension tailleEcran = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize((int)(tailleEcran.getWidth()*3/4),(int)(tailleEcran.getHeight()*3/4));
		this.setLocation((int)(tailleEcran.getWidth()/8),(int)(tailleEcran.getHeight()/8));

		JPanel conteneur = new JPanel();
		conteneur.setLayout(new BorderLayout());
		conteneur.add(creerHaut(), BorderLayout.NORTH);

		this.tableau = new JTable(modele); //tableau
		conteneur.add(new JScrollPane(tableau), BorderLayout.CENTER); //tableau
		// Pour empêcher de pouvoir déplacer les colonnes
		tableau.getTableHeader().setReorderingAllowed(false);
		// Pour empêcher de pouvoir modifier la taille des colonnes
		tableau.getTableHeader().setResizingAllowed(false);

		JPanel boutons = new JPanel(); //tableau
		JButton prec = new JButton("Précédent");
		ControlJButtonNext precedent = new ControlJButtonNext(this,new CatherineAccueil(this.getMere().getMere(),this.cets,true)); //BOUTON
		prec.addActionListener(precedent); //BOUTON
		boutons.add(prec);
		JButton validate = new JButton("Enregistrer le nomenclature");
		ControlJButtonEnregistrerNomenclature controlenregistrer = new ControlJButtonEnregistrerNomenclature(this.cets,this,new CatherineAccueil(this.getMere().getMere(),this.cets,true));
		validate.addActionListener(controlenregistrer);
		boutons.add(new JButton(new AddAction())); //tableau
		boutons.add(new JButton(new RemoveAction())); //tableau
		boutons.add(validate);		
		conteneur.add(boutons, BorderLayout.SOUTH); //tableau

		this.getContentPane().add(creerWest(), BorderLayout.WEST);
		this.getContentPane().add(conteneur, BorderLayout.CENTER);
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

	public JPanel creerHaut() {
		JPanel panneauHaut = new JPanel();			
		panneauHaut.setLayout(new BoxLayout(panneauHaut, BoxLayout.Y_AXIS));

		panneauHaut.add(Box.createRigidArea(new Dimension(0,50)));
		JLabel fonction = new JLabel("NOMENCLATURE");
		fonction.setFont(new Font("Helvetica",Font.ITALIC, 48));
		fonction.setAlignmentX(CENTER_ALIGNMENT);
		panneauHaut.add(fonction);
		panneauHaut.add(Box.createRigidArea(new Dimension(0,30)));

		combobox = new ComboBoxes(cets,1);
		panneauHaut.add(combobox.getBox());
		panneauHaut.add(Box.createRigidArea(new Dimension(0,20)));

		JButton ok = new JButton("OK");
		ControlJButtonOkNomenclature controlOk = new ControlJButtonOkNomenclature(cets, this, modele);
		ok.addActionListener(controlOk);
		panneauHaut.add(ok);
		panneauHaut.add(Box.createRigidArea(new Dimension(0,20)));

		return panneauHaut;	
	}

	//POUR LES TABLEAUX
	public static void main(String[] args) {
		ChicEtStyle cets = new ChicEtStyleJDBC();
		Principal principal = new Principal();
		MainWindow grandmere = new MainWindow(principal);
		CatherineAccueil mere = new CatherineAccueil(grandmere,cets, true);
		new CatherineNomenclature(mere, cets, true).setVisible(true);
	}

	private class AddAction extends AbstractAction {
		private AddAction() {
			super("Ajouter");
		}

		public void actionPerformed(ActionEvent e) {
			modele.addAmi(new TabNomenclature1("", "", 0, false,false));
		}
	}

	private class RemoveAction extends AbstractAction {
		private RemoveAction() {
			super("Supprimmer");
		}

		public void actionPerformed(ActionEvent e) {
			int[] selection = tableau.getSelectedRows();

			for(int i = selection.length - 1; i >= 0; i--){
				modele.removeAmi(selection[i]);
			}
		}
	}
}
