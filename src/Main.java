public class Main {

	public static void main(String[] args) {

		GameManager manager = new GameManager().getManager();

		//Character ch1 = createCharacter(1);
		Character ch1 = new Character(300,10,10,10,10, "Joueur 1");

		System.out.println(ch1.getName());

		//Character ch2 = createCharacter(2);
		Character ch2 = new Character(1,10,10,10,10, "Joueur 2");

		manager.setCh1(ch1);
		manager.setCh2(ch2);

		System.out.println(manager.getCharacter(1).getName());


		GameWindow game = new GameWindow();

		manager.setGw(game);

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
