package model.monster.movementstrategy;

import model.player.Direction;

public class StaticMovementStrategy implements MovementStrategy {
    @Override
    public void move() {
    }

    @Override
    public Direction getDirection() {
        return Direction.IDLE;
    }
}
