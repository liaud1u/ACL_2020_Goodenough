package model.monster.movementstrategy;

import model.PacmanGame;
import model.labyrinthe.Case;
import model.labyrinthe.Labyrinthe;
import model.labyrinthe.dijkstra.Dijkstra;
import model.monster.Monster;
import model.player.Direction;
import model.player.Player;
import model.util.Util;

import java.util.ArrayList;

/**
 * Class for the Following strategy, with this strategy, the monster will follow the nearest player
 */
public class FollowMovementStrategy implements MovementStrategy {

    /**
     * Maze where the monster evolve
     */
    private final Labyrinthe labyrinthe;

    /**
     * Monster wich use the Strategy
     */
    private final Monster monstre;

    /**
     * Direction to take
     */
    private Direction direction = Direction.IDLE;

    /**
     * The two potential target for the monster
     */
    private Player player1, player2;

    /**
     * Constructor of the strategy
     * @param monstre Monster wich have this movement strategy
     * @param labyrinthe Maze where the monster evolve
     * @param p1 First player of the game
     * @param p2 Second player of the game (or null)
     */
    public FollowMovementStrategy(Monster monstre, Labyrinthe labyrinthe, Player p1, Player p2) {
        this.monstre = monstre;
        this.labyrinthe = labyrinthe;
        this.player1 =p1;
        this.player2=p2;
    }

    /**
     * Movement of the monster
     */
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

    /**
     * Choose a direction for the monster
     */
    public void chooseDirection() {


        //On récupère les cases du monstre et du joueur
        Case monsterLocation = labyrinthe.getCaseLabyrinthe(monstre.getX(), monstre.getY());

        Dijkstra dijkstra;

        Case playerLocation;

        //On prend le joueur le plus proche
        if(Util.player>1){
            Case firstPlayerLocation = labyrinthe.getCaseLabyrinthe(player1.getX() + player1.getCurrentMoveDirection().getX_dir(), player1.getY() + player1.getCurrentMoveDirection().getY_dir());
            Case secondPlayerLocation = labyrinthe.getCaseLabyrinthe(player2.getX() + player2.getCurrentMoveDirection().getX_dir(), player2.getY() + player2.getCurrentMoveDirection().getY_dir());

            dijkstra =  new Dijkstra(labyrinthe,monsterLocation);

            if(dijkstra.getDistance(monsterLocation,firstPlayerLocation)>dijkstra.getDistance(monsterLocation,secondPlayerLocation))
                playerLocation = secondPlayerLocation;
            else
                playerLocation = firstPlayerLocation;

        }else {
            playerLocation = labyrinthe.getCaseLabyrinthe(player1.getX() +player1.getCurrentMoveDirection().getX_dir(), player1.getY() + player1.getCurrentMoveDirection().getY_dir());
        }


        //On récupère les directions que le monstre peut prendre
        ArrayList<Direction> directions = labyrinthe.getFreeDirection(monsterLocation.getX(), monsterLocation.getY());

        Direction nearest;

        dijkstra = new Dijkstra(labyrinthe,playerLocation);

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
                if (dijkstra.getDistance(playerLocation,nearestCase) > dijkstra.getDistance(playerLocation,followingDir))
                    nearest = d;

                //Si les deux distance sont égales, on la prend avec équiprobabilitée
                if (dijkstra.getDistance(playerLocation,nearestCase) == dijkstra.getDistance(playerLocation,followingDir))
                    if (Math.random() < 1 / directions.size())
                        nearest = d;
            }

            direction = nearest;
        } else {
            direction = Direction.IDLE;
        }
    }

    /**
     * Getter of the current direction
     * @return Direction current
     */
    public Direction getDirection() {
        return direction;
    }
}
