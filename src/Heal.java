

import java.lang.Math;

public class Heal extends Attack{

  GameManager manager = new GameManager().getManager();

  public Heal(Character c ){

    this.name= " Sort de soins";
    this.damage= 20+ 4*c.getIntel();
    this.creditsRequired=3;

  }

  public void attack(){
      Character c1 = manager.getPlayer(manager.getTurn());
      if ( c1.getCredits()>this.creditsRequired){
        healCharacter (int this.damage );

      }
    }
}
