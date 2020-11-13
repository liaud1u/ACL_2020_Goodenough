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

    public Monstre(PacmanGame game, int x, int y) {
        this.game = game;
        this.x = x;
        this.y = y;
        this.movementStrategy = new RandomMovementStrategy(this, game);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void actionMovement() {
        movementStrategy.move();
    }


}
