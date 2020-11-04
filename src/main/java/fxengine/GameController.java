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
	 * quand on demande les commandes, le controleur retourne la commande en
	 * cours
	 *
	 * @return commande faite par le joueur
	 */
	Cmd getCommand();

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
