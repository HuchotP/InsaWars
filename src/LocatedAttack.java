

import java.lang.Math;

public class LocatedAttack extends Attack{

  GameManager manager = new GameManager().getManager();

  public LocatedAttack(Character c){

    this.name= "Mêlée";
    this.damage= 3*c.getStrength();
    this.dodgerate= 1.0/8;
    this.creditsRequired=3;

  }

  @Override
  public boolean attack(){

    Character c1= manager.getCharacter(manager.getTurn());
    Character c2= manager.getCharacter(manager.getOppositeTurn());
    if ( c1.getCredits()>this.creditsRequired){
      if (( Math.abs(c1.getX()- c2.getX())==1 || Math.abs(c1.getY()- c2.getY())==1) && Math.abs(c1.getX()- c2.getX()) !=  Math.abs(c1.getY()- c2.getY())){
        c2.takeDamage(this.damage, this.dodgerate);
        return true;
      } else{
        return false;
      }
    }else{
      return false;
    }

  }

  @Override
  public void paint(GameWindow gw, int x, int y) {
    gw.getWorld()[x][y].paintLocated();
  }

}
