import java.lang.Math;

public class Character{

  private int credits;
  private int maxcredits = 7; //test
  private double life;
  private double maxlife;
  private int strength;
  private int intel;
  private int speed;
  private int luck;
  private String name;
  private Attack[] attacks = new Attack[4];

  private int x;
  private int y;

  private boolean hasAttacked = false;
  private boolean canAttack = true;
  private boolean hasMoved = false;

  private static GameManager manager;

  public Character(double life, int strength, int intel, int speed, int luck, String name){

    this.maxcredits= 7;
    this.credits = this.maxcredits;
    this.maxlife = life;
    this.life = this.maxlife;
    System.out.println(name + " " + life + " " + maxlife);
    this.strength = strength;
    this.intel = intel;
    this.speed = speed;
    this.luck = luck;
    this.name = name;

    attacks[0] = new ZoneAttack(this);
    attacks[1] = new FireBall(this);
    attacks[2] = new LocatedAttack(this);
    attacks[3] = new Heal(this);

    manager = new GameManager().getManager();
  }

  /**
  * Returns value of life
  * @return
  */
  public double getLife() {
    return life;
  }

  public int getCredits(){
    return credits;
  }

  public double getMaxLife() {
    return maxlife;
  }

  public int getMaxCredits(){
    return maxcredits;
  }


  /**
  * Returns value of strength
  * @return
  */
  public int getStrength() {
    return strength;
  }


  /**
  * Returns value of intel
  * @return
  */
  public int getIntel() {
    return intel;
  }


  /**
  * Returns value of speed
  * @return
  */
  public int getSpeed() {
    return speed;
  }


  /**
  * Returns value of luck
  * @return
  */
  public int getLuck() {
    return luck;
  }


  /**
  * Returns value of name
  * @return
  */
  public String getName() {
    return name;
  }

  public int getX(){

    return this.x;

  }

  public void setX(int n){

    this.x = n;

  }

  public int getY(){

    return this.y;

  }

  public void setY(int n){

    this.y = n;

  }

  public Attack getAttack(int n) {
    return this.attacks[n];
  }


  public void takeDamage(double damage, double dodgerate){
    if((int)(Math.random()*100) > this.luck*dodgerate){
      this.life= this.life - damage;
      System.out.println("Dommages pris");
    }

  }

  /*public void attack(int n){

  attacks[n].attack();
}*/


public boolean attack(int n){

  if(this.canAttack == true){
    if(attacks[n].attack() == true) {
      this.hasAttacked = true;
      System.out.println(hasAttacked);
      this.credits -= attacks[n].getCreditsRequired();
      System.out.println(attacks[n].getCreditsRequired());
      return true;

    }
  }
  return false;
}



public void healCharacter( double healing ){
  this.life= this.life+ healing;

}

public void resetCredits(){

  this.credits = this.maxcredits;
  this.hasMoved = false;
  this.hasAttacked = false;
  this.canAttack = true;

}
public boolean move(int newX, int newY){


  System.out.println(hasMoved);
  Character ennemy = manager.getCharacter(manager.getOppositeTurn());

  if ( Math.abs(this.getX() - newX) + Math.abs((this.getY()- newY) ) < 20){

    if( this.credits >= Math.abs(this.getX() - newX) + Math.abs((this.getY()- newY) ) && !(newX== ennemy.getX() && newY== ennemy.getY()) && this.hasMoved == false ){
      manager.changeWorld(newX, newY);
      this.credits -= (Math.abs(this.getX() - newX) + Math.abs((this.getY()- newY)));
      this.setX(newX);
      this.setY(newY);

      GameWindow gw = manager.getGw();
      gw.getActive().updateCredits();

      this.hasMoved = true;
      if(this.hasAttacked == true) {
        this.canAttack = false;
        return true;
      } else {

          return true; // always true for testing but will be false later

        }
      }else {

      return false; // always true for testing but will be false later
  }
}
return false;
}

public void checkCredits() {
  if(this.credits == 0) {
    manager.nextTurn();
  }
}

public void resetLife(){

  this.life = this.maxlife;

}

}
