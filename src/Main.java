public class Main {

	public static void main(String[] args) {

		int ch1 = createCharacter(1);

		//System.out.println(ch1.getName());

		int ch2 = createCharacter(2);

		//test
	}

	public static int createCharacter(int n) {


		CharacterWindow window = new CharacterWindow("Joueur " + n);
		int i = 0;
		do {
			try {
				Thread.sleep(1);
			} catch(InterruptedException e) {

			}
		} while(window.isVisible());

		return 1;

	}

}
