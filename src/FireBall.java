

import java.lang.Math;

public class FireBall extends Attack{

  GameManager manager = new GameManager().getManager();

  public FireBall (Character c){

    this.name= "Boule de feu";
    this.damage= 1.5*c.getStrength();
    this.dodgerate= 1.0/3;
    this.creditsRequired= 3;

  }

  public boolean attack(){

    Character c1= manager.getCharacter(manager.getTurn());
    Character c2= manager.getCharacter(manager.getOppositeTurn());
    if ( c1.getCredits()>this.creditsRequired){
      if( ( c1.getX() == c2.getX() && Math.abs(c1.getX()- c2.getX()) < 7 ) || (c1.getY() == c2.getY() && Math.abs( c1.getY()- c2.getY()) < 7 )){
        c2.takeDamage(this.damage, this.dodgerate);
        return true;
      }
    }

    return false;
  }
}
