package fxengine;

import javafx.scene.Group;

/**
 * Génère l'image du jeu
 */
public interface GamePainter {

	/**
	 * Méthode de dessin de l'image
	 */
    void draw();

	Group getRoot();

	int getWidth();

	int getHeight();
	
}
