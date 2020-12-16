package views.menus;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import model.BestScore;
import model.util.Util;

import java.util.Collection;

/**
 * BestScore view
 */
public class BestScoresView extends VBox {
  private Label title;

  /**
   * Initialize the view
   * @param bestScores
   */
  private void init(Collection<BestScore> bestScores) {
    // create the tile for the best scores, add its style class, and add it to the main vbox
    title = new Label("Best scores");
    title.getStyleClass().add("text_");
    this.getChildren().add(title);

    // for each best score, we add a label to the main vbox, with a style class, displaying the score and the player name
    for (BestScore bs : bestScores) {
      Label scoreLabel = new Label(String.format("%s: %d", bs.getPlayerName().toUpperCase(), bs.getScore()));
      scoreLabel.getStyleClass().add("text_");

      this.getChildren().add(scoreLabel);
    }

    while (this.getChildren().size() <= Util.maxBestScores) { //TODO Util this, and check in the Writer too
      Label scoreLabel = new Label("----: ----");
      scoreLabel.getStyleClass().add("text_");

      this.getChildren().add(scoreLabel);
    }

    this.setAlignment(Pos.CENTER);
  }

  /**
   * Add style
   * @param class_
   */
  public void addTitleStyleClass(String class_) {
    this.title.getStyleClass().add(class_);
  }

  /**
   * Constructor
   * @param bestScores BestScores collection
   */
  public BestScoresView(Collection<BestScore> bestScores) {
    this.init(bestScores);
  }
}
