package model.monster.movementstrategy;

import model.player.Direction;

/**
 * Interface for the Strategy design pattern
 */
public interface MovementStrategy {

    /**
     * Make the monster move
     */
    void move();

    /**
     * Getter of the current direction
     * @return Direction current direction
     */
    Direction getDirection();
}
