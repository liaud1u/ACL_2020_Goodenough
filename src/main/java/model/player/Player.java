package model.player;

import model.PacmanGame;

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
  private double x, y;


  private double xPrec, yPrec;

  /**
   * Direction courante du joueur
   */
  private Direction currentMoveDirection = Direction.RIGHT;
  private final Direction lastKnownDirection = currentMoveDirection;

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
   * Fait évoluer le joueur sur le terrai
   */
  public void go() {
    //Si il n'y a pas de collisions, on met à jour les coordonnées
    //if (!game.willPlayerCollide() && !game.willPlayerCollideMob()) {
    //  game.willPlayerEatPastille();
    xPrec = x;
    yPrec = y;
    x += currentMoveDirection.getX_dir();
    y += currentMoveDirection.getY_dir();
    //}
  }

  public double getxPrec() {
    return xPrec;
  }

  public double getyPrec() {
    return yPrec;
  }

  /**
   * Getter de la coordonnée x du joueur
   *
   * @return double coordonnée x
   */
  public double getX() {
    return x;
  }

  /**
   * Getter de la coordonnée y du joueur
   *
   * @return double coordonnée y du joueur
   */
  public double getY() {
    return y;
  }
}
