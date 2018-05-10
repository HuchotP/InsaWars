

import java.lang.Math;

public class Heal extends Attack{

  GameManager manager = new GameManager().getManager();

  public Heal(Character c){

    this.name= "Soins";
    this.damage= 2*c.getIntel();
    this.creditsRequired=4;
    this.dodgerate=0;

  }

  @Override
  public boolean attack(){
      Character c1 = manager.getCharacter(manager.getTurn());
      if ( c1.getCredits()>this.creditsRequired && c1.getLife() != c1.getMaxLife()){

        if(this.damage >= c1.getMaxLife() - c1.getLife()) {
          c1.healCharacter(c1.getMaxLife() - c1.getLife());
          return true;
        }

        c1.healCharacter(this.damage);
        return true;
      } else{
        return false;
      }
    }

  @Override
  public void paint(GameWindow gw, int x, int y) {
    gw.getWorld()[x][y].paintHeal();
  }
}
