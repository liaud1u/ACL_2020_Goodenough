package views;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Pastille;
import model.util.Util;

/** Class used for the display of the chip
 * */
public class PastilleView extends ImageView {
  private final Pastille pastille; // the current item we want to display

  /** @param pastille (:{@link Pastille}) the item to display
   *  @param x        (:int)              item's x coordinate
   *  @param y        (:int)              item's y coordinate
   *  */
  public PastilleView(Pastille pastille, int x, int y) {
    this.pastille = pastille;

    final double size = Util.slotSizeProperty.multiply(Util.RATIO_PASTILLE).get();

    // set the image to display
    this.setImage(new Image("pastille.png", size, size,true,false));

    // FIXME: why do we have /2 here?
    // define the coordinates of the view
    this.setX(
      Util.slotSizeProperty.multiply(x).add(Util.slotSizeProperty.divide(2).subtract(size / 2)).get()
    );
    this.setY(
      Util.slotSizeProperty.multiply(y).add(Util.slotSizeProperty.divide(2).subtract(size / 2)).get()
    );
  }
}
