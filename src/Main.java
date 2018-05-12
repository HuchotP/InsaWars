public class Main {

	public static void main(String[] args) {

		int status = 0;

		GameManager manager = new GameManager().getManager();


		Character ch1 = createCharacter(1);
		//Character ch1 = new Character(300,10,10,10,10, "Joueur 1");

		System.out.println(ch1.getName());

		Character ch2 = createCharacter(2);
		//Character ch2 = new Character(1,10,10,10,10, "Joueur 2");

		manager.setCh1(ch1);
		manager.setCh2(ch2);

		System.out.println(manager.getCharacter(1).getName());


		GameWindow game = new GameWindow();

		manager.setGw(game);

		do {

			do {
				try {

					Thread.sleep(1);

				} catch(InterruptedException e) {

				}
			} while(game.isVisible());

			EndGame test = new EndGame(manager.isFinished());

			do {
				try {
					//System.out.println("E");
					Thread.sleep(1);
				} catch(InterruptedException e) {

				}
			} while(test.isVisible());
			status = test.getStatus();


			manager.newGame(test.getStatus());

		} while(status == 0 || status == 1);

		System.exit(1); // fin du programme (les joueurs ne rejouent pas)

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
