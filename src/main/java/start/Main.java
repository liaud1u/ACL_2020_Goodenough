package start;

import fxengine.GameApplication;
import model.util.Util;

/**
 * lancement du moteur avec le jeu
 */
public class Main {
	public static void main(String[] args) throws InterruptedException {
		Util.init();

		// classe qui lance le moteur de jeu generique
		GameApplication engine = new GameApplication();
		engine.run();
	}
}
