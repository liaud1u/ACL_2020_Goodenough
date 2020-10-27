package model;

import fxengine.GamePainter;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * @author Horatiu Cirstea, Vincent Thomas
 *
 * afficheur graphique pour le game
 * 
 */
public class PacmanPainter implements GamePainter{

	/**
	 * la taille des cases
	 */
	protected static final int WIDTH = 100;
	protected static final int HEIGHT = 100;

	private final Canvas canvas;

	/**
	 * appelle constructeur parent
	 */
	public PacmanPainter(Canvas main) {
		this.canvas = main;
	}

	/**
	 * methode  redefinie de Afficheur retourne une image du jeu
	 */
	public void draw() {
		System.out.println("draw");
		GraphicsContext crayon = canvas.getGraphicsContext2D();
		crayon.setFill(Color.BLUE);
		crayon.fill();
		crayon.fillOval(0,0,10,10);
	}

	public int getWidth() {
		return WIDTH;
	}

	public int getHeight() {
		return HEIGHT;
	}

	public Canvas getCanvas() {
		return canvas;
	}
}
