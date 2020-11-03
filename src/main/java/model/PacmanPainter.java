package model;

import fxengine.GamePainter;
import javafx.scene.Group;
import model.player.Player;
import views.PlayerView;

/**
 * @author Horatiu Cirstea, Vincent Thomas
 *
 * afficheur graphique pour le game
 *
 */
public class PacmanPainter implements GamePainter {

  /**
   * la taille des cases
   */
  protected int WIDTH = 100;
  protected int HEIGHT = 100;

  private final Group root;
  private PacmanGame game;
  private PlayerView playerView;

  /**
   * appelle constructeur parent
   */
  public PacmanPainter(Group main, PacmanGame game) {
    this.root = main;
    this.game = game;

    this.playerView = new PlayerView(game.getPlayer());
    this.root.getChildren().add(this.playerView);
  }

  /**
   * methode  redefinie de Afficheur retourne une image du jeu
   */
  public void draw() {
    this.playerView.draw(this.game);
  }

  public int getWidth() {
    return WIDTH;
  }

  public int getHeight() {
    return HEIGHT;
  }

  public Group getRoot() {
    return root;
  }
}
