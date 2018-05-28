import java.awt.*;
import javax.swing.*;
import java.awt.Color;
import java.awt.event.*;

// Interface active contenant les boutons d'action
public class ActiveInterface extends JPanel {

  // Elements d'interface

  private JLabel nameLabel; // JLabel affichant le nom du joueur
  private JProgressBar lifeBar; // Barre de vie
  private JLabel lifeValue; // JLabel affichant les points de vie du joueur
  private int credits; // Crédits restants
  private JLabel creditsLabel; // JLabel affichant les crédits restants
  private JButton[] attackButtons; // Boutons d'attaque
  private JButton nextTurn; // Bouton pour passer au tour suivant

  private Character ch; // Personnage associé à l'interface
  private GameWindow gw; // GameWindow associée à l'interface
  public GameManager manager = new GameManager().getManager(); // on récupère le GameManager pour accéder à certaines valeurs

  public ActiveInterface(Character ch, GameWindow gw) {

    this.gw = gw;
    this.ch = ch;

    // Affichage du nom

    nameLabel = new JLabel(ch.getName());
    nameLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
    this.add(nameLabel);

    // Panneau d'affichage des points de vie (barre + valeur numérique)

    JPanel lifePanel = new JPanel();

    lifeBar = new JProgressBar();
    lifeBar.setValue((int) Math.ceil(ch.getLife()));
    lifeBar.setMaximum((int) Math.ceil(ch.getMaxLife()));
    lifeBar.setForeground(new Color(0,255,0));
    lifePanel.add(lifeBar);

    lifeValue = new JLabel(Double.toString(ch.getLife()));
    lifePanel.add(lifeValue);

    lifePanel.setAlignmentX(JComponent.CENTER_ALIGNMENT);

    this.add(lifePanel);

    // Création de la partie "contrôle"

    attackButtons = new JButton[4]; // Création du tableau de boutons

    Box actionBox = Box.createHorizontalBox(); // conteneur des boutons + du nombre de crédits

    // Affichage des crédits

    JPanel creditsPanel = new JPanel();
    GridLayout creditsGrid = new GridLayout(2,1);
    creditsPanel.setLayout(creditsGrid);

    credits = ch.getCredits();
    creditsLabel = new JLabel(Integer.toString(credits));
    creditsPanel.add(new JLabel("Crédits"));
    creditsPanel.add(creditsLabel);

    // Création du panneau contenant les boutons d'attaque

    JPanel attackPanel = new JPanel();
    GridLayout attackGrid = new GridLayout(2, 2);
    attackPanel.setLayout(attackGrid);

    for(int i = 0 ; i < attackButtons.length ; i++) {

      attackButtons[i] = new JButton(ch.getAttack(i).getName()); // Création du bouton et affichage du nom de l'attaque
      attackButtons[i].putClientProperty("index", i); // on inscrit l'index du bouton (qui correspond à celui de l'attaque) dans l'objet lui même pour y avoir accès dans les listeners
      attackPanel.add(attackButtons[i]); // ajout du bouton au JPanel

      // Création des listeners (passage de la souris)

      attackButtons[i].addMouseListener(new MouseAdapter() {

        public void mouseEntered(MouseEvent arg0) { // affichage de la zone d'attaque quand on survole le bouton
          int currentIndex = (Integer)((JButton)arg0.getSource()).getClientProperty("index"); // récupération de l'index du bouton actionné
          // Récupération des coordonnées du personnage
          int x = ch.getX();
          int y = ch.getY();
          ch.getAttack(currentIndex).paint(gw, x, y); // affichage de la zone

        }

        public void mouseExited(MouseEvent arg0) { // Reset du plateau

          Case[][] world = gw.getWorld();
          int x = ch.getX();
          int y = ch.getY();
          world[x][y].rePaintWorld();
        }

      });

      // Création du listener appelé quand le bouton est actionné

      attackButtons[i].addActionListener(new ActionListener() {

        public void actionPerformed(ActionEvent arg0) {

            int currentIndex = (Integer)((JButton)arg0.getSource()).getClientProperty("index"); // Récupération de l'index du bouton actionné

            if(ch.attack(currentIndex)) { // si l'attaque a été réussie
              System.out.println("Attaque réussie"); // debug
              ch.checkCredits(); // si les crédits sont à 0, on passe au tour suivant
            }
            updateCredits(); // mise à jour des crédits
            gw.updateLife(); // mise à jour des barres de vie

        }

      });

    }

    // Création du bouton permettant de passer au tour suivant

    nextTurn = new JButton("Finir tour");

    nextTurn.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent arg0) {

        manager.nextTurn();

      }

    });

    // Ajout des panneaux crédits et action à l'interface de contrôle et ajout de cette interface à la boîte globale

    actionBox.add(creditsPanel);
    actionBox.add(attackPanel);
    this.add(actionBox);

    // Ajout du bouton "Valider" à la boîte globale

    this.add(nextTurn);

  }


  // Met à jour l'affichage des crédits
  public void updateCredits() {
    System.out.println(ch.getCredits()); // debug
    this.creditsLabel.setText(Integer.toString(ch.getCredits()));
  }

  // Met à jour l'affichage des points de vie
  public void updateLife() {
    this.lifeBar.setValue((int) Math.ceil(ch.getLife()));
    this.lifeValue.setText(Double.toString(ch.getLife()));

    System.out.println(ch.getLife()); // debug
  }

}
