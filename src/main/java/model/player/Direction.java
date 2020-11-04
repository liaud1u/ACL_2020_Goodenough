package model.player;

/**
 * Différentes directions que peut prendre le joueur
 */
public enum Direction {
  UP(0, -1),
  LEFT(-1, 0),
  DOWN(0, 1),
  RIGHT(1, 0);

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
}
