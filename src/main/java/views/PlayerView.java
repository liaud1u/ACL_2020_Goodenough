package views;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.transform.Translate;
import model.PacmanGame;
import model.player.Direction;

/**
 * @author Ribeyrolles Matthieu
 * 03/11/2020, 14:07
 */
public class PlayerView extends Circle {
  private final double RADIUS = 10; //TODO: change the value
  private final PacmanGame game;

  /*------------------------------------------------------------------
                              Methods
   ------------------------------------------------------------------*/

  // private

  private void init() {
    this.setRadius(this.RADIUS); //TODO: can be done through constructor but need an external way to get radius
    this.setFill(Color.GOLDENROD);
  }
  // public

  public PlayerView(PacmanGame game) {
    this.game = game;
    this.init();
  }
   
   /*------------------------------------------------------------------
                            Constructors
   ------------------------------------------------------------------*/

  public void draw() {
    Direction direction = game.getPlayer().getCurrentMoveDirection();

    this.getTransforms().add(new Translate(direction.getX_dir(), direction.getY_dir()));
  }
}
