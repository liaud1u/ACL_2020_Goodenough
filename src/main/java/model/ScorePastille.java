package model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class ScorePastille extends Pastille {

    private Circle c1;
    private static final Color couleur = Color.BLUE;

    public ScorePastille(Case casePastille, Labyrinthe labyrintheCourant)
    {
        super(casePastille,labyrintheCourant,couleur,"scorePastille");
        double x = casePastille.getX();
        double y = casePastille.getY();
        this.c1 = new Circle(x,y,getRayon(),couleur);
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