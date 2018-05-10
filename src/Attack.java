

public abstract class Attack{

  protected String name;
  protected int type;
  protected double damage;
  protected double dodgerate;
  protected int creditsRequired;

  public String getName(){

    return this.name;

  }

  public abstract boolean attack();

  public abstract void paint(GameWindow gw, int x, int y);

  public int getCreditsRequired(){

    return this.creditsRequired;

  }


  public double getDamage() {

    return this.damage;

}

}
