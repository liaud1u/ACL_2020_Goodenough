package model.movementstrategy;

import fxengine.Game;
import model.Direction;
import model.PacmanGame;
import model.labyrinthe.Case;
import model.labyrinthe.Labyrinthe;
import model.monster.GhostType;
import model.monster.Monster;
import model.util.RandomGenerator;
import model.util.Util;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

class RandomMovementStrategyTest {

    private RandomMovementStrategy strategy;
    private Monster monstre;
    private PacmanGame game;

    @BeforeEach
    void setUp() {
        game = new PacmanGame("");
        monstre=new Monster((PacmanGame) game,2,2, GhostType.RED);
        strategy = new RandomMovementStrategy(monstre,((PacmanGame) game).getLabyrinthe());
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
    void chooseRandomDirection() {
            Direction previousDirectionTest = strategy.getDirection();

            strategy.chooseDirection();

            assertNotEquals(previousDirectionTest, strategy.getDirection().opposite());
            assertFalse(game.getLabyrinthe().getCaseLabyrinthe(monstre.getX() + strategy.getDirection().getX_dir(), monstre.getY() + strategy.getDirection().getY_dir()).estUnMur());
            assertTrue(game.getLabyrinthe().getCaseLabyrinthe(monstre.getX() + strategy.getDirection().getX_dir(), monstre.getY() + strategy.getDirection().getY_dir()).estVide());
    }

}