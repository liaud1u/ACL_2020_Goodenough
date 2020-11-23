package model.monster.movementstrategy;

import model.PacmanGame;
import model.labyrinthe.Case;
import model.labyrinthe.Labyrinthe;
import model.monster.Monster;
import model.player.Direction;
import model.util.RandomGenerator;
import model.util.Util;

import java.util.ArrayList;

public class RandomMovementStrategy implements MovementStrategy {

    private final PacmanGame game;
    private final Monster monstre;
    private Direction direction = Direction.IDLE;
    private ArrayList<Direction> previousPossibleDirection;
    private Direction previousDirection = Direction.IDLE;

    public RandomMovementStrategy(Monster monstre, PacmanGame game) {
        this.monstre = monstre;
        this.game = game;
    }

    @Override
    public void move() {


        Labyrinthe labyrinthe = game.getLabyrinthe();

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


    public void chooseRandomDirection() {
        previousDirection = direction;

        Labyrinthe labyrinthe = game.getLabyrinthe();
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

    public Direction getDirection() {
        return direction;
    }
}
