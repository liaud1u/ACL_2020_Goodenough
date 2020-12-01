package model.movementstrategy;

import model.Direction;

/**
 * Class for the static movement strategy (monster will not move)
 */
public class StaticMovementStrategy implements MovementStrategy {

    /**
     * Make the monster not move
     */
    @Override
    public void move() {
    }

    /**
     * Get the current direction ( always IDLE)
     * @return Direction.IDLE
     */
    @Override
    public Direction getDirection() {
        return Direction.IDLE;
    }

    @Override
    public boolean isRespawn() {
        return false;
    }

    @Override
    public void chooseDirection() {
    }

    /**
     * Setter of the direction of the strategy
     * @param direction direction
     */
    @Override
    public void setDirection(Direction direction) {
    }
}
