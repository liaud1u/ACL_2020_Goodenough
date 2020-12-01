package model.projectile;

import model.labyrinthe.Labyrinthe;
import model.Direction;
import model.monster.MonsterState;
import model.util.Util;

/**
 * Fireball object
 */
public class Fireball implements Projectile {

    /**
     * Direction of the fireball
     */
    private Direction direction;

    /**
     * Current x and y coords of the fireball
     */
    private int x, y;

    /**
     * Precedent x and y coords of the fireball
     */
    private int xPrec, yPrec;

    /**
     * If fireball has not already explose
     */
    private boolean alive = true;

    /**
     * If fireball has destroy monster or not
     */
    private boolean hasDestroyMonster = false;

    /**
     * Constructor of a fireball
     *
     * @param direction Direction of the fireball
     * @param x         int coord x of the fireball
     * @param y         int coord y of the fireball
     */
    public Fireball(Direction direction, int x, int y) {
        xPrec = x;
        yPrec = y;
        this.x = x;
        this.y = y;

        this.direction = direction;

        //If there's no direction, we take the default player sprite direction
        if (this.direction == Direction.IDLE)
            this.direction = Direction.DOWN;

    }

    /**
     * Getter to know if fireball is alive or not
     * @return boolean true if alive, false if not
     */
    public boolean isAlive() {
        return alive;
    }

    /**
     * Getter to know if fireball has destroy monster or not
     * @return boolean true if has, false if not
     */
    public boolean hasDestroyMonster() {
        return hasDestroyMonster;
    }

    @Override
    public int getY() {
        return y;
    }

    /**
     * Getter of the direction of the fireball
     *
     * @return Direction of the fireball
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * Make the fireball move
     */
    @Override
    public void move() {
        xPrec = x;
        x = (x + direction.getX_dir() + Util.MAZE_SIZE) % Util.MAZE_SIZE;
        yPrec = y;
        y = (y + direction.getY_dir() + Util.MAZE_SIZE) % Util.MAZE_SIZE;
    }

    /**
     * Getter of the x coord of the fireball
     * @return int x coord
     */
    @Override
    public int getX() {
        return x;
    }

    /**
     * Getter of the precedent x coord of the fireball
     * @return int precedent x coord
     */
    @Override
    public int getxPrec() {
        return xPrec;
    }

    /**
     * Destroy the fireball
     */
    @Override
    public void destroy() {
        alive=false;
    }

    @Override
    public void evolve(Labyrinthe labyrinthe) {
        if (labyrinthe.getCaseLabyrinthe(getX(), getY()).getMonstre() != null && labyrinthe.getCaseLabyrinthe(getX(), getY()).getMonstre().getLifeState()!= MonsterState.DEAD) {
            destroy();
            labyrinthe.getCaseLabyrinthe(getX(), getY()).getMonstre().destroy();
            hasDestroyMonster = true;
        }

        move();

        if (labyrinthe.getCaseLabyrinthe(getX(), getY()).estUnMur()) {
            destroy();
        }

        if (labyrinthe.getCaseLabyrinthe(getxPrec(), getyPrec()).getMonstre() != null && labyrinthe.getCaseLabyrinthe(getX(), getY()).getMonstre().getLifeState()!= MonsterState.DEAD) {
            destroy();
            labyrinthe.getCaseLabyrinthe(getxPrec(),getyPrec()).getMonstre().destroy();
            hasDestroyMonster = true;
        }

        if (labyrinthe.getCaseLabyrinthe(getX(), getY()).getMonstre() != null && alive && labyrinthe.getCaseLabyrinthe(getX(), getY()).getMonstre().getLifeState()!= MonsterState.DEAD) {
            destroy();
            labyrinthe.getCaseLabyrinthe(getX(), getY()).getMonstre().destroy();
            hasDestroyMonster = true;
        }
    }

    /**
     * Setter of the precX coord
     *
     * @param xPrec int
     */
    public void setxPrec(int xPrec) {
        this.xPrec = xPrec;
    }

    /**
     * Getter of the precedent y coord of the fireball
     * @return int precedent y
     */
    @Override
    public int getyPrec() {
        return yPrec;
    }

    /**
     * Setter of the precY coord
     *
     * @param yPrec int
     */
    public void setyPrec(int yPrec) {
        this.yPrec = yPrec;
    }

    /**
     * Return true
     * @return true
     */
    @Override
    public boolean isFireball() {
        return true;
    }
}
