package model.player;

import model.PacmanGame;
import model.util.Util;

/**
 * @author Ribeyrolles Matthieu
 * 03/11/2020, 14:07
 */
public class Player {
  private final PacmanGame game;
  private double x, y;

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

  public Player(PacmanGame game) {
    this.game = game;

    x = Util.slotSizeProperty.intValue() / 2;
    y = Util.slotSizeProperty.intValue() / 2;
  }

  public void setCurrentMoveDirection(Direction currentMoveDirection) {
    this.currentMoveDirection = currentMoveDirection;

  }

  public void go() {
    if (!game.willPlayerCollide()) {
      game.willPlayerEatPastille();

      x += currentMoveDirection.getX_dir() * Util.speedDifficulty;
      y += currentMoveDirection.getY_dir() * Util.speedDifficulty;
      }
  }

  public double getX() {
    return x;
  }

  /*------------------------------------------------------------------
                            Constructors
   ------------------------------------------------------------------*/

  public double getY() {
    return y;
  }
}
