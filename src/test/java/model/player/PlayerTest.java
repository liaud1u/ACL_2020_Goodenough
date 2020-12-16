package model.player;

import fxengine.Game;
import model.Direction;
import model.PacmanGame;
import model.labyrinthe.Case;
import model.labyrinthe.Labyrinthe;
import model.monster.Monster;
import model.util.Util;
import org.easymock.EasyMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.easymock.EasyMock.*;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    private Player player1;
    private Player player2;


    private PacmanGame gameMock;

    @BeforeEach
    void setUp() {
        PacmanGame game = new PacmanGame("");
        player1 = new Player(game,PlayerType.PLAYER1);
        player2 = new Player(game,PlayerType.PLAYER2);

        gameMock = EasyMock.createMock(PacmanGame.class);
    }

    @Test
    void spawnPlayer1() {
        player1.spawn();

        assertEquals(player1.getX(),1);
        assertEquals(player1.getY(),1);
        assertEquals(player1.getxPrec(),1);
        assertEquals(player1.getyPrec(),1);
        assertEquals(player1.getCurrentMoveDirection(),Direction.IDLE);
        assertFalse(player1.isInvincible());
        assertFalse(player1.isStuck());
    }

    @Test
    void spawnPlayer2() {
        player2.spawn();

        assertEquals(player2.getX(),Util.MAZE_SIZE-2);
        assertEquals(player2.getY(),1);
        assertEquals(player2.getxPrec(),Util.MAZE_SIZE-2);
        assertEquals(player2.getyPrec(),1);
        assertEquals(player2.getCurrentMoveDirection(),Direction.IDLE);
        assertFalse(player2.isInvincible());
        assertFalse(player2.isStuck());
    }

    @Test
    void playerGoDownNotCollide() {
        player1 = new Player(gameMock,PlayerType.PLAYER1);

        int x = player1.getX();
        int y = player1.getY();

        player1.setCurrentMoveDirection(Direction.DOWN);

        gameMock.isEatingAPastille();
        expectLastCall();
        gameMock.setPlayerTurn(1);
        expectLastCall();
        expect(gameMock.willPlayerCollide()).andReturn(false);
        expect(gameMock.willPlayerCollideMob()).andReturn(false);

        replay(gameMock);

        player1.go();

        assertEquals(player1.getX(), x+player1.getCurrentMoveDirection().getX_dir());
        assertEquals(player1.getY(), y+player1.getCurrentMoveDirection().getY_dir());
        assertEquals(player1.getxPrec(), x);
        assertEquals(player1.getyPrec(), y);
        assertFalse(player1.isStuck());

        verify(gameMock);

    }

    @Test
    void playerGoUpNotCollide() {
        player1 = new Player(gameMock,PlayerType.PLAYER1);

        int x = player1.getX();
        int y = player1.getY();

        player1.setCurrentMoveDirection(Direction.UP);

        gameMock.isEatingAPastille();
        expectLastCall();
        gameMock.setPlayerTurn(1);
        expectLastCall();
        expect(gameMock.willPlayerCollide()).andReturn(false);
        expect(gameMock.willPlayerCollideMob()).andReturn(false);

        replay(gameMock);

        player1.go();

        assertEquals(player1.getX(), x+player1.getCurrentMoveDirection().getX_dir());
        assertEquals(player1.getY(), y+player1.getCurrentMoveDirection().getY_dir());
        assertEquals(player1.getxPrec(), x);
        assertEquals(player1.getyPrec(), y);
        assertFalse(player1.isStuck());

        verify(gameMock);

    }

    @Test
    void playerGoLeftNotCollide() {
        player1 = new Player(gameMock,PlayerType.PLAYER1);

        int x = player1.getX();
        int y = player1.getY();

        player1.setCurrentMoveDirection(Direction.LEFT);

        gameMock.isEatingAPastille();
        expectLastCall();
        gameMock.setPlayerTurn(1);
        expectLastCall();
        expect(gameMock.willPlayerCollide()).andReturn(false);
        expect(gameMock.willPlayerCollideMob()).andReturn(false);

        replay(gameMock);

        player1.go();

        assertEquals(player1.getX(), x+player1.getCurrentMoveDirection().getX_dir());
        assertEquals(player1.getY(), y+player1.getCurrentMoveDirection().getY_dir());
        assertEquals(player1.getxPrec(), x);
        assertEquals(player1.getyPrec(), y);
        assertFalse(player1.isStuck());

        verify(gameMock);

    }

    @Test
    void playerGoRightNotCollide() {
        player1 = new Player(gameMock,PlayerType.PLAYER1);

        int x = player1.getX();
        int y = player1.getY();

        player1.setCurrentMoveDirection(Direction.RIGHT);

        gameMock.isEatingAPastille();
        expectLastCall();
        gameMock.setPlayerTurn(1);
        expectLastCall();
        expect(gameMock.willPlayerCollide()).andReturn(false);
        expect(gameMock.willPlayerCollideMob()).andReturn(false);

        replay(gameMock);

        player1.go();

        assertEquals(player1.getX(), x+player1.getCurrentMoveDirection().getX_dir());
        assertEquals(player1.getY(), y+player1.getCurrentMoveDirection().getY_dir());
        assertEquals(player1.getxPrec(), x);
        assertEquals(player1.getyPrec(), y);
        assertFalse(player1.isStuck());

        verify(gameMock);

    }

    @Test
    void playerGoDownCollideWall() {
        player1 = new Player(gameMock,PlayerType.PLAYER1);

        int x = player1.getX();
        int y = player1.getY();

        player1.setCurrentMoveDirection(Direction.DOWN);

        gameMock.isEatingAPastille();
        expectLastCall();
        gameMock.setPlayerTurn(1);
        expectLastCall();
        expect(gameMock.willPlayerCollide()).andReturn(true);

        replay(gameMock);

        player1.go();

        assertEquals(player1.getX(), x);
        assertEquals(player1.getY(), y);
        assertEquals(player1.getxPrec(), x);
        assertEquals(player1.getyPrec(), y);
        assertTrue(player1.isStuck());

        verify(gameMock);

    }

    @Test
    void playerGoUpCollideWall() {
        player1 = new Player(gameMock,PlayerType.PLAYER1);

        int x = player1.getX();
        int y = player1.getY();

        player1.setCurrentMoveDirection(Direction.UP);

        gameMock.isEatingAPastille();
        expectLastCall();
        gameMock.setPlayerTurn(1);
        expectLastCall();
        expect(gameMock.willPlayerCollide()).andReturn(true);

        replay(gameMock);

        player1.go();

        assertEquals(player1.getX(), x);
        assertEquals(player1.getY(), y);
        assertEquals(player1.getxPrec(), x);
        assertEquals(player1.getyPrec(), y);
        assertTrue(player1.isStuck());

        verify(gameMock);

    }

    @Test
    void playerGoLeftCollideWall() {
        player1 = new Player(gameMock,PlayerType.PLAYER1);

        int x = player1.getX();
        int y = player1.getY();

        player1.setCurrentMoveDirection(Direction.LEFT);

        gameMock.isEatingAPastille();
        expectLastCall();
        gameMock.setPlayerTurn(1);
        expectLastCall();
        expect(gameMock.willPlayerCollide()).andReturn(true);

        replay(gameMock);

        player1.go();

        assertEquals(player1.getX(), x);
        assertEquals(player1.getY(), y);
        assertEquals(player1.getxPrec(), x);
        assertEquals(player1.getyPrec(), y);
        assertTrue(player1.isStuck());

        verify(gameMock);

    }

    @Test
    void playerGoRightCollideWall() {
        player1 = new Player(gameMock,PlayerType.PLAYER1);

        int x = player1.getX();
        int y = player1.getY();

        player1.setCurrentMoveDirection(Direction.RIGHT);

        gameMock.isEatingAPastille();
        expectLastCall();
        gameMock.setPlayerTurn(1);
        expectLastCall();
        expect(gameMock.willPlayerCollide()).andReturn(true);

        replay(gameMock);

        player1.go();

        assertEquals(player1.getX(), x);
        assertEquals(player1.getY(), y);
        assertEquals(player1.getxPrec(), x);
        assertEquals(player1.getyPrec(), y);
        assertTrue(player1.isStuck());

        verify(gameMock);

    }

    @Test
    void playerGoDownCollideMob() {
        player1 = new Player(gameMock,PlayerType.PLAYER1);

        int x = player1.getX();
        int y = player1.getY();

        player1.setCurrentMoveDirection(Direction.DOWN);

        gameMock.isEatingAPastille();
        expectLastCall();
        gameMock.setPlayerTurn(1);
        expectLastCall();
        expect(gameMock.willPlayerCollide()).andReturn(false);
        expect(gameMock.willPlayerCollideMob()).andReturn(true);

        replay(gameMock);

        player1.go();

        assertEquals(player1.getX(), x);
        assertEquals(player1.getY(), y);
        assertEquals(player1.getxPrec(), x);
        assertEquals(player1.getyPrec(), y);
        assertTrue(player1.isStuck());

        verify(gameMock);

    }

    @Test
    void playerGoUpCollideMob() {
        player1 = new Player(gameMock,PlayerType.PLAYER1);

        int x = player1.getX();
        int y = player1.getY();

        player1.setCurrentMoveDirection(Direction.UP);

        gameMock.isEatingAPastille();
        expectLastCall();
        gameMock.setPlayerTurn(1);
        expectLastCall();
        expect(gameMock.willPlayerCollide()).andReturn(false);
        expect(gameMock.willPlayerCollideMob()).andReturn(true);

        replay(gameMock);

        player1.go();

        assertEquals(player1.getX(), x);
        assertEquals(player1.getY(), y);
        assertEquals(player1.getxPrec(), x);
        assertEquals(player1.getyPrec(), y);
        assertTrue(player1.isStuck());

        verify(gameMock);

    }

    @Test
    void playerGoLeftCollideMob() {
        player1 = new Player(gameMock,PlayerType.PLAYER1);

        int x = player1.getX();
        int y = player1.getY();

        player1.setCurrentMoveDirection(Direction.LEFT);

        gameMock.isEatingAPastille();
        expectLastCall();
        gameMock.setPlayerTurn(1);
        expectLastCall();
        expect(gameMock.willPlayerCollide()).andReturn(false);
        expect(gameMock.willPlayerCollideMob()).andReturn(true);

        replay(gameMock);

        player1.go();

        assertEquals(player1.getX(), x);
        assertEquals(player1.getY(), y);
        assertEquals(player1.getxPrec(), x);
        assertEquals(player1.getyPrec(), y);
        assertTrue(player1.isStuck());

        verify(gameMock);

    }

    @Test
    void playerGoRightCollideMob() {
        player1 = new Player(gameMock,PlayerType.PLAYER1);

        int x = player1.getX();
        int y = player1.getY();

        player1.setCurrentMoveDirection(Direction.RIGHT);

        gameMock.isEatingAPastille();
        expectLastCall();
        gameMock.setPlayerTurn(1);
        expectLastCall();
        expect(gameMock.willPlayerCollide()).andReturn(false);
        expect(gameMock.willPlayerCollideMob()).andReturn(true);

        replay(gameMock);

        player1.go();

        assertEquals(player1.getX(), x);
        assertEquals(player1.getY(), y);
        assertEquals(player1.getxPrec(), x);
        assertEquals(player1.getyPrec(), y);
        assertTrue(player1.isStuck());

        verify(gameMock);

    }
}