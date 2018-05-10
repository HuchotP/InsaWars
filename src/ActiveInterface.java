import java.awt.*;
import javax.swing.*;
import java.awt.Color;
import java.awt.event.*;

public class ActiveInterface extends JPanel {

  private JLabel nameLabel;
  private JProgressBar lifeBar;
  private JLabel lifeValue;
  private int credits;
  private JLabel creditsLabel;
  private JButton[] attackButtons;

  private Character ch;
  private GameWindow gw;
  public GameManager manager = new GameManager().getManager();

  public ActiveInterface(Character ch, GameWindow gw) {

    this.gw = gw;
    this.ch = ch;

    nameLabel = new JLabel(ch.getName()+"\n");
    nameLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
    this.add(nameLabel);

    JPanel lifePanel = new JPanel();
    GridLayout lifeGrid = new GridLayout(1,2);
    //lifePanel.setLayout(lifeGrid);

    lifeBar = new JProgressBar();

    lifeBar.setValue((int) Math.ceil(ch.getLife()));
    lifeBar.setMaximum((int) Math.ceil(ch.getMaxLife()));
    lifeBar.setForeground(new Color(0,255,0));
    lifePanel.add(lifeBar);

    lifeValue = new JLabel(Double.toString(ch.getLife()));
    lifePanel.add(lifeValue);

    lifePanel.setAlignmentX(JComponent.CENTER_ALIGNMENT);

    this.add(lifePanel);

    attackButtons = new JButton[4];

    Box actionBox = Box.createHorizontalBox();

    JPanel creditsPanel = new JPanel();
    GridLayout creditsGrid = new GridLayout(2,1);
    creditsPanel.setLayout(creditsGrid);

    credits = ch.getCredits();
    creditsLabel = new JLabel(Integer.toString(credits));
    creditsPanel.add(new JLabel("Crédits"));
    creditsPanel.add(creditsLabel);

    JPanel attackPanel = new JPanel();
    GridLayout attackGrid = new GridLayout(2, 2);
    attackPanel.setLayout(attackGrid);

    for(int i = 0 ; i < attackButtons.length ; i++) {
      attackButtons[i] = new JButton(ch.getAttack(i).getName());
      attackButtons[i].putClientProperty("index", i);
      attackPanel.add(attackButtons[i]);

      attackButtons[i].addMouseListener(new MouseAdapter() {

        public void mouseEntered(MouseEvent arg0) {
          int currentIndex = (Integer)((JButton)arg0.getSource()).getClientProperty("index");
          int x = ch.getX();
          int y = ch.getY();
          ch.getAttack(currentIndex).paint(gw, x, y);

        }

        public void mouseExited(MouseEvent arg0) {

          Case[][] world = gw.getWorld();
          int x = ch.getX();
          int y = ch.getY();
          world[x][y].rePaintWorld();
        }

      });

      attackButtons[i].addActionListener(new ActionListener() {

        public void actionPerformed(ActionEvent arg0) {

            int currentIndex = (Integer)((JButton)arg0.getSource()).getClientProperty("index");
            if(ch.attack(currentIndex)) {
              System.out.println("Attaque réussie");
            }
            updateCredits();
            gw.updateLife();

        }

      });

    }

    /*attackButtons[0].addMouseListener(new MouseAdapter() {

      public void mouseEntered(MouseEvent arg0) {

        Case[][] world = gw.getWorld();
        int x = (manager.getCharacter(0)).getX();
        int y = (manager.getCharacter(0)).getY();
        world[x][y].paintZone();
      }

    });

    attackButtons[1].addMouseListener(new MouseAdapter() {

      public void mouseEntered(MouseEvent arg0) {

        Case[][] world = gw.getWorld();
        int x = (manager.getCharacter(0)).getX();
        int y = (manager.getCharacter(0)).getY();
        world[x][y].paintFireball();
      }

    });*/


    actionBox.add(creditsPanel);
    actionBox.add(attackPanel);
    this.add(actionBox);



  }

  public void updateCredits() {
    this.creditsLabel.setText(Integer.toString(ch.getCredits()));
  }

  public void updateLife() {
    this.lifeBar.setValue((int) Math.ceil(ch.getLife()));
    this.lifeValue.setText(Double.toString(ch.getLife()));
  }

}
