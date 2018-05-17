import java.awt.*;
import javax.swing.*;
import java.awt.Color;
import java.awt.event.*;

//objet représentant chaque case de notre fenêtre de jeu
public class Case extends JLabel{

  // on récupère toutes les images dont on va avoir besoin pour l'affichage des cases

  private ImageIcon defaultCase = new ImageIcon("img/DefaultCase.png");
  private ImageIcon blueCase = new ImageIcon("img/MouseCase.png");
  private ImageIcon player1 = new ImageIcon("img/Player1.png");
  private ImageIcon player2 = new ImageIcon("img/Player2.png");
  private ImageIcon player1selected = new ImageIcon("img/Player1Selected.png");
  private ImageIcon player1healed = new ImageIcon("img/Player1Healed.png");
  private ImageIcon player2selected = new ImageIcon("img/Player2Selected.png");
  private ImageIcon player2healed = new ImageIcon("img/Player2Healed.png");
  private ImageIcon momo = new ImageIcon("img/Momo.png");

  //On récupère une instance du GameManager et de la fenêtre de jeu

  private GameWindow gw;
  public GameManager manager = new GameManager().getManager();

  public Case(GameWindow gw, int x, int y){

    this.gw = gw;

    //Chaque case est enregistrée avec ses coordonées x y dans la fenêtre de jeu

    this.putClientProperty("x", x);
    this.putClientProperty("y", y);
    this.setIcon(defaultCase); // case blanche de base


 // On ajoute les différents listener utiles au bon affihage du jeu
    this.addMouseListener(new MouseAdapter() {

      public void mouseEntered(MouseEvent arg0) { // appellée quand la souris passe sur la case, pour afficher la prévisualisation du déplacement

        // on récupère l'instance actuelle qui est survolée et ses coordonées

        Case current = (Case)arg0.getSource();

        int x = (Integer) current.getClientProperty("x");
        int y = (Integer) current.getClientProperty("y");

        //On récupère les coordonées du joueur qui joue via le GameManager

        int chx = manager.getCharacter(manager.getTurn()).getX();
        int chy = manager.getCharacter(manager.getTurn()).getY();

        //On calcul la distance totale entre le joueur actuel  et la case, sachant que la diagonale est interdite

        int totalDistance = Math.abs(chx-x) + Math.abs(chy-y);

        //On recupère les crédits actuels du joueur pour voir de combien il peut se déplacer

        int credits = manager.getCharacter(manager.getTurn()).getCredits();

        Case[][] world = gw.getWorld(); //On récupère le monde de case contenue dans la fenêtre de jeu

        // On vérifie si le joueur est effectivement en train de choisir un déplacement et s'il peut le faire en donction de ses crédits

        if((gw.getState() == GameWindow.STATE_SELECT_MOVING) && credits >= totalDistance){

          // On change les images des cases en bleu en fonction du déplacement pour le prévisualiser

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

          // On s'assure que la case du joueur ennemi reste bien celle du joueur ennemi et ne se colore pas en bleu
          world[manager.getCharacter(manager.getOppositeTurn()).getX()][manager.getCharacter(manager.getOppositeTurn()).getY()].setIcon(getOppositeIcon());
        }

}



        public void mouseExited(MouseEvent arg0) {

          GameManager manager = new GameManager().getManager();
          Case current = (Case)arg0.getSource();

          int x = (Integer) current.getClientProperty("x");
          int y = (Integer) current.getClientProperty("y");

          int chx = manager.getCharacter(manager.getTurn()).getX();
          int chy = manager.getCharacter(manager.getTurn()).getY();

          Case[][] world = gw.getWorld();



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
      manager.getCharacter(manager.getTurn()).checkCredits();
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
    //System.out.println("debug");

    for(int i = 0; i<world1.length; i++){

      for(int k = 0; k < world1[0].length; k++){

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

  public void paintZone() {

    Case[][] world = gw.getWorld();
    int[][] world1 = manager.getWorld();
    int x = (Integer) this.getClientProperty("x");
    int y = (Integer) this.getClientProperty("y");
    //System.out.println("debug");

    for(int i = -3; i<= 3; i++){

      for(int k = -3 ; k <= 3; k++){

        try {
          switch(world1[x+i][y+k]){
            case 0:
            world[x+i][y+k].setIcon(blueCase);
            break;
            case 1:
            world[x+i][y+k].setIcon(player1selected); // PIERRE : ici
            break;
            case 2:
            world[x+i][y+k].setIcon(player2selected); // PIERRE : ici
            break;

          }
        } catch(ArrayIndexOutOfBoundsException e) {

        }


      }

    }

  }

  public void paintFireball() {

    Case[][] world = gw.getWorld();
    int[][] world1 = manager.getWorld();
    int x = (Integer) this.getClientProperty("x");
    int y = (Integer) this.getClientProperty("y");

    for(int i = y-7 ; i <= y+7 ; i++) {

      try {

        switch(world1[x][i]){
          case 0:
          world[x][i].setIcon(blueCase);
          break;
          case 1:
          world[x][i].setIcon(player1selected); // PIERRE : ici
          break;
          case 2:
          world[x][i].setIcon(player2selected); // PIERRE : ici
          break;

        }

      } catch(ArrayIndexOutOfBoundsException e) {

      }


    }

    for(int i = x-7 ; i <= x+7 ; i++) {

      try {

        switch(world1[i][y]){
          case 0:
          world[i][y].setIcon(blueCase);
          break;
          case 1:
          world[i][y].setIcon(player1selected); // PIERRE : ici
          break;
          case 2:
          world[i][y].setIcon(player2selected); // PIERRE : ici
          break;

        }


      } catch(ArrayIndexOutOfBoundsException e) {

      }


    }


  }

  public void paintLocated() {

    Case[][] world = gw.getWorld();
    int[][] world1 = manager.getWorld();
    int x = (Integer) this.getClientProperty("x");
    int y = (Integer) this.getClientProperty("y");

    for(int i = y-1 ; i <= y+1 ; i++) {

      try {

        switch(world1[x][i]){
          case 0:
          world[x][i].setIcon(blueCase);
          break;
          case 1:
          world[x][i].setIcon(player1selected); // PIERRE : ici
          break;
          case 2:
          world[x][i].setIcon(player2selected); // PIERRE : ici
          break;

        }

      } catch(ArrayIndexOutOfBoundsException e) {

      }


    }

    for(int i = x-1 ; i <= x+1 ; i++) {

      try {

        switch(world1[i][y]){
          case 0:
          world[i][y].setIcon(blueCase);
          break;
          case 1:
          world[i][y].setIcon(player1selected); // PIERRE : ici
          break;
          case 2:
          world[i][y].setIcon(player2selected); // PIERRE : ici
          break;

        }


      } catch(ArrayIndexOutOfBoundsException e) {

      }


    }


  }

  public void paintHeal() {

    Case[][] world = gw.getWorld();
    int[][] world1 = manager.getWorld();
    int x = (Integer) this.getClientProperty("x");
    int y = (Integer) this.getClientProperty("y");

    switch(manager.getTurn()){

      case 0:
        world[x][y].setIcon(player1healed);
        break;
      case 1:
        world[x][y].setIcon(player2healed);
        break;

    }
  }

  private void printInt2DArray(int[][] t) {

    for(int i = 0; i < t.length; i++){

      for(int k = 0; k < t[0].length; k++){

        System.out.print(t[i][k] + " ");

      }
      System.out.println();
    }
    System.out.println();

  }

  private ImageIcon getOppositeIcon() {
    if(this.manager.getOppositeTurn() == 1) {
      return this.player2;
    } else {
      return this.player1;
    }
  }

}
