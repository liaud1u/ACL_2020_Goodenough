package views;

import fxengine.GameApplication;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.Case;

import java.util.ArrayList;
import java.util.List;


/**
 * @author adrien
 */
public class CaseView extends Group {

    private DoubleProperty tailleCase;
    private DoubleProperty tailleMur;
    private double diffTailleCase;

    private Case modelCase;
    private List<Rectangle> representationCase;

    public CaseView(Case c) {

            this.modelCase = c;

            this.tailleCase = new SimpleDoubleProperty();
            this.tailleMur = new SimpleDoubleProperty();


            this.tailleCase.bind(GameApplication.blocSizeProperty.divide(20));
            this.tailleMur.bind(this.tailleCase.divide(10));
            this.diffTailleCase = this.tailleCase.get() - this.tailleMur.get();


            //System.out.println(GameApplication.blocSizeProperty.get());
            representationCase = new ArrayList<>();

            // Case de base
            Rectangle base = new Rectangle(c.getY() * this.tailleCase.get(),c.getX() * this.tailleCase.get(),  this.tailleCase.get(), this.tailleCase.get());
            base.setFill(Color.GRAY);
            representationCase.add(base);


          //  Rectangle mur = .setFill(Color.DARKKHAKI);

            
            // Mur ouest
            if(c.isMurOuest()) {
                Rectangle mur =  new Rectangle(c.getY() * this.tailleCase.get(),c.getX() * this.tailleCase.get(), tailleMur.get(), this.tailleCase.get());
                mur.setFill(Color.BLACK);
                representationCase.add(mur);
                if(!c.isMurSud()) {

                }
                if(!c.isMurNord()) {

                }
            }


            // Mur est
            if(c.isMurEst()) {
                Rectangle mur =  new Rectangle(c.getY() * this.tailleCase.get() + diffTailleCase, c.getX() * this.tailleCase.get(), tailleMur.get(), this.tailleCase.get());
                mur.setFill(Color.BLACK);
                representationCase.add(mur);
            }
            // Mur nord
            if(c.isMurNord()) {
                Rectangle mur =  new Rectangle(c.getY() * this.tailleCase.get(),c.getX() * this.tailleCase.get(),  this.tailleCase.get(), tailleMur.get());
                mur.setFill(Color.BLACK);
                representationCase.add(mur);
            }
            // Mur sud
            if(c.isMurSud()) {
                Rectangle mur =  new Rectangle(c.getY() * this.tailleCase.get(),c.getX() * this.tailleCase.get() + diffTailleCase,  this.tailleCase.get(), tailleMur.get());
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
