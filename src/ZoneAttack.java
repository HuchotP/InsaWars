package info.insawars;

import java.lang.Math;

public class ZoneAttack extends Attack{

  GameManager manager = new GameManager().getManager();

  public ZoneAttack(){

    this.name = "Attaque de zone";

  }

  public void attack(){

    Character c1 = manager.getPlayer(manager.getTurn());
    Character c2 = manager.getPlayer(manager.getOppositeTurn());

    if(Math.abs(c1.getX() - c2.getX()) < 4 && Math.abs(c1.getY() - c2.getY()) < 4 ){

      c2.takeDamage();

    }

  }

}
