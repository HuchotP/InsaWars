import java.awt.*;
import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


// Fenêtre de création du personnage

public class CharacterWindow extends JFrame {

	private int life; // Vie
	private int strength; // Force
	private int intel; // Intelligence
	private int speed; // Rapidité
	private int luck; // Chance

	private String name; // Nom du personnage
	private JTextField nameField; // Boîte de texte pour entrer le nom du personnage
	private int credits = 100; // Crédits disponibles pour la création
	private JLabel credsLabel; // JLabel affichant le nombre de crédits disponibles

	// Tous les tableaux suivants sont liés (pour un entier i donné, chaque élément i de chaque tableau correspond à la même "entité")

	private JLabel[] attribute; // JLabels affichant le nom des attributs
	private String[] attributeStr = {"Vie", "Force", "Intelligence", "Rapidit\u00e9", "Chance"}; // nom des attributs
	private int[] vals; // Valeur de chaque attribut
	private JLabel[] valLabels; // JLabels affichant les valeurs de chaque attribut
	private int[][] bornes = { {100,300} , {10,50} , {10,50} , {0,10} , {0,10} }; // Valeurs min et max pour chaque attribut
	private int[] increments = {2,1,1,1,1}; // Incrément par crédit consommé
	private JProgressBar[] bars; // Barres de progression permettant d'avoir une visualisation graphique
	private JButton[][] attributeButtons; // boutons +/-

	private JButton createButton; // bouton de création du personnage

	// GETTERS & SETTERS

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

	// Les deux suivantes sont en @Override car l'objet parent JFrame possède déjà des fonctions set/getName() qui nous sont inutiles

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	// CONSTRUCTEUR

	public CharacterWindow(String windowName) {

		// Mise en place de la fenêtre (titre, dimensions, redimensionnement et évènement "bouton croix appuyé")

		this.setTitle(windowName);
		this.setSize(470,320);
		this.setResizable(true);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

		// Création de la boîte qui contiendra toute l'interface (boite globale)

		Box globalBox = Box.createVerticalBox();

		// Création du panneau des attributs

		JPanel attributesPanel = new JPanel();
		GridLayout attributesGrid = new GridLayout(5,6); // Le GridLayout est le plus adapté ici
		attributesPanel.setLayout(attributesGrid);

		// Instanciation des tableaux

		attribute = new JLabel[5];
		valLabels = new JLabel[5];
		vals = new int[5];
		bars = new JProgressBar[5];
		attributeButtons = new JButton[5][2];

		// Ajout du titre du jeu

		JLabel title = new JLabel("INSAWARS");
		title.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		globalBox.add(title);

		globalBox.add(Box.createVerticalGlue()); // la glue sert à espacer deux éléments

		// Ajout du label indiquant la boîte d'entrée du nom du guerrier

		JLabel name = new JLabel("Nom du guerrier");
		name.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		globalBox.add(name);

		globalBox.add(Box.createVerticalGlue());

		// Création du champ d'entrée du nom (1 ligne)

		nameField = new JTextField(1);
		nameField.setAlignmentX(JComponent.CENTER_ALIGNMENT); // centrage du texte interne
		nameField.setHorizontalAlignment(JLabel.CENTER);
		globalBox.add(nameField);

		globalBox.add(Box.createVerticalGlue());

		// Ajout du label indiquant le nombre de crédits restants

		credsLabel = new JLabel("Cr\u00e9dits : " + Integer.toString(credits));
		credsLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		globalBox.add(credsLabel);

		// Mise en place du panneau des attributs

		for(int i = 0; i < attribute.length; i++) {

			attribute[i] = new JLabel(attributeStr[i]);
			attribute[i].setAlignmentX(JComponent.CENTER_ALIGNMENT);
			attributesPanel.add(attribute[i]);

			vals[i] = bornes[i][0];
			valLabels[i] = new JLabel(Integer.toString(vals[i]));
			valLabels[i].setAlignmentX(JComponent.CENTER_ALIGNMENT);
			attributesPanel.add(valLabels[i]);


			bars[i] = new JProgressBar();
			bars[i].setMinimum(bornes[i][0]);
			bars[i].setMaximum(bornes[i][1]);
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

		createButton = new JButton("Valider");
		createButton.setEnabled(this.credits == 0);
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
		int increment;
		int changement;
		if(signe) {
			changement = 1;
			increment = this.increments[i];
		} else {
			changement = -1;
			increment = -this.increments[i];
		}

		if(updateCredits(changement, bars[i])) {
			bars[i].setValue(bars[i].getValue() + increment);

		}


	}

	private void updateLabel(int i) {

		vals[i] = bars[i].getValue();
		valLabels[i].setText(Integer.toString(vals[i]));

	}

	public boolean updateCredits(int i, JProgressBar bar) {

		if((this.credits > 0) && ((bar.getValue() > bar.getMinimum() && i == -1) || (bar.getValue() < bar.getMaximum() && i == 1)) || (this.credits == 0 && i == -1)) {
			this.credits -= i;
			this.credsLabel.setText("Cr\u00e9dits : " + this.credits);

			if(this.credits == 0) {
				this.createButton.setEnabled(true);
			} else {
				this.createButton.setEnabled(false);
			}

			return true;

		}

		return false;

	}

	private void close() {
		this.life = this.bars[0].getValue();
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
