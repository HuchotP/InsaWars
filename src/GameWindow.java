import java.awt.*;
import javax.swing.*;
import java.awt.Color;
import java.awt.event.*;

public class GameWindow extends JFrame{

  //private GameManager manager = new GameManager().getManager();

  private JLabel[][] world;
  private int currentX;
  private int currentY;
  private int mouseX;
  private int mouseY;

  public JLabel[][] getWorld() {
    return world;
  }

  public GameWindow(){

    this.setSize(640,900);

    world = new JLabel[10][10];

    Box globalBox = Box.createVerticalBox();

    ImageIcon icon = new ImageIcon("DefaultCase.png");

    JPanel worldContainer = new JPanel();
    GridLayout worldGrid = new GridLayout(10,10);
    worldContainer.setLayout(worldGrid);

    //JLabel[][] world = new JLabel[10][10];

    for(int i = 0; i < world.length; i++){

      for(int k = 0; k < world[0].length; k++){

        world[i][k] = new JLabel(icon);
        world[i][k].putClientProperty("x", i);
        world[i][k].putClientProperty("y", k);

        world[i][k].addMouseListener(new MouseAdapter() {

          public void mouseEntered(MouseEvent arg0) {

            JLabel current = (JLabel)arg0.getSource();
            int x = (Integer) current.getClientProperty("x");

            for(int i = currentX ; i <= (Integer) current.getClientProperty("x") ; i++) {
              world[i][currentY].setIcon(new ImageIcon("MouseCase.png"));
            }
            for(int i = currentY ; i <= (Integer) current.getClientProperty("y") ; i++) {
              world[x][i].setIcon(new ImageIcon("MouseCase.png"));
            }
            for(int i = currentX ; i >= (Integer) current.getClientProperty("x") ; i--) {
              world[i][currentY].setIcon(new ImageIcon("MouseCase.png"));
            }
            for(int i = currentY ; i >= (Integer) current.getClientProperty("y") ; i--) {
              world[x][i].setIcon(new ImageIcon("MouseCase.png"));
            }

            /*JLabel current = (JLabel)arg0.getSource();
            current.setIcon(new ImageIcon("MouseCase.png"));
            int x = (Integer) current.getClientProperty("x");
            int y = (Integer) current.getClientProperty("y");
            System.out.println("Label " + x + " , " + y + " hovered");*/

          }

          public void mouseExited(MouseEvent arg0) {

            JLabel current = (JLabel)arg0.getSource();
            int x = (Integer) current.getClientProperty("x");
            int y = (Integer) current.getClientProperty("y");

            for(int i = currentX ; i <= (Integer) current.getClientProperty("x") ; i++) {
              world[i][currentY].setIcon(new ImageIcon("DefaultCase.png"));
            }
            for(int i = currentY ; i <= (Integer) current.getClientProperty("y") ; i++) {
              world[x][i].setIcon(new ImageIcon("DefaultCase.png"));
            }
            for(int i = currentX ; i >= (Integer) current.getClientProperty("x") ; i--) {
              world[i][currentY].setIcon(new ImageIcon("DefaultCase.png"));
            }
            for(int i = currentY ; i >= (Integer) current.getClientProperty("y") ; i--) {
              world[x][i].setIcon(new ImageIcon("DefaultCase.png"));
            }

            /*JLabel current = (JLabel)arg0.getSource();
            current.setIcon(new ImageIcon("DefaultCase.png"));
            int x = (Integer) current.getClientProperty("x");*/
          }

          public void mouseClicked(MouseEvent arg0) {

            JLabel current = (JLabel)arg0.getSource();
            world[currentX][currentY].setIcon(new ImageIcon("DefaultCase.png"));

            System.out.println(currentX + " " + currentY);

            for(int i = currentX ; i <= (Integer) current.getClientProperty("x") ; i++) {
              world[i][currentY].setIcon(new ImageIcon("DefaultCase.png"));
            }
            for(int i = currentY ; i <= (Integer) current.getClientProperty("y") ; i++) {
              world[(Integer) current.getClientProperty("x")][i].setIcon(new ImageIcon("DefaultCase.png"));
            }

            for(int i = currentX ; i >= (Integer) current.getClientProperty("x") ; i--) {
              world[i][currentY].setIcon(new ImageIcon("DefaultCase.png"));
            }
            for(int i = currentY ; i >= (Integer) current.getClientProperty("y") ; i--) {
              world[(Integer) current.getClientProperty("x")][i].setIcon(new ImageIcon("DefaultCase.png"));
            }

            currentX = (Integer) current.getClientProperty("x");
            currentY = (Integer) current.getClientProperty("y");
            world[currentX][currentY].setIcon(new ImageIcon("MouseCase.png"));

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
