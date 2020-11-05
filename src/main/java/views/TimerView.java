package views;

import fxengine.GameTimer;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.util.StringConverter;
import model.util.SpriteTools;
import model.util.Util;

import static model.util.Util.maxTimerSize;

/**
 * @author Ribeyrolles Matthieu
 * 05/11/2020, 16:28
 */
public class TimerView extends Group {
  private final Image SPRITE = new Image("score.png", 300, 24, true, false);

  private GameTimer gameTimer;
  private Text textView;
  private ImageView[] digitsImages;

  /*------------------------------------------------------------------
                              Methods
   ------------------------------------------------------------------*/

  // getters
  // setters

  public void setGameTimer(GameTimer gameTimer) {
    this.gameTimer = gameTimer;
  }

  // private

  /**
   * Init all the components, and bing the timer property to the text
   * */
  private void init() {
    this.textView = new Text();
    this.digitsImages = new ImageView[maxTimerSize];

    this.textView.textProperty().bindBidirectional(this.gameTimer.getTimerProperty(), new StringConverter<Number>() {
      @Override
      public String toString(Number number) {
        return String.format("Time left: %d seconds", gameTimer.getTimerProperty().get());
      }

      @Override
      public Number fromString(String s) {
        return null;
      }
    });

    // init the view properties (css and position)
    this.textView.setStyle(
      "-fx-font-size: 20;" +
        "-fx-fill: #fff"
    );
    this.setTranslateX(Util.MAZE_SIZE*Util.slotSizeProperty.get() + 10.);
    this.setTranslateY(50.);
    this.getChildren().add(this.textView);

//    for (int i = maxTimerSize-1; i >= 0; i--) {
//      this.digitsImages[i] = new ImageView(this.SPRITE);
//      this.digitsImages[i].setTranslateX(this.SPRITE.getWidth()/10 * i);
//      this.digitsImages[i].setViewport(
//        new Rectangle2D(
//          this.SPRITE.getWidth() / 10 * (this.gameTimer.getCurrentTimer() / (int) (Math.pow(10, i)) % 10),
//          0,
//          this.SPRITE.getWidth() / 10,
//          this.SPRITE.getHeight())
//      );
//
//      this.getChildren().add(this.digitsImages[i]);
//    }
  }
  // public

  public void draw() {
//    for (int i = maxTimerSize - 1; i >= 0; i--) {
//      this.digitsImages[i].setViewport(
//        new Rectangle2D(
//          this.SPRITE.getWidth() / 10 * Integer.parseInt(String.valueOf(this.gameTimer.getformatedTimer().charAt(i))),
//          0,
//          this.SPRITE.getWidth() / 10,
//          this.SPRITE.getHeight())
//      );
//
//    }
  }
   /*------------------------------------------------------------------
                            Constructors
   ------------------------------------------------------------------*/

  public TimerView(GameTimer gameTimer) {
    this.gameTimer = gameTimer;

    this.init();
  }
}
