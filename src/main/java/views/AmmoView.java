package views;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.util.SpriteTools;

/**
 * @author Ribeyrolles Matthieu
 * 01/12/2020, 00:22
 */
public class AmmoView extends Group {
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
    this.counter = new Group(SpriteTools.getSpritedNumber(ammo, 2, 0));

    this.getChildren().addAll(this.counter);
  }
   
   /*------------------------------------------------------------------
                            Constructors
   ------------------------------------------------------------------*/

  public AmmoView(int ammo) {
    this.ammoView = new ImageView(new Image("projectile/fireball/fireball_right.png"));

    this.getChildren().addAll(ammoView);
    this.draw(ammo);
  }
}
