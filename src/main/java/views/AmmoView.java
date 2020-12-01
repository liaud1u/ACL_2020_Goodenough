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
  public void draw(int ammo) {
    this.getChildren().remove(this.counter);

    this.counter = new Group(SpriteTools.getSpritedNumber(ammo, 2, 0));

    this.getChildren().addAll(this.counter);
  }
   
   /*------------------------------------------------------------------
                            Constructors
   ------------------------------------------------------------------*/

  public AmmoView() {
    final double size = Util.slotSizeProperty.multiply(Util.RATIO_FIREBALL).get();

    this.ammoView = new ImageView(
      new Image("projectile/fireball/fireball_right.png",
      200.,
      200.,
      true,
        false));
    this.ammoView.setViewport(new Rectangle2D(0, 0, size, size)); // set the viewport

    this.setAlignment(Pos.CENTER);
    this.setSpacing(15.);
    this.getChildren().addAll(ammoView);
  }
}
