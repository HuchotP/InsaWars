package info.insawars;

public class Main {

	public static void main(String[] args) {

		Character ch1 = createCharacter(1);
		
		System.out.println(ch1.getName());

		Character ch2 = createCharacter(2);

		//test
	}

	public static Character createCharacter(int n) {


		CharacterWindow window = new CharacterWindow("Joueur " + n);
		int i = 0;
		do {
			try {
				Thread.sleep(1);
			} catch(InterruptedException e) {

			}
		} while(window.isVisible());

		return window.getCharacter();

	}

}