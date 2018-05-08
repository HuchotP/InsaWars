import java.awt.*;
import javax.swing.*;
import java.awt.Color;
import java.awt.event.*;

public class ActiveInterface extends JPanel {

  private JLabel nameLabel;
  private JProgressBar lifeBar;
  private JLabel lifeValue;
  private JButton[] attackButtons;
  private GameWindow gw;
  public GameManager manager = new GameManager().getManager();

  public ActiveInterface(Character ch, GameWindow gw) {

    this.gw = gw;

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
      attackButtons[i] = new JButton(ch.getAttack(i).getName());
      attackButtons[i].putClientProperty("index", i);
      attackPanel.add(attackButtons[i]);

      attackButtons[i].addMouseListener(new MouseAdapter() {

        public void mouseExited(MouseEvent arg0) {

          Case[][] world = gw.getWorld();
          int x = (manager.getCharacter(0)).getX();
          int y = (manager.getCharacter(0)).getY();
          world[x][y].rePaintWorld();
        }

      });

    }

    attackButtons[0].addMouseListener(new MouseAdapter() {

      public void mouseEntered(MouseEvent arg0) {

        Case[][] world = gw.getWorld();
        int x = (manager.getCharacter(0)).getX();
        int y = (manager.getCharacter(0)).getY();
        world[x][y].paintZone();
      }

    });

    this.add(attackPanel);



  }

}
