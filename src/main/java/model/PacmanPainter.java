package model;

import fxengine.GamePainter;
import fxengine.GameTimer;
import javafx.scene.Group;
import model.util.Util;
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
  private TimerView timerView;

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
   * Liste des vues de Pastilles
   */
  private ArrayList<PastilleView> pastillesView;

  /**
   * Vue du score
   */
  private final ScoreView scoreView;

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

   this.generatePastilleView();



    this.scoreView = new ScoreView(game,300,24,0,0);
    this.root.getChildren().add(scoreView);

    this.playerView = new PlayerView(game.getPlayer());
    this.root.getChildren().add(this.playerView);

    this.timerView = new TimerView(this.game.getGameTimer());
    this.root.getChildren().add(this.timerView);
  }

  private void generatePastilleView() {
    List<Pastille> pastilles = game.getPastille();
    pastillesView = new ArrayList<>();
    for(Pastille p : pastilles) {
      PastilleView view = new PastilleView(p,p.getX(), p.getY());
      pastillesView.add(view);
      this.root.getChildren().add(view);
    }
  }
  /**
   * methode  redefinie de Afficheur retourne une image du jeu
   */
  public void draw() {
    // En cas de victoire, on change de labyrinthe et on génère de nouvelles pastilles.
    if(game.getGameState().getState() == PacmanGameState.EtatJeu.VICTOIRE) {

      int indexLaby = this.root.getChildren().indexOf(labyrintheView);

      this.root.getChildren().remove(labyrintheView);
      this.root.getChildren().removeAll(pastillesView);
      // On récupère le nouveau labyrinthe
      labyrintheView = new LabyrintheView(game.getLabyrinthe());
      generatePastilleView();
      // On remplace a la position de l'ancien dans la liste
      this.root.getChildren().set(indexLaby, labyrintheView);


    } else {
      this.playerView.draw();
      for (PastilleView pastilleView : pastillesView) {
        pastilleView.draw();
      }
      this.scoreView.draw();
      this.timerView.draw();
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
