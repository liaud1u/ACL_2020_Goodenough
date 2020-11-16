package model.monster.movementstrategy;

import model.Case;
import model.Labyrinthe;
import model.PacmanGame;
import model.monster.Monstre;
import model.player.Direction;
import model.util.Util;

import java.util.ArrayList;

public class FollowMovementStrategy implements MovementStrategy {

    private final PacmanGame game;
    private final Monstre monstre;
    private Direction direction = Direction.IDLE;

    public FollowMovementStrategy(Monstre monstre, PacmanGame game) {
        this.monstre = monstre;
        this.game = game;
    }

    @Override
    public void move() {

        Labyrinthe labyrinthe = game.getLabyrinthe();

        chooseDirection();

        int nextX = (monstre.getX() + direction.getX_dir() + Util.MAZE_SIZE) % Util.MAZE_SIZE;
        int nextY = (monstre.getY() + direction.getY_dir() + Util.MAZE_SIZE) % Util.MAZE_SIZE;


        monstre.setX(nextX);
        monstre.setY(nextY);

    }


    public void chooseDirection() {
        Labyrinthe labyrinthe = game.getLabyrinthe();
        Case monsterLocation = labyrinthe.getCaseLabyrinthe(monstre.getX(), monstre.getY());
        Case playerLocation = labyrinthe.getCaseLabyrinthe(game.getPlayer().getX() + game.getPlayer().getCurrentMoveDirection().getX_dir(), game.getPlayer().getY() + game.getPlayer().getCurrentMoveDirection().getY_dir());

        ArrayList<Direction> directions = labyrinthe.getFreeDirection(monsterLocation.getX(), monsterLocation.getY());

        Direction nearest;

        if (directions.size() != 0) {
            nearest = directions.get(0);
            int xFollowingDir, yFollowingDir;
            int betterX, betterY;

            for (Direction d : directions) {

                xFollowingDir = (monstre.getX() + d.getX_dir() + Util.MAZE_SIZE) % Util.MAZE_SIZE;
                yFollowingDir = (monstre.getY() + d.getY_dir() + Util.MAZE_SIZE) % Util.MAZE_SIZE;
                betterX = (monstre.getX() + nearest.getX_dir() + Util.MAZE_SIZE) % Util.MAZE_SIZE;
                betterY = (monstre.getY() + nearest.getY_dir() + Util.MAZE_SIZE) % Util.MAZE_SIZE;

                Case nearestCase = labyrinthe.getLabyrinthe()[betterX][betterY];
                Case followingDir = labyrinthe.getLabyrinthe()[xFollowingDir][yFollowingDir];

                if (nearestCase.distance(playerLocation) > followingDir.distance(playerLocation))
                    nearest = d;

                if (nearestCase.distance(playerLocation) == followingDir.distance(playerLocation))
                    if (Math.random() < 1 / directions.size())
                        nearest = d;
            }

            direction = nearest;
        } else
            direction = Direction.IDLE;

    }

    public Direction getDirection() {
        return direction;
    }
}
