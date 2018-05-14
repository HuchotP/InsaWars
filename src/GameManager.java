public class GameManager {

  /*
    Tableau des personages.
  */

  private Character[] chara = new Character[2];

  /*
    L'instance GameWindow qui est la fenêtre de notre jeu. On a besoin de l'avoir en référence pour la distribuer à plusieurs objets.
  */

  private GameWindow gw;

  /*
    Le monde est représenté par un tableau 2D d'entiers, avec 1 qui correspond au joueur 1, 2 au joueur 2, et 0 si la case est vide.
  */

  private int[][] world = new int[10][10];

  private int turn = 0;  //0 = player 1, 1 = player 2

  private static GameManager manager;

  /*
    On créé une intance static du GameManager dans un objet GameManager ainsi peut importe ou on instancie un objet GameManager, on peut récupérer une instance commune
    qui sert à stocker toutes les variables utiles au jeu et à son bon déroulement.
  */

  private int gameFinished = -1; // -1 : jeu pas fini, 0 = joueur 1 gagne, 1 = joueur 2 gagne

  /*
    Constructeur du GameManager qui initialise l'instance commune manager.
  */

  public GameManager(){

    if(manager == null){

      chara[0] = null;
      chara[1] = null;
      gw = null;

      manager = this;
    }


  }

  /*
    On référence le joueur 1 dans le GameManager et on définit sa position de départ
  */

  public void setCh1(Character ch1) {
    this.chara[0] = ch1;

    chara[0].setX(5);
    chara[0].setY(1);


  }

 // Même chose pour le joueur 2

  public void setCh2(Character ch2) {
    this.chara[1] = ch2;

    chara[1].setX(5);
    chara[1].setY(8);

  }

  // On référence notre fenêtre de jeu
  public void setGw(GameWindow gw) {
    this.gw = gw;
  }

  public GameWindow getGw() {
    return this.gw;
  }

  public Character getCharacter(int n){

    return chara[n];

  }
  public GameManager getManager(){

    return manager;

  }


  /*
    Cette méthode est appellée à chaque changement de tour.
  */

  public void nextTurn(){
    turn = (turn+1)%2;  //On fait passer le tour de 0 à 1 ou inversement

    chara[0].resetCredits(); // on reset les crédits de tours pour chaque joueur
    chara[1].resetCredits();

    this.gw.switchInterface(); // On change l'interface active pour masquer les boutons du joueur qui ne joue pas et re-afficher ceux au joueur dont c'est le tour

    for(int i =0; i <2; i++){

        if(chara[i].getLife() <= 0){ // On vérifie si un joueur n'a plus de vie ce qui signifie la fin de la partie et le cas écheant on désactive la fenêtre de jeu.

          this.gameFinished = i;
          gw.setVisible(false);

        }

    }
  }

  //méthode appellée quand une nouvelle partie commence
    public void newGame(int n){

      int status = n; // si le statut est de 0 on recommence avec les mêmes personnages, si 1 avec des nouveaux personnages

      if(status == 0){

        chara[0].resetLife();
        chara[1].resetCredits();
        chara[1].resetLife();
        chara[0].resetCredits();

        chara[0].setX(5);
        chara[0].setY(1);

        chara[1].setX(5);
        chara[1].setY(8);

        gw = new GameWindow();
        this.turn = 0;



    }
    if(status == 1){


      setCh1(Main.createCharacter(1)); //on apelle les fenêtres de création de personnage
      setCh2(Main.createCharacter(2));

      chara[0].setX(5);
      chara[0].setY(1);

      chara[1].setX(5);
      chara[1].setY(8);

      gw = new GameWindow(); // on relance la fenêtre de jeu
      this.turn = 0;

    }

    

  }


    public int isFinished(){

      return this.gameFinished;

    }


  public int getTurn(){

    return this.turn;

  }

  public int getOppositeTurn(){ //renvoit l'opposé du tour actuel
    return (turn+1)%2;
  }

  public int[][] getWorld(){ //met à jour le tableau du monde, et renvoit ce tableau

    //on récupère les coordonées des personnages et on modifie le monde en conéquence

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

    //Méthode appellée quand un joueur se déplace, on récupère ses coordonées de début et de fin et on modofie le monde en conséquence

    int cx = getCharacter(getTurn()).getX();
    int cy = getCharacter(getTurn()).getY();
    this.world[x][y] = getTurn()+1;
    this.world[cx][cy] = 0;

  }

}
