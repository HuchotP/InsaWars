

import java.lang.Math; // import la bibliothèque lang

public class FireBall extends Attack{  // la classe FireBalle ( L'attaque de la boule de feu) est une classe fille de la classe Attack qui englobe toute les attaques

  // Definition d'un nouveau GameManager
  GameManager manager = new GameManager().getManager();

  // Constructeur
  public FireBall (Character c){

    this.name= "Boule de feu"; // indique le nom de l'attaque
    this.damage= 1.5*c.getStrength();  // indique les dégats infligés à l'adversaire, en fonction de l'attribut force de l'attaquant
    this.dodgerate= 1.0/3; // indique la probabilité pour l'adversaire d'esquiver l'attaque
    this.creditsRequired= 3; // indique le nombre de crédits nécessaire pour l'attaquant afin d'effectuer l'attaque

  }
  @Override

  // Méthode d'attaque ciblée sur une direction séléctionée. Renvoie un booléen true si l'attaque a eu lieu, false si l'attaque n'a pas eu lieu

  public boolean attack(){

    Character c1= manager.getCharacter(manager.getTurn()); // Character 1 est définit comme étant l'attaquant
    Character c2= manager.getCharacter(manager.getOppositeTurn()); // Character 2 est définit comme étant l'adversaire
    if ( c1.getCredits()>=this.creditsRequired){ // verrification que l'attaquant possède suffisamment de crédits pour effectuer l'attaque
      if( ( c1.getX() == c2.getX() && Math.abs(c1.getX()- c2.getX()) < 7 ) || (c1.getY() == c2.getY() && Math.abs( c1.getY()- c2.getY()) < 7 )){ // l'adversaire doit se trouver dans une portée de 7 cases en ligne droite par rapport à l'attaquant
        c2.takeDamage(this.damage, this.dodgerate); // L'adervsaire subit des dégats
        return true; // l'attaque a été effectuée
      }
    }

    return false; // L'attaque n'a pas été exécutée
  }

  @Override

  // Affiche sur la fenetre de l'attaquant la portée et les différentes directions possibles de l'attaque
  public void paint(GameWindow gw, int x, int y) {
    gw.getWorld()[x][y].paintFireball();
  }
}
