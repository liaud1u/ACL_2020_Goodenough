package views;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.Case;
import model.util.Util;

import java.util.ArrayList;
import java.util.List;


/**
 * Vue d'une case
 */
public class CaseView extends Group {

    private final List<Rectangle> representationCase;
    /**
     * Différence de taille entre le mur et la case (obsolète)
     */
    private double diffTailleCase;

    public CaseView(int x,int y, int i) {

        //System.out.println(GameApplication.blocSizeProperty.get());
        representationCase = new ArrayList<>();

        //System.err.println("DEBUG:" + c.toString());
        // Case de base
            Rectangle base = new Rectangle(x * Util.slotSizeProperty.get(),y * Util.slotSizeProperty.get(),  Util.slotSizeProperty.get(), Util.slotSizeProperty.get());
            if(i == 1) {
                base.setFill(Color.GRAY);
            } else {
                base.setFill(Color.WHITE);
            }
            representationCase.add(base);

            for(Rectangle r :representationCase) {
                if(r != null) {
                    getChildren().add(r);
                }
            }
    }





}
