package model;

import javafx.scene.paint.Color;

/**
 * Pastille de score
 */
public class ScorePastille extends Pastille {

    /**
     * Couleur de la pastille
     */
    private static final Color couleur = Color.BLUE;

    /**
     * Pastille de score
     *
     * @param x int x coordonnée x de la pastille
     * @param y int y coordonnée y de la pastille
     */
    public ScorePastille(int x, int y) {
        super(couleur, "scorePastille", x, y);
        value = 10;
    }

}