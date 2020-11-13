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

    public RandomMovementStrategy(Monstre monstre, PacmanGame game) {
        this.monstre = monstre;
        this.game = game;
    }

    @Override
    public void move() {

        System.out.println("MOVE");

        Labyrinthe labyrinthe = game.getLabyrinthe();
        Case monsterLocation = labyrinthe.getCaseLabyrinthe(monstre.getX(), monstre.getY());


        chooseRandomDirection();

    }

    public void chooseRandomDirection() {
        ArrayList<Direction> directionToFreeCase = new ArrayList<>();
        Labyrinthe labyrinthe = game.getLabyrinthe();
        Case monsterLocation = labyrinthe.getCaseLabyrinthe(monstre.getX(), monstre.getY());

        if (monsterLocation.getX() - 1 >= 0 && !labyrinthe.getCaseLabyrinthe(monsterLocation.getX() - 1, monsterLocation.getY()).estUnMur()) {
            directionToFreeCase.add(Direction.LEFT);
        }
        if (monsterLocation.getY() - 1 >= 0 && !labyrinthe.getCaseLabyrinthe(monsterLocation.getX(), monsterLocation.getY() - 1).estUnMur()) {
            directionToFreeCase.add(Direction.UP);
        }
        if (monsterLocation.getX() + 1 < Util.MAZE_SIZE - 1 && !labyrinthe.getCaseLabyrinthe(monsterLocation.getX() + 1, monsterLocation.getY()).estUnMur()) {
            directionToFreeCase.add(Direction.RIGHT);
        }
        if (monsterLocation.getY() + 1 < Util.MAZE_SIZE - 1 && !labyrinthe.getCaseLabyrinthe(monsterLocation.getX(), monsterLocation.getY() + 1).estUnMur()) {
            directionToFreeCase.add(Direction.DOWN);
        }

        Direction direction = directionToFreeCase.get(RandomGenerator.getRandomValue(directionToFreeCase.size()));

        //System.out.println(direction);

    }
}
