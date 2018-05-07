public class Main {

	public static void main(String[] args) {

		GameManager manager = new GameManager();

		Character ch1 = createCharacter(1);

		System.out.println(ch1.getName());

		Character ch2 = createCharacter(2);

		manager.setCh1(ch1);
		manager.setCh2(ch2);

		System.out.println(manager.getCharacter(0).getName());


		GameWindow game = new GameWindow();

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
