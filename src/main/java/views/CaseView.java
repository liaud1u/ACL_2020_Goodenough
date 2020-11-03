package views;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.Case;

public class CaseView extends Group {

    private int TAILLE_CASE = 32;
    private int TAILLE_MUR = 3;
    private int DIFF_TAILLE_CASE = TAILLE_CASE - TAILLE_MUR;

    private Case _case;
    private Rectangle[] representationCase;

    public CaseView(Case c) {
            representationCase = new Rectangle[5];

            // Case de base
            representationCase[0] = new Rectangle(c.getX() * TAILLE_CASE, c.getY() * TAILLE_CASE, TAILLE_CASE, TAILLE_CASE);
            representationCase[0].setFill(Color.DARKKHAKI);

            // Mur ouest
            if(c.isMurOuest()) {
              //  representationCase[1] = new Rectangle(c.getX() * TAILLE_CASE, c.getY() * TAILLE_CASE)
            }
            if(c.isMurNord()) {

            }
            if(c.isMurEst()) {

            }
            if(c.isMurSud()) {

            }


            getChildren().add(representationCase[0]); // FIXME: addall
    }





}
