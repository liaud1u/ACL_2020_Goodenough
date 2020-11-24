package views;

import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.monster.Monster;
import model.monster.MonsterState;
import model.util.Util;

//FIXME: extends from ImageView instead of Group
/** Class used for the display of a monster
 * */
public class MonstreView extends Group {
  private final Monster monstre;  // the monster to display
  private final Image sprite; // the current sprite for the monster
  private final Image deadSprite; // the current sprite for the monster
  private final ImageView view; // the imageview used to display the monster


  private final double animX; //FIXME: dunno how to doc this
  private final double animY; // same

  private final Rectangle2D[] frames;

  private int currentFrame; // current fram for the animations

  /**
   * @param monstre (:{@link Monster}) the monster to display
   */
  public MonstreView(Monster monstre) {
    this.monstre = monstre;
    int size = (int) (Util.slotSizeProperty.intValue() * Util.RATIO_MONSTRE);

    /** check the type of the monster
     *  and pick the colour depending of the monster's type
     *  */
    switch (monstre.getType()) {
      case RED: // red is used as default color
      default:
        sprite = new Image("monsters/red_ghost.png", size * 8, size, true, false);
        break;
      case BLUE:
        sprite = new Image("monsters/blue_ghost.png", size * 8, size, true, false);
        break;
      case ORANGE:
        sprite = new Image("monsters/orange_ghost.png", size * 8, size, true, false);
        break;
      case PINK:
        sprite = new Image("monsters/pink_ghost.png", size * 8, size, true, false);
        break;
    }

    deadSprite = new Image("monsters/dead_ghost.png", size * 4, size, true, false);

    view = new ImageView(sprite); // init the view
    currentFrame = 0;
    view.setViewport(new Rectangle2D(0, 0, size, size));  // set the viewport

    this.init();  // we display the view

    animX = (int) (Util.slotSizeProperty.get() / 2);  //FIXME: dunno how to doc this
    animY = (int) (Util.slotSizeProperty.get() / 2);  //same

    frames = new Rectangle2D[8];
    for (int i = 0; i < 8; i++) frames[i] = new Rectangle2D(size * i, 0, size, size);
  }

  /** init the view
   * */
  private void init() {
    this.getChildren().add(view);
  }

  /** @param ratio (:double), the second ratio the gameloop is currently in
   *  the method draw the monster
   * */
  public void draw(double ratio) {
    int size = (int) (Util.slotSizeProperty.intValue() * Util.RATIO_MONSTRE); // set the size

    /** set coords depending of ??????*/
    if (  //FIXME: dunno how to doc this
      monstre.getyPrec() == monstre.getY() &&
      monstre.getX() == monstre.getxPrec())
    {
      view.setX(Util.slotSizeProperty.multiply(monstre.getX()).add(Util.slotSizeProperty.divide(2).subtract(size/2)).get());
      view.setY(Util.slotSizeProperty.multiply(monstre.getY()).add(Util.slotSizeProperty.divide(2).subtract(size / 2)).get());
    } else {
      view.setX(Util.slotSizeProperty.multiply(monstre.getxPrec() + ratio * this.monstre.getMovementStrategy().getDirection().getX_dir()).get());
      view.setY(Util.slotSizeProperty.multiply(monstre.getyPrec() + ratio * this.monstre.getMovementStrategy().getDirection().getY_dir()).get());
    }

    currentFrame = (currentFrame + 1) % 20; // set the frame
    int printedFrame = currentFrame / 10;


    if (monstre.getLifeState() == MonsterState.DEAD && view.getImage() != deadSprite) {
      view.setImage(deadSprite);
    }

    if (monstre.getLifeState() == MonsterState.ALIVE && view.getImage() != sprite) {
      view.setImage(sprite);
    }

    // set the viewport depending of the direction
    switch (monstre.getMovementStrategy().getDirection()) {
      case UP:
        if (monstre.getLifeState() == MonsterState.ALIVE)
          view.setViewport(frames[0 + printedFrame]);
        else
          view.setViewport(frames[0]);

        break;
      case DOWN:
        if (monstre.getLifeState() == MonsterState.ALIVE)
          view.setViewport(frames[2 + printedFrame]);
        else
          view.setViewport(frames[1]);
        break;
      case LEFT:
        if (monstre.getLifeState() == MonsterState.ALIVE)
          view.setViewport(frames[4 + printedFrame]);
        else
          view.setViewport(frames[2]);
        break;
      default:
        if (monstre.getLifeState() == MonsterState.ALIVE)
          view.setViewport(frames[6 + printedFrame]);
        else
          view.setViewport(frames[3]);
        break;
    }

  }
}
