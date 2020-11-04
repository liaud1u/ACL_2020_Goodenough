package views;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.PacmanGame;
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
  private Image[] sprite = new Image[4];

  /**
   * Vue de l'image à afficher
   */
  private ImageView view;


  /**
   * Constructeur de la vue
   *
   * @param player Player joueur à afficher
   */
  public PlayerView(Player player) {
    this.player = player;

    int size = (int) (Util.slotSizeProperty.intValue()*Util.RATIO_PERSONNAGE);

    sprite[0]= new Image("pacman/pacman_down.png",size,size,true,false);
    sprite[1]= new Image("pacman/pacman_up.png",size,size,true,false);
    sprite[2]= new Image("pacman/pacman_left.png",size,size,true,false);
    sprite[3]= new Image("pacman/pacman_right.png",size,size,true,false);

    view = new ImageView(sprite[0]);

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
   *
   * @param game PacmanGame jeu principal
   */
  public void draw(PacmanGame game) {
    double rayon =  Util.slotSizeProperty.getValue()*Util.RATIO_PERSONNAGE /2;

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

    view.setX(player.getX()-rayon);
    view.setY(player.getY()-rayon);
  }
}
