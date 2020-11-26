package fxengine;

import javafx.scene.Group;

/**
 * Génère l'image du jeu
 */
public interface GamePainter {

	/**
	 * Méthode de dessin de l'image
	 * @param ratio
	 */
    void draw(double ratio);

	/**
	 * Getter of the root of the scene
	 * @return Group root
	 */
	Group getRoot();

	/**
	 * Getter of the width of the scene
	 * @return int width
	 */
	int getWidth();

	/**
	 * Getter of the height of the scene
	 * @return int height
	 */
	int getHeight();
}
