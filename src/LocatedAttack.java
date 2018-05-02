import java.lang.Math;

public class LocatedAttack extends Attack{

  GameManager manager = new GameManager().getManager();

  public LocatedAttack(){
    this.name= " Attaque ciblée";

  }

  public void attack(){

    Character c1= manager.getPlayer(manager.getTurn());
    Character c2= manager.getPlayer( manager.getOppositeTurn());
    if ( Maths.abs(c1.getX()- c2.getX())<1 || Maths.abs(c1.getY()- c2.getY())<1){
      c2.takeDamage();


    }

  }

}
