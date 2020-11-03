package views;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.Case;


/**
 * @author adrien
 */
public class CaseView extends Group {

    private int TAILLE_CASE = 32;
    private int TAILLE_MUR = 3;
    private int DIFF_TAILLE_CASE = TAILLE_CASE - TAILLE_MUR;

    private Case modelCase;
    private Rectangle[] representationCase;

    public CaseView(Case c) {

            this.modelCase = c;
            representationCase = new Rectangle[5];

            // Case de base
            representationCase[0] = new Rectangle(c.getY() * TAILLE_CASE,c.getX() * TAILLE_CASE,  TAILLE_CASE, TAILLE_CASE);
            representationCase[0].setFill(Color.DARKKHAKI);

            // Mur ouest
            if(c.isMurOuest()) {
                representationCase[1] = new Rectangle(c.getY() * TAILLE_CASE,c.getX() * TAILLE_CASE,  TAILLE_MUR, TAILLE_CASE);
                representationCase[1].setFill(Color.DARKRED);
            }
            // Mur est
            if(c.isMurEst()) {
                representationCase[2] = new Rectangle(c.getY() * TAILLE_CASE + DIFF_TAILLE_CASE, c.getX() * TAILLE_CASE, TAILLE_MUR, TAILLE_CASE);
                representationCase[2].setFill(Color.DARKRED);
            }
            // Mur nord
            if(c.isMurNord()) {
                representationCase[3] = new Rectangle(c.getY() * TAILLE_CASE,c.getX() * TAILLE_CASE,  TAILLE_CASE, TAILLE_MUR);
                representationCase[3].setFill(Color.DARKRED);
            }
            // Mur sud
            if(c.isMurSud()) {
                representationCase[4] = new Rectangle(c.getY() * TAILLE_CASE,c.getX() * TAILLE_CASE + DIFF_TAILLE_CASE,  TAILLE_CASE, TAILLE_MUR);
                representationCase[4].setFill(Color.DARKRED);
            }

            for(Rectangle r :representationCase) {
                if(r != null) {
                    getChildren().add(r);
                }
            }
    }





}
