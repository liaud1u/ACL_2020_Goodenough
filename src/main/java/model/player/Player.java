package model.player;

/**
 * @author Ribeyrolles Matthieu
 * 03/11/2020, 14:07
 */
public class Player {
  private final int SPAWN_X = 0; //TODO change the value
  private final int SPAWN_Y = 0; //TODO change the value

  private int x;
  private int y;

  private Direction currentMoveDirection = Direction.LEFT;
  /*------------------------------------------------------------------
                              Methods
   ------------------------------------------------------------------*/

  // getters

  public Direction getCurrentMoveDirection() {
    return currentMoveDirection;
  }


  // private

  public Player() {
    this.init();
  }

  // public

  private void init() {
    //TODO: if playerView is not given as a parameter, create it at this Point
    x = this.SPAWN_X;
    y = this.SPAWN_Y;
  }

  public void setCurrentMoveDirection(Direction currentMoveDirection) {
    this.currentMoveDirection = currentMoveDirection;
  }

  public int getX() {
    return x;
  }

  /*------------------------------------------------------------------
                            Constructors
   ------------------------------------------------------------------*/

  public int getY() {
    return y;
  }
}
