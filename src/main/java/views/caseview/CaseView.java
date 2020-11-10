package views.caseview;

import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Case;
import model.util.Util;


/**
 * Vue d'une case
 */
public class CaseView extends Group {


    public CaseView(Image sprite, Case c) {
        if (c.estUnMur()) {
            ImageView texture = new ImageView(sprite);
            Rectangle2D view = CaseImageView.REVERSE_SQUARE.getView();

            if (!c.voisinDroite() && !c.voisinGauche() && !c.voisinDessous() && !c.voisinDessus())
                view = CaseImageView.REVERSE_SQUARE.getView();

            if (!c.voisinDroite() && !c.voisinGauche() && !c.voisinDessous() && c.voisinDessus())
                view = CaseImageView.UP.getView();

            if (!c.voisinDroite() && !c.voisinGauche() && c.voisinDessous() && !c.voisinDessus())
                view = CaseImageView.DOWN.getView();

            if (!c.voisinDroite() && c.voisinGauche() && !c.voisinDessous() && !c.voisinDessus())
                view = CaseImageView.LEFT.getView();

            if (c.voisinDroite() && !c.voisinGauche() && !c.voisinDessous() && !c.voisinDessus())
                view = CaseImageView.RIGHT.getView();

            if (c.voisinDroite() && c.voisinGauche() && !c.voisinDessous() && !c.voisinDessus())
                view = CaseImageView.RIGHT_LEFT.getView();

            if (!c.voisinDroite() && !c.voisinGauche() && c.voisinDessous() && c.voisinDessus())
                view = CaseImageView.UP_DOWN.getView();

            if (c.voisinDroite() && !c.voisinGauche() && c.voisinDessous() && !c.voisinDessus())
                view = CaseImageView.RIGHT_DOWN_CORNER.getView();

            if (c.voisinDroite() && !c.voisinGauche() && !c.voisinDessous() && c.voisinDessus())
                view = CaseImageView.RIGHT_UP_CORNER.getView();

            if (!c.voisinDroite() && c.voisinGauche() && c.voisinDessous() && !c.voisinDessus())
                view = CaseImageView.LEFT_DOWN_CORNER.getView();

            if (!c.voisinDroite() && c.voisinGauche() && !c.voisinDessous() && c.voisinDessus())
                view = CaseImageView.LEFT_UP_CORNER.getView();

            if (c.voisinDroite() && c.voisinGauche() && c.voisinDessous() && c.voisinDessus())
                view = CaseImageView.SQUARE.getView();

            if (c.voisinDroite() && !c.voisinGauche() && c.voisinDessous() && c.voisinDessus())
                view = CaseImageView.RIGHT_DOWN_UP_T.getView();

            if (c.voisinDroite() && c.voisinGauche() && !c.voisinDessous() && c.voisinDessus())
                view = CaseImageView.UP_LEFT_RIGHT_T.getView();

            if (!c.voisinDroite() && c.voisinGauche() && c.voisinDessous() && c.voisinDessus())
                view = CaseImageView.LEFT_DOWN_UP_T.getView();

            if (c.voisinDroite() && c.voisinGauche() && c.voisinDessous() && !c.voisinDessus())
                view = CaseImageView.DOWN_LEFT_RIGHT_T.getView();


            texture.setViewport(view);
            texture.setX(c.getX() * Util.slotSizeProperty.get());
            texture.setY(c.getY() * Util.slotSizeProperty.get());

            getChildren().add(texture);
        }
    }

}
