import java.awt.*;
import javax.swing.*;
import java.awt.Color;
import java.awt.event.*;

public class GameWindow extends JFrame{

  //private GameManager manager = new GameManager().getManager();

  public GameWindow(){

    this.setSize(640,900);

    Box globalBox = Box.createVerticalBox();

    ImageIcon icon = new ImageIcon("DefaultCase.png");

    JPanel worldContainer = new JPanel();
    GridLayout worldGrid = new GridLayout(10,10);
    worldContainer.setLayout(worldGrid);

    JLabel[][] world = new JLabel[10][10];

    for(int i = 0; i < world.length; i++){

      for(int k = 0; k < world[0].length; k++){

        world[i][k] = new JLabel(icon);
        world[i][k].putClientProperty("x", i);
        world[i][k].putClientProperty("y", k);

        world[i][k].addMouseListener(new MouseAdapter() {

          public void mouseEntered(MouseEvent arg0) {
            
            int x = (Integer)((JLabel)arg0.getSource()).getClientProperty("x");
            int y = (Integer)((JLabel)arg0.getSource()).getClientProperty("y");
            System.out.println("Label " + x + " , " + y + " hovered");

          }
        });



        worldContainer.add(world[i][k]);

      }

    }

    worldContainer.setMinimumSize(new Dimension(600,600));
    worldContainer.setMaximumSize(new Dimension(600,600));
    globalBox.add(worldContainer);

    JPanel charaUI = new JPanel();
    charaUI.setMinimumSize(new Dimension(600,300));
    charaUI.setMaximumSize(new Dimension(600,300));
    globalBox.add(charaUI);

    this.add(globalBox);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);

  }

}
