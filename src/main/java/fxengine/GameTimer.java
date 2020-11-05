package fxengine;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

/**
 * @author Ribeyrolles Matthieu
 * 05/11/2020, 16:01
 */
public class GameTimer {
  private int maxTimer; // max timer used as referent
  private int currentTimer; // current timer, the one looping
  private Timeline timeline;  // the timer itself

  /*------------------------------------------------------------------
                              Methods
   ------------------------------------------------------------------*/

  // getters

  public int getCurrentTimer() {
    return currentTimer;
  }


  // setters

  public void setCurrentTimer(int currentTimer) {
    this.currentTimer = currentTimer;
  }

  // private
  private void initTimer() {
    this.timeline = new Timeline(new KeyFrame(
      Duration.seconds(1),
      ae -> {
        this.currentTimer--;
        if (this.currentTimer <= 0) System.exit(0); //TODO: end the game
      }
    ));
    this.timeline.setCycleCount(Animation.INDEFINITE);
  }

  // public
  public void pause() { this.timeline.pause(); }
  public void play() { this.timeline.play(); }
  public void reset() {
    this.timeline.stop();
    this.initTimer();
    this.play();
  }

   /*------------------------------------------------------------------
                            Constructors
   ------------------------------------------------------------------*/

  public GameTimer(int maxTimer) {
    this.maxTimer = maxTimer;
    this.currentTimer = this.maxTimer;

    this.initTimer();
  }
}
