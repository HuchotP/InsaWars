public class Character{

    private int credits;
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

      this.credits= 10 +2*this.speed;
      this.life = life;
      this.strength = strength;
      this.intel = intel;
      this.speed = speed;
      this.luck = luck;
      this.name = name;

      attacks[0] = new ZoneAttack(this);
      attacks[1] = new FireBallAttack(this);
      attacks[2] = new LocatedAttack(this);
      attacks[3] = new Heal(this);


    }

  	/**
  	* Returns value of life
  	* @return
  	*/
  	public int getLife() {
  		return life;
  	}

    public int getCredits(){
      return credits;
    }

    public void modifyCredits(int value){
      this.credits= this.credits- value;
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



    public void takeDamage(int damage, int dodgerate){
        if(luck * dodgerate< 150){
          this.life= this.life- damage;
        }
      }

    /*public void attack(int n){

      attacks[n].attack();
    }*/
    public void takeDamge(int damage, int dodgerate){
        if(luck * dodgerate)

    }
    public void attack(int n){

      attacks[n].attack(this, manager.getCharacter(/**joueur adverse**/));

    }



    public int healCharacter( int healing ){
      this.life= this.life+ healing;
      return this.life;
    }

}
