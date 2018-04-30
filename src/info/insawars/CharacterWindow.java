package info.insawars;

import java.awt.*;
import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class CharacterWindow extends JFrame {

	private int life;
    private int strength;
    private int intel;
    private int speed;
    private int luck;
    
    private String name;
    private JLabel[] attribute;
    private String[] attributeStr = {"Vie", "Force", "Intelligence", "Rapidité", "Chance"};
    private int[] vals;
    private JLabel[] valLabels;
    private JProgressBar[] bars;
    private JButton[][] attributeButtons;
    
	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public int getIntel() {
		return intel;
	}

	public void setIntel(int intel) {
		this.intel = intel;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getLuck() {
		return luck;
	}

	public void setLuck(int luck) {
		this.luck = luck;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CharacterWindow() {

		this.setSize(300,400);
		
		Box globalBox = Box.createVerticalBox();
		
		JPanel attributesPanel = new JPanel();
		GridLayout attributesGrid = new GridLayout(5,6);
		attributesPanel.setLayout(attributesGrid);
		
		
		attribute = new JLabel[5];
		
		valLabels = new JLabel[5];
		vals = new int[5];
		
		bars = new JProgressBar[5];
		
		attributeButtons = new JButton[5][2];
		
		JLabel title = new JLabel("INSAWARS");
		title.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		globalBox.add(title);
		
		globalBox.add(Box.createVerticalGlue());
		
		JLabel name = new JLabel("Nom du guerrier");
		name.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		globalBox.add(name);
		
		globalBox.add(Box.createVerticalGlue());
		
		JTextField nameField = new JTextField(1);
		nameField.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		nameField.setHorizontalAlignment(JLabel.CENTER);
		globalBox.add(nameField);
		globalBox.add(Box.createVerticalGlue());
		
		
		for(int i = 0; i < attribute.length; i++) {
			
			attribute[i] = new JLabel(attributeStr[i]);
			attribute[i].setAlignmentX(JComponent.CENTER_ALIGNMENT);
			attributesPanel.add(attribute[i]);
			
			valLabels[i] = new JLabel(Integer.toString(vals[i]));
			valLabels[i].setAlignmentX(JComponent.CENTER_ALIGNMENT);
			attributesPanel.add(valLabels[i]);
			
			
			bars[i] = new JProgressBar();
			bars[i].setMinimum(0);
			bars[i].setMaximum(10);
			bars[i].setBackground(new Color(153, 13, 13));
			bars[i].setForeground(new Color(255,255,255));
			attributesPanel.add(bars[i]);
			
			attributeButtons[i][0] = new JButton("-");
			attributeButtons[i][0].setBackground(new Color(255,255,255));
			attributeButtons[i][0].putClientProperty("index", i);
			attributeButtons[i][0].addActionListener(new ActionListener() {

			    public void actionPerformed(ActionEvent e) {
			    	int i = (Integer)((JButton)e.getSource()).getClientProperty("index");
			    	modifyBar(i,false);
			    	updateLabel(i);
			    	
			    }
			});
			
			attributeButtons[i][1] = new JButton("+");
			attributeButtons[i][1].setBackground(new Color(255,255,255));
			attributeButtons[i][1].putClientProperty("index", i);
			System.out.println();
			attributeButtons[i][1].addActionListener(new ActionListener() {

			    public void actionPerformed(ActionEvent e) {
			    	int i = (Integer)((JButton)e.getSource()).getClientProperty("index");
			    	modifyBar(i, true);
			    	updateLabel(i);
			    }
			});
			
			attributesPanel.add(attributeButtons[i][0]);
			attributesPanel.add(attributeButtons[i][1]);
		}

		globalBox.add(attributesPanel);
		
		JButton createButton = new JButton("Valider");
		createButton.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {
		    	
		    	for(JProgressBar bar : bars) {
		    		
		    	}
		    	
		    }
		});
		this.add(globalBox);
		this.setVisible(true);
	}
	
	private void modifyBar(int i, boolean signe) {
		int increment = 0;
		if(signe) {
			increment = 1;
		} else {
			increment = -1;
		}
		
		bars[i].setValue(bars[i].getValue() + increment);
		
	}
	
	private void updateLabel(int i) {
		
		vals[i] = bars[i].getValue();
		valLabels[i].setText(Integer.toString(vals[i]));
		
	}
}
