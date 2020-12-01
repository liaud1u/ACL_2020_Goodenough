package model.movementstrategy;

import model.labyrinthe.Case;
import model.labyrinthe.Labyrinthe;
import model.monster.Monster;
import model.Direction;
import model.util.RandomGenerator;
import model.util.Util;

import java.util.ArrayList;

/**
 * Class for the Random movement Strategy of the Monster, with this strategy, the monster will moove randomly in the maze
 */
public class RandomMovementStrategy implements MovementStrategy {

    /**
     * Maze where the Monster evolve
     */
    private final Labyrinthe labyrinthe;

    /**
     * Monster wich evolve in the maze
     */
    private final Monster monstre;

    /**
     * Current direction of the monster
     */
    private Direction direction = Direction.IDLE;

    /**
     * Arraylist of all the previous possible direction (to avoid the monster to go back)
     */
    private ArrayList<Direction> previousPossibleDirection;

    /**
     * Previous direction (to avoid the monster to go back)
     */
    private Direction previousDirection = Direction.IDLE;

    /**
     * Constructor of the strategy
     * @param monstre Monster wich evolve with this strategy
     * @param labyrinthe Maze where the monster evolve
     */
    public RandomMovementStrategy(Monster monstre, Labyrinthe labyrinthe) {
        this.monstre = monstre;
        this.labyrinthe = labyrinthe;
    }

    /**
     * Make the monster move
     */
    @Override
    public void move() {

        //On détermine le prochain x et y
        int nextX = (monstre.getX() + direction.getX_dir() + Util.MAZE_SIZE) % Util.MAZE_SIZE;
        int nextY = (monstre.getY() + direction.getY_dir() + Util.MAZE_SIZE) % Util.MAZE_SIZE;

        //Si le monstre est statique, on choisis une direction
        if (direction == Direction.IDLE)
            chooseRandomDirection();

        //Si la direction qu'empruntait le monstre précedement n'est plus disponible, on en choisis une nouvelle
        if (!(nextX >= 0 && nextX < Util.MAZE_SIZE - 1 && nextY >= 0 && nextY < Util.MAZE_SIZE - 1 && previousPossibleDirection.equals(labyrinthe.getFreeDirection(monstre.getX(), monstre.getY())))) {


            chooseRandomDirection();


            // Le modulo de java peux retourner un nombre négatif, on comble donc pour obtenir toujours des positifs
            nextX = (monstre.getX() + direction.getX_dir() + Util.MAZE_SIZE) % Util.MAZE_SIZE;
            nextY = (monstre.getY() + direction.getY_dir() + Util.MAZE_SIZE) % Util.MAZE_SIZE;
        }

        //On fait avancer le monstre
        monstre.setX(nextX);
        monstre.setY(nextY);

    }

    /**
     * The strategy will choose a random direction (but will not go back, however it's the only solution)
     */
    public void chooseRandomDirection() {
        previousDirection = direction;

        Case monsterLocation = labyrinthe.getCaseLabyrinthe(monstre.getX(), monstre.getY());

        //On récupère les directions disponibles
        ArrayList<Direction> directions = labyrinthe.getFreeDirection(monsterLocation.getX(), monsterLocation.getY());

        //Si il n'y a pas de directions disponibles, le monstre reste inactif, sinon il bouge
        if (directions.size() != 0) {

            //On choisi une direction
            if (directions.size() == 1) {
                direction = directions.get(0);
            } else {
                //On enleve la direction opposée de la liste des directions possibles si elle n'est pas seule dans la liste, pour éviter les aller retours.
                directions.remove(previousDirection.opposite());
                direction = directions.get(RandomGenerator.getRandomValue(directions.size()));
            }
        } else
            direction = Direction.IDLE;

        // On met à jours les anciennes directions
        previousPossibleDirection = directions;
    }

    /**
     * Getter of the current direction
     * @return Direction current direction
     */
    public Direction getDirection() {
        return direction;
    }

    @Override
    public boolean isRespawn() {
        return false;
    }

    /**
     * Setter of the direction of the strategy
     * @param direction direction
     */
    @Override
    public void setDirection(Direction direction) {
        this.direction=direction;
    }
}
