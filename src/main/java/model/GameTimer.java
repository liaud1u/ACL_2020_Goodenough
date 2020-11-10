package model;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.util.Duration;
import model.util.Util;

/**
 * @author Ribeyrolles Matthieu
 * 05/11/2020, 16:01
 */
public class GameTimer {
  private Timeline timeline;  // the timer loop
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

  // setters

  public void setCurrentTimer(int currentTimer) { this.timer = currentTimer; }

  // private
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

  public void pause() { this.timeline.pause(); }
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
