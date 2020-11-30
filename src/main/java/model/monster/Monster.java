package model.monster;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import model.PacmanGame;
import model.monster.movementstrategy.MovementStrategy;
import model.monster.movementstrategy.RandomMovementStrategy;

/**
 * Class for the Monster object
 */
public class Monster {
    /**
     * PacmanGame jeu princial
     */
    private final PacmanGame game;

    /**
     * Movement strategy of the monster
     */
    private MovementStrategy movementStrategy;

    /**
     * x coord of the monster
     */
    private int x;

    /**
     * y coord of the monster
     */
    private int y;

    /**
     * previous x coord of the monster
     */
    private int xPrec;

    /**
     * previous y coord of the monster
     */
    private int yPrec;

    /**
     * GhostType (color of the ghost)
     */
    private final GhostType type;

    /**
     * Lifestate of the ghost (ALIVE, DEAD...)
     */
    private MonsterState lifeState = MonsterState.ALIVE;

    /**
     * Timer to manage fear switching
     */
    private Timeline fearTimeline;

    /**
     * Constructor of the monster
     * @param game PacmanGame
     * @param x coord x of the monster at the beginning
     * @param y coord y of the monster at the beginning
     * @param type type of the monster (color)
     */
    public Monster(PacmanGame game, int x, int y, GhostType type) {
        this.game = game;
        this.x = x;
        this.y = y;
        this.yPrec = y;
        this.xPrec = x;
        this.movementStrategy = new RandomMovementStrategy(this, game.getLabyrinthe());
        this.type = type;
        fearTimeline = new Timeline(
                new KeyFrame(Duration.seconds(0.5), ae -> {
                    lifeState = (lifeState==MonsterState.FEAR1) ? MonsterState.FEAR2 : MonsterState.FEAR1;
                })
        );
    }

    /**
     * Setter of the movement strategy of the ghost
     * @param strategy Strategy of movement
     */
    public void setMovementStrategy(MovementStrategy strategy) {
        this.movementStrategy = strategy;
    }

    /**
     * Getter of the coord x of the monster
     * @return int x
     */
    public int getX() {
        return x;
    }

    /**
     * Destroy the monster
     */
    public void destroy() {
        fearTimeline.stop();
        game.getLabyrinthe().getCaseLabyrinthe(x, y).addMonster(null);
        lifeState = MonsterState.DEAD;
    }


    /**
     * Enable the fear mode for the mob
     */
    public void setFear() {
        if(lifeState == MonsterState.ALIVE) {
            lifeState = MonsterState.FEAR1;
            if(fearTimeline.getStatus() != Animation.Status.RUNNING) {
                fearTimeline.setCycleCount(Animation.INDEFINITE);
                fearTimeline.play();
            }
        }
    }

    /**
     * Pause the invincibility timer when pausing
     */
    public void pauseTimer() {
        if(fearTimeline.getStatus() == Animation.Status.RUNNING) 
            this.fearTimeline.pause();

    }

    /**
     *  Relaunch the invincibility timer after pause
     */
    public void restartTimer() {
        if(fearTimeline.getStatus() == Animation.Status.PAUSED)
            this.fearTimeline.play();

    }
    /**
     * Remove the fear mode for the mob
     */
    public void removeFear(){
        fearTimeline.stop();
        if(lifeState == MonsterState.FEAR1 || lifeState == MonsterState.FEAR2) {
            lifeState = MonsterState.ALIVE;
        }

    }

    /**
     * Setter for the monster state
     */
    public void setMonsterState(MonsterState state) {
        this.lifeState = state;
    }

    /**
     * Get the lifestate of the monster
     * @return Lifestate
     */
    public MonsterState getLifeState() {
        return lifeState;
    }

    /**
     * Get the type (color) of the monster
     * @return GhostType
     */
    public GhostType getType() {
        return type;
    }

    /**
     * Setter of the x coord of the monster
     * @param x int x coord
     */
    public void setX(int x) {
        game.getLabyrinthe().getCaseLabyrinthe(this.x, this.y).addMonster(null);
        this.xPrec = this.x;
        this.x = x;
        game.getLabyrinthe().getCaseLabyrinthe(x, y).addMonster(this);
    }

    /**
     * Getter of the y coord of the monster
     * @return int y
     */
    public int getY() {
        return y;
    }

    /***
     * Getter of the precedent x coord of the monster
     * @return int precedent x
     */
    public int getxPrec() {
        return xPrec;
    }

    /**
     * Getter of the current movement strategy
     * @return MovementStrategy current
     */
    public MovementStrategy getMovementStrategy() {
        return movementStrategy;
    }

    /**
     * Getter of the precedent y coord
     * @return int precedent y
     */
    public int getyPrec() {
        return yPrec;
    }

    /**
     * Setter of the y coord of the monster
     * @param y coord y
     */
    public void setY(int y) {
        game.getLabyrinthe().getCaseLabyrinthe(this.x, this.y).addMonster(null);
        this.yPrec = this.y;
        this.y = y;
        game.getLabyrinthe().getCaseLabyrinthe(x, y).addMonster(this);
    }

    /**
     * Make the monster move
     */
    public void actionMovement() {
        movementStrategy.move();
    }
}
