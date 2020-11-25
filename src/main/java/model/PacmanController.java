package model;

import fxengine.Cmd;
import fxengine.GameController;
import javafx.scene.input.KeyEvent;
import model.player.PlayerType;


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
	private Cmd commandComplementaire;
	private PlayerType type;

	/**
	 * construction du controleur par defaut le controleur n'a pas de commande
	 */
	public PacmanController(PlayerType type) {
		this.commandeEnCours = Cmd.IDLE;
		this.commandComplementaire = Cmd.IDLE;
		this.type = type;
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

	public Cmd getCommandComplementaire() {
		return this.commandComplementaire;
	}

	public PlayerType getType() {
		return type;
	}

	/**
	 * met a jour les commandes en fonctions des touches appuyees
	 */
	public void keyPressed(KeyEvent e) {

		switch (e.getCode()) {
			case Q:
			case LEFT :
				this.commandeEnCours = Cmd.LEFT;
				break;
			case D:
			case RIGHT:
				this.commandeEnCours = Cmd.RIGHT;
				break;
			case UP:
			case Z:
				this.commandeEnCours = Cmd.UP;
				break;
			case DOWN:
			case S:
				this.commandeEnCours = Cmd.DOWN;
				break;
			case A:
			case SPACE:
				this.commandComplementaire = Cmd.SHOOT;
				break;
		}
	}

	/**
	 * met a jour les commandes quand le joueur relache une touche
	 */
	public void keyReleased(KeyEvent e) {
		this.commandeEnCours = Cmd.IDLE;
		this.commandComplementaire = Cmd.IDLE;
	}

}
