package views;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.Case;
import model.Labyrinthe;
import model.util.Util;

import java.util.ArrayList;
import java.util.List;


/**
 * Vue d'une case
 */
public class CaseView extends Group {

    private final List<Rectangle> representationCase;


    public CaseView(Case c) {

        representationCase = new ArrayList<>();

            Rectangle base = new Rectangle(c.getX() * Util.slotSizeProperty.get(),c.getY() * Util.slotSizeProperty.get(),  Util.slotSizeProperty.get(), Util.slotSizeProperty.get());
            base.setFill(Color.BLACK);
            if(c.estUnMur()) {
                base.setStyle("-fx-stroke: #DEA185");
            }
            representationCase.add(base);

            for(Rectangle r :representationCase) {
                if(r != null) {
                    getChildren().add(r);
                }
            }
    }





}
