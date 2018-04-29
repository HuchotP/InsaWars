package info.insawars;

public class Character{

    private int life;
    private int strength;
    private int intel;
    private int speed;
    private int luck;
    private String name;
    private Attack[] attacks = new Attack[4];

    private int x;
    private int y;

    private static GameManager manager;

    public Character(int life, int strength, int intel, int speed, int luck, String name){

      this.life = life;
      this.strength = strength;
      this.intel = intel;
      this.speed = speed;
      this.luck = luck;
      this.name = name;

      attacks[0] = new ZoneAttack();
      System.out.println(attacks[0].getName());

 //test
    }

  	/**
  	* Returns value of life
  	* @return
  	*/
  	public int getLife() {
  		return life;
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

    public void attack(int n){

      attacks[n].attack(this, manager.getCharacter(/**joueur adverse**/);


    }
    public void takeDamge(int damage, int dodgerate){

<<<<<<< HEAD:src/Character.java
        if(luck * dodgerate)
=======
    public void attack(int n){

      attacks[n].attack(this, manager.getCharacter(/**joueur adverse**/));
>>>>>>> 30355eb163017e36eb374bf756787f904cab94a1:src/info/insawars/Character.java


    }

    public void getDamage() {

    }

}
