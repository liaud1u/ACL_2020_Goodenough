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

  /*------------------------------------------------------------------
                              Methods
   ------------------------------------------------------------------*/


  /** Method displaying the painter, changing digits on screen.
   *  This method display sprites for digits
   *  */
  public void draw(int timeLeft) {
    this.getChildren().clear(); // remove all the elements
    this.getChildren().addAll(SpriteTools.getSpritedNumber(timeLeft, maxTimerSize, 10));  //  add the current digits to group root
  }

}
