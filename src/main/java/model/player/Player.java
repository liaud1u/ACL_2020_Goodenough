package model.player;

import views.PlayerView;

/**
 * @author Ribeyrolles Matthieu
 * 03/11/2020, 14:07
 */
public class Player {
  private final int SPAWN_X = 0; //TODO change the value
  private final int SPAWN_Y = 0; //TODO change the value

  private PlayerView playerView;
  private Direction currentMoveDirection;
  /*------------------------------------------------------------------
                              Methods
   ------------------------------------------------------------------*/

  // getters

  public Direction getCurrentMoveDirection() {
    return currentMoveDirection;
  }


  // private

  private void init() {
    //TODO: if playerView is not given as a parameter, create it at this Point
    this.playerView.setCenterX(this.SPAWN_X);
    this.playerView.setCenterY(this.SPAWN_Y);
  }

  // public

  public void setCurrentMoveDirection(Direction currentMoveDirection) {
    this.currentMoveDirection = currentMoveDirection;
  }

   /*------------------------------------------------------------------
                            Constructors
   ------------------------------------------------------------------*/

  public Player(PlayerView playerView) {
    this.playerView = playerView;
    this.init();
  }
}
