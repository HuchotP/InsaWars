import java.lang.Math;

public class ZoneAttack extends Attack{

  public ZoneAttack(){

    this.name = "Attaque de zone";

  }

  public void attack(Character c1, Character c2){

    if(Math.abs(c1.getX() - c2.getX()) < 4 && Math.abs(c1.getY() - c2.getY()) < 4 ){

      c2.takeDamge();

    }

  }

}
