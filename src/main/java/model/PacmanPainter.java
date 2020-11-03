package model;

import fxengine.GamePainter;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.transform.Translate;

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

	private final Group root;

	private final Circle circle;

	/**
	 * appelle constructeur parent
	 */
	public PacmanPainter(Group main) {
		this.root = main;

		circle = new Circle(10, Color.BLUE);
		circle.setCenterX(0);
		circle.setCenterY(0);
		root.getChildren().add(circle);
	}

	/**
	 * methode  redefinie de Afficheur retourne une image du jeu
	 */
	public void draw() {
		Translate translate = new Translate();
		translate.setX(1);
		translate.setY(2);
		circle.getTransforms().add(translate);
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
