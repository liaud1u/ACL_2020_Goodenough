package start;

import fxengine.GameApplication;
import model.PacmanGame;

/**
 * lancement du moteur avec le jeu
 */
public class Main {

	public static void main(String[] args) throws InterruptedException {

		// creation du jeu particulier et de son afficheur
		PacmanGame game = new PacmanGame("helpFilePacman.txt");

		// classe qui lance le moteur de jeu generique
		GameApplication engine = new GameApplication();

		System.out.println("run");
		engine.run();
	}

}
