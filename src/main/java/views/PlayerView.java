package views;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import model.player.Player;
import model.util.Util;

/**
 * Vue du joueur
 */
public class PlayerView extends Group {


  /**
   * Joueur à afficher
   */
  private final Player player;

  /**
   * Liste des 4 différentes images décrivant le personnage
   */
  private final Image[] sprite = new Image[4];

  /**
   * Vue de l'image à afficher
   */
  private final ImageView view;


  /**
   * Frame actuelle (pour les animations)
   */
  private int frame;

  private double animX;

  private double animY;

  /**
   * Constructeur de la vue
   *
   * @param player Player joueur à afficher
   */
  public PlayerView(Player player) {
    this.player = player;
    this.frame = 0;

    int size = (int) (Util.slotSizeProperty.intValue() * Util.RATIO_PERSONNAGE);

    sprite[0] = new Image("pacman/pacman_down.png", size * 3, size, true, false);
    sprite[1] = new Image("pacman/pacman_up.png", size * 3, size, true, false);
    sprite[2] = new Image("pacman/pacman_left.png", size * 3, size, true, false);
    sprite[3] = new Image("pacman/pacman_right.png", size * 3, size, true, false);

    view = new ImageView(sprite[0]);
    view.setViewport(new Rectangle2D(0, 0, size, size));

    animX = (int) (Util.slotSizeProperty.get() / 2);
    animY = (int) (Util.slotSizeProperty.get() / 2);

    this.init();
  }

  /**
   * Initialisation de la vue
   */
  private void init() {
    this.getChildren().add(view);
  }

  /**
   * Dessin de la vue
   */
  public void draw(double ratio) {
    switch (player.getCurrentMoveDirection()){
      case DOWN:
        view.setImage(sprite[0]);
        break;
      case UP:
        view.setImage(sprite[1]);
        break;
      case LEFT:
        view.setImage(sprite[2]);
        break;
      case RIGHT:
        view.setImage(sprite[3]);
        break;
    }

    view.setX(Util.slotSizeProperty.multiply( player.getX()-1).get()); //+ ratio * this.player.getCurrentMoveDirection().getX_dir() ).get());
    view.setY(Util.slotSizeProperty.multiply( player.getY()-1).get()); //+ ratio * this.player.getCurrentMoveDirection().getY_dir() ).get());

    int size = (int) (Util.slotSizeProperty.intValue() * Util.RATIO_PERSONNAGE);

    frame = (frame + 1) % 20;

    int printedFrame = frame / 5;

    // Les image ne sont pas dans l'ordre dans la ressource, on remet les binds aux bonnes frames
    switch (printedFrame) {
      case 0:
        printedFrame = 2;
        break;
      case 1:
      case 3:
        printedFrame = 0;
        break;
      case 2:
        printedFrame = 1;
        break;
    }

    view.setViewport(new Rectangle2D(printedFrame * size,0,size,size));
  }
}
