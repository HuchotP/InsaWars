

public abstract class Attack{

  protected String name;
  protected int type;
  protected int damage;
  protected int dodgerate;
  protected int creditsRequired;

  public String getName(){

    return this.name;

  }

  public abstract void attack();



  public int getCreditsRequired(){

    return this.creditsRequired;

  }


  public int getDamage() {

    return this.damage;

}
}
