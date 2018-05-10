import java.awt.*;
import javax.swing.*;
import java.awt.Color;
import java.awt.event.*;

public class PassiveInterface extends JPanel {

  private JLabel nameLabel;
  private JProgressBar lifeBar;
  private JLabel lifeValue;

  private Character ch;
  private GameWindow gw;
  public GameManager manager = new GameManager().getManager();

  public PassiveInterface(Character ch, GameWindow gw) {

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



  }

  public void updateLife() {
    this.lifeBar.setValue((int) Math.ceil(ch.getLife()));
    this.lifeValue.setText(Double.toString(ch.getLife()));
  }

}
