package model.movementstrategy;

import model.Direction;
import model.PacmanGame;
import model.labyrinthe.Case;
import model.labyrinthe.dijkstra.Dijkstra;
import model.monster.GhostType;
import model.monster.Monster;
import model.util.Util;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class RespawnMovementStrategyTest {

    private RespawnMovementStrategy strategy;
    private Monster monstre;
    private PacmanGame game;

    @BeforeEach
    void setUp() {
        Util.player = 1;
        game = new PacmanGame("");
        monstre=new Monster((PacmanGame) game, Util.MAZE_SIZE-2,Util.MAZE_SIZE-2, GhostType.RED);
        strategy = new RespawnMovementStrategy(monstre,game.getLabyrinthe(),new StaticMovementStrategy());
    }

    @Test
    void moveInitUp() {
        Direction direction = Direction.UP;

        int oldx = monstre.getX();
        int oldy = monstre.getY();

        strategy.setDirection(direction);
        strategy.move();

        assertEquals(oldx+strategy.getDirection().getX_dir(),monstre.getX());
        assertEquals(oldy+ strategy.getDirection().getY_dir(),monstre.getY());

    }

    @Test
    void moveInitDown() {
        Direction direction = Direction.DOWN;

        int oldx = monstre.getX();
        int oldy = monstre.getY();

        strategy.setDirection(direction);
        strategy.move();

        assertEquals(oldx+strategy.getDirection().getX_dir(),monstre.getX());
        assertEquals(oldy+ strategy.getDirection().getY_dir(),monstre.getY());

    }

    @Test
    void moveInitRight() {
        Direction direction = Direction.RIGHT;

        int oldx = monstre.getX();
        int oldy = monstre.getY();

        strategy.setDirection(direction);
        strategy.move();

        assertEquals(oldx+strategy.getDirection().getX_dir(),monstre.getX());
        assertEquals(oldy+ strategy.getDirection().getY_dir(),monstre.getY());

    }

    @Test
    void moveInitLeft() {
        Direction direction = Direction.LEFT;

        int oldx = monstre.getX();
        int oldy = monstre.getY();

        strategy.setDirection(direction);
        strategy.move();

        assertEquals(oldx+strategy.getDirection().getX_dir(),monstre.getX());
        assertEquals(oldy+ strategy.getDirection().getY_dir(),monstre.getY());

    }

    @Test
    void nearestPath() {
            Case monsterLocation = game.getLabyrinthe().getCaseLabyrinthe(monstre.getX(), monstre.getY());

            Case target = game.getLabyrinthe().getSpawnableCase().get(0);

            Dijkstra dijkstra = new Dijkstra(game.getLabyrinthe(), target);

            ArrayList<Case> path = dijkstra.getChemin(target, monsterLocation);


            strategy.chooseDirection();

            assertEquals(path, strategy.getPath());

    }
}