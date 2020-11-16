package views;

import javafx.scene.Group;
import model.PacmanGame;
import model.util.SpriteTools;

import static model.util.Util.SCORE_SIZE;

/** Class for the display of the score
 * */
public class ScoreView extends Group {
  private PacmanGame game;  // The game

  private double width;
  private double heigth;

  /** @param game     (:{@link PacmanGame}) The current model
   *  @param width    (:int)
   *  @param height   (:int)
   *  */
  public ScoreView(PacmanGame game, int width, int height, int x, int y) {
    this.game = game;
    this.heigth = height;
    this.width = width;
  }

  /**
   * Méthode pour rafraichir le compteur
   */
  public void draw(){
    this.getChildren().clear(); //clear all the elements
    this.getChildren().addAll(SpriteTools.getSpritedNumber(this.game.getScore(), SCORE_SIZE, 20.)); // add the digits sprites for the current score
  }
}
