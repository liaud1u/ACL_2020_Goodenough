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

import static org.junit.jupiter.api.Assertions.assertEquals;

class FollowMovementStrategyTest {


    private FollowMovementStrategy strategy;
    private Monster monstre;
    private PacmanGame game;

    @BeforeEach
    void setUp() {
        Util.player = 1;
        game = new PacmanGame("");
        monstre=new Monster((PacmanGame) game, Util.MAZE_SIZE-2,Util.MAZE_SIZE-2, GhostType.RED);
        strategy = new FollowMovementStrategy(monstre,((PacmanGame) game).getLabyrinthe(),game.getPlayer(),game.getSecondPlayer());
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
    void chooseDijsktraDirection() {
            Case monsterLocation = game.getLabyrinthe().getCaseLabyrinthe(monstre.getX(), monstre.getY());

            Case playerLoca = game.getLabyrinthe().getCaseLabyrinthe(game.getPlayer().getX(), game.getPlayer().getY());

            Dijkstra dijkstra = new Dijkstra(game.getLabyrinthe(), playerLoca);

            int size = dijkstra.getChemin(playerLoca, monsterLocation).size() - 1;


            strategy.chooseDirection();

            System.out.println(strategy.getPath());
            assertEquals(size, strategy.getPath().size());

    }

}