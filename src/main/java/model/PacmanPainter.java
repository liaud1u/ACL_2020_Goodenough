package model;

import fxengine.GamePainter;
import javafx.scene.Group;
import views.LabyrintheView;
import views.PastilleView;
import views.PlayerView;

import java.util.ArrayList;

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
  private final PacmanGame game;

  private final LabyrintheView labyrintheView;
  private final PlayerView playerView;
  private final ArrayList<PastilleView> pastillesView;

  /**
   * appelle constructeur parent
   */
  public PacmanPainter(Group main, PacmanGame game) {
    this.root = main;
    this.game = game;

    this.labyrintheView = new LabyrintheView(game.getLabyrinthe());
    this.root.getChildren().add(this.labyrintheView);

    Pastille[][] pastille = game.getPastille();

    pastillesView = new ArrayList<>();

    for (int i = 0; i < pastille.length; i++) {
      for (int j = 0; j < pastille.length; j++) {
        PastilleView view = new PastilleView(pastille[i][j], i, j);
        pastillesView.add(view);
        this.root.getChildren().add(view);
      }

    }

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

  public LabyrintheView getLabyrintheView() {
    return labyrintheView;
  }

  public PlayerView getPlayerView() {
    return playerView;
  }

  public ArrayList<PastilleView> getPastillesView() {
    return pastillesView;
  }
}
