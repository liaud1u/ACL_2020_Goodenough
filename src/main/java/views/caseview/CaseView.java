package views.caseview;

import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Case;
import model.util.Util;
import views.PastilleView;


/** class used to display a slot
 * */
public class CaseView extends Group {
  /** @param sprite (:{@link Image}), the image to display
   *  @param c (:{@link Case})      , the Case to display
   *  */
  public CaseView(Image sprite, Case c) {
/*    TODO : ajouter un mode debug pour print les rectangles
    Rectangle r = new Rectangle(c.getX() * Util.slotSizeProperty.get(), c.getY()* Util.slotSizeProperty.get(),
            Util.slotSizeProperty.get(), Util.slotSizeProperty.get());
    r.setStroke(Color.ALICEBLUE);
    this.getChildren().add(r);
*/
    // if we have an item, we create a view and add it to the group root
    if (c.hasPastille()) {
      final PastilleView pastilleView = new PastilleView(c.getPastille(), c.getX(), c.getY());  // create the view
      pastilleView.visibleProperty().bind(c.hasPastilleProperty); // if the slot has an item, it is visible. Otherwise it is not

      this.getChildren().add(pastilleView); // add item to the group root
    }

    // if this is a wall, we create a wall view and add it to the group view
    if (c.estUnMur()) {
      ImageView texture = new ImageView(sprite);  // the wall texture
      Rectangle2D view = CaseImageTiles.REVERSE_SQUARE.getView(); // the wall view

      /** Depending of the neighbourhood, we change the view
       *  this allows us to display corners and standalone walls insteads of the ame view for each wall
       *  */
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


      texture.setViewport(view);  // set the viewport
      texture.setX(c.getX() * Util.slotSizeProperty.get()); //set the x of the texture
      texture.setY(c.getY() * Util.slotSizeProperty.get()); //set the y of the texture

      getChildren().add(texture); // add the texture to the group root
    }
  }

}
