
import java.lang.Math;

public class Move{

  private int newX;
  private int newY;
  private int length;

  private static GameManager manager;

  public Move( int changeX, int changeY){
      this.newX= changeX;
      this.newY= changeY;
      this.length= Math.sqrt(Math.pow(changeX, 2)+ Math.pow(changeY, 2));
  }
}
