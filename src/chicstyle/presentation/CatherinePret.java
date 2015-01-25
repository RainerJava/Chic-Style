package chicstyle.presentation;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Toolkit;

import javax.swing.Box;
import javax.swing.BoxLayout;
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
import chicstyle.controle.ControlJButtonNext;
import chicstyle.controle.ControlJButtonNext;
import chicstyle.controle.ControlJButtonNext;
import chicstyle.controle.ControlJButtonPrec;
import chicstyle.tableaux.TabPret1;
import chicstyle.tableaux.TabPret2;

public class CatherinePret extends JFrame {

	private static final long serialVersionUID = 1L;
	static final int LABEL_SIZE = 18;
	private TabPret2 modele = new TabPret2(); //tableau
	private JTable tableau; //tableau
	private CatherineAccueil mere;
	private ChicEtStyle cets;
	
	public CatherineAccueil getMere() {
		return mere;
	}

	public void setMere(CatherineAccueil mere) {
		this.mere = mere;
	}

	public ChicEtStyle getCets() {
		return cets;
	}

	public void setCets(ChicEtStyle cets) {
		this.cets = cets;
	}

	public CatherinePret(CatherineAccueil mere, ChicEtStyle cets, boolean creation) {
        super();
        this.cets=cets;
        this.mere=mere;
        setTitle("Chic & Style");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension tailleEcran = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize((int)(tailleEcran.getWidth()*3/4),(int)(tailleEcran.getHeight()*3/4));
		this.setLocation((int)(tailleEcran.getWidth()/8),(int)(tailleEcran.getHeight()/8));
        
        Container conteneur = this.getContentPane();
		conteneur.setLayout(new BorderLayout());
		conteneur.add(creerHaut(), BorderLayout.NORTH);
		
		tableau = new JTable(modele); //tableau
		for (int i=0; i<cets.findAllHousser().size();i++){
			String id_piece = cets.findAllHousser().get(i).getId_piece();
			Piece piece = cets.findPiece(id_piece);
			tableau.setValueAt(piece.getClient(),i,0);
			tableau.setValueAt(piece.getModele(),i,1);
			tableau.setValueAt(piece.getTissu(),i,2);
			tableau.setValueAt(piece.getCouleur(),i,3);
			tableau.setValueAt(""+cets.findAllHousser().get(i).getTaille(), i, 4);
			tableau.setValueAt(""+cets.findAllHousser().get(i).getQte(), i, 5);
			if(i!=(cets.findAllHousser().size()-1)){
				modele.addAmi(new TabPret1("", "", "", "", "", ""));
			}
		}
		
        getContentPane().add(new JScrollPane(tableau), BorderLayout.CENTER); //tableau
        JPanel boutons = new JPanel(); //tableau
        
        JButton expe = new JButton("Expédition");
        ControlJButtonPrec control = new ControlJButtonPrec(this,new CatherineExpedition(this.mere, this.cets,true)); //BOUTON
		expe.addActionListener(control); //BOUTON
		
		JButton prec = new JButton("Précédent");
		ControlJButtonPrec precedent = new ControlJButtonPrec(this, new CatherineAccueil(this.getMere().getMere(),this.cets,true)); //BOUTON
		prec.addActionListener(precedent); //BOUTON
		
		boutons.add(prec);
		boutons.add(expe);
        getContentPane().add(boutons, BorderLayout.SOUTH); //tableau
    }
    
	public JPanel creerHaut() {
		JPanel panneauHaut = new JPanel();
		panneauHaut.setLayout(new BoxLayout(panneauHaut, BoxLayout.Y_AXIS));
		panneauHaut.add(Box.createRigidArea(new Dimension(0,60)));

		JLabel fonction = new JLabel("PRET A PARTIR");
		fonction.setFont(new Font("Helvetica",Font.ITALIC, 48));
		fonction.setAlignmentX(CENTER_ALIGNMENT);
		panneauHaut.add(fonction);
		panneauHaut.add(Box.createRigidArea(new Dimension(0,60)));
		return panneauHaut;	
	}
 
//POUR LES TABLEAUX
    public static void main(String[] args) {
    	ChicEtStyle cets = new ChicEtStyleJDBC();
    	Principal principal = new Principal();
		MainWindow grandmere = new MainWindow(principal);
		CatherineAccueil mere = new CatherineAccueil(grandmere, cets, true);
        new CatherinePret(mere, cets, true).setVisible(true);
    }
}
