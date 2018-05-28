import java.awt.*;
import javax.swing.*;
import java.awt.Color;
import java.awt.event.*;

// INTERFACE DE JEU

public class GameWindow extends JFrame{

  private GameManager manager = new GameManager().getManager(); // on récupère le GameManager pour avoir accès à certaines variables du jeu

  private Case[][] world;
  private int currentX;
  private int currentY;
  private int mouseX;
  private int mouseY;

  // JPanels qui contiendront les boutons de jeu et la barre de vie

  private JPanel card1;
  private JPanel card2;

  // Interfaces "actives" contenant la barre de vie et les boutons de jeu

  private ActiveInterface active1;
  private ActiveInterface active2;

  // Interfaces "passives" ne montrant que la barre de vie

  private PassiveInterface passive1;
  private PassiveInterface passive2;

  // La méthode retourne le tableau de cases modélisant le plateau

  public Case[][] getWorld() {
    return world;
  }

  public GameWindow(){

    this.setSize(640,900); // définition de la taille

    world = new Case[10][10]; // création du tableau de cases

    Box globalBox = Box.createVerticalBox(); // création de la boite globale qui contiendra tous les éléments

    // Création du conteneur qui acceuillera la représentation du tableau

    JPanel worldContainer = new JPanel();
    GridLayout worldGrid = new GridLayout(10,10); // on utilise le GridLayout qui est le plus pratique ici
    worldContainer.setLayout(worldGrid);

    // Création individuelle de chaque case et ajout au conteneur

    for(int i = 0; i < world.length; i++){

      for(int k = 0; k < world[0].length; k++){

        world[i][k] = new Case(this, i, k);

        worldContainer.add(world[i][k]);

      }

    }

    // Mise à jour initiale du plateau (la méthode rePaintWorld est définie pour une case et aurait pu être appellée par n'importe laquelle)

    world[0][0].rePaintWorld();

    // Définition de la taille absolue du plateau et ajout au conteneur global

    worldContainer.setMinimumSize(new Dimension(550,550));
    worldContainer.setMaximumSize(new Dimension(550,550));
    globalBox.add(worldContainer);

    // Création du panneau qui contiendra l'interface de jeu

    JPanel charaUI = new JPanel();
    charaUI.setMinimumSize(new Dimension(600,300));
    charaUI.setMaximumSize(new Dimension(600,300));

    GridLayout charaGrid = new GridLayout(1,2); // définition d'un GridLayout pour séparer l'interface joueur 1 / joueur 2
    charaUI.setLayout(charaGrid);

    card1 = new JPanel(new CardLayout()); // Conteneur de l'interface du joueur 1
    card2 = new JPanel(new CardLayout()); // Conteneur de l'interface du joueur 2
    // Le CardLayout permet d'empiler des conteneurs à la manière d'un paquet de cartes et de changer le conteneur affiché très facilement.

    // Création des interfaces actives

    active1 = new ActiveInterface(manager.getCharacter(0), this);
    active2 = new ActiveInterface(manager.getCharacter(1), this);

    // Création des interfaces passives

    passive1 = new PassiveInterface(manager.getCharacter(0), this);
    passive2 = new PassiveInterface(manager.getCharacter(1), this);

    // Ajout des interfaces à leurs CardLayouts respectifs (les strings servent d'identifiants)

    card1.add(active1, "ACTIVE");
    card1.add(passive1, "PASSIVE");

    card2.add(active2, "ACTIVE");
    card2.add(passive2, "PASSIVE");

    // Initialisation de l'interface de jeu : c'est toujours J1 qui attaque en premier

    CardLayout cl1 = (CardLayout)(card1.getLayout());
    CardLayout cl2 = (CardLayout)(card2.getLayout());

    cl1.show(card1, "ACTIVE");
    cl2.show(card2, "PASSIVE");

    // Ajout des cartes à l'interface de contrôle

    charaUI.add(card1);
    charaUI.add(card2);

    // Ajout de l'interface de contrôle au conteneur global

    globalBox.add(charaUI);

    // Ajout du conteneur global à la fenêtre

    this.add(globalBox);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);

  }


  // Retourne l'interface active actuelle

  public ActiveInterface getActive() {
    if(manager.getTurn() == 0) {
      return active1;
    }

    return active2;
  }

  // Met à jour les barres de vie après une attaque

  public void updateLife() {
    active1.updateLife();
    active2.updateLife();

    passive1.updateLife();
    passive2.updateLife();

  }

  // Echange les interfaces à chaque nouveau tour

  public void switchInterface() {

    CardLayout cl1 = (CardLayout)(card1.getLayout());
    CardLayout cl2 = (CardLayout)(card2.getLayout());

    cl1.next(card1);
    cl2.next(card2);

    getActive().updateCredits();

  }

}
