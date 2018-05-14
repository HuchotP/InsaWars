
// import la bibliothèque lang
import java.lang.Math;

public class ZoneAttack extends Attack{ // la classe ZoneAttack ( L'attaque de mélée) est une classe fille de la classe Attack qui englobe toute les attaques

  // Definition d'un nouveau GameManager
  GameManager manager = new GameManager().getManager();

  // Constructeur
  public ZoneAttack(Character c){

    this.name = "Zone";  // indique le nom de l'attaque
    this.damage = 1*c.getStrength() + 1*c.getIntel(); // indique les dégats infligés à l'adversaire, en fonction de l'attribut Intelligence de l'attaquant
    this.dodgerate= 1/15; // indique la probabilité pour l'adversaire d'esquiver l'attaque
    this.creditsRequired= 4;  // indique le nombre de crédits nécessaire pour l'attaquant afin d'effectuer l'attaque

  }

  @Override

    // Méthode d'attaque ciblée sur une zone séléctionée. Renvoie un booléen true si l'attaque a eu lieu, false si l'attaque n'a pas eu lieu
  public boolean attack(){

    Character c1 = manager.getCharacter(manager.getTurn());  // Character 1 est définit comme étant l'attaquant
    Character c2 = manager.getCharacter(manager.getOppositeTurn());  // Character 2 est définit comme étant l'adversaire
    if ( c1.getCredits()>=this.creditsRequired){// verrification que l'attaquant possède suffisamment de crédits pour effectuer l'attaque
      if(Math.abs(c1.getX() - c2.getX()) < 4 && Math.abs(c1.getY() - c2.getY()) < 4 ){  // Verrification que l'adversaire se situe dans le perimetre selectionné pour l'attaque
        c2.takeDamage(this.damage, this.dodgerate);  // L'adversaire subit les dégars
        return true;  // L'attaque a été effectuée
    }else{
      return false;  // L'attaque n'a pas été effectuée
    }
  }else{
    return false;  // L'attaque n'a pas été effectuée
  }
  }

  @Override

  // Affiche sur la fenetre de l'attaquant les zones possibles de l'attaque
  public void paint(GameWindow gw, int x, int y) {
    gw.getWorld()[x][y].paintZone();
  }

}
