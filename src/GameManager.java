public class GameManager {

  private Character[] chara = new Character[2];

  private int[][] world = new int[10][10]; /** the world is represented by a 2D array of int, 0 = no player, 1 = player 1, 2 = player 2. Position is calculated in the same way of graphical interface**/

  private int turn = 0; //0 = player 1, 1 = player 2

  public static GameManager manager;

  private boolean gameFinished = false;

  public GameManager(){

    if(manager == null){

      chara[0] = null;
      chara[1] = null;

      manager = this;
    }


  }

  public void setCh1(Character ch1) {
    this.chara[0] = ch1;

    chara[0].setX(0);
    chara[0].setY(0);


  }

  public void setCh2(Character ch2) {
    this.chara[1] = ch2;

    chara[1].setX(0);
    chara[1].setY(0);

  }

  public Character getCharacter(int n){

    return chara[n];

  }
  public GameManager getManager(){

    return manager;

  }



  public void nextTurn(){

    turn = (turn+1)%2;
    chara[0].resetCredits();
    chara[1].resetCredits();

    if(chara[0].getLife() == 0 || chara[1].getLife() == 0){

      this.gameFinished = true;

    }
  }
    public boolean isFinished(){

      return this.gameFinished;

    }


  public int getTurn(){

    return this.turn;

  }

  public int getOppositeTurn(){

    return (turn+1)%2;
  }

  public int[][] getWorld(){

    return this.world;

  }

  public void changeWorld(int x, int y){

    int cx = getCharacter(getTurn()).getX();
    int cy = getCharacter(getTurn()).getY();
    this.world[x][y] = getTurn()+1;
    this.world[cx][cy] = 0;

  }

}
