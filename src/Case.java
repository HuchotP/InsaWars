import java.awt.*;
import javax.swing.*;
import java.awt.Color;
import java.awt.event.*;

public class Case extends JLabel{

  private ImageIcon defaultCase = new ImageIcon("DefaultCase.png");
  private ImageIcon blueCase = new ImageIcon("MouseCase.png");
  private ImageIcon player1 = new ImageIcon("Player1.png");
  private ImageIcon player2 = new ImageIcon("Player2.png");
  private GameWindow gw;
  public GameManager manager = new GameManager().getManager();

  public Case(GameWindow gw, int x, int y){

    this.gw = gw;

    this.putClientProperty("x", x);
    this.putClientProperty("y", y);
    this.setIcon(defaultCase);



    this.addMouseListener(new MouseAdapter() {

      public void mouseEntered(MouseEvent arg0) {

        Case current = (Case)arg0.getSource();

        int x = (Integer) current.getClientProperty("x");
        int y = (Integer) current.getClientProperty("y");

        int chx = manager.getCharacter(manager.getTurn()).getX();
        int chy = manager.getCharacter(manager.getTurn()).getY();
        Case[][] world = gw.getWorld();

        if(gw.getState() == GameWindow.STATE_SELECT_MOVING){


          for(int i = chx+1 ; i <= x ; i++) {
            world[i][chy].setIcon(blueCase);
          }
          for(int i = chy+1 ; i <= y ; i++) {
            world[x][i].setIcon(blueCase);
          }
          for(int i = chx-1 ; i >= x ; i--) {
            world[i][chy].setIcon(blueCase);
          }
          for(int i = chy-1 ; i >= y ; i--) {
            world[x][i].setIcon(blueCase);
          }
        }

        if(gw.getState() == GameWindow.STATE_ATTACK1){

          for(int i = chx -4; i <= chx + 4; i ++){

            for(int k = chx -4; k <= chx + 4; k ++)

            try {
              world[i][k].setIcon(blueCase);
            } catch(ArrayIndexOutOfBoundsException e) {

            }

          }


        }

      }

      /*JLabel current = (JLabel)arg0.getSource();
      current.setIcon(new ImageIcon("MouseCase.png"));
      int x = (Integer) current.getClientProperty("x");
      int y = (Integer) current.getClientProperty("y");
      System.out.println("Label " + x + " , " + y + " hovered");*/


      public void mouseExited(MouseEvent arg0) {

        GameManager manager = new GameManager().getManager();
        Case current = (Case)arg0.getSource();

        int x = (Integer) current.getClientProperty("x");
        int y = (Integer) current.getClientProperty("y");

        int chx = manager.getCharacter(manager.getTurn()).getX();
        int chy = manager.getCharacter(manager.getTurn()).getY();

        Case[][] world = gw.getWorld();

        if(gw.getState() == GameWindow.STATE_ATTACK1){

          for(int i = chx -4; i <= chx + 4; i ++){

            for(int k = chx -4; k <= chx + 4; k ++)

            try {
              world[i][k].setIcon(defaultCase);
            } catch(ArrayIndexOutOfBoundsException e) {

            }

          }

        }

        if(gw.getState() == GameWindow.STATE_SELECT_MOVING){


         /**for(int i = chx ; i <= x ; i++) {
            world[i][chy].setIcon(defaultCase);
          }
          for(int i = chy ; i <= y ; i++) {
            world[x][i].setIcon(defaultCase);
          }
          for(int i = chx ; i >= x ; i--) {
            world[i][chy].setIcon(defaultCase);
          }
          for(int i = chy ; i >= y ; i--) {
            world[x][i].setIcon(defaultCase);
          }**/


          rePaintWorld();
        }
        /*JLabel current = (JLabel)arg0.getSource();
        current.setIcon(new ImageIcon("DefaultCase.png"));
        int x = (Integer) current.getClientProperty("x");*/
      }

      public void mouseClicked(MouseEvent arg0) {

        GameManager manager = new GameManager().getManager();
        Case current = (Case)arg0.getSource();

        int x = (Integer) current.getClientProperty("x");
        int y = (Integer) current.getClientProperty("y");

        int chx = manager.getCharacter(manager.getTurn()).getX();
        int chy = manager.getCharacter(manager.getTurn()).getY();
        Case[][] world = gw.getWorld();



        if(gw.getState() == GameWindow.STATE_SELECT_MOVING){

          if(manager.getCharacter(manager.getTurn()).move(x,y) == true){

            /**for(int i = chx ; i <= x ; i++) {
              world[i][chy].setIcon(defaultCase);
            }
            for(int i = chy ; i <= y ; i++) {
              world[x][i].setIcon(defaultCase);
            }
            for(int i = chx ; i >= x ; i--) {
              world[i][chy].setIcon(defaultCase);
            }
            for(int i = chy ; i >= y ; i--) {
              world[x][i].setIcon(defaultCase);
            }**/

            rePaintWorld();
            System.out.println(manager.getCharacter(manager.getTurn()).getX() + " " + manager.getCharacter(manager.getTurn()).getY());
          }else{

              System.out.println("Vous ne pouvez pas bouger : pas asssez de crédits ou pas sur un ennemi");

          }

        }

        if(gw.getState() == GameWindow.STATE_ATTACK1){

          if(manager.getCharacter(manager.getTurn()).attack(0) == true){



          }

        }

      }

    });


  }

  public void rePaintWorld(){

      Case[][] world = gw.getWorld();
      int[][] world1 = manager.getWorld();
      System.out.println("debug");

      for(int i = 0; i<world1.length; i++){

          for(int k = 0; k < world1[0].length; k++){

            System.out.println(world1[i][k]);
            switch(world1[i][k]){
              case 0:
                world[i][k].setIcon(defaultCase);
                break;
              case 1:
                world[i][k].setIcon(player1);
                break;
              case 2:
                world[i][k].setIcon(player2);
                break;

            }

          }

      }

  }

}
