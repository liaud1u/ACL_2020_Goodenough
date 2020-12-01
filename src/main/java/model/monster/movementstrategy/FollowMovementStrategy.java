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


            if(firstPlayerLocation.estUnMur())
                firstPlayerLocation = labyrinthe.getCaseLabyrinthe(player1.getX()  , player1.getY() );


            if(secondPlayerLocation.estUnMur())
                secondPlayerLocation = labyrinthe.getCaseLabyrinthe(player2.getX()  , player2.getY() );

            dijkstra =  new Dijkstra(labyrinthe,monsterLocation);

            if(dijkstra.getChemin(monsterLocation,firstPlayerLocation).size()>dijkstra.getChemin(monsterLocation,secondPlayerLocation).size()) {
                playerLocation = secondPlayerLocation;

            }
            else {
                playerLocation = firstPlayerLocation;
            }
        }else {
            playerLocation = labyrinthe.getCaseLabyrinthe(player1.getX() +player1.getCurrentMoveDirection().getX_dir(), player1.getY() + player1.getCurrentMoveDirection().getY_dir());

            if(playerLocation.estUnMur())
                playerLocation = labyrinthe.getCaseLabyrinthe(player1.getX()  , player1.getY() );

        }



        dijkstra = new Dijkstra(labyrinthe,playerLocation);

        ArrayList<Case> path = dijkstra.getChemin(playerLocation,monsterLocation);



        if(path.size()==1)
        {
            direction = Direction.IDLE;

        }
        else{
            Case cible = path.get(1);
            path.remove(1);

            direction=Direction.valueOf(cible.getX()-monstre.getX(),cible.getY()-monstre.getY());

        }

    }

    /**
     * Getter of the current direction
     * @return Direction current
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * Return true if the movement strategy is a respawn
     * @return boolean true if a respawn strategy
     */
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
        this.direction = direction;
    }
}
