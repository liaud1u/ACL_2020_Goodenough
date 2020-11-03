package views;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import model.PacmanGame;
import model.player.Player;
import model.util.Util;

/**
 * Vue du joueur
 */
public class PlayerView extends Circle {

  /**
   * Joueur à afficher
   */
  private final Player player;

  /**
   * Constructeur de la vue
   *
   * @param player Player joueur à afficher
   */
  public PlayerView(Player player) {
    this.player = player;
    this.init();
  }

  /**
   * Initialisation de la vue
   */
  private void init() {
    this.setRadius(Util.slotSizeProperty.get() * 0.3); //TODO: can be done through constructor but need an external way to get radius
    this.setFill(Color.GOLDENROD);
  }

  /**
   * Dessin de la vue
   *
   * @param game PacmanGame jeu principal
   */
  public void draw(PacmanGame game) {
    this.setCenterX(player.getX());
    this.setCenterY(player.getY());
  }
}
