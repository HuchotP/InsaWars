package info.insawars;

import java.lang.Math;

public class Heal extends Attack{

  GameManager manager = new GameManager().getManager();

  public Heal(){

    this.name= " Sort de soins";

  }

  public void spell(){
      Character c1 = manager.getPlayer(manager.getTurn());
      c1.life= c1.getlife()+ 20;
  }
