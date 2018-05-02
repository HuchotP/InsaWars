

import java.lang.Math;

public class ZoneAttack extends Attack{

  GameManager manager = new GameManager().getManager();

  public ZoneAttack(){

    this.name = "Attaque de zone";
    this.damage = 20 + 4* c.getStrength() + 2* c.getIntel();
    this.dodgerate= 5;
    this.creditsRequired= 10;

  }

  public void attack(){

    Character c1 = manager.getPlayer(manager.getTurn());
    Character c2 = manager.getPlayer(manager.getOppositeTurn());
    if ( c1.getCredits()>this.creditsRequired){
      if(Math.abs(c1.getX() - c2.getX()) < 4 && Math.abs(c1.getY() - c2.getY()) < 4 ){
        c2.takeDamage(this.damage, this.dodgerate);

    }
  }
  }

}
