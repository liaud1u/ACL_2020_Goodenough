package model.movementstrategy;

import model.Direction;

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

    /**
     * Setter of the direction of the strategy
     * @param direction direction
     */
    void setDirection(Direction direction);

    /**
     * Return true if the movement strategy is a respawn
     * @return boolean true if a respawn strategy
     */
    boolean isRespawn();

    /**
     * Choose a direction
     */
    void chooseDirection();
}
