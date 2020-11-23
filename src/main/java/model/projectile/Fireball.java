package model.projectile;

import model.player.Direction;
import model.util.Util;

public class Fireball implements Projectile {
    private Direction direction;
    private int x, y, xPrec, yPrec;

    public Fireball(Direction direction, int x, int y) {
        this.x = x;
        this.y = y;

        this.direction = direction;

        if (this.direction == Direction.IDLE)
            this.direction = Direction.DOWN;
    }

    @Override
    public void move() {
        xPrec = x;
        x = (x + direction.getX_dir() + Util.MAZE_SIZE) % Util.MAZE_SIZE;
        yPrec = y;
        y = (y + direction.getY_dir() + Util.MAZE_SIZE) % Util.MAZE_SIZE;
    }

    @Override
    public void hit() {

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public void destroy() {

    }

    @Override
    public boolean isFireball() {
        return true;
    }
}
