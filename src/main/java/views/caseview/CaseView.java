package views.caseview;

import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Case;
import model.util.Util;
import views.PastilleView;


/**
 * Vue d'une case
 */
public class CaseView extends Group {
  public CaseView(Image sprite, Case c) {
    if (c.hasPastille()) {
      final PastilleView pastilleView = new PastilleView(c.getPastille(), c.getX(), c.getY());
      pastilleView.visibleProperty().bind(c.hasPastilleProperty);

      this.getChildren().add(pastilleView);
    }

    if (c.estUnMur()) {
      ImageView texture = new ImageView(sprite);
      Rectangle2D view = CaseImageTiles.REVERSE_SQUARE.getView();

      if (!c.voisinDroite() && !c.voisinGauche() && !c.voisinDessous() && !c.voisinDessus())
        view = CaseImageTiles.REVERSE_SQUARE.getView();

      if (!c.voisinDroite() && !c.voisinGauche() && !c.voisinDessous() && c.voisinDessus())
        view = CaseImageTiles.UP.getView();

      if (!c.voisinDroite() && !c.voisinGauche() && c.voisinDessous() && !c.voisinDessus())
        view = CaseImageTiles.DOWN.getView();

      if (!c.voisinDroite() && c.voisinGauche() && !c.voisinDessous() && !c.voisinDessus())
        view = CaseImageTiles.LEFT.getView();

      if (c.voisinDroite() && !c.voisinGauche() && !c.voisinDessous() && !c.voisinDessus())
        view = CaseImageTiles.RIGHT.getView();

      if (c.voisinDroite() && c.voisinGauche() && !c.voisinDessous() && !c.voisinDessus())
        view = CaseImageTiles.RIGHT_LEFT.getView();

      if (!c.voisinDroite() && !c.voisinGauche() && c.voisinDessous() && c.voisinDessus())
        view = CaseImageTiles.UP_DOWN.getView();

      if (c.voisinDroite() && !c.voisinGauche() && c.voisinDessous() && !c.voisinDessus())
        view = CaseImageTiles.RIGHT_DOWN_CORNER.getView();

      if (c.voisinDroite() && !c.voisinGauche() && !c.voisinDessous() && c.voisinDessus())
        view = CaseImageTiles.RIGHT_UP_CORNER.getView();

      if (!c.voisinDroite() && c.voisinGauche() && c.voisinDessous() && !c.voisinDessus())
        view = CaseImageTiles.LEFT_DOWN_CORNER.getView();

      if (!c.voisinDroite() && c.voisinGauche() && !c.voisinDessous() && c.voisinDessus())
        view = CaseImageTiles.LEFT_UP_CORNER.getView();

      if (c.voisinDroite() && c.voisinGauche() && c.voisinDessous() && c.voisinDessus())
        view = CaseImageTiles.SQUARE.getView();

      if (c.voisinDroite() && !c.voisinGauche() && c.voisinDessous() && c.voisinDessus())
        view = CaseImageTiles.RIGHT_DOWN_UP_T.getView();

      if (c.voisinDroite() && c.voisinGauche() && !c.voisinDessous() && c.voisinDessus())
        view = CaseImageTiles.UP_LEFT_RIGHT_T.getView();

      if (!c.voisinDroite() && c.voisinGauche() && c.voisinDessous() && c.voisinDessus())
        view = CaseImageTiles.LEFT_DOWN_UP_T.getView();

      if (c.voisinDroite() && c.voisinGauche() && c.voisinDessous() && !c.voisinDessus())
        view = CaseImageTiles.DOWN_LEFT_RIGHT_T.getView();


      texture.setViewport(view);
      texture.setX(c.getX() * Util.slotSizeProperty.get());
      texture.setY(c.getY() * Util.slotSizeProperty.get());

      getChildren().add(texture);
    }
  }

}
