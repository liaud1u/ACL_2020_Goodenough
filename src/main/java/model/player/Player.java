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

  /**
   * Past x and y coord of the player in the maze
   */
  private int xPrec, yPrec;

  /**
   * Boolean to know if the player is stuck
   */
  private boolean isStuck;

  /**
   * Direction courante du joueur
   */
  private Direction currentMoveDirection;

  /**
   * Player type (PLAYER1 or PLAYER2)
   */
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

  /**
   * Getter of the type of the player
   * @return PLAYER1 or PLAYER2
   */
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

  /**
   * Getter to know if the player is stuck
   * @return boolean isStuck
   */
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

  /**
   * Getter of the precedent x of the player
   * @return int precedent x
   */
  public int getxPrec() {
    return xPrec;
  }

  /**
   * Getter of the precedent y of the player
   * @return int precedent y
   */
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
