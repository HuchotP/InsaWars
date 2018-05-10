
import java.lang.Math;

public class ZoneAttack extends Attack{

  GameManager manager = new GameManager().getManager();

  public ZoneAttack(Character c){

    this.name = "Zone";
    this.damage = 1*c.getStrength() + 1*c.getIntel();
    this.dodgerate= 1/15;
    this.creditsRequired= 4;

  }

  @Override
  public boolean attack(){

    Character c1 = manager.getCharacter(manager.getTurn());
    Character c2 = manager.getCharacter(manager.getOppositeTurn());
    if ( c1.getCredits()>this.creditsRequired){
      if(Math.abs(c1.getX() - c2.getX()) < 4 && Math.abs(c1.getY() - c2.getY()) < 4 ){
        c2.takeDamage(this.damage, this.dodgerate);
        return true;
    }else{
      return false;
    }
  }else{
    return false;
  }
  }

  @Override
  public void paint(GameWindow gw, int x, int y) {
    gw.getWorld()[x][y].paintZone();
  }

}
