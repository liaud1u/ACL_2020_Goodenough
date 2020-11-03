package views;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import model.Pastille;

public class PastilleView extends Circle {

private Pastille pastille;


    public PastilleView(Pastille pastille,int x, int y) {
        super(pastille.getRayonPastille());
        
        this.pastille = pastille;
        this.setFill(pastille.getCouleurPastille());
        this.setCenterX(x * 32 + 16);
        this.setCenterY(y * 32 + 16);

    }
    public void draw(){
        this.setVisible(false);
    }
}
