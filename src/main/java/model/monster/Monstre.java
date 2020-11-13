package model.monster;

import model.PacmanGame;
import model.monster.movementstrategy.MovementStrategy;
import model.monster.movementstrategy.RandomMovementStrategy;

public class Monstre {
    /**
     * PacmanGame jeu princial
     */
    private final PacmanGame game;

    private final MovementStrategy movementStrategy;
    /**
     * Coordonnées actuelles du monstre dans le labyrinthe
     */
    private int x;
    private int y;

    private final GhostType type;

    private int xPrec, yPrec;

    public Monstre(PacmanGame game, int x, int y, GhostType type) {
        this.game = game;
        this.x = x;
        this.y = y;
        this.yPrec = y;
        this.xPrec = x;
        this.movementStrategy = new RandomMovementStrategy(this, game);
        this.type = type;
    }

    public int getX() {
        return x;
    }


    public GhostType getType() {
        return type;
    }

    public void setX(int x) {
        this.xPrec = this.x;
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public int getxPrec() {
        return xPrec;
    }

    public MovementStrategy getMovementStrategy() {
        return movementStrategy;
    }

    public int getyPrec() {
        return yPrec;
    }

    public void setY(int y) {
        this.yPrec = this.y;
        this.y = y;
    }

    public void actionMovement() {
        movementStrategy.move();
    }


}
