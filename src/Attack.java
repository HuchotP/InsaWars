

public abstract class Attack{ //classe abstraite utilisée pour rassembler toutes les attaques dans un seul tableau

  // les attributs communs à toutes les attaques
  protected String name;
  protected double damage;
  protected double dodgerate; //Nombre qui représente la capacité de l'attaque à être évitée, plus le nombre est élevé moins l'attaque est facilement évitable
  protected int creditsRequired; //nombre de crédits requis pour cette attaque

  public String getName(){

    return this.name;

  }

  //On définit les méthodes abstraites communes
  
  public abstract boolean attack(); //On définit les méthodes abstraites communes

  public abstract void paint(GameWindow gw, int x, int y);

  public int getCreditsRequired(){

    return this.creditsRequired;

  }


  public double getDamage() {

    return this.damage;

}

}
