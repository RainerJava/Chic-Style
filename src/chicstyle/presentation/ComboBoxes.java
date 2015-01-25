package chicstyle.presentation;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import chicstyle.abstraction.ChicEtStyle;
import chicstyle.controle.ControlJComboBoxClients;
import chicstyle.controle.ControlJComboBoxModeles;
import chicstyle.controle.ControlJComboBoxTissus;

public class ComboBoxes extends JPanel {

	private static final long serialVersionUID = 1L;
	static final int LABEL_SIZE = 18;
	private JPanel box;
	private int layout;
	private JComboBox<String> comboClients;
	private JComboBox<String> comboModeles;
	private JComboBox<String> comboTissus;
	private JComboBox<String> comboCouleurs;
	private JPanel ti;
	private JPanel co;
	private ChicEtStyle cets;

	public ChicEtStyle getCets() {
		return cets;
	}

	public void setCets(ChicEtStyle cets) {
		this.cets = cets;
	}

	public JPanel getTi() {
		return ti;
	}

	public void setTi(JPanel ti) {
		this.ti = ti;
	}

	public JPanel getCo() {
		return co;
	}

	public void setCo(JPanel co) {
		this.co = co;
	}

	public ComboBoxes(ChicEtStyle cets, int layout){
		super();
		this.cets=cets;
		this.layout = layout;

		box = new JPanel();

		if(layout == 0){
			box.setLayout(new BoxLayout(box, BoxLayout.Y_AXIS));
		}
		else{
			box.setLayout(new BoxLayout(box, BoxLayout.X_AXIS));
		}

		JPanel cl = new JPanel();
		JLabel client = new JLabel("  Client: ");
		Vector<String> clients = this.cets.findAllClients();
		clients.add(0, "-");
		comboClients = new JComboBox<String>(clients);
		comboClients.setAlignmentX(CENTER_ALIGNMENT);
		comboClients.setAlignmentY(CENTER_ALIGNMENT);
		comboClients.setPreferredSize(new Dimension(150,25));
		comboClients.setEditable(false);

		JPanel mo = new JPanel();
		JLabel modele = new JLabel(" Modèle: ");
		String[] modeles = {"-"};
		comboModeles=new JComboBox<String>(modeles);
		comboModeles.setAlignmentX(CENTER_ALIGNMENT);
		comboModeles.setAlignmentY(CENTER_ALIGNMENT);
		comboModeles.setPreferredSize(new Dimension(150,25));
		comboModeles.setEditable(false);

		ti = new JPanel();
		JLabel tissu = new JLabel("   Tissu: ");
		String[] tissus = {"-"};
		comboTissus=new JComboBox<String>(tissus);
		comboTissus.setAlignmentX(CENTER_ALIGNMENT);
		comboTissus.setAlignmentY(CENTER_ALIGNMENT);
		comboTissus.setPreferredSize(new Dimension(150,25));
		comboTissus.setEditable(false);

		co = new JPanel();
		JLabel couleur = new JLabel("Couleur: ");
		String[] couleurs = {"-"};
		comboCouleurs=new JComboBox<String>(couleurs);
		comboCouleurs.setAlignmentX(CENTER_ALIGNMENT);
		comboCouleurs.setAlignmentY(CENTER_ALIGNMENT);
		comboCouleurs.setPreferredSize(new Dimension(150,25));
		comboCouleurs.setEditable(false);

		//Controles

		ControlJComboBoxClients controlComboClients = new ControlJComboBoxClients(this.cets, comboModeles);
		comboClients.addActionListener(controlComboClients);
		ControlJComboBoxModeles controlComboModeles = new ControlJComboBoxModeles(this.cets, comboTissus);
		comboModeles.addActionListener(controlComboModeles);
		ControlJComboBoxTissus controlComboTissus = new ControlJComboBoxTissus(this.cets, comboCouleurs);
		comboTissus.addActionListener(controlComboTissus);

		cl.add(client);
		cl.add(comboClients);
		mo.add(modele);
		mo.add(comboModeles);
		ti.add(tissu);
		ti.add(comboTissus);
		co.add(couleur);
		co.add(comboCouleurs);

		box.add(cl);
		box.add(mo);
		box.add(ti);
		box.add(co);
	}

	public JPanel getBox() {
		return box;
	}

	public void setBox(JPanel box) {
		this.box = box;
	}

	public JComboBox<String> getComboClients() {
		return comboClients;
	}
	public String getClient(){
		return (String)(this.comboClients.getSelectedItem());
	}

	public void setComboClients(JComboBox<String> comboClients) {
		this.comboClients = comboClients;
	}

	public JComboBox<String> getComboModeles() {
		return comboModeles;
	}
	public String getModele(){
		return (String)(this.comboModeles.getSelectedItem());
	}

	public void setComboModeles(JComboBox<String> comboModeles) {
		this.comboModeles = comboModeles;
	}

	public JComboBox<String> getComboTissus() {
		return comboTissus;
	}
	public String getTissu(){
		return (String)(this.comboTissus.getSelectedItem());
	}

	public void setComboTissus(JComboBox<String> comboTissus) {
		this.comboTissus = comboTissus;
	}

	public JComboBox<String> getComboCouleurs() {
		return comboCouleurs;
	}
	public String getCouleur(){
		return (String)(this.comboCouleurs.getSelectedItem());
	}

	public void setComboCouleurs(JComboBox<String> comboCouleurs) {
		this.comboCouleurs = comboCouleurs;
	}
}
