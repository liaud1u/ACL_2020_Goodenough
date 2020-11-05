package views;

import fxengine.Game;
import fxengine.GameTimer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;
import model.util.Util;

/**
 * @author Ribeyrolles Matthieu
 * 05/11/2020, 16:28
 */
public class TimerView extends Text {
  private GameTimer gameTimer;
  /*------------------------------------------------------------------
                              Methods
   ------------------------------------------------------------------*/

  // getters
  // setters

  public void setGameTimer(GameTimer gameTimer) {
    this.gameTimer = gameTimer;
  }

  // private
  private void init() {
    this.textProperty().bindBidirectional(this.gameTimer.getTimerProperty(), new StringConverter<Number>() {
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
    this.setStyle(
      "-fx-font-size: 20;" +
        "-fx-fill: #fff"
    );
    this.setTranslateX(Util.MAZE_SIZE*Util.slotSizeProperty.get() + 10.);
    this.setTranslateY(50.);
  }
  // public
   
   /*------------------------------------------------------------------
                            Constructors
   ------------------------------------------------------------------*/

  public TimerView(GameTimer gameTimer) {
    this.gameTimer = gameTimer;

    this.init();
  }
}
