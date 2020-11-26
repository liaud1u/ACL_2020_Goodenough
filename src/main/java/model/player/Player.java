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

  private boolean isStuck;


  private int xPrec, yPrec;

  /**
   * Direction courante du joueur
   */
  private Direction currentMoveDirection;

  private PlayerType type;

  /**
   * Constructeur du joueur
   *
   * @param game Game ou évolue le joueur
   */
  public Player(PacmanGame game, PlayerType type) {
    this.game = game;
    this.type = type;
    this.spawn();
  }

  public PlayerType getType() {
    return type;
  }

  /**
   * réinitialise la position du joueur dans le labyrinthe
   */
  public void spawn() {
    y=yPrec=1;
    if(type==PlayerType.PLAYER1)
      xPrec = x   =  1;
    else
      xPrec = x   = Util.MAZE_SIZE-2;
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


  public boolean isStuck() { return this.isStuck; }

  /**
   * Fait évoluer le joueur sur le terrain
   */
  public void go() {
    //Si il n'y a pas de collisions, on met à jour les coordonnées
    game.isEatingAPastaga();
    game.setPlayerTurn(type==PlayerType.PLAYER1?1:2);
    if (!game.willPlayerCollide() && !game.willPlayerCollideMob()) {
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
