import java.awt.*;
import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EndGame extends JFrame{

  GameManager manager = new GameManager().getManager();
  private int status = -1;

  public EndGame(int n){

      this.setSize(300,300);
      Box box = Box.createVerticalBox();
      JPanel pan = new JPanel();
      pan.setLayout(new GridLayout(1,3));

      JLabel whowon = new JLabel("Le Joueur " + n + " a gagné la partie");
      JButton sameCharacter = new JButton("Rejouer avec les mêmes personnages");
      JButton differentCharacter = new JButton("Jouer avec des personnages différents");

      pan.add(whowon);
      pan.add(sameCharacter);
      pan.add(differentCharacter);
      this.add(pan);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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


  }

  public int getStatus(){

    return this.status;

  }

}
