package model.movementstrategy;

import model.labyrinthe.Case;
import model.labyrinthe.Labyrinthe;
import model.labyrinthe.dijkstra.Dijkstra;
import model.monster.Monster;
import model.Direction;

import java.util.ArrayList;

public class RespawnMovementStrategy implements MovementStrategy{

    /**
     * Monster wich use the strategy
     */
    private Monster monstre;

    /**
     * Maze where the monster evolve
     */
    private Labyrinthe labyrinthe;


    /**
     * Old movement strategy
     */
    private MovementStrategy oldStrategy;

    /**
     * Path to take
     */
    private ArrayList<Case> path;

    /**
     * Direction
     */
    private Direction direction = Direction.IDLE;

    /**
     * Constructor of the strategy
     * @param monstre Monster wich have this movement strategy
     * @param labyrinthe Maze where the monster evolve
     */
    public RespawnMovementStrategy(Monster monstre, Labyrinthe labyrinthe, MovementStrategy oldStrategy) {
        this.monstre = monstre;
        this.labyrinthe = labyrinthe;
        this.oldStrategy = oldStrategy;



        Case target = labyrinthe.getSpawnableCase().get(0);

        Dijkstra dijkstra = new Dijkstra(labyrinthe,target);
        path = dijkstra.getChemin(target,labyrinthe.getCaseLabyrinthe(monstre.getX(),monstre.getY()));


    }

    /**
     * Make the monster move
     */
    @Override
    public void move() {


        Case target = labyrinthe.getSpawnableCase().get(2);

        if(target.getY()==monstre.getY()&&target.getX()==monstre.getX()) {
            monstre.respawn();
            oldStrategy.setDirection(Direction.IDLE);
            monstre.setX(target.getX());
            monstre.setY(target.getY());
        }
        else{
            if(path.size()==1){
                Dijkstra dijkstra = new Dijkstra(labyrinthe,target);
                path = dijkstra.getChemin(target,labyrinthe.getCaseLabyrinthe(monstre.getX(),monstre.getY()));
            }
            else{
                Case cible = path.get(1);
                path.remove(1);

                monstre.setX(cible.getX());
                monstre.setY(cible.getY());

                direction=Direction.valueOf(cible.getX()-monstre.getxPrec(),cible.getY()-monstre.getyPrec());

            }
        }
    }

    /**
     * Get the direction to take
     */
    @Override
    public Direction getDirection() {
        return direction;
    }

    /**
     * Setter of the direction of the strategy
     * @param direction direction
     */
    @Override
    public void setDirection(Direction direction) {
        this.direction=direction;
    }

    /**
     * Return true if the movement strategy is a respawn
     * @return boolean true if a respawn strategy
     */
    @Override
    public boolean isRespawn() {
        return true;
    }


    @Override
    public void chooseDirection() {
    }

    /**
     * Return the old movement strategy before the respawn
     * @return Movement strategy
     */
    public MovementStrategy getOldStrategy() {
        return oldStrategy;
    }


    public ArrayList<Case> getPath() {
        return path;
    }
}
