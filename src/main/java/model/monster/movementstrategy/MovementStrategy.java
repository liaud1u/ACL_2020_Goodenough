package model.monster.movementstrategy;

import model.player.Direction;

public interface MovementStrategy {


    void move();

    Direction getDirection();
}
