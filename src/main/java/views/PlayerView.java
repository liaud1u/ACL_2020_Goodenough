package views;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import model.PacmanGame;
import model.player.Player;

/**
 * @author Ribeyrolles Matthieu
 * 03/11/2020, 14:07
 */
public class PlayerView extends Circle {
  private final double RADIUS = 10; //TODO: change the value
  private final Player player;

  /*------------------------------------------------------------------
                              Methods
   ------------------------------------------------------------------*/

  // private

  private void init() {
    this.setRadius(this.RADIUS); //TODO: can be done through constructor but need an external way to get radius
    this.setFill(Color.GOLDENROD);
  }
  // public

  public void draw(PacmanGame game) {
    this.setCenterX(player.getX());
    this.setCenterY(player.getY());
  }
   
   /*------------------------------------------------------------------
                            Constructors
   ------------------------------------------------------------------*/

  public PlayerView(Player player) {
    this.player = player;
    this.init();
  }
}
