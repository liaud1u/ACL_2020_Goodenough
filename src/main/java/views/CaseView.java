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
           // this.tailleCase.bind();
            this.diffTailleCase = Util.slotSizeProperty.get() - Util.wallSizeProperty.get();


            //System.out.println(GameApplication.blocSizeProperty.get());
            representationCase = new ArrayList<>();

            // Case de base
            Rectangle base = new Rectangle(c.getY() * Util.slotSizeProperty.get(),c.getX() * Util.slotSizeProperty.get(),  Util.slotSizeProperty.get(), Util.slotSizeProperty.get());
            base.setFill(Color.GRAY);
            representationCase.add(base);

            for(Rectangle r :representationCase) {
                if(r != null) {
                    getChildren().add(r);
                }
            }
    }





}
