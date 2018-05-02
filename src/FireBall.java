

import java.lang.Math;

public class FireBall extends Attack{

  GameManager manager = new GameManager.getManager();

  public FireBallAttack (Character c){

    this.name= " Boule de feu ";
    this.damage= 20+ 4*getIntel()+ 2*getStrength();
    this.dodgerate= 15;
    this.creditsRequired= 3;

  }

  public void attack(){

    Character c1= manager.getCharacter(manager.getTurn());
    Character c2= manager.getCharacter(manager.getOppositeTurn());
    if ( c1.getCredits()>this.creditsRequired){
      if( ( c1.getX()= c2.getX() && Math.abs(c1.getX()- c2.getX())<7)|| (c1.getY()= c2.getY() && Math.abs( c1.getY()- c2.getY())<7 )){
        c2.takeDamge(this.damage, this.dodgerate);
      }
    }
  }
}
