package model;

import fxengine.GamePainter;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.util.Duration;
import model.monster.Monstre;
import views.*;
import views.menus.EndLevelView;
import views.menus.LostLevelView;
import views.menus.WonLevelView;

import java.sql.Time;
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
  private boolean inGame;
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
    this.inGame = true; //TODO: init to false when we have a menu

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
    if (! this.inGame) {  //check if we are in a end level view
      if (this.endLevelView.isReadyToStart()) {
        this.inGame = true;
        this.repaint();
        this.game.restartTimer(); // start the timer
      }
    } else {  // if we're in game

      if(this.game.isLost() || this.game.isWon()) {
        this.inGame = false;

        this.endLevelView = (this.game.isLost())  // set the end level view depending of the win or loss
          ? new LostLevelView(0, this.game.getScore(), this.game.getGameTimer().isOver())
          : new WonLevelView(0, this.game.getScore());

        this.root.getChildren().clear();  //clear current level
        this.root.getChildren().add(this.endLevelView); //and add the end level view

        this.game.pauseTimer(); //pause the timer
        this.game.resetTimer(); //then rest it
        if (this.game.isLost()) this.game.resetScore(); // and score too if we lost
      } else {
        this.playerView.draw(ratio);
        this.scoreView.draw();
        this.timerView.draw();

        for (MonstreView monstre : monstreView) {
          monstre.draw(ratio);
        }
      }
//      if(game.isWon()) {
//        game.pauseTimer();
//        this.repaint();
//        game.restartTimer();
//      }

//      if (transitionView != null) {
//        if (transitionView.timerOver()) {
//          this.root.getChildren().remove(transitionView);
//          this.transitionView = null;
//        }
//      }
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

    //TODO: add timer before the game starts
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
