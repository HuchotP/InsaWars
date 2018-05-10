public class GameManager {

  private Character[] chara = new Character[2];

  private GameWindow gw;

  private int[][] world = new int[10][10]; /** the world is represented by a 2D array of int, 0 = no player, 1 = player 1, 2 = player 2. Position is calculated in the same way of graphical interface**/

  private int turn = 0; //0 = player 1, 1 = player 2

  public static GameManager manager;

  private boolean gameFinished = false;

  public GameManager(){

    if(manager == null){

      chara[0] = null;
      chara[1] = null;
      gw = null;

      manager = this;
    }


  }

  public void setCh1(Character ch1) {
    this.chara[0] = ch1;

    chara[0].setX(5);
    chara[0].setY(1);

  /*for(int i = 0 ; i < world.length ; i++) {
    switch(world1[x][i]){
      case 0:
        world[x][i].setIcon(blueCase);
        break;
      case 1:
        world[x][i].setIcon(player1);
        break;
      case 2:
        world[x][i].setIcon(player2);
        break;

    }

  }*/

  }

  public void setCh2(Character ch2) {
    this.chara[1] = ch2;

    chara[1].setX(5);
    chara[1].setY(8);

  }

  public void setGw(GameWindow gw) {
    this.gw = gw;
  }

  public Character getCharacter(int n){

    return chara[n];

  }
  public GameManager getManager(){

    return manager;

  }



  public void nextTurn(){
    turn = (turn+1)%2;
    System.out.println(turn);
    chara[0].resetCredits();
    chara[1].resetCredits();
    this.gw.switchInterface();

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

    int x1 = getCharacter(0).getX();
    int y1 = getCharacter(0).getY();
    int x2 = getCharacter(1).getX();
    int y2 = getCharacter(1).getY();

    for(int i = 0; i < world.length; i++) {

      for(int j = 0; j < world[0].length; j++){

        if(i == x1 && j == y1) {
          world[i][j] = 1;
        }
        else if(i == x2 && j == y2) {
          world[i][j] = 2;
        }
        else {
          world[i][j] = 0;
        }

      }
    }

    return this.world;

  }

  public void changeWorld(int x, int y){

    int cx = getCharacter(getTurn()).getX();
    int cy = getCharacter(getTurn()).getY();
    this.world[x][y] = getTurn()+1;
    this.world[cx][cy] = 0;

  }

}
