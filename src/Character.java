
// import la bibliothèque lang
import java.lang.Math;

public class Character{

  private int credits; // Chaque personnage a un nombre de crédit
  private int maxcredits = 7; // Le personnage possède initialement 7 crédits à chaque nouveau tour
  private double life;// Chaque personnage a un nombre de points de vie
  private double maxlife;// Le personnage possède le maximum de sa vie au début du jeu. Cette valeur ne peut être dépassée même avec des attaques de soin
  private int strength; // Le personnage a un attribut Force de valeur personnalisable sur la fenêtre de création de personnage
  private int intel; // Le personnage a un attribut Intelligence de valeur personnalisable sur la fenêtre de création de personnage
  private int speed; // Le personnage a un attribut Vitesse de valeur personnalisable sur la fenêtre de création de personnage qui influera sur le nombre de crédits disponibles à chaque tours
  private int luck; // Le personnage a un attribut Chance de valeur personnalisable sur la fenêtre de création de personnage
  private String name;  // Le personnage a un nom personnalisable sur la fenêtre de création de personnage
  private Attack[] attacks = new Attack[4];  // Le personnage possède une liste de 4 attaques à sa disposition, rangées dans un tableau d'objets

  private int x; // La coordonée d'abscisse de la position du personnage
  private int y; // La coordonée d'ordonnée de la position du personnage

  private boolean hasAttacked = false;  // Au commencement du jeu et à chaque nouveau tour, le personnage n'a pas encore attaqué
  private boolean canAttack = true; //Au commencement du jeu et à chaque nouveau tour, le personnage a la possibilité d'attaquer
  private boolean hasMoved = false; //Au commencement du jeu et à chaque nouveau tour, le personnage n'a pas encore bougé

  private static GameManager manager;

// Constructeur
  public Character(double life, int strength, int intel, int speed, int luck, String name){

    this.maxcredits= 7+ (int) 0.3*speed; // La valeur des crédits disponibles est fixée selon une valeur standard et peut augmenter selon l'attribut Vitesse du personnage
    this.maxcredits= 7 + (int) 0.3*speed;
    this.credits = this.maxcredits;
    this.maxlife = life;
    this.life = this.maxlife;
    System.out.println(name + " " + life + " " + maxlife);
    this.strength = strength;
    this.intel = intel;
    this.speed = speed;
    this.luck = luck;
    this.name = name;

    attacks[0] = new ZoneAttack(this); // Le joueur peut faire une attaque de zone
    attacks[1] = new FireBall(this); // Le joueur peut lancer une boule de feu
    attacks[2] = new LocatedAttack(this); // Le joueur peut faire une attaque de mélée
    attacks[3] = new Heal(this); // Le joueur peut se soigner

    // Definition d'un nouveau GameManager
    manager = new GameManager().getManager();
  }

  /**
  * Returns value of life
  * @return
  */
  public double getLife() {
    return life;
  }

  /**
  * Returns value of Credits
  * @return
  */
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

  /**
  * Returns value of Abscisse position
  * @return
  */
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


// Méthode permettant de sélectionner une attaque dans le tableau d'attaques
  public Attack getAttack(int n) {
    return this.attacks[n];
  }

// Methode retirant un nombre de points de vie correspondant à des points de dégats pris en entrée
  public void takeDamage(double damage, double dodgerate){
    if((int)(Math.random()*100) > this.luck*dodgerate){ // L'attaque n'a lieu que si la chance du personnage attaqué est inférieure à celle nécessaire pour l'esquiver
    if((Math.random()*100) > this.luck*dodgerate){
      this.life= this.life - damage;
      System.out.println("Dommages pris");
    }

  }


// Methode permettant au joueur d'attaquer
public boolean attack(int n){

  if(this.canAttack == true){ // Verrifie que le joueur peut attaquer ( si il n'a pas déjà attaqué puis s'est déplacé dans le même tour)
    if(attacks[n].attack() == true) {
      this.hasAttacked = true; // Considère que le joueur a effectué une action de type attaque
      System.out.println(hasAttacked);
      this.credits -= attacks[n].getCreditsRequired(); // Retire au joueur le nombre de crédits utilisés pour effectuer l'attaque

      if(manager.getCharacter(manager.getOppositeTurn()).getLife() <= 0) { // Verrifie que l'adversaire est toujours en vie pour continuer. Sinon le jeu prend fin
        manager.nextTurn();
      }

      return true;

    }
  }
  return false;
}


// Méthode permettant de soigner le joueur d'un nombre de points de vie pris en entrée
public void healCharacter( double healing ){
  this.life= this.life+ healing;

}

// Méthode permettant de réinitialiser les différents paramètres d'action du personnage
public void resetCredits(){

  this.credits = this.maxcredits; // Le nombre de crédit maximal par tour est fourni au joueur
  this.hasMoved = false; // On considère que le joueur n'a pas effectué d'action de type déplacement au démarrage du nouveau tour
  this.hasAttacked = false;// On considère que le joueur n'a pas effectué d'action de type attaque au démarrage du nouveau tour
  this.canAttack = true;

}

// Méthode permettant au joueur de se déplacer vers la nouvelle case désignée par ses coordonnées newX et newY
public boolean move(int newX, int newY){


  System.out.println(hasMoved);
  Character ennemy = manager.getCharacter(manager.getOppositeTurn()); // On désigne l'adversaire au travers du Game Manager

  if ( Math.abs(this.getX() - newX) + Math.abs((this.getY()- newY) ) < this.credits ){  // On verrifie que le joueur a suffisemment de crédits pour se déplacer jusqu

    if( this.credits >= Math.abs(this.getX() - newX) + Math.abs((this.getY()- newY) ) && !(newX== ennemy.getX() && newY== ennemy.getY()) && this.hasMoved == false ){ // Verrifie que l'adversaire ne se situe pas sur la nouvlle case
      manager.changeWorld(newX, newY);
      this.credits -= (Math.abs(this.getX() - newX) + Math.abs((this.getY()- newY))); // Retire au joueur le nombre de crédits nécessaires pour le déplacement
      this.setX(newX);
      this.setY(newY);

      GameWindow gw = manager.getGw();
      gw.getActive().updateCredits();

      this.hasMoved = true;  // On considère que le joueur a effectué une action de type déplacement pendant ce tour
      if(this.hasAttacked == true) {
        this.canAttack = false; // Si le joueur a bougé puis attaqué, il ne peut plus bouger.
      }
      return true;
    }else {

          return false; // Le joueur ne s'est pas déplacé, l'adversaire se situait sur la nouvelle case

        }
      }else {

      return false; // Le joueur ne s'est pas déplacé, le nombre de crédit nécessaires n'était pas suffisant
  }

}


// Méthode permettant de finir automatiquement le tour du joueur si ce dernier n'a plus de crédits
public void checkCredits() {
  if(this.credits == 0) {
    manager.nextTurn();
  }
}


// Méthode permettant d'attribuer la valeur maximale de points de vie au joueur
public void resetLife(){

  this.life = this.maxlife;

}

}
