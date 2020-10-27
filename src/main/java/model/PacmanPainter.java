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
	protected   int WIDTH = 100;
	protected   int HEIGHT = 100;

	private int cpt =0;

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
		GraphicsContext crayon = canvas.getGraphicsContext2D();

		//On nettoie l'image
		crayon.clearRect(0,0,canvas.getWidth(),canvas.getHeight());

		crayon.setFill(Color.BLUE);
		crayon.fill();
		crayon.fillOval(cpt,cpt++,10,10);
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
