package model;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.util.Duration;
import model.util.Util;

/**
 * Class for the GameTimer
 */
public class GameTimer {
  /**
   * Timer of the loop
   */
  private Timeline timeline;

  /**
   * Current time
   */
  private int timer;

  /*------------------------------------------------------------------
                              Methods
   ------------------------------------------------------------------*/

  // getters

  /**
   * @return an integer value, the current time
   * */
  public int getCurrentTimer() {
    return this.timer;
  }


  /**
   * Set the current time
   * @param currentTimer
   */
  public void setCurrentTimer(int currentTimer) { this.timer = currentTimer; }

  /**
   * Init the timer
   */
  private void initTimer() {
    this.timer = Util.timer;

    this.timeline = new Timeline(
      new KeyFrame(Duration.seconds(1), ae -> {
        this.timer--;
      })
    );
    timeline.setCycleCount(Animation.INDEFINITE);
    this.play();
  }

  /**
   * Check if the timer has ends.
   * We check under 0 too, in case of we drop under (e.g. removeTime)
   * */
  public boolean isOver() {
    return this.timer <= 0;
  }

  // public

  /**
   * @param timeToAdd (:int), the amount of seconds to add to the timer
   * */
  public void addTime(int timeToAdd) {
    this.timer += timeToAdd;
  }

  /**
   * @param timeToRemove (:int), the amount of seconds to add to the timer
   * */
  public void removeTime(int timeToRemove) {
    this.timer -= timeToRemove;
    if (this.isOver()) this.timer = 0;
  }

  /**
   * Pause the timer
   */
  public void pause() { this.timeline.pause(); }

  /**
   * Resume the timer
   */
  public void play() { this.timeline.play(); }

  /**
   *  Reset the timer.
   * */
  public void reset() {
    this.timeline.stop();
    this.initTimer();
    this.play();
  }

   /*------------------------------------------------------------------
                            Constructors
   ------------------------------------------------------------------*/

  /**
   * @param maxTimer (:int), the value in seconds the timer will be set to
   * */
  public GameTimer(int maxTimer) {
    this.initTimer();
  }
}
