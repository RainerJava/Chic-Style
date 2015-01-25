package chicstyle.presentation;

import java.awt.Dimension;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import chicstyle.abstraction.ChicEtStyle;
import chicstyle.controle.ControlJComboBoxRefClientVanessaRecep;
import chicstyle.controle.ControlJComboBoxRefVanessaRecep;

public class ComboBoxesReceptionFourniture {

	private JPanel box;
	private JComboBox<String> comboClients;
	private JComboBox<String> comboRefs;
	private JTextField texteType;
	private JTextField texteStock;
	
	public ComboBoxesReceptionFourniture(ChicEtStyle cets){
		super();

		box = new JPanel();
		box.setLayout(new BoxLayout(box, BoxLayout.Y_AXIS));

		JPanel cl = new JPanel();
		JLabel client = new JLabel("Client: ");
		Vector<String> clients = cets.findAllClients();
		comboClients = new JComboBox<String>(clients);
		comboClients.setPreferredSize(new Dimension(150,20));
		comboClients.setEditable(false);

		JPanel re = new JPanel();
		JLabel reference = new JLabel("Références: ");
		String[] refs = {"-"};
		comboRefs=new JComboBox<String>(refs);
		comboRefs.setPreferredSize(new Dimension(150,20));
		comboRefs.setEditable(false);
		
		JPanel panelType = new JPanel();
		JLabel nomType = new JLabel("Type: ");
		texteType = new JTextField("");
		texteType.setPreferredSize(new Dimension(150,20));
		texteType.setEditable(false);
		
		JPanel panelStock = new JPanel();
		JLabel nomStock = new JLabel("Stock");
		texteStock = new JTextField("");
		texteStock.setPreferredSize(new Dimension(150,20));
		texteStock.setEditable(false);

		//Controles

		ControlJComboBoxRefClientVanessaRecep controlComboClients = new ControlJComboBoxRefClientVanessaRecep(cets, comboRefs);
		comboClients.addActionListener(controlComboClients);
		ControlJComboBoxRefVanessaRecep controlComboRefs = new ControlJComboBoxRefVanessaRecep(cets, texteType, texteStock);
		comboRefs.addActionListener(controlComboRefs);

		cl.add(client);
		cl.add(comboClients);
		re.add(reference);
		re.add(comboRefs);
		panelType.add(nomType);
		panelType.add(texteType);
		panelStock.add(nomStock);
		panelStock.add(texteStock);

		box.add(cl);
		box.add(re);
		box.add(panelType);
		box.add(panelStock);
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

	public JComboBox<String> getComboRefs() {
		return comboRefs;
	}
	public String getRef(){
		return (String)(this.comboRefs.getSelectedItem());
	}

	public void setComboRefs(JComboBox<String> comboRefs) {
		this.comboRefs = comboRefs;
	}
}
