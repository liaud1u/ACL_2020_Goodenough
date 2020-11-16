package model.player;

import model.PacmanGame;
import model.util.Util;

/**
 * Classe du joueur
 */
public class Player {
  /**
   * PacmanGame jeu princial ou évolue le joueur
   */
  private final PacmanGame game;

  /**
   * Coordonnées actuelles du joueur dans le labyrinthe
   */
  private int x, y;


  private int xPrec, yPrec;

  /**
   * Direction courante du joueur
   */
  private Direction currentMoveDirection = Direction.RIGHT;

  /**
   * Constructeur du joueur
   *
   * @param game Game ou évolue le joueur
   */
  public Player(PacmanGame game) {
    this.game = game;

    // On récupère la moitié de la taille d'une case comme coordonnées par défaut
    xPrec = yPrec = x = y = 1;
  }

  /**
   * réinitialise la position du joueur dans le labyrinthe
   */
  public void respawn() {
    xPrec = yPrec = x = y = 1;
    this.isStuck = false;
    currentMoveDirection = Direction.IDLE;
  }
  /**
   * Getter de la direction de mouvement
   *
   * @return Direction direction du mouvement
   */
  public Direction getCurrentMoveDirection() {
    return currentMoveDirection;
  }

  /**
   * Setter de la direction en cours
   *
   * @param currentMoveDirection Direction direction à prendre
   */
  public void setCurrentMoveDirection(Direction currentMoveDirection) {
    this.currentMoveDirection = currentMoveDirection;
  }

  private boolean isStuck = false;
  public boolean isStuck() { return this.isStuck; }

  /**
   * Fait évoluer le joueur sur le terrain
   */
  public void go() {
    //Si il n'y a pas de collisions, on met à jour les coordonnées

    if (!game.willPlayerCollide() && !game.willPlayerCollideMob()) {
      game.isEatingAPastaga();
      xPrec = x;
      yPrec = y;
      x = (x + currentMoveDirection.getX_dir() + Util.MAZE_SIZE) % Util.MAZE_SIZE;
      y = (y + currentMoveDirection.getY_dir() + Util.MAZE_SIZE) % Util.MAZE_SIZE;
      this.isStuck = false;
    } else {
      this.isStuck = true;
    }
  }

  public int getxPrec() {
    return xPrec;
  }

  public int getyPrec() {
    return yPrec;
  }

  /**
   * Getter de la coordonnée x du joueur
   *
   * @return double coordonnée x
   */
  public int getX() {
    return x;
  }

  /**
   * Getter de la coordonnée y du joueur
   *
   * @return double coordonnée y du joueur
   */
  public int getY() {
    return y;
  }
}
