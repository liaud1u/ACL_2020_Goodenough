package views;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.Case;
import model.util.Util;

import java.util.ArrayList;
import java.util.List;


/**
 * @author adrien
 */
public class CaseView extends Group {

    private double diffTailleCase;
    private Case modelCase;
    private List<Rectangle> representationCase;

    public CaseView(Case c) {

            this.modelCase = c;
            //System.out.println(GameApplication.blocSizeProperty.get());
            representationCase = new ArrayList<>();

            // Case de base
            Rectangle base = new Rectangle(c.getX() * (Util.slotSizeProperty.get()/2),c.getY() * (Util.slotSizeProperty.get()/2),  Util.slotSizeProperty.get()/2, Util.slotSizeProperty.get()/2);
            if(c.isEstUnMur()) {
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
