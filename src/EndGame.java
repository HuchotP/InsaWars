import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EndGame extends JFrame {

  private int status = -1; // -1 : ne pas recommencer / 0 : recommencer avec les mêmes personnages / 1 : créer de nouveaux personnages
  private JButton sameCharacter; // bouton pour recommencer avec les mêmes personnages
  private JButton differentCharacter; // bouton pour créer de nouveaux personnages
  private JButton stop; // bouton pour arrêter de jouer

  public EndGame(int n) {

    // Mise en place de la fenêtre (titre, dimensions et évènement "bouton croix appuyé")

    this.setTitle("Fin du match");
    this.setSize(300,150);
    this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE); // formel, n'influe pas sur le déroulement du programme et n'est pas utilisé

    // Création du panneau contenant tous les éléments

    JPanel pan = new JPanel();

    // Création du label annonçant le gagnant

    JLabel whowon = new JLabel("Le Joueur " + n + " a gagn\u00e9 la partie"); // n valant 1 ou 2, selon le gagnant déduit par la fonction Manager.isFinished()
    whowon.setAlignmentX(JComponent.CENTER_ALIGNMENT); // centrage du label

    // Création des différents boutons

    sameCharacter = new JButton("Rejouer avec les m\u00eames personnages");
    differentCharacter = new JButton("Jouer avec des personnages diff\u00e9rents");
    stop = new JButton("Arr\u00eater de jouer");

    // Insertion des éléments dans le panneau (ordre d'ajout = ordre vertical d'apparition)

    pan.add(whowon);
    pan.add(sameCharacter);
    pan.add(differentCharacter);
    pan.add(stop);

    // Ajout du panneau à la fenêtre et activation

    this.add(pan);
    this.setVisible(true);

    // Création des listeners pour les boutons

    sameCharacter.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {

        status = 0; // on veut rejouer avec les mêmes personnages
        setVisible(false); // on cache la fenêtre qui sera recréée à la fin de la prochaine partie

      }


    });

    differentCharacter.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {

        status = 1; // on veut créer de nouveaux personnages
        setVisible(false); // on cache la fenêtre qui sera recréée à la fin de la prochaine partie

      }


    });

    stop.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {

        status = -1; // on ne veut pas rejouer
        setVisible(false); // on cache la fenêtre et main() clôt le programme

      }

    });


  }

  /*
  * @return : statut de la fenêtre expliqué au début du programme
  */

  public int getStatus(){

    return this.status;

  }

}
