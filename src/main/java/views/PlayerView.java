package views;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import model.player.Direction;

/**
 * @author Ribeyrolles Matthieu
 * 03/11/2020, 14:07
 */
public class PlayerView extends Circle {
  private final double RADIUS = 10; //TODO: change the value

  /*------------------------------------------------------------------
                              Methods
   ------------------------------------------------------------------*/

  // private

  private void init() {
    this.setRadius(this.RADIUS); //TODO: can be done through constructor but need an external way to get radius
    this.setFill(Color.GOLDENROD);
  }
  // public

  public void draw(Direction direction) {
    this.setTranslateX(direction.getX_dir());
    this.setTranslateY(direction.getY_dir());
  }
   
   /*------------------------------------------------------------------
                            Constructors
   ------------------------------------------------------------------*/

  public PlayerView() {
    this.init();
  }
}
