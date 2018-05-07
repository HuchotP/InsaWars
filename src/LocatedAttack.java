

import java.lang.Math;

public class LocatedAttack extends Attack{

  GameManager manager = new GameManager().getManager();

  public LocatedAttack(Character c){

    this.name= " Attaque ciblée";
    this.damage= 3*c.getStrength();
    this.dodgerate= 1/8;
    this.creditsRequired=3;

  }

  public boolean attack(){

    Character c1= manager.getCharacter(manager.getTurn());
    Character c2= manager.getCharacter( manager.getOppositeTurn());
    if ( c1.getCredits()>this.creditsRequired){
      if ( Math.abs(c1.getX()- c2.getX())<1 || Math.abs(c1.getY()- c2.getY())<1){
        c2.takeDamage(this.damage, this.dodgerate );
        return true;
      } else{
        return false;
      }
    }else{
      return false
    }

  }

}
