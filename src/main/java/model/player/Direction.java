package model.player;

/**
 * Différentes directions que peut prendre le joueur
 */
public enum Direction {
  UP(0, -1),
  LEFT(-1, 0),
  DOWN(0, 1),
  RIGHT(1, 0),
  IDLE(0, 0);

  /**
   * Champs privés correspondant aux directions du vecteur
   */
  private final int x_dir;
  private final int y_dir;

  /**
   * Constructeur d'une direction
   *
   * @param x_dir vecteur X correspondant
   * @param y_dir vecteur Y correspondant
   */
  Direction(int x_dir, int y_dir) {
    this.x_dir = x_dir;
    this.y_dir = y_dir;
  }

  /**
   * Return the opposite of a direction
   * @return Direction opposite
   */
  public Direction opposite() {
    switch (this) {
      case RIGHT:
        return LEFT;
      case UP:
        return DOWN;
      case DOWN:
        return UP;
      case LEFT:
        return RIGHT;
      default:
        return IDLE;
    }
  }

  /**
   * Getter du vecteur de direction x
   *
   * @return int x
   */
  public int getX_dir() {
    return x_dir;
  }

  /**
   * Getter du vecteur de direction y
   *
   * @return int y
   */
  public int getY_dir() {
    return y_dir;
  }

  /**
   * Return Direction value of two dir
   * @param x x dir
   * @param y y dir
   * @return Direction
   */
  public static Direction valueOf(int x, int y){
    for(Direction d : values()){
      //System.out.println(x+" " +y);
      if(d.x_dir==x && d.y_dir == y)
        return d;
    }
    return IDLE;
  }
}
