package model.monster.movementstrategy;

import model.Case;
import model.Labyrinthe;
import model.PacmanGame;
import model.monster.Monstre;
import model.player.Direction;
import model.util.RandomGenerator;
import model.util.Util;

import java.util.ArrayList;

public class RandomMovementStrategy implements MovementStrategy {

    private final PacmanGame game;
    private final Monstre monstre;
    private Direction direction;
    private ArrayList<Direction> previousPossibleDirection;
    private Direction previousDirection;

    public RandomMovementStrategy(Monstre monstre, PacmanGame game) {
        this.monstre = monstre;
        this.game = game;


        chooseRandomDirection();
        previousDirection = direction;
    }

    @Override
    public void move() {


        Labyrinthe labyrinthe = game.getLabyrinthe();
        Case monsterLocation = labyrinthe.getCaseLabyrinthe(monstre.getX(), monstre.getY());

        int nextX = monstre.getX() + direction.getX_dir();
        int nextY = monstre.getY() + direction.getY_dir();


        if (!(nextX >= 0 && nextX < Util.MAZE_SIZE - 1 && nextY >= 0 && nextY < Util.MAZE_SIZE - 1 && previousPossibleDirection.equals(labyrinthe.getFreeDirection(monstre.getX(), monstre.getY())))) {


            chooseRandomDirection();

            /*if (!(nextX >= 0 && nextX < Util.MAZE_SIZE - 1 && nextY >= 0 && nextY < Util.MAZE_SIZE - 1)) {
                chooseRandomDirection();
            }*/


            nextX = monstre.getX() + direction.getX_dir();
            nextY = monstre.getY() + direction.getY_dir();
        }

        monstre.setX(nextX);
        monstre.setY(nextY);


    }


    public void chooseRandomDirection() {
        previousDirection = direction;
        Labyrinthe labyrinthe = game.getLabyrinthe();
        Case monsterLocation = labyrinthe.getCaseLabyrinthe(monstre.getX(), monstre.getY());

        ArrayList<Direction> directions = labyrinthe.getFreeDirection(monsterLocation.getX(), monsterLocation.getY());

        direction = directions.get(RandomGenerator.getRandomValue(directions.size()));

        if (previousDirection != null && direction.equals(previousDirection.opposite()) && directions.size() != 1)
            chooseRandomDirection();

        previousPossibleDirection = directions;
    }

    public Direction getDirection() {
        return direction;
    }
}
