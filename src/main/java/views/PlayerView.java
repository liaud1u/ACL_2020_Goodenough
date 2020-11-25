package views;

import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.player.Player;
import model.player.PlayerType;
import model.util.Util;

//FIXME: extends ImageView instead of Group
/** Class for the display of the player
 * */
public class PlayerView extends Group {
  private final Player player;  // The player we are currently displaying
  private final Image[] sprite = new Image[4];  // The sprites for the player
  private final ImageView view; // The current sprite to display


  private int frame;  // Current frame (for animations)

  /** @param player (:{@link Player}) the current player to display
   * */
  public PlayerView(Player player) {
    this.player = player;
    this.frame = 0;

    final int size = (int) Util.slotSizeProperty.multiply(Util.RATIO_PERSONNAGE).get();
    final int spriteSize = size * 3;

      // Initialize all the sprites

      String path = ((player.getType()== PlayerType.PLAYER1)?"pacman":"pacgirl");

            sprite[0] = new Image(path+"/"+path+"_down.png", spriteSize, size, true, false);   // going down sprite
            sprite[1] = new Image(path+"/"+path+"_up.png", spriteSize, size, true, false);     // going up sprite
            sprite[2] = new Image(path+"/"+path+"_left.png", spriteSize, size, true, false);   // going left sprite
            sprite[3] = new Image(path+"/"+path+"_right.png", spriteSize, size, true, false);  // going right sprite



    this.view = new ImageView(sprite[0]); // the current sprite to display
    this.view.setViewport(new Rectangle2D(0, 0, size, size)); // set the viewport


    this.getChildren().add(view);
  }

  /** @param ratio (:double)
   *  The method display the current sprite for the player
   *  */
  public void draw(double ratio) {
      // define the player sprite to display depending of the current direction
      switch (player.getCurrentMoveDirection()){
        case DOWN:  view.setImage(sprite[0]);   break;
        case UP:    view.setImage(sprite[1]);   break;
        case LEFT:  view.setImage(sprite[2]);   break;
        case RIGHT: view.setImage(sprite[3]);   break;
      }

      // if the player is not stuck, refresh the coordinates
      if (! this.player.isStuck()) {
        view.setX(
          Util.slotSizeProperty.multiply(
            this.player.getxPrec() + ratio * this.player.getCurrentMoveDirection().getX_dir()
          ).get()
        );

        view.setY(
          Util.slotSizeProperty.multiply(
            player.getyPrec() + ratio * this.player.getCurrentMoveDirection().getY_dir()
          ).get()
        );
      }

      int size = (int) (Util.slotSizeProperty.intValue() * Util.RATIO_PERSONNAGE);
      frame = (frame + 1) % 20;
      int printedFrame = frame / 5;

      // frame ain't in the right order. We have to bind them to the good frames
      switch (printedFrame) {
        case 0: printedFrame = 2; break;
        case 1:
        case 3: printedFrame = 0; break;
        case 2: printedFrame = 1; break;
      }

      // we set the current viewport of the viewport
      view.setViewport(new Rectangle2D(printedFrame * size,0,size,size));
    }
}
