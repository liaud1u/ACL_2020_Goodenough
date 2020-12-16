package views.rightpanels;

import javafx.scene.Group;
import model.PacmanGame;
import model.util.SpriteTools;

import static model.util.Util.SCORE_SIZE;

/** Class for the display of the score
 * */
public class ScoreView extends Group {



  /**
   * Méthode pour rafraichir le compteur
   */
  public void draw(int score){
    this.getChildren().clear(); //clear all the elements
    this.getChildren().addAll(SpriteTools.getSpritedNumber((score), SCORE_SIZE, 20.)); // add the digits sprites for the current score
  }
}
