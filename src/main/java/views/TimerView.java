package views;

import javafx.scene.Group;
import javafx.scene.image.ImageView;
import model.GameTimer;
import model.util.SpriteTools;
import model.util.Util;

import static model.util.Util.maxTimerSize;

/** The class implementing the view for the timer.
 * */
public class TimerView extends Group {
  private GameTimer gameTimer;  // the current timer to display

  /*------------------------------------------------------------------
                              Methods
   ------------------------------------------------------------------*/

  // getters
  // setters

  /** @param gameTimer (:{@link GameTimer}), the new GameTimer to set to this timer view
   * */
  public void setGameTimer(GameTimer gameTimer) {
    this.gameTimer = gameTimer;
    this.init();
  }

  // private

  /** Init all the components, and bing the timer property to the text
   * */
  private void init() {
    this.setTranslateX(Util.MAZE_SIZE*Util.slotSizeProperty.get() + 10.); //TODO: remove when new right side class created
    this.setTranslateY(50.);

    SpriteTools.setImageSize(300, 24);  //  define the sprites size TODO: delete hardcoded values
    ImageView[] digitsViews = SpriteTools.getSpritedNumber(this.gameTimer.getCurrentTimer(), maxTimerSize, 10); //define the current sprite to use (0 to 9 values)
    this.getChildren().addAll(digitsViews); // add digit sprites to group root
  }

  // public

  /** Method displaying the painter, changing digits on screen.
   *  This method display sprites for digits
   *  */
  public void draw() {
    this.getChildren().clear(); // remove all the elements
    this.getChildren().addAll(SpriteTools.getSpritedNumber(this.gameTimer.getCurrentTimer(), maxTimerSize, 10));  //  add the current digits to group root
  }
   /*------------------------------------------------------------------
                            Constructors
   ------------------------------------------------------------------*/

  /** @param gameTimer (:{@link GameTimer}). The constructor inits the timer
   * */
  public TimerView(GameTimer gameTimer) {
    this.gameTimer = gameTimer;
    this.init();
  }
}
