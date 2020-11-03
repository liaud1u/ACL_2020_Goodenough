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
   * Groupe racine ou ajouter les vues
   */
  private final Group root;
  /**
   * Jeu principal
   */
  private final PacmanGame game;
  /**
   * Vue du labyrinthe
   */
  private final LabyrintheView labyrintheView;
  /**
   * Vue du joueur
   */
  private final PlayerView playerView;
  /**
   * Liste des vues de Pastilles
   */
  private final ArrayList<PastilleView> pastillesView;
  /**
   * Largeur
   */
  protected int WIDTH = 100;
  /**
   * Hauteur
   */
  protected int HEIGHT = 100;

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
    for (PastilleView pastilleView : pastillesView) {
      pastilleView.draw();
    }
  }

  /**
   * Renvoie la taille
   *
   * @return int Width
   */
  public int getWidth() {
    return WIDTH;
  }

  /**
   * Renvoie la taille
   *
   * @return int Height
   */
  public int getHeight() {
    return HEIGHT;
  }

  /**
   * Getter du groupe racine
   *
   * @return Group racine
   */
  public Group getRoot() {
    return root;
  }

  /**
   * Getter de la vue du labyrinthe
   *
   * @return LabyrintheView vue du labyrinthe
   */
  public LabyrintheView getLabyrintheView() {
    return labyrintheView;
  }

  /**
   * Getter de la vue du joueur
   *
   * @return PlayerView vue du joueur
   */
  public PlayerView getPlayerView() {
    return playerView;
  }

  /**
   * Getter de la liste des vues de pastilles
   *
   * @return Liste des vues de pastilles
   */
  public ArrayList<PastilleView> getPastillesView() {
    return pastillesView;
  }
}
