package model;

import fxengine.GamePainter;
import fxengine.GameTimer;
import javafx.scene.Group;
import javafx.scene.Node;
import model.monster.Monstre;
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
  private  Group root;

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
  private  PlayerView playerView;

  /**
   * Vue des pastilles
   */
  private List<PastilleView> pastillesView;

  /**
   * Vue des monstres
   */
    private List<MonstreView> monstreView;
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


    this.addPastilles();
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
   */
  public void draw() {
    // En cas de victoire, on change de labyrinthe et on génère de nouvelles pastilles.
    if(game.getGameState().getState() == PacmanGameState.EtatJeu.VICTOIRE) {
      this.repaint();
    }
    for(PastilleView p : pastillesView)  p.draw();
   // for(MonstreView mv : monstreView)
    this.playerView.draw();
    this.scoreView.draw();
    this.timerView.draw();
  }

  /**
   * Methode qui redessine l'interface
   */
  private void repaint() {

    // On récupère le nouveau labyrinthe et les nouvelles pastilles
    labyrintheView = new LabyrintheView(game.getLabyrinthe());
    //pastillesView = new PastillesView(game.getPastille());

    // On nettoie la liste des vues
    this.root.getChildren().clear();

    // On rajoute les nouvelles vues
    this.root.getChildren().add(labyrintheView);
    this.addPastilles();
    this.addMonstres();
    this.root.getChildren().add(scoreView);
    this.root.getChildren().add(playerView);
    this.root.getChildren().add(timerView);
  }


  private void addPastilles() {
    this.pastillesView = new ArrayList<>();
    for(Pastille p : game.getPastille()) {
      PastilleView view = new PastilleView(p,p.getX(), p.getY());
      pastillesView.add(view);
      this.root.getChildren().add(view);
    }
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
