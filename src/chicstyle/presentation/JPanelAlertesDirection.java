package chicstyle.presentation;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import chicstyle.abstraction.ChicEtStyle;

public class JPanelAlertesDirection {

	private JTextField TextSource;
	private JTextField TextMessage;
	private JPanel panel;
	private JCheckBox supprimer;
	
	public JPanelAlertesDirection(ChicEtStyle cets, String source, String message){
		super();
		this.panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		this.TextSource = new JTextField(source);
		TextSource.setEditable(false);
		this.TextMessage = new JTextField(message);
		TextMessage.setEditable(false);
		TextMessage.setMaximumSize(new Dimension(600,70));
		
		JPanel panSource = new JPanel();
		JLabel s = new JLabel("Source :");
		supprimer = new JCheckBox();
		panSource.add(s);
		panSource.add(TextSource);
		panSource.add(supprimer);
		panel.add(panSource);
		
		panel.add(TextMessage);
		panel.setMaximumSize(new Dimension(600,60));
		panel.setBorder(BorderFactory.createLineBorder(Color.black));
	}

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public JCheckBox getSupprimer() {
		return supprimer;
	}

	public void setSupprimer(JCheckBox supprimer) {
		this.supprimer = supprimer;
	}

	public JTextField getTextSource() {
		return TextSource;
	}

	public void setTextSource(JTextField textSource) {
		TextSource = textSource;
	}

	public JTextField getTextMessage() {
		return TextMessage;
	}

	public void setTextMessage(JTextField textMessage) {
		TextMessage = textMessage;
	}
	
	
	
	
	
	
}
