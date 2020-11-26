package model;

import fxengine.GamePainter;
import javafx.scene.Group;
import model.monster.Monster;
import model.util.Util;
import views.LabyrintheView;
import views.MonstreView;
import views.PlayerView;
import views.menus.EndLevelView;
import views.menus.LostLevelView;
import views.menus.RightSideView;
import views.menus.WonLevelView;
import views.projectileview.ProjectileView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Horatiu Cirstea, Vincent Thomas
 *
 * afficheur graphique pour le game
 *
 */
public class PacmanPainter implements GamePainter {

  /**
   * View of the end level menu
   */
  private EndLevelView endLevelView;

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
  private LabyrintheView labyrintheView;

  /**
   * Vue du joueur
   */
  private final PlayerView playerView;

  /**
   * View of the second player
   */
  private PlayerView secondPlayerView;

  /**
   * Vue des monstres
   */
  private List<MonstreView> monstreView;

  /**
   * Vue panneau latéral de droite
   */
  private final RightSideView rightSideView;

  /**
   * View of all prjectiles
   */
  private final ProjectileView projectileView;

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


    this.addMonstres();


    this.playerView = new PlayerView(game.getPlayer());
    this.root.getChildren().add(this.playerView);

    if(Util.player>1) {
      this.secondPlayerView = new PlayerView(game.getSecondPlayer());
      this.root.getChildren().add(this.secondPlayerView);
    }

    this.projectileView = new ProjectileView(game.getProjectiles());
    this.root.getChildren().add(this.projectileView);

    this.rightSideView = new RightSideView();
    this.root.getChildren().add(this.rightSideView);


  }


  /**
   * methode  redefinie de Afficheur retourne une image du jeu
   * @param ratio
   */
  public void draw(double ratio) {
    if (this.game.isLost() || this.game.isWon()) {
      this.endLevelView = (this.game.isLost())  // set the end level view depending of the win or loss
              ? new LostLevelView(this.game)
              : new WonLevelView(this.game);
      this.root.getChildren().clear();  //clear current level
      this.root.getChildren().add(this.endLevelView); //and add the end level view
    } else {
      if (this.game.hasJustChanged()) {
        this.repaint();
        this.game.setJustChanged(false);
      }

      this.playerView.draw(ratio);

      if(Util.player>1)
        this.secondPlayerView.draw(ratio);

      this.projectileView.draw(ratio);

      this.rightSideView.draw(game.getScore(), game.getGameTimer().getCurrentTimer());
      for (MonstreView monstre : monstreView) {
        monstre.draw(ratio);
      }

    }
  }



  /**
   * Methode qui redessine l'interface
   */
  private void repaint() {
    // On récupère le nouveau labyrinthe et les nouvelles pastilles
    labyrintheView = new LabyrintheView(game.getLabyrinthe());

    // On nettoie la liste des vues
    this.root.getChildren().clear();

    // On rajoute les nouvelles vues
    this.root.getChildren().add(labyrintheView);

    this.addMonstres();
    this.root.getChildren().add(playerView);

    if(Util.player>1) {
      this.root.getChildren().add(this.secondPlayerView);
    }

    this.root.getChildren().add(rightSideView);
    this.root.getChildren().add(this.projectileView);

  }

  /**
   * Add monster to the painter
   */
  private void addMonstres() {
    this.monstreView = new ArrayList<>();
    for (Monster m : game.getMonstres()) {
      MonstreView view = new MonstreView(m);
      monstreView.add(view);
      this.root.getChildren().add(view);
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

}
