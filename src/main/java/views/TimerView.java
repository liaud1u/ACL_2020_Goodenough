package views;

import fxengine.GameTimer;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import model.util.SpriteTools;
import model.util.Util;

import static model.util.Util.maxTimerSize;

/**
 * @author Ribeyrolles Matthieu
 * 05/11/2020, 16:28
 */
public class TimerView extends Group {
  private GameTimer gameTimer;

  /*------------------------------------------------------------------
                              Methods
   ------------------------------------------------------------------*/

  // getters
  // setters

  /**
   * @param gameTimer (:{@link GameTimer}, the new GameTmer to set to this timer view
   * */
  public void setGameTimer(GameTimer gameTimer) {
    this.gameTimer = gameTimer;
    this.init();
  }

  // private

  /**
   * Init all the components, and bing the timer property to the text
   * */
  private void init() {
    this.setTranslateX(Util.MAZE_SIZE*Util.slotSizeProperty.get() + 10.);
    this.setTranslateY(50.);

    ImageView[] digitsViews = SpriteTools.getSpritedNumber(this.gameTimer.getCurrentTimer(), maxTimerSize, 10);
    this.getChildren().addAll(digitsViews);
  }

  // public

  public void draw() {
    SpriteTools.updateSpritedDigits();
    this.getChildren().clear();
    this.getChildren().addAll(SpriteTools.getSpritedNumber(this.gameTimer.getCurrentTimer(), maxTimerSize, 10));
  }
   /*------------------------------------------------------------------
                            Constructors
   ------------------------------------------------------------------*/

  public TimerView(GameTimer gameTimer) {
    this.gameTimer = gameTimer;

    this.init();
  }
}
