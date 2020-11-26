package fxengine;


import javafx.scene.input.KeyEvent;

/**
 * @author Horatiu Cirstea
 * 
 * controleur qui envoie des commandes au jeu 
 *
 */
public interface GameController {

	/**
	 * when we ask commands, the game controller return the current maain command (deplacement)
	 *
	 * @return Cmd made by the player
	 */
	Cmd getCommand();

	/**
	 * when we ask commands, the game controller return the current secondary command (shoot, ...)
	 *
	 * @return Cmd made by the player (shoot)
	 */
	Cmd getCommandComplementaire();

	/**
	 * Lorsqu'une touche est appuyée
	 *
	 * @param event KeyEvent correspondant
	 */
	void keyPressed(KeyEvent event);

	/**
	 * Lorsqu'une touche est relachée
	 *
	 * @param event KeyEvent correspondant
	 */
	void keyReleased(KeyEvent event);
}
