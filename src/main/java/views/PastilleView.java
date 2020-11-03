package views;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import model.Pastille;
import model.util.Util;

public class PastilleView extends Circle {

private Pastille pastille;


    public PastilleView(Pastille pastille,int x, int y) {
        super(Util.slotSizeProperty.get()/6);

        this.pastille = pastille;
        this.setFill(pastille.getCouleurPastille());
        this.setCenterX(x * Util.slotSizeProperty.get() + Util.slotSizeProperty.get()/2);
        this.setCenterY(y * Util.slotSizeProperty.get() + Util.slotSizeProperty.get()/2);

    }
    public void draw(){
        this.setVisible(false);
    }
}
