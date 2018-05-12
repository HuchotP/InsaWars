import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EndGame extends JFrame {

  //GameManager manager = new GameManager().getManager();
  private int status = -1;
  private JButton sameCharacter;
  private JButton differentCharacter;
  private JButton stop;

  public EndGame(int n) {

    this.setTitle("Fin du match");
    this.setSize(300,150);
    Box box = Box.createVerticalBox();
    JPanel pan = new JPanel();
    //pan.setLayout(box);

    JLabel whowon = new JLabel("Le Joueur " + n + " a gagné la partie");
    whowon.setAlignmentX(JComponent.CENTER_ALIGNMENT);
    sameCharacter = new JButton("Rejouer avec les mêmes personnages");
    differentCharacter = new JButton("Jouer avec des personnages différents");
    stop = new JButton("Arrêter de jouer");

    pan.add(whowon);
    pan.add(sameCharacter);
    pan.add(differentCharacter);
    pan.add(stop);
    this.add(pan);
    this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    this.setVisible(true);


    sameCharacter.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {

        status = 0;
        setVisible(false);

      }


    });

    differentCharacter.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {

        status = 1;
        setVisible(false);

      }


    });

    stop.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {

        status = -1;
        setVisible(false);

      }

    });


  }

  public int getStatus(){

    return this.status;

  }

}
