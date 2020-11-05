package fxengine;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.util.Duration;

import java.sql.Time;

/**
 * @author Ribeyrolles Matthieu
 * 05/11/2020, 16:01
 */
public class GameTimer {
  private Timeline timeline;  // the timer loop
  private IntegerProperty timerProperty;  // the timer

  /*------------------------------------------------------------------
                              Methods
   ------------------------------------------------------------------*/

  // getters


  /**
   * @return an integer value, the current time
   * */
  public int getCurrentTimer() {
    return this.timerProperty.get();
  }
  public String getformatedTimer() {
    return String.format("%03d", this.getCurrentTimer());
  }

  /**
   * @return an {@link IntegerProperty}, corresponding to the current time property
   * */
  public IntegerProperty getTimerProperty() { return this.timerProperty; }

  // setters

  /**
   * @param currentTimer (:int) = value which the new timer will be set for
   *                              Note that it will be set through an {@link IntegerProperty}
   * */
  public void setCurrentTimer(int currentTimer) { this.timerProperty.set(currentTimer); }

  // private
  /**
   * Init the timer loop ({@link Timeline}, loop that will subtract one second to the timer every second
   * */
  private void initTimer() {
    this.timeline = new Timeline(
      new KeyFrame(Duration.seconds(1), ae -> {
        this.timerProperty.set(this.timerProperty.subtract(1).get());
        this.checkTimerEnds();
      })
    );
    timeline.setCycleCount(Animation.INDEFINITE);
    this.play();
  }

  /**
   * Check if the timer has ends.
   * We check under 0 too, in case of we drop under (e.g. removeTime)
   * */
  private void checkTimerEnds() {
    if (this.timerProperty.get() <= 0) {
      this.timeline.stop();
//      System.exit(0); //TODO: end the game
    }
  }

  // public

  /**
   * @param timeToAdd (:int), the amount of seconds to add to the timer
   * */
  public void addTime(int timeToAdd) {
    this.timerProperty.set(this.timerProperty.add(timeToAdd).get());
  }

  /**
   * @param timeToRemove (:int), the amount of seconds to add to the timer
   * */
  public void removeTime(int timeToRemove) {
    this.timerProperty.set(this.timerProperty.subtract(timeToRemove).get());
    this.checkTimerEnds();
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
    this.timerProperty = new SimpleIntegerProperty(maxTimer);

    this.initTimer();
  }
}
