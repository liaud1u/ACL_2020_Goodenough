package model;

import fxengine.Cmd;
import fxengine.GameController;
import javafx.scene.input.KeyEvent;


/**
 * @author Horatiu Cirstea, Vincent Thomas
 *
 * controleur de type KeyListener
 * 
 */
public class PacmanController implements GameController {

	/**
	 * commande en cours
	 */
	private Cmd commandeEnCours;
	
	/**
	 * construction du controleur par defaut le controleur n'a pas de commande
	 */
	public PacmanController() {
		this.commandeEnCours = Cmd.IDLE;
	}

	/**
	 * quand on demande les commandes, le controleur retourne la commande en
	 * cours
	 * 
	 * @return commande faite par le joueur
	 */
	public Cmd getCommand() {
		return this.commandeEnCours;
	}


	/**
	 * met a jour les commandes en fonctions des touches appuyees
	 */
	public void keyPressed(KeyEvent e) {

		switch (e.getCode()) {
			case LEFT:
				this.commandeEnCours = Cmd.LEFT;
				break;
			case RIGHT:
				this.commandeEnCours = Cmd.RIGHT;
				break;
			case UP:
				this.commandeEnCours = Cmd.UP;
				break;
			case DOWN:
				this.commandeEnCours = Cmd.DOWN;
				break;
		}
	}

	/**
	 * met a jour les commandes quand le joueur relache une touche
	 */
	public void keyReleased(KeyEvent e) {
		this.commandeEnCours = Cmd.IDLE;
	}

}
