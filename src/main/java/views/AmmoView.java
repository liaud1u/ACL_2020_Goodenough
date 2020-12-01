package views;

import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.control.Label;
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
  private HBox container;
  private ImageView[] maxAmmo;
  private Label dividerLabel;
  private int displayDigitsSize;
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
    if (this.counter != null) this.getChildren().remove(this.counter);  // remove current counter
    if (this.dividerLabel != null) this.getChildren().remove(this.dividerLabel);
    if (this.maxAmmo != null) this.getChildren().removeAll(this.maxAmmo);

    this.counter = new Group(SpriteTools.getSpritedNumber(ammo, this.displayDigitsSize, 0)); // add the digits (many images)
    this.dividerLabel = new Label("/");
    this.dividerLabel.getStyleClass().add("text_");
    this.maxAmmo = SpriteTools.getSpritedNumber(Util.MAX_AMMOS, this.displayDigitsSize, 0);

    this.getChildren().addAll(this.counter, this.dividerLabel);  // add it to the root
    this.getChildren().addAll(this.maxAmmo);
  }
   
   /*------------------------------------------------------------------
                            Constructors
   ------------------------------------------------------------------*/

  public AmmoView() {
    final double size = Util.slotSizeProperty.multiply(Util.RATIO_FIREBALL).get();
    final int spriteSize = (int) (size * 8);


    this.ammoView = new ImageView(  // create the imageView for the ammos
      new Image("projectile/fireball/fireball_right.png",
              spriteSize,
      size,
      true,
        false));
    this.ammoView.setViewport(new Rectangle2D(0, 0, size, size)); // set the viewport

    this.displayDigitsSize = String.valueOf(Util.MAX_AMMOS).length();

    this.setAlignment(Pos.CENTER);  // center elements on root
    this.setSpacing(5.); // spacing between elements
    this.getChildren().addAll(ammoView);  // add view to the children
  }
}
