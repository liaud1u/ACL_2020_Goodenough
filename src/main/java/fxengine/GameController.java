package fxengine;


import javafx.scene.input.KeyEvent;

/**
 * @author Horatiu Cirstea
 * 
 * controleur qui envoie des commandes au jeu 
 * 
 */
public interface GameController  {

	/**
	 * quand on demande les commandes, le controleur retourne la commande en
	 * cours
	 * 
	 * @return commande faite par le joueur
	 */
    Cmd getCommand();

	void keyPressed(KeyEvent event);

	void keyReleased(KeyEvent event);
}
