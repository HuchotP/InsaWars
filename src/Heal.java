

import java.lang.Math; // import la bibliothèque lang

public class Heal extends Attack{ // la classe Heal ( L'attaque de soin) est une classe fille de la classe Attack qui englobe toute les attaques

  // Definition d'un nouveau GameManager
  GameManager manager = new GameManager().getManager();

  // Constructeur
  public Heal(Character c){

    this.name= "Soins";  // indique le nom de l'attaque
    this.damage= 2*c.getIntel(); // indique les points de vie à ajouter en fonction de l'attribut Intelligence du personnage
    this.creditsRequired=4; // indique le nombre de crédits nécessaire pour l'attaquant afin d'effectuer l'attaque
    this.dodgerate=0;// indique la probabilité d'esquiver l'attaque

  }

  @Override

  // Méthode d'attaque ciblée sur le personnage lui même pour se soigner d'un certain nombre de points de vie. Renvoie un booléen true si l'attaque a eu lieu, false si l'attaque n'a pas eu lieu

  public boolean attack(){
      Character c1 = manager.getCharacter(manager.getTurn()); // Character 1 est définit comme étant l'attaquant
      if ( c1.getCredits()>=this.creditsRequired && c1.getLife() != c1.getMaxLife()){// verrification que l'attaquant possède suffisamment de crédits pour effectuer l'attaque

        if(this.damage >= c1.getMaxLife() - c1.getLife()) { // Verrification que l'ajout de vie de permette pas de dépasser le niveau maximale de points de vie
          c1.healCharacter(c1.getMaxLife() - c1.getLife()); // Le personnage est soigné jusqu'à la valeur maximale pour les points de vie
          return true; // L'attaque a été effectuée
        }

        c1.healCharacter(this.damage); // L'attaquant est soigné
        return true; // L'attaque a été effectuée
      } else{
        return false; // L'attaque n'a pas été effectuée
      }
    }

  @Override

  // Affiche sur la fenetre de l'attaquant la portée et les différentes directions possibles de l'attaque
  public void paint(GameWindow gw, int x, int y) {
    gw.getWorld()[x][y].paintHeal();
  }
}
