package model.player;

import views.PlayerView;

/**
 * @author Ribeyrolles Matthieu
 * 03/11/2020, 14:07
 */
public class Player {
  private final int SPAWN_X = 0; //TODO change the value
  private final int SPAWN_Y = 0; //TODO change the value

  private Direction currentMoveDirection = Direction.RIGHT;
  /*------------------------------------------------------------------
                              Methods
   ------------------------------------------------------------------*/

  // getters

  public Direction getCurrentMoveDirection() {
    return currentMoveDirection;
  }

  // private
  // public

  public void setCurrentMoveDirection(Direction currentMoveDirection) {
    this.currentMoveDirection = currentMoveDirection;
  }

  /*------------------------------------------------------------------
                            Constructors
   ------------------------------------------------------------------*/

  public Player() {
  }
}
