package model.player;

/**
 * @author Ribeyrolles Matthieu
 * 03/11/2020, 14:07
 */
public enum Direction {
  UP    (0, -1),
  LEFT  (-1, 0),
  DOWN  (0, 1),
  RIGHT (1, 0);

  public int getX_dir() { return x_dir; }
  public int getY_dir() { return y_dir; }

  private int x_dir, y_dir;

  Direction(int x_dir, int y_dir) {
    this.x_dir = x_dir;
    this.y_dir = y_dir;
  }
}
