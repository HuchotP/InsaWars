public class GameManager {

  private Character[] chara = new Character[2];

  private int[][] world = new int[15][15]; /** the world is represented by a 2D array of int, 0 = no player, 1 = player 1, 2 = player 2. Position is calculated in the same way of graphical interface**/

  private int turn = 0; //0 = player 1, 1 = player 2

  public GameManager(Character c1, Character c2){

    chara[0] = c1;
    chara[1] = c2;

    chara[0].setManager(this);
    chara[1].setManager(this);

  }

  public Character getCharacter(int n){

    return chara[n];

  }
  public GameManager getManager(){

    return this;

    }

  public void nextTurn(){

    turn = (turn-1)%2;

  }
  public int getTurn(){

    return this.turn;

  }


}
