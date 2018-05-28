import java.awt.*;
import javax.swing.*;
import java.awt.Color;
import java.awt.event.*;

// Interface passive n'affichant que le nom et les points de vie du personnage
public class PassiveInterface extends JPanel {

  // Elements d'interface

  private JLabel nameLabel; // JLabel affichant le nom du joueur
  private JProgressBar lifeBar; // Barre de vie
  private JLabel lifeValue; // JLabel affichant les points de vie du joueur

  private Character ch; // Personnage associé à l'interface
  private GameWindow gw; // GameWindow associée à l'interface
  public GameManager manager = new GameManager().getManager(); // on récupère le GameManager pour accéder à certaines valeurs

  public PassiveInterface(Character ch, GameWindow gw) {

    this.gw = gw;
    this.ch = ch;

    // Affichage du nom

    nameLabel = new JLabel(ch.getName()+"\n");
    nameLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
    this.add(nameLabel); // ajout du nom à l'interface

    // Affichage de la vie

    JPanel lifePanel = new JPanel();

    lifeBar = new JProgressBar();
    lifeBar.setValue((int) Math.ceil(ch.getLife()));
    lifeBar.setMaximum((int) Math.ceil(ch.getMaxLife()));
    lifeBar.setForeground(new Color(0,255,0));
    lifePanel.add(lifeBar);

    lifeValue = new JLabel(Double.toString(ch.getLife()));
    lifePanel.add(lifeValue);

    lifePanel.setAlignmentX(JComponent.CENTER_ALIGNMENT);

    this.add(lifePanel); // ajout du panneau de vie à l'interface



  }

  // Met à jour l'affichage des points de vie
  public void updateLife() {
    this.lifeBar.setValue((int) Math.ceil(ch.getLife()));
    this.lifeValue.setText(Double.toString(ch.getLife()));
  }

}
