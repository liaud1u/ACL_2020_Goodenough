package model.player;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import model.Direction;
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
   * Boolean to know if the player is invincible
   */
  private boolean invincible;

  /**
   * Direction courante du joueur
   */
  private Direction currentMoveDirection;

  /**
   * Player type (PLAYER1 or PLAYER2)
   */
  private PlayerType type;

  /**
   * Timer to manage invincibility
   */
  private Timeline invincibleTimeline;

  /**
   * Constructeur du joueur
   *
   * @param game Game ou évolue le joueur
   */
  public Player(PacmanGame game, PlayerType type) {
    this.game = game;
    this.type = type;
    this.invincible = false;
    this.spawn();
    invincibleTimeline = new Timeline(
            new KeyFrame(Duration.seconds(Util.INVINCIBLE_TIME), ae -> {
              this.invincible = false;
            })
    );
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
    invincibleTimeline.stop();
    invincible = false;
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
   * Sets the player invincible (called when he eats an invincible pastille)
   */
  public void setInvincible() {
    this.invincibleTimeline.stop(); // stop the timer if another coin was eaten less than 5 seconds ago
    this.invincibleTimeline.play(); // starts a new timer for 5 seconds
    this.invincibleTimeline.setCycleCount(1); // the timer is executed 1 time
    this.invincible = true; // the player is currently invincible
  }

  /**
   *  Relaunch the invincibility timer after pause
   */
  public void restartTimer() {
    if(this.invincibleTimeline.getStatus() == Animation.Status.PAUSED)
      this.invincibleTimeline.play();
  }

  /**
   * Pause the invincibility timer when pausing
   */
  public void pauseTimer() {
    if(this.invincibleTimeline.getStatus() == Animation.Status.RUNNING)
      this.invincibleTimeline.pause();
  }

  /**
   * Get invincible timer
   */
  public Duration getTimerInvincible() {
    return invincibleTimeline.getCurrentTime();
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
    game.isEatingAPastille();
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

  /**
   * Getter de l'etat invincible du joueur
   * @return booleen etat invincible du joueur
   */
  public boolean isInvincible() {
    return invincible;
  }

  /**
   * Returns true when 75% of the invincibility time has been already given
   * @return boolean when 25% near the end
   */
  public boolean isNearEndInvincible() {
    return invincible && invincibleTimeline.getCurrentTime().greaterThan(Duration.seconds(Util.INVINCIBLE_TIME * 0.75));
  }
}
