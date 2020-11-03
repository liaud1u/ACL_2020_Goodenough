package model;

import javafx.scene.paint.Color;

public class ScorePastille extends Pastille {

    private static final Color couleur = Color.BLUE;

    public ScorePastille(int x, int y) {
        super(couleur, "scorePastille", x, y);
    }

}