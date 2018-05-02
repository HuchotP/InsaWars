

import java.lang.Math;

public class LocatedAttack extends Attack{

  GameManager manager = new GameManager().getManager();

  public LocatedAttack(Character c){

    this.name= " Attaque ciblée";
    this.damage= 20+20*c.getStrength();
    this.dodgerate= 0;
    this.creditsRequired=4;

  }

  public void attack(){

    Character c1= manager.getCharacter(manager.getTurn());
    Character c2= manager.getCharacter( manager.getOppositeTurn());
    if ( c1.getCredits()>this.creditsRequired){
      if ( Math.abs(c1.getX()- c2.getX())<1 || Math.abs(c1.getY()- c2.getY())<1){
        c2.takeDamage(this.damage, this.dodgerate );

      }
    }

  }

}
