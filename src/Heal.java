

import java.lang.Math;

public class Heal extends Attack{

  GameManager manager = new GameManager().getManager();

  public Heal(Character c){

    this.name= " Sort de soins";
    this.damage= 20+ 4*c.getIntel();
    this.creditsRequired=3;

  }

  public boolean attack(){
      Character c1 = manager.getCharacter(manager.getTurn());
      if ( c1.getCredits()>this.creditsRequired){
        c1.healCharacter(this.damage );
        return true;
      } else{
        return false;
      }
    }
}
