package model;

import fxengine.GamePainter;
import javafx.scene.Group;
import views.PlayerView;

/**
 * @author Horatiu Cirstea, Vincent Thomas
 *
 * afficheur graphique pour le game
 *
 */
public class PacmanPainter implements GamePainter {

	/**
	 * la taille des cases
	 */
	protected int WIDTH = 100;
	protected int HEIGHT = 100;

	private final PlayerView playerView;

	private final Group root;


	/**
	 * appelle constructeur parent
	 */
	public PacmanPainter(Group main, PacmanGame game) {
		this.root = main;

		this.playerView = new PlayerView(game);

		this.root.getChildren().add(this.playerView);
	}

	/**
	 * methode  redefinie de Afficheur retourne une image du jeu
	 */
	public void draw() {
		playerView.draw();
	}

	public int getWidth() {
		return WIDTH;
	}

	public int getHeight() {
		return HEIGHT;
	}

	public Group getRoot() {
		return root;
	}
}
