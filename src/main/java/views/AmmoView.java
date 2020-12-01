package views;

import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import model.util.SpriteTools;
import model.util.Util;

/**
 * @author Ribeyrolles Matthieu
 * 01/12/2020, 00:22
 */
public class AmmoView extends HBox {
  private ImageView ammoView;
  private Group counter;
  /*------------------------------------------------------------------
                              Methods
   ------------------------------------------------------------------*/

  // getters
  // setters
  // private
  // public

  /**
   * @param ammo (:int): the number of ammo the player has
   * This method redraw the counter by calling an external method
   * */
  public void draw(int ammo) {
    this.getChildren().remove(this.counter);  // remove current counter

    this.counter = new Group(SpriteTools.getSpritedNumber(ammo, 2, 0)); // add the digits (many images)

    this.getChildren().addAll(this.counter);  // add it ti the root
  }
   
   /*------------------------------------------------------------------
                            Constructors
   ------------------------------------------------------------------*/

  public AmmoView() {
    final double size = Util.slotSizeProperty.multiply(Util.RATIO_FIREBALL).get();

    this.ammoView = new ImageView(  // create the imageView for the ammos
      new Image("projectile/fireball/fireball_right.png",
      200.,
      200.,
      true,
        false));
    this.ammoView.setViewport(new Rectangle2D(0, 0, size, size)); // set the viewport

    this.setAlignment(Pos.CENTER);  // center elements on root
    this.setSpacing(15.); // spacing between elements
    this.getChildren().addAll(ammoView);  // add view to the children
  }
}
