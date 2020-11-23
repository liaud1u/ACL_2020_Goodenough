package model;

import fxengine.GamePainter;
import javafx.scene.Group;
import model.monster.Monstre;
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
   * Vue des monstres
   */
  private List<MonstreView> monstreView;


    /**
     * Vue panneau latéral de droite
     */
    private final RightSideView rightSideView;

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

      this.rightSideView = new RightSideView();
      this.root.getChildren().add(this.rightSideView);


      this.projectileView = new ProjectileView(game.getProjectiles());
      this.root.getChildren().add(this.projectileView);
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
      this.rightSideView.draw(game.getScore(), game.getGameTimer().getCurrentTimer());
      for (MonstreView monstre : monstreView) {
        monstre.draw(ratio);
      }

      this.projectileView.draw(ratio);
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
    this.root.getChildren().add(rightSideView);
  }




  private void addMonstres() {
    this.monstreView = new ArrayList<>();
    for(Monstre m : game.getMonstres()) {
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
