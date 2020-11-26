package views.pastille;

import javafx.scene.image.ImageView;
import model.util.Util;

/** Class used for the display of the chip
 * */
public abstract class PastilleView extends ImageView {

  protected final double size = Util.slotSizeProperty.multiply(Util.RATIO_PASTILLE).get();
  /**
   *  @param x        (:int)              item's x coordinate
   *  @param y        (:int)              item's y coordinate
   *  */

  public PastilleView(int x, int y) {
    // define the coordinates of the view
    this.setX(
      Util.slotSizeProperty.multiply(x).add(Util.slotSizeProperty.divide(2).subtract(size / 2)).get()    // FIXME: why do we have /2 here?
    );
    this.setY(
      Util.slotSizeProperty.multiply(y).add(Util.slotSizeProperty.divide(2).subtract(size / 2)).get()
    );
  }
}
