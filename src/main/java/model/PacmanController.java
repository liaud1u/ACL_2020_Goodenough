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

	/**
	 * Current complementay command (shoot)
	 */
	private Cmd commandComplementaire;

	/**
	 * Type of player (PLAYER1 or PLAYER2)
	 */
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

	/**
	 * Getter of the complementary command (SHOOT)
	 * @return Complementary command
	 */
	public Cmd getCommandComplementaire() {
		return this.commandComplementaire;
	}

	/**
	 * Get the playertype of that controller
	 * @return PlayerType PLAYER1 or PLAYER2
	 */
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
			case E:
			case CONTROL:
				this.commandComplementaire = Cmd.PLACE_WEAPON;
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
