package views.animations;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import model.pastille.PastilleType;
import model.util.Util;

/**
 * @author Ribeyrolles Matthieu
 * 14/12/2020, 17:40
 */
public class TriggeredPastille extends Group {
  private Timeline timeline;
  private PastilleType type;
  private Label text;

  /*------------------------------------------------------------------
                              Methods
   ------------------------------------------------------------------*/

  // getters
  // setters
  // private
  // public

  public void animate() {
    this.setOpacity(1);

    if (this.type == PastilleType.INVINCIBILITY) {
      double size = Util.slotSizeProperty.multiply(Util.RATIO_PASTILLE).get();
      Image image = new Image("pastilles/strong.png", size, size, true, false);
      this.getChildren().add(new ImageView(image));
    }

    this.timeline.play();
    this.timeline.setOnFinished(event -> this.getChildren().clear());
  }

  public void setValue(int value) {
    switch (type) {
      case SCORE: this.text.setText(String.format("+%s", value)); break;
      case TIME: this.text.setText(String.format("+%s sec", value)); break;
      case AMMO: case LANDMINE: this.text.setText("+1"); break;

    }
    this.setTranslateX(-this.text.getLayoutBounds().getWidth()/2);
    this.setTranslateY(-this.text.getLayoutBounds().getHeight()/2);
  }
   
   /*------------------------------------------------------------------
                            Constructors
   ------------------------------------------------------------------*/
  public TriggeredPastille(double x, double y, int value, PastilleType type) {
    this.type = type;
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
  }
}
