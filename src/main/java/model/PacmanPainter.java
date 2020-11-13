package model;

import fxengine.GamePainter;
import javafx.scene.Group;
import model.monster.Monstre;
import views.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Horatiu Cirstea, Vincent Thomas
 *
 * afficheur graphique pour le game
 *
 */
public class PacmanPainter implements GamePainter {
  private final TimerView timerView;

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
   * Vue du score
   */
  private final ScoreView scoreView;

  private TransitionView transitionView;

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


    //this.addPastilles();
    this.addMonstres();


    this.scoreView = new ScoreView(game,300,24,0,0);
    this.root.getChildren().add(this.scoreView);

    this.playerView = new PlayerView(game.getPlayer());
    this.root.getChildren().add(this.playerView);

    this.timerView = new TimerView(this.game.getGameTimer());
    this.root.getChildren().add(this.timerView);



  }


  /**
   * methode  redefinie de Afficheur retourne une image du jeu
   * @param ratio
   */
  public void draw(double ratio) {
    // En cas de victoire, on change de labyrinthe et on génère de nouvelles pastilles.
    if(game.isLost()) {
      game.pauseTimer();
      transitionView = new TransitionView("Perdu");
      game.resetTimer();
      game.resetScore();
      this.repaint();
      this.root.getChildren().add(transitionView);
      game.restartTimer();

    }
    if(game.isWon()) {
      game.pauseTimer();
      transitionView = new TransitionView("Gagné");
      this.repaint();
      this.root.getChildren().add(transitionView);
      game.restartTimer();
    }

    if (transitionView != null) {
      if (transitionView.timerOver()) {
        this.root.getChildren().remove(transitionView);
        this.transitionView = null;
      }
    }

    this.playerView.draw(ratio);
    this.scoreView.draw();
    this.timerView.draw();

    for (MonstreView monstre : monstreView) {
      monstre.draw(ratio);
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
    this.root.getChildren().add(scoreView);
    this.root.getChildren().add(playerView);
    this.root.getChildren().add(timerView);
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
