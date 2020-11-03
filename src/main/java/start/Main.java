package start;

import fxengine.GameApplication;

/**
 * lancement du moteur avec le jeu
 */
public class Main {

	public static void main(String[] args) throws InterruptedException {
		// classe qui lance le moteur de jeu generique
		GameApplication engine = new GameApplication();

		engine.run();



	}

}
