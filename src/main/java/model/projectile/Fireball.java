package model.projectile;

import model.player.Direction;
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

    @Override
    public void move() {
        xPrec = x;
        x = (x + direction.getX_dir() + Util.MAZE_SIZE) % Util.MAZE_SIZE;
        yPrec = y;
        y = (y + direction.getY_dir() + Util.MAZE_SIZE) % Util.MAZE_SIZE;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getxPrec() {
        return xPrec;
    }

    @Override
    public void destroy() {
    }

    /**
     * Setter of the precX coord
     *
     * @param xPrec int
     */
    public void setxPrec(int xPrec) {
        this.xPrec = xPrec;
    }

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

    @Override
    public boolean isFireball() {
        return true;
    }
}
