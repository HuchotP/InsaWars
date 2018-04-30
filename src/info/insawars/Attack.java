package info.insawars;

public abstract class Attack{

  protected String name;
  protected int type;
  protected int damage;

  public String getName(){

    return this.name;

  }

  public abstract void attack();


}
