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
	private JTextField nameField;
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

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	public CharacterWindow(String windowName) {

		this.setSize(300,400);
		this.setTitle(windowName);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

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

		nameField = new JTextField(1);
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
		createButton.setAlignmentX(JComponent.CENTER_ALIGNMENT);

		createButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				close();
			}
		});
		globalBox.add(Box.createVerticalStrut(50));
		globalBox.add(createButton);
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

	private void close() {

		this.strength = this.bars[1].getValue();
		this.intel = this.bars[2].getValue();
		this.speed = this.bars[3].getValue();
		this.luck = this.bars[4].getValue();
		this.name = this.nameField.getText();
		this.setVisible(false);
	}

	public Character getCharacter() {
		return new Character(this.getLife(), this.getStrength(), this.getIntel(), this.getSpeed(), this.getLuck(), this.getName());
	}
}
