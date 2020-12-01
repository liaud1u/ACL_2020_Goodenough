package model;

import fxengine.Cmd;
import model.player.Player;
import model.util.Util;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.easymock.EasyMock.*;
import static org.junit.jupiter.api.Assertions.*;
class PacmanGameTest {

    private PacmanGame game;
    private Player mockPlayer;

    @BeforeEach
    void setUp() {
        mockPlayer = createMock(Player.class);
        game = new PacmanGame("");
    }

    @Test
    void evolveAnyJ1() {
        Util.player=1;

        Util.player=1;

        game.evolve(Cmd.LEFT);

        assertEquals(game.getPlayer().getCurrentMoveDirection(),Direction.LEFT);
    }

    @Test
    void evolveIdleJ1() {
        Util.player=1;
        game.evolve(Cmd.IDLE);
        assertEquals(game.getPlayer().getCurrentMoveDirection(),Direction.IDLE);
    }

    @Test
    void evolveAnyJ2() {
        Util.player=2;

        Util.player=1;

        game.setPlayerTurn(2);
        game.evolve(Cmd.LEFT);

        assertEquals(game.getSecondPlayer().getCurrentMoveDirection(),Direction.LEFT);
    }

    @Test
    void evolveIdleJ2() {
        Util.player=1;


        game.setPlayerTurn(2);
        game.evolve(Cmd.IDLE);
        assertEquals(game.getSecondPlayer().getCurrentMoveDirection(),Direction.IDLE);
    }


    @Test
    void evolveTheWorldMovePlayer() {
        int x = game.getPlayer().getX();
        int y = game.getPlayer().getY();

        game.getPlayer().setCurrentMoveDirection(Direction.DOWN);
        game.evolveTheWorld();

        assertEquals(x+Direction.DOWN.getX_dir(),game.getPlayer().getX());
        assertEquals(y+Direction.DOWN.getY_dir(),game.getPlayer().getY());
        assertEquals(x,game.getPlayer().getxPrec());
        assertEquals(y,game.getPlayer().getyPrec());
    }

    @Test
    void evolveTheWorldMovePlayerIdle() {
        int x = game.getPlayer().getX();
        int y = game.getPlayer().getY();

        game.getPlayer().setCurrentMoveDirection(Direction.IDLE);
        game.evolveTheWorld();

        assertEquals(x,game.getPlayer().getX());
        assertEquals(y,game.getPlayer().getY());
        assertEquals(x,game.getPlayer().getxPrec());
        assertEquals(y,game.getPlayer().getyPrec());
    }

    @Test
    void summonFireball() {
    }

    @Test
    void changeLevel() {
    }

    @Test
    void willPlayerCollideMob() {
    }

    @Test
    void isEatingAPastille() {
    }

    @Test
    void willPlayerCollide() {
    }
}