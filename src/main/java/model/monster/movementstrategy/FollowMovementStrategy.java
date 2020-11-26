package model.monster.movementstrategy;

import model.PacmanGame;
import model.labyrinthe.Case;
import model.labyrinthe.Labyrinthe;
import model.monster.Monster;
import model.player.Direction;
import model.util.Util;

import java.util.ArrayList;

public class FollowMovementStrategy implements MovementStrategy {

    private final PacmanGame game;
    private final Monster monstre;
    private Direction direction = Direction.IDLE;

    public FollowMovementStrategy(Monster monstre, PacmanGame game) {
        this.monstre = monstre;
        this.game = game;
    }

    @Override
    public void move() {
        //On choisit une direction permettant de se rapprocher au plus du joueur
        chooseDirection();

        //On détermine le X & Y résultant du déplacement
        int nextX = (monstre.getX() + direction.getX_dir() + Util.MAZE_SIZE) % Util.MAZE_SIZE;
        int nextY = (monstre.getY() + direction.getY_dir() + Util.MAZE_SIZE) % Util.MAZE_SIZE;

        //On déplace
        monstre.setX(nextX);
        monstre.setY(nextY);

    }


    public void chooseDirection() {
        Labyrinthe labyrinthe = game.getLabyrinthe();

        //On récupère les cases du monstre et du joueur
        Case monsterLocation = labyrinthe.getCaseLabyrinthe(monstre.getX(), monstre.getY());

        Case playerLocation;

        //On prend le joueur le plus proche
        if(Util.player>1){
            Case firstPlayerLocation = labyrinthe.getCaseLabyrinthe(game.getPlayer().getX() + game.getPlayer().getCurrentMoveDirection().getX_dir(), game.getPlayer().getY() + game.getPlayer().getCurrentMoveDirection().getY_dir());
            Case secondPlayerLocation = labyrinthe.getCaseLabyrinthe(game.getSecondPlayer().getX() + game.getSecondPlayer().getCurrentMoveDirection().getX_dir(), game.getSecondPlayer().getY() + game.getSecondPlayer().getCurrentMoveDirection().getY_dir());

            if(monsterLocation.distance(firstPlayerLocation)>monsterLocation.distance(secondPlayerLocation))
                playerLocation = secondPlayerLocation;
            else
                playerLocation = firstPlayerLocation;

        }else {
            playerLocation = labyrinthe.getCaseLabyrinthe(game.getPlayer().getX() + game.getPlayer().getCurrentMoveDirection().getX_dir(), game.getPlayer().getY() + game.getPlayer().getCurrentMoveDirection().getY_dir());
        }


        //On récupère les directions que le monstre peut prendre
        ArrayList<Direction> directions = labyrinthe.getFreeDirection(monsterLocation.getX(), monsterLocation.getY());

        Direction nearest;

        //Si il n'y a pas de directions le monstre devient inactif
        //Sinon, on détermine la meilleure direction
        if (directions.size() != 0) {

            //On récupère la première direction pour la comparer aux autres
            nearest = directions.get(0);

            int xFollowingDir, yFollowingDir;
            int betterX, betterY;

            //On regarde quelle directions permet de se rapprocher au plus du joueur
            for (Direction d : directions) {

                //On fait les calculs avec le modulo pour compter le fait que le monstre peux utiliser les raccourcis
                xFollowingDir = (monstre.getX() + d.getX_dir() + Util.MAZE_SIZE) % Util.MAZE_SIZE;
                yFollowingDir = (monstre.getY() + d.getY_dir() + Util.MAZE_SIZE) % Util.MAZE_SIZE;

                betterX = (monstre.getX() + nearest.getX_dir() + Util.MAZE_SIZE) % Util.MAZE_SIZE;
                betterY = (monstre.getY() + nearest.getY_dir() + Util.MAZE_SIZE) % Util.MAZE_SIZE;

                Case nearestCase = labyrinthe.getLabyrinthe()[betterX][betterY];
                Case followingDir = labyrinthe.getLabyrinthe()[xFollowingDir][yFollowingDir];

                //Si la distance entre le joueur et la direction actuelle est meilleure que la distance entre le joueur et la direction comparative, on met à jour la meilleure distance
                if (nearestCase.distance(playerLocation) > followingDir.distance(playerLocation))
                    nearest = d;

                //Si les deux distance sont égales, on la prend avec équiprobabilitée
                if (nearestCase.distance(playerLocation) == followingDir.distance(playerLocation))
                    if (Math.random() < 1 / directions.size())
                        nearest = d;
            }

            direction = nearest;
        } else {
            direction = Direction.IDLE;
        }
    }

    public Direction getDirection() {
        return direction;
    }
}
