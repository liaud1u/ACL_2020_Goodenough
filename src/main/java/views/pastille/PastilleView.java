package views.pastille;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.pastille.PastilleType;
import model.util.Util;

/** Class used for the display of the chip
 * */
public  class PastilleView extends ImageView {

  /**
   *  @param x        (:int)              item's x coordinate
   *  @param y        (:int)              item's y coordinate
   *  */

  public PastilleView(int x, int y , PastilleType type) {


    double size = Util.slotSizeProperty.multiply(Util.RATIO_PASTILLE).get();

    switch(type) {
      case AMMO:
        this.setImage(new Image("pastilles/ammoPastille.png", size, size,true,false));
        break;
      case TIME:
        this.setImage(new Image("pastilles/timePastille.png", size, size,true,false));
        break;
      case INVINCIBILITY:
        this.setImage(new Image("pastilles/invincibilitePastille.png", size, size,true,false));
        break;
      case LANDMINE:
        this.setImage(new Image("pastilles/landminePastille.png", size*1.5, size*1.5,true,false));
        break;
      case SCORE:
      default:
        this.setImage(new Image("pastilles/scorePastille.png", size, size,true,false));
        break;
    }
    // define the coordinates of the view
    this.setX(
      Util.slotSizeProperty.multiply(x).add(Util.slotSizeProperty.divide(2).subtract(this.getImage().getWidth()/ 2)).get()    // FIXME: why do we have /2 here?
    );
    this.setY(
      Util.slotSizeProperty.multiply(y).add(Util.slotSizeProperty.divide(2).subtract(this.getImage().getWidth() / 2)).get()
    );
  }
}
