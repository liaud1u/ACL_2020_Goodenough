package model.monster;

import model.PacmanGame;
import model.monster.movementstrategy.MovementStrategy;
import model.monster.movementstrategy.RandomMovementStrategy;

public class Monster {
    /**
     * PacmanGame jeu princial
     */
    private final PacmanGame game;

    private MovementStrategy movementStrategy;
    /**
     * Coordonnées actuelles du monstre dans le labyrinthe
     */
    private int x;
    private int y;

    private final GhostType type;

    private int xPrec, yPrec;

    private MonsterState lifeState = MonsterState.ALIVE;

    public Monster(PacmanGame game, int x, int y, GhostType type) {
        this.game = game;
        this.x = x;
        this.y = y;
        this.yPrec = y;
        this.xPrec = x;
        this.movementStrategy = new RandomMovementStrategy(this, game);
        this.type = type;
    }

    public void setMovementStrategy(MovementStrategy strategy) {
        this.movementStrategy = strategy;
    }

    public int getX() {
        return x;
    }

    public void destroy() {
        game.getLabyrinthe().getCaseLabyrinthe(x, y).setMonster(null);
        lifeState = MonsterState.DEAD;
    }

    public MonsterState getLifeState() {
        return lifeState;
    }

    public GhostType getType() {
        return type;
    }

    public void setX(int x) {
        game.getLabyrinthe().getCaseLabyrinthe(this.x, this.y).setMonster(null);
        this.xPrec = this.x;
        this.x = x;
        game.getLabyrinthe().getCaseLabyrinthe(x, y).setMonster(this);
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
        game.getLabyrinthe().getCaseLabyrinthe(this.x, this.y).setMonster(null);
        this.yPrec = this.y;
        this.y = y;
        game.getLabyrinthe().getCaseLabyrinthe(x, y).setMonster(this);
    }

    public void actionMovement() {
        movementStrategy.move();
    }


}
