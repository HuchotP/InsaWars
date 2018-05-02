import java.lang.Math;

public class FireBallAttack extends Attack{

  GameManager manager = new GameManager.getManager();

  public FireBallAttack (){
    this.name= " Boule de feu ";

  }

  public void attack(){

    Character c1= manager.getPlayer(manager.getTurn());
    Character c2= manager.getPlayer(manager.getOppositeTurn());

    if( ( c1.getX()= c2.getX() && Math.abs(c1.getX()- c2.getX())<7)|| (c1.getY()= c2.getY() && Math.abs( c1.getY()- c2.getY())<7 )){
      c2.takeDamge();
    }
  }
}
