package views;

import fxengine.GameApplication;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.Case;
import model.PacmanGame;
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


          //  Rectangle mur = .setFill(Color.DARKKHAKI);

            
            // Mur ouest
            if(c.isMurOuest()) {
                Rectangle mur =  new Rectangle(c.getY() * Util.slotSizeProperty.get(),c.getX() * Util.slotSizeProperty.get(), Util.wallSizeProperty.get(), Util.slotSizeProperty.get());
                mur.setFill(Color.BLACK);
                representationCase.add(mur);
            }


            // Mur est
            if(c.isMurEst()) {
                Rectangle mur =  new Rectangle(c.getY() * Util.slotSizeProperty.get() + diffTailleCase, c.getX() * Util.slotSizeProperty.get(), Util.wallSizeProperty.get(), Util.slotSizeProperty.get());
                mur.setFill(Color.BLACK);
                representationCase.add(mur);


            }
            // Mur nord
            if(c.isMurNord()) {
                Rectangle mur =  new Rectangle(c.getY() * Util.slotSizeProperty.get(),c.getX() * Util.slotSizeProperty.get(),  Util.slotSizeProperty.get(), Util.wallSizeProperty.get());
                mur.setFill(Color.BLACK);
                representationCase.add(mur);
            }
            // Mur sud
            if(c.isMurSud()) {
                Rectangle mur =  new Rectangle(c.getY() * Util.slotSizeProperty.get(),c.getX() * Util.slotSizeProperty.get() + diffTailleCase,  Util.slotSizeProperty.get(), Util.wallSizeProperty.get());
                mur.setFill(Color.BLACK);
                representationCase.add(mur);
            }
            
            for(Rectangle r :representationCase) {
                if(r != null) {
                    getChildren().add(r);
                }
            }
    }





}
