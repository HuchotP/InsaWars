import java.awt.*;
import javax.swing.*;
import java.awt.Color;
import java.awt.event.*;

public class GameWindow extends JFrame{

  private GameManager manager = new GameManager().getManager();

  private Case[][] world;
  private int currentX;
  private int currentY;
  private int mouseX;
  private int mouseY;

  private JPanel card1;
  private JPanel card2;

  private ActiveInterface active1;
  private ActiveInterface active2;

  private PassiveInterface passive1;
  private PassiveInterface passive2;

  public static final int STATE_SELECT_MOVING =0;
  public static final int STATE_ATTACK1 = 1;
  public static final int STATE_ATTACK2 = 2;
  public static final int STATE_ATTACK3 = 3;
  public static final int STATE_ATTACK4 = 4;
  public static final int STATE_NOTHING = -1;

  private int currentState = STATE_SELECT_MOVING; //phase de test


  public Case[][] getWorld() {
    return world;
  }

  public GameWindow(){

    this.setSize(640,900);

    world = new Case[10][10];

    Box globalBox = Box.createVerticalBox();



    JPanel worldContainer = new JPanel();
    GridLayout worldGrid = new GridLayout(10,10);
    worldContainer.setLayout(worldGrid);

    //JLabel[][] world = new JLabel[10][10];

    for(int i = 0; i < world.length; i++){

      for(int k = 0; k < world[0].length; k++){

        world[i][k] = new Case(this, i, k);

        worldContainer.add(world[i][k]);

      }

    }

    world[0][0].rePaintWorld();

    worldContainer.setMinimumSize(new Dimension(550,550));
    worldContainer.setMaximumSize(new Dimension(550,550));
    globalBox.add(worldContainer);

    JPanel charaUI = new JPanel();
    charaUI.setMinimumSize(new Dimension(600,300));
    charaUI.setMaximumSize(new Dimension(600,300));

    GridLayout charaGrid = new GridLayout(1,2);
    charaUI.setLayout(charaGrid);

    card1 = new JPanel(new CardLayout());
    card2 = new JPanel(new CardLayout());

    active1 = new ActiveInterface(manager.getCharacter(0), this);
    active2 = new ActiveInterface(manager.getCharacter(1), this);

    passive1 = new PassiveInterface(manager.getCharacter(0), this);
    passive2 = new PassiveInterface(manager.getCharacter(1), this);

    card1.add(active1, "ACTIVE");
    card1.add(passive1, "PASSIVE");

    card2.add(active2, "ACTIVE");
    card2.add(passive2, "PASSIVE");

    CardLayout cl1 = (CardLayout)(card1.getLayout());
    CardLayout cl2 = (CardLayout)(card2.getLayout());

    cl1.show(card1, "ACTIVE");
    cl2.show(card2, "PASSIVE");

    charaUI.add(card1);
    charaUI.add(card2);

    globalBox.add(charaUI);

    this.add(globalBox);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);

  }

  public int getState(){

    return this.currentState;

  }

  public ActiveInterface getActive() {
    if(manager.getTurn() == 0) {
      return active1;
    }

    return active2;
  }

  public void updateLife() {
    active1.updateLife();
    active2.updateLife();

    passive1.updateLife();
    passive2.updateLife();

  }

  public void switchInterface() {

    CardLayout cl1 = (CardLayout)(card1.getLayout());
    CardLayout cl2 = (CardLayout)(card2.getLayout());

    cl1.next(card1);
    cl2.next(card2);

    getActive().updateCredits();

  }

}
