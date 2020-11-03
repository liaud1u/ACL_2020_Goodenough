package model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class ScorePastille extends Pastille {

    private Circle c1;
    private static final Color couleur = Color.BLUE;

    public ScorePastille()
    {
        super(couleur,"scorePastille");

    }

    public void setScorePastille(Circle c)
    {
        this.c1 = c;
    }

    public Circle getScorePastille (){
        return this.c1;
    }

    public String toString() {
        return "Score";
    }
}