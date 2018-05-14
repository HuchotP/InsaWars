
/* import la bibliothèque lang*/

import java.lang.Math;

public class LocatedAttack extends Attack{  /* la classe LocatedAttack ( L'attaque de mélée) est une classe fille de la classe Attack qui englobe toute les attaques */

  /* Definition d'un nouveau GameManager */
  GameManager manager = new GameManager().getManager();

  /* Constructeur */
  public LocatedAttack(Character c){

    this.name= "Mêlée";  /* indique le nom de l'attaque */
    this.damage= 3*c.getStrength(); /* indique les dégats infligés à l'adversaire, en fonction de l'attribut force de l'attaquant */
    this.dodgerate= 1.0/8; /* indique la probabilité pour l'adversaire d'esquiver l'attaque */
    this.creditsRequired=3; /* indique le nombre de crédits nécessaire pour l'attaquant afin d'effectuer l'attaque */

  }

  @Override

  /* Méthode d'attaque ciblée sur une case séléctionée adjacente à celle de l'attaquant
    Renvoie un booléen true si l'attaque a eu lieu, false si l'attaque n'a pas eu lieu */

  public boolean attack(){

    Character c1= manager.getCharacter(manager.getTurn());  /* Character 1 est définit comme étant l'attaquant */
    Character c2= manager.getCharacter(manager.getOppositeTurn());  /* Character 2 est définit comme étant l'adversaire  */
    if ( c1.getCredits()>=this.creditsRequired){ /* verrification que l'attaquant possède suffisamment de crédits pour effectuer l'attaque*/
      if (( Math.abs(c1.getX()- c2.getX())==1 || Math.abs(c1.getY()- c2.getY())==1) && Math.abs(c1.getX()- c2.getX()) !=  Math.abs(c1.getY()- c2.getY())){ /* verrification que l'adversaire se trouve sur une case adjacente à celle de l'attaquant*/
        c2.takeDamage(this.damage, this.dodgerate);
        return true; /* L'attaque a été effectuée */
      } else{
        return false; /* L'attaque n'a pas été effectuée */
      }
    }else{
      return false; /* L'attaque n'a pas été effectuée */
    }

  }

  @Override

  /* Affiche sur la fenetre de l'attaquant les cases possibles de l'attaque */
  public void paint(GameWindow gw, int x, int y) {
    gw.getWorld()[x][y].paintLocated();
  }

}
