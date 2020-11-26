package model.monster.movementstrategy;

import model.player.Direction;

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
}
