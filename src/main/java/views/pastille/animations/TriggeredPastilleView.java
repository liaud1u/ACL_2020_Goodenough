package views.pastille.animations;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import model.pastille.Pastille;
import model.pastille.PastilleType;
import model.pastille.ScorePastille;
import model.pastille.TimePastille;
import model.util.Util;

import java.util.Observable;
import java.util.Observer;

/**
 * Class for text when getting a pastille
 */
public class TriggeredPastilleView extends Group implements Observer {
  /**
   * Timeline for animation
   */
  private Timeline timeline;

  /**
   * Pastille linked
   */
  private Pastille pastille;

  /**
   * Text to print
   */
  private Label text;

  /**
   * Animate method, call when text needs to be printed
   */
  public void animate() {
    this.setOpacity(1);

    if (pastille.getType() == PastilleType.INVINCIBILITY) {
      double size = Util.slotSizeProperty.multiply(Util.RATIO_PASTILLE).get();
      Image image = new Image("pastilles/strong.png", size, size, true, false);
      this.getChildren().add(new ImageView(image));
    }

    this.timeline.play();
    this.timeline.setOnFinished(event -> this.getChildren().clear());
  }

  public void setValue(int value) {
    switch (pastille.getType()) {
      case SCORE: this.text.setText(String.format("+%s", value)); break;
      case TIME: this.text.setText(String.format("+%s sec", value)); break;
      case AMMO: case LANDMINE: this.text.setText("+1"); break;

    }
    this.setTranslateX(-this.text.getLayoutBounds().getWidth()/2);
    this.setTranslateY(-this.text.getLayoutBounds().getHeight()/2);
  }

  /**
   * Default constructor
   * @param x int x
   * @param y int y
   * @param value int value (text to print)
   * @param pastille Pastille linked
   */
  public TriggeredPastilleView(double x, double y, int value, Pastille pastille) {
    this.pastille = pastille;
    this.text = new Label();
    this.setOpacity(0);

    this.setValue(value);

    this.setLayoutX(Util.slotSizeProperty.multiply(x).get());
    this.setLayoutY(Util.slotSizeProperty.multiply(y).get());

    this.text.setTextFill(Color.ANTIQUEWHITE);
    this.text.getStyleClass().add("text_");

    this.timeline = new Timeline(
      new KeyFrame(
        Duration.seconds(1),
        new KeyValue(this.opacityProperty(), 0),
        new KeyValue(this.translateYProperty(), -70.)
      )
    );

    this.timeline.setCycleCount(1);

    this.getChildren().add(this.text);

    switch (pastille.getType()){
      case SCORE: setValue(((ScorePastille)pastille).getScore()); break;
      case TIME: setValue(((TimePastille)pastille).getTime()); break;
      case AMMO: case LANDMINE: this.text.setText("+1"); break;
    }
  }

  /**
   * On linked Pastille update, update the view
   * @param o
   * @param arg
   */
  @Override
  public void update(Observable o, Object arg) {
    animate();
  }
}
