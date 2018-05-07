import java.awt.*;
import javax.swing.*;
import java.awt.Color;
import java.awt.event.*;

public class ActiveInterface extends JPanel {

  private JLabel nameLabel;
  private JProgressBar lifeBar;
  private JLabel lifeValue;
  private JButton[] attackButtons;
  private String[] attacks = {"Zone", "Boule de feu", "Directive", "Soin"};

  public ActiveInterface(Character ch) {

    nameLabel = new JLabel(ch.getName()+"\n");
    nameLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
    this.add(nameLabel);

    JPanel lifePanel = new JPanel();
    GridLayout lifeGrid = new GridLayout(1,2);
    //lifePanel.setLayout(lifeGrid);

    lifeBar = new JProgressBar();

    lifeBar.setMaximum((int) Math.ceil(ch.getLife()));
    lifeBar.setValue((int) Math.ceil(ch.getMaxLife()));
    lifeBar.setForeground(new Color(0,255,0));
    lifePanel.add(lifeBar);

    lifeValue = new JLabel(Double.toString(ch.getLife()));
    lifePanel.add(lifeValue);

    lifePanel.setAlignmentX(JComponent.CENTER_ALIGNMENT);

    this.add(lifePanel);

    attackButtons = new JButton[4];

    JPanel attackPanel = new JPanel();
    GridLayout attackGrid = new GridLayout(2, 2);
    attackPanel.setLayout(attackGrid);

    for(int i = 0 ; i < attackButtons.length ; i++) {
      attackButtons[i] = new JButton(attacks[i]);
      attackButtons[i].putClientProperty("index", i);
      attackPanel.add(attackButtons[i]);
    }

    attackButtons[0].addMouseListener(new MouseAdapter() {

      public void mouseEntered(MouseEvent arg0) {
        System.out.println("Attaque de zone");
      }

    });

    this.add(attackPanel);



  }

}
