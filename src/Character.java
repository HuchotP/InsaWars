

public class Character{

    private int life;
    private int strength;
    private int intel;
    private int speed;
    private int luck;
    private String name;
    private Attack[] attacks = new Attack[4];


    public Character(int life, int strength, int intel, int speed, int luck, String name){

      this.life = life;
      this.strength = strength;
      this.intel = intel;
      this.speed = speed;
      this.luck = luck;
      this.name = name;

      attacks[0] = new ZoneAttack();
      System.out.println(attacks[0].getName());
      attacks[0].name = "lol2";
      System.out.println(attacks[0].getName());

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



}
