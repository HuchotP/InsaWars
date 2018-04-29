package info.insawars;

import java.awt.*;
import javax.swing.*;
import java.awt.Color;


public class CharacterWindow extends JFrame {

	private int life;
    private int strength;
    private int intel;
    private int speed;
    private int luck;
    private String name;

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
		GridLayout attributesGrid = new GridLayout(5,5);
		attributesPanel.setLayout(attributesGrid);
		
		
		JLabel[] attribute = new JLabel[5];
		String[] attributeStr = {"Vie", "Force", "Intelligence", "Rapidité", "Chance"};
		JProgressBar[] bars = new JProgressBar[6];
		JButton[][] attributeButtons = new JButton[5][2];
		
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
			
			bars[i] = new JProgressBar();
			bars[i].setMinimum(0);
			bars[i].setMaximum(100);
			bars[i].setBackground(new Color(128,0,0));;
			attributesPanel.add(bars[i]);
			
			attributeButtons[i][0] = new JButton("-");
			attributeButtons[i][1] = new JButton("+");
			
			attributesPanel.add(attributeButtons[i][0]);
			attributesPanel.add(attributeButtons[i][1]);
			//window.add(Box.createVerticalGlue());
		}

		
		//window.add(grid);
		globalBox.add(attributesPanel);
		this.add(globalBox);
		//window.add(attributesPanel);
		this.setVisible(true);
	}

}
