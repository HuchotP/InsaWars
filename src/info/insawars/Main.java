package info.insawars;

public class Main {

  public static void main(String[] args) {
    CharacterWindow window1 = new CharacterWindow("Joueur 1");
    int i = 0;
    do {
    	try {
        	Thread.sleep(1);
    	} catch(InterruptedException e) {
    		
    	}
    } while(window1.isVisible());
    
    Character ch1 = new Character(window1.getLife(), window1.getStrength(), window1.getIntel(), window1.getSpeed(), window1.getLuck(), window1.getName());
    
    CharacterWindow window2 = new CharacterWindow("Joueur 2");
    
    do {
    	try {
        	Thread.sleep(1);
    	} catch(InterruptedException e) {
    		
    	}
    } while(window1.isVisible());
    
    System.out.println(window2.getName());
    
    Character ch2 = new Character(window2.getLife(), window2.getStrength(), window2.getIntel(), window2.getSpeed(), window2.getLuck(), window2.getName());
    

//test
  }

}
