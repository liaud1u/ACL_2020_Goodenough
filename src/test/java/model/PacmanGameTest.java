package model;

import fxengine.Cmd;
import model.labyrinthe.Case;
import model.labyrinthe.Labyrinthe;
import model.monster.Monster;
import model.monster.MonsterState;
import model.player.Player;
import model.projectile.Fireball;
import model.projectile.Projectile;
import model.util.Util;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Stack;

import static org.easymock.EasyMock.*;
import static org.junit.jupiter.api.Assertions.*;
class PacmanGameTest {

    private PacmanGame game;
    private Case mockCase;
    private Labyrinthe mockLabyrinthe;
    private Monster mockMonstre;

    @BeforeEach
    void setUp() {
        Util.player=1;
        mockCase = createMock(Case.class);
        mockLabyrinthe = createMock(Labyrinthe.class);
        mockMonstre = createMock(Monster.class);
        game = new PacmanGame("");
        game.setLabyrinthe(mockLabyrinthe);
    }

    @Test
    void evolveTheWorldMovePlayerIdle() {
        int x = game.getPlayer().getX();
        int y = game.getPlayer().getY();

        expect(mockLabyrinthe.getCaseLabyrinthe(anyInt(),anyInt())).andReturn(mockCase).anyTimes();
        expect(mockLabyrinthe.getSpawnableCase()).andReturn(new Stack<>()).anyTimes();
        expect(mockLabyrinthe.getLeftPastilles()).andReturn(1).anyTimes();
        expect(mockCase.estUnMur()).andReturn(false).anyTimes();
        expect(mockCase.getMonstre()).andReturn(null).anyTimes();

        mockCase.removePastille();
        expectLastCall().anyTimes();
        mockCase.addMonster(anyObject());
        expectLastCall().anyTimes();

        replay(mockCase,mockLabyrinthe);

        game.getPlayer().setCurrentMoveDirection(Direction.IDLE);
        game.evolveTheWorld();

        assertEquals(x,game.getPlayer().getX());
        assertEquals(y,game.getPlayer().getY());
        assertEquals(x,game.getPlayer().getxPrec());
        assertEquals(y,game.getPlayer().getyPrec());
        assertFalse(game.isWon());
        assertFalse(game.isLost());

        verify(mockCase,mockLabyrinthe);
    }

    @Test
    void evolveTheWorldMovePlayerUp() {
        int x = game.getPlayer().getX();
        int y = game.getPlayer().getY()-1;
        int xPrec = game.getPlayer().getX();
        int yPrec = game.getPlayer().getY();

        expect(mockLabyrinthe.getCaseLabyrinthe(anyInt(),anyInt())).andReturn(mockCase).anyTimes();
        expect(mockLabyrinthe.getSpawnableCase()).andReturn(new Stack<>()).anyTimes();
        expect(mockLabyrinthe.getLeftPastilles()).andReturn(1).anyTimes();
        expect(mockCase.estUnMur()).andReturn(false).anyTimes();
        expect(mockCase.getMonstre()).andReturn(null).anyTimes();

        mockCase.removePastille();
        expectLastCall().anyTimes();
        mockCase.addMonster(anyObject());
        expectLastCall().anyTimes();

        replay(mockCase,mockLabyrinthe);

        game.getPlayer().setCurrentMoveDirection(Direction.UP);
        game.evolveTheWorld();

        assertEquals(x,game.getPlayer().getX());
        assertEquals(y,game.getPlayer().getY());
        assertEquals(xPrec,game.getPlayer().getxPrec());
        assertEquals(yPrec,game.getPlayer().getyPrec());
        assertFalse(game.isWon());
        assertFalse(game.isLost());

        verify(mockCase,mockLabyrinthe);
    }

    @Test
    void evolveTheWorldMovePlayerDown() {
        int x = game.getPlayer().getX();
        int y = game.getPlayer().getY()+1;
        int xPrec = game.getPlayer().getX();
        int yPrec = game.getPlayer().getY();

        expect(mockLabyrinthe.getCaseLabyrinthe(anyInt(),anyInt())).andReturn(mockCase).anyTimes();
        expect(mockLabyrinthe.getSpawnableCase()).andReturn(new Stack<>()).anyTimes();
        expect(mockLabyrinthe.getLeftPastilles()).andReturn(1).anyTimes();
        expect(mockCase.estUnMur()).andReturn(false).anyTimes();
        expect(mockCase.getMonstre()).andReturn(null).anyTimes();

        mockCase.removePastille();
        expectLastCall().anyTimes();
        mockCase.addMonster(anyObject());
        expectLastCall().anyTimes();

        replay(mockCase,mockLabyrinthe);

        game.getPlayer().setCurrentMoveDirection(Direction.DOWN);
        game.evolveTheWorld();

        assertEquals(x,game.getPlayer().getX());
        assertEquals(y,game.getPlayer().getY());
        assertEquals(xPrec,game.getPlayer().getxPrec());
        assertEquals(yPrec,game.getPlayer().getyPrec());
        assertFalse(game.isWon());
        assertFalse(game.isLost());

        verify(mockCase,mockLabyrinthe);
    }

    @Test
    void evolveTheWorldMovePlayerRight() {
        int x = game.getPlayer().getX()+1;
        int y = game.getPlayer().getY();
        int xPrec = game.getPlayer().getX();
        int yPrec = game.getPlayer().getY();

        expect(mockLabyrinthe.getCaseLabyrinthe(anyInt(),anyInt())).andReturn(mockCase).anyTimes();
        expect(mockLabyrinthe.getSpawnableCase()).andReturn(new Stack<>()).anyTimes();
        expect(mockLabyrinthe.getLeftPastilles()).andReturn(1).anyTimes();
        expect(mockCase.estUnMur()).andReturn(false).anyTimes();
        expect(mockCase.getMonstre()).andReturn(null).anyTimes();

        mockCase.removePastille();
        expectLastCall().anyTimes();
        mockCase.addMonster(anyObject());
        expectLastCall().anyTimes();

        replay(mockCase,mockLabyrinthe);

        game.getPlayer().setCurrentMoveDirection(Direction.RIGHT);
        game.evolveTheWorld();

        assertEquals(x,game.getPlayer().getX());
        assertEquals(y,game.getPlayer().getY());
        assertEquals(xPrec,game.getPlayer().getxPrec());
        assertEquals(yPrec,game.getPlayer().getyPrec());
        assertFalse(game.isWon());
        assertFalse(game.isLost());

        verify(mockCase,mockLabyrinthe);
    }

    @Test
    void evolveTheWorldMovePlayerLeft() {
        int x = game.getPlayer().getX()-1;
        int y = game.getPlayer().getY();
        int xPrec = game.getPlayer().getX();
        int yPrec = game.getPlayer().getY();

        expect(mockLabyrinthe.getCaseLabyrinthe(anyInt(),anyInt())).andReturn(mockCase).anyTimes();
        expect(mockLabyrinthe.getSpawnableCase()).andReturn(new Stack<>()).anyTimes();
        expect(mockLabyrinthe.getLeftPastilles()).andReturn(1).anyTimes();
        expect(mockCase.estUnMur()).andReturn(false).anyTimes();
        expect(mockCase.getMonstre()).andReturn(null).anyTimes();

        mockCase.removePastille();
        expectLastCall().anyTimes();
        mockCase.addMonster(anyObject());
        expectLastCall().anyTimes();

        replay(mockCase,mockLabyrinthe);

        game.getPlayer().setCurrentMoveDirection(Direction.LEFT);
        game.evolveTheWorld();

        assertEquals(x,game.getPlayer().getX());
        assertEquals(y,game.getPlayer().getY());
        assertEquals(xPrec,game.getPlayer().getxPrec());
        assertEquals(yPrec,game.getPlayer().getyPrec());
        assertFalse(game.isWon());
        assertFalse(game.isLost());

        verify(mockCase,mockLabyrinthe);
    }

    @Test
    void evolveTheWorldMoveSecondPlayerIdle() {
        int x = game.getSecondPlayer().getX();
        int y = game.getSecondPlayer().getY();

        expect(mockLabyrinthe.getCaseLabyrinthe(anyInt(),anyInt())).andReturn(mockCase).anyTimes();
        expect(mockLabyrinthe.getSpawnableCase()).andReturn(new Stack<>()).anyTimes();
        expect(mockLabyrinthe.getLeftPastilles()).andReturn(1).anyTimes();
        expect(mockCase.estUnMur()).andReturn(false).anyTimes();
        expect(mockCase.getMonstre()).andReturn(null).anyTimes();

        mockCase.removePastille();
        expectLastCall().anyTimes();
        mockCase.addMonster(anyObject());
        expectLastCall().anyTimes();

        replay(mockCase,mockLabyrinthe);

        game.getSecondPlayer().setCurrentMoveDirection(Direction.IDLE);
        game.evolveTheWorld();

        assertEquals(x,game.getSecondPlayer().getX());
        assertEquals(y,game.getSecondPlayer().getY());
        assertEquals(x,game.getSecondPlayer().getxPrec());
        assertEquals(y,game.getSecondPlayer().getyPrec());
        assertFalse(game.isWon());
        assertFalse(game.isLost());

        verify(mockCase,mockLabyrinthe);
    }

    @Test
    void evolveTheWorldMoveSecondPlayerUp() {
        Util.player = 2; game.setPlayerTurn(2);

        int x = game.getSecondPlayer().getX();
        int y = game.getSecondPlayer().getY()-1;
        int xPrec = game.getSecondPlayer().getX();
        int yPrec = game.getSecondPlayer().getY();

        expect(mockLabyrinthe.getCaseLabyrinthe(anyInt(),anyInt())).andReturn(mockCase).anyTimes();
        expect(mockLabyrinthe.getSpawnableCase()).andReturn(new Stack<>()).anyTimes();
        expect(mockLabyrinthe.getLeftPastilles()).andReturn(1).anyTimes();
        expect(mockCase.estUnMur()).andReturn(false).anyTimes();
        expect(mockCase.getMonstre()).andReturn(null).anyTimes();

        mockCase.removePastille();
        expectLastCall().anyTimes();
        mockCase.addMonster(anyObject());
        expectLastCall().anyTimes();

        replay(mockCase,mockLabyrinthe);

        game.getSecondPlayer().setCurrentMoveDirection(Direction.UP);
        game.evolveTheWorld();

        assertEquals(x,game.getSecondPlayer().getX());
        assertEquals(y,game.getSecondPlayer().getY());
        assertEquals(xPrec,game.getSecondPlayer().getxPrec());
        assertEquals(yPrec,game.getSecondPlayer().getyPrec());
        assertFalse(game.isWon());
        assertFalse(game.isLost());

        verify(mockCase,mockLabyrinthe);
    }

    @Test
    void evolveTheWorldMoveSecondPlayerDown() {
        Util.player = 2; game.setPlayerTurn(2);

        int x = game.getSecondPlayer().getX();
        int y = game.getSecondPlayer().getY()+1;
        int xPrec = game.getSecondPlayer().getX();
        int yPrec = game.getSecondPlayer().getY();

        expect(mockLabyrinthe.getCaseLabyrinthe(anyInt(),anyInt())).andReturn(mockCase).anyTimes();
        expect(mockLabyrinthe.getSpawnableCase()).andReturn(new Stack<>()).anyTimes();
        expect(mockLabyrinthe.getLeftPastilles()).andReturn(1).anyTimes();
        expect(mockCase.estUnMur()).andReturn(false).anyTimes();
        expect(mockCase.getMonstre()).andReturn(null).anyTimes();

        mockCase.removePastille();
        expectLastCall().anyTimes();
        mockCase.addMonster(anyObject());
        expectLastCall().anyTimes();

        replay(mockCase,mockLabyrinthe);

        game.getSecondPlayer().setCurrentMoveDirection(Direction.DOWN);
        game.evolveTheWorld();

        assertEquals(x,game.getSecondPlayer().getX());
        assertEquals(y,game.getSecondPlayer().getY());
        assertEquals(xPrec,game.getSecondPlayer().getxPrec());
        assertEquals(yPrec,game.getSecondPlayer().getyPrec());
        assertFalse(game.isWon());
        assertFalse(game.isLost());

        verify(mockCase,mockLabyrinthe);
    }

    @Test
    void evolveTheWorldMoveSecondPlayerRight() {
        Util.player = 2; game.setPlayerTurn(2);

        int x = game.getSecondPlayer().getX()+1;
        int y = game.getSecondPlayer().getY();
        int xPrec = game.getSecondPlayer().getX();
        int yPrec = game.getSecondPlayer().getY();

        expect(mockLabyrinthe.getCaseLabyrinthe(anyInt(),anyInt())).andReturn(mockCase).anyTimes();
        expect(mockLabyrinthe.getSpawnableCase()).andReturn(new Stack<>()).anyTimes();
        expect(mockLabyrinthe.getLeftPastilles()).andReturn(1).anyTimes();
        expect(mockCase.estUnMur()).andReturn(false).anyTimes();
        expect(mockCase.getMonstre()).andReturn(null).anyTimes();

        mockCase.removePastille();
        expectLastCall().anyTimes();
        mockCase.addMonster(anyObject());
        expectLastCall().anyTimes();

        replay(mockCase,mockLabyrinthe);

        game.getSecondPlayer().setCurrentMoveDirection(Direction.RIGHT);
        game.evolveTheWorld();

        assertEquals(x,game.getSecondPlayer().getX());
        assertEquals(y,game.getSecondPlayer().getY());
        assertEquals(xPrec,game.getSecondPlayer().getxPrec());
        assertEquals(yPrec,game.getSecondPlayer().getyPrec());
        assertFalse(game.isWon());
        assertFalse(game.isLost());

        verify(mockCase,mockLabyrinthe);
    }

    @Test
    void evolveTheWorldMoveSecondPlayerLeft() {
        Util.player = 2; game.setPlayerTurn(2);

        int x = game.getSecondPlayer().getX()-1;
        int y = game.getSecondPlayer().getY();
        int xPrec = game.getSecondPlayer().getX();
        int yPrec = game.getSecondPlayer().getY();

        expect(mockLabyrinthe.getCaseLabyrinthe(anyInt(),anyInt())).andReturn(mockCase).anyTimes();
        expect(mockLabyrinthe.getSpawnableCase()).andReturn(new Stack<>()).anyTimes();
        expect(mockLabyrinthe.getLeftPastilles()).andReturn(1).anyTimes();
        expect(mockCase.estUnMur()).andReturn(false).anyTimes();
        expect(mockCase.getMonstre()).andReturn(null).anyTimes();

        mockCase.removePastille();
        expectLastCall().anyTimes();
        mockCase.addMonster(anyObject());
        expectLastCall().anyTimes();

        replay(mockCase,mockLabyrinthe);

        game.getSecondPlayer().setCurrentMoveDirection(Direction.LEFT);
        game.evolveTheWorld();

        assertEquals(x,game.getSecondPlayer().getX());
        assertEquals(y,game.getSecondPlayer().getY());
        assertEquals(xPrec,game.getSecondPlayer().getxPrec());
        assertEquals(yPrec,game.getSecondPlayer().getyPrec());
        assertFalse(game.isWon());
        assertFalse(game.isLost());

        verify(mockCase,mockLabyrinthe);
    }

    @Test
    void evolveTheWorldMovePlayerUpWall() {
        int x = game.getPlayer().getX();
        int y = game.getPlayer().getY();
        int xPrec = game.getPlayer().getX();
        int yPrec = game.getPlayer().getY();

        expect(mockLabyrinthe.getCaseLabyrinthe(anyInt(),anyInt())).andReturn(mockCase).anyTimes();
        expect(mockLabyrinthe.getSpawnableCase()).andReturn(new Stack<>()).anyTimes();
        expect(mockLabyrinthe.getLeftPastilles()).andReturn(1).anyTimes();
        expect(mockCase.estUnMur()).andReturn(true).anyTimes();
        expect(mockCase.getMonstre()).andReturn(null).anyTimes();

        mockCase.removePastille();
        expectLastCall().anyTimes();
        mockCase.addMonster(anyObject());
        expectLastCall().anyTimes();

        replay(mockCase,mockLabyrinthe);

        game.getPlayer().setCurrentMoveDirection(Direction.UP);
        game.evolveTheWorld();

        assertEquals(x,game.getPlayer().getX());
        assertEquals(y,game.getPlayer().getY());
        assertEquals(xPrec,game.getPlayer().getxPrec());
        assertEquals(yPrec,game.getPlayer().getyPrec());
        assertFalse(game.isWon());
        assertFalse(game.isLost());

        verify(mockCase,mockLabyrinthe);
    }

    @Test
    void evolveTheWorldMovePlayerDownWall() {
        int x = game.getPlayer().getX();
        int y = game.getPlayer().getY();
        int xPrec = game.getPlayer().getX();
        int yPrec = game.getPlayer().getY();

        expect(mockLabyrinthe.getCaseLabyrinthe(anyInt(),anyInt())).andReturn(mockCase).anyTimes();
        expect(mockLabyrinthe.getSpawnableCase()).andReturn(new Stack<>()).anyTimes();
        expect(mockLabyrinthe.getLeftPastilles()).andReturn(1).anyTimes();
        expect(mockCase.estUnMur()).andReturn(true).anyTimes();
        expect(mockCase.getMonstre()).andReturn(null).anyTimes();

        mockCase.removePastille();
        expectLastCall().anyTimes();
        mockCase.addMonster(anyObject());
        expectLastCall().anyTimes();

        replay(mockCase,mockLabyrinthe);

        game.getPlayer().setCurrentMoveDirection(Direction.DOWN);
        game.evolveTheWorld();

        assertEquals(x,game.getPlayer().getX());
        assertEquals(y,game.getPlayer().getY());
        assertEquals(xPrec,game.getPlayer().getxPrec());
        assertEquals(yPrec,game.getPlayer().getyPrec());
        assertFalse(game.isWon());
        assertFalse(game.isLost());

        verify(mockCase,mockLabyrinthe);
    }

    @Test
    void evolveTheWorldMovePlayerRightWall() {
        int x = game.getPlayer().getX();
        int y = game.getPlayer().getY();
        int xPrec = game.getPlayer().getX();
        int yPrec = game.getPlayer().getY();

        expect(mockLabyrinthe.getCaseLabyrinthe(anyInt(),anyInt())).andReturn(mockCase).anyTimes();
        expect(mockLabyrinthe.getSpawnableCase()).andReturn(new Stack<>()).anyTimes();
        expect(mockLabyrinthe.getLeftPastilles()).andReturn(1).anyTimes();
        expect(mockCase.estUnMur()).andReturn(true).anyTimes();
        expect(mockCase.getMonstre()).andReturn(null).anyTimes();

        mockCase.removePastille();
        expectLastCall().anyTimes();
        mockCase.addMonster(anyObject());
        expectLastCall().anyTimes();

        replay(mockCase,mockLabyrinthe);

        game.getPlayer().setCurrentMoveDirection(Direction.RIGHT);
        game.evolveTheWorld();

        assertEquals(x,game.getPlayer().getX());
        assertEquals(y,game.getPlayer().getY());
        assertEquals(xPrec,game.getPlayer().getxPrec());
        assertEquals(yPrec,game.getPlayer().getyPrec());
        assertFalse(game.isWon());
        assertFalse(game.isLost());

        verify(mockCase,mockLabyrinthe);
    }

    @Test
    void evolveTheWorldMovePlayerLeftWall() {
        int x = game.getPlayer().getX();
        int y = game.getPlayer().getY();
        int xPrec = game.getPlayer().getX();
        int yPrec = game.getPlayer().getY();

        expect(mockLabyrinthe.getCaseLabyrinthe(anyInt(),anyInt())).andReturn(mockCase).anyTimes();
        expect(mockLabyrinthe.getSpawnableCase()).andReturn(new Stack<>()).anyTimes();
        expect(mockLabyrinthe.getLeftPastilles()).andReturn(1).anyTimes();
        expect(mockCase.estUnMur()).andReturn(true).anyTimes();
        expect(mockCase.getMonstre()).andReturn(null).anyTimes();

        mockCase.removePastille();
        expectLastCall().anyTimes();
        mockCase.addMonster(anyObject());
        expectLastCall().anyTimes();

        replay(mockCase,mockLabyrinthe);

        game.getPlayer().setCurrentMoveDirection(Direction.LEFT);
        game.evolveTheWorld();

        assertEquals(x,game.getPlayer().getX());
        assertEquals(y,game.getPlayer().getY());
        assertEquals(xPrec,game.getPlayer().getxPrec());
        assertEquals(yPrec,game.getPlayer().getyPrec());
        assertFalse(game.isWon());
        assertFalse(game.isLost());

        verify(mockCase,mockLabyrinthe);
    }

    @Test
    void evolveTheWorldMoveSecondPlayerUpWall() {
        int x = game.getSecondPlayer().getX();
        int y = game.getSecondPlayer().getY();
        int xPrec = game.getSecondPlayer().getX();
        int yPrec = game.getSecondPlayer().getY();

        expect(mockLabyrinthe.getCaseLabyrinthe(anyInt(),anyInt())).andReturn(mockCase).anyTimes();
        expect(mockLabyrinthe.getSpawnableCase()).andReturn(new Stack<>()).anyTimes();
        expect(mockLabyrinthe.getLeftPastilles()).andReturn(1).anyTimes();
        expect(mockCase.estUnMur()).andReturn(true).anyTimes();
        expect(mockCase.getMonstre()).andReturn(null).anyTimes();

        mockCase.removePastille();
        expectLastCall().anyTimes();
        mockCase.addMonster(anyObject());
        expectLastCall().anyTimes();

        replay(mockCase,mockLabyrinthe);

        game.getSecondPlayer().setCurrentMoveDirection(Direction.UP);
        game.evolveTheWorld();

        assertEquals(x,game.getSecondPlayer().getX());
        assertEquals(y,game.getSecondPlayer().getY());
        assertEquals(xPrec,game.getSecondPlayer().getxPrec());
        assertEquals(yPrec,game.getSecondPlayer().getyPrec());
        assertFalse(game.isWon());
        assertFalse(game.isLost());

        verify(mockCase,mockLabyrinthe);
    }

    @Test
    void evolveTheWorldMoveSecondPlayerDownWall() {
        int x = game.getSecondPlayer().getX();
        int y = game.getSecondPlayer().getY();
        int xPrec = game.getSecondPlayer().getX();
        int yPrec = game.getSecondPlayer().getY();

        expect(mockLabyrinthe.getCaseLabyrinthe(anyInt(),anyInt())).andReturn(mockCase).anyTimes();
        expect(mockLabyrinthe.getSpawnableCase()).andReturn(new Stack<>()).anyTimes();
        expect(mockLabyrinthe.getLeftPastilles()).andReturn(1).anyTimes();
        expect(mockCase.estUnMur()).andReturn(true).anyTimes();
        expect(mockCase.getMonstre()).andReturn(null).anyTimes();

        mockCase.removePastille();
        expectLastCall().anyTimes();
        mockCase.addMonster(anyObject());
        expectLastCall().anyTimes();

        replay(mockCase,mockLabyrinthe);

        game.getSecondPlayer().setCurrentMoveDirection(Direction.DOWN);
        game.evolveTheWorld();

        assertEquals(x,game.getSecondPlayer().getX());
        assertEquals(y,game.getSecondPlayer().getY());
        assertEquals(xPrec,game.getSecondPlayer().getxPrec());
        assertEquals(yPrec,game.getSecondPlayer().getyPrec());
        assertFalse(game.isWon());
        assertFalse(game.isLost());

        verify(mockCase,mockLabyrinthe);
    }

    @Test
    void evolveTheWorldMoveSecondPlayerRightWall() {
        int x = game.getSecondPlayer().getX();
        int y = game.getSecondPlayer().getY();
        int xPrec = game.getSecondPlayer().getX();
        int yPrec = game.getSecondPlayer().getY();

        expect(mockLabyrinthe.getCaseLabyrinthe(anyInt(),anyInt())).andReturn(mockCase).anyTimes();
        expect(mockLabyrinthe.getSpawnableCase()).andReturn(new Stack<>()).anyTimes();
        expect(mockLabyrinthe.getLeftPastilles()).andReturn(1).anyTimes();
        expect(mockCase.estUnMur()).andReturn(true).anyTimes();
        expect(mockCase.getMonstre()).andReturn(null).anyTimes();

        mockCase.removePastille();
        expectLastCall().anyTimes();
        mockCase.addMonster(anyObject());
        expectLastCall().anyTimes();

        replay(mockCase,mockLabyrinthe);

        game.getSecondPlayer().setCurrentMoveDirection(Direction.RIGHT);
        game.evolveTheWorld();

        assertEquals(x,game.getSecondPlayer().getX());
        assertEquals(y,game.getSecondPlayer().getY());
        assertEquals(xPrec,game.getSecondPlayer().getxPrec());
        assertEquals(yPrec,game.getSecondPlayer().getyPrec());
        assertFalse(game.isWon());
        assertFalse(game.isLost());

        verify(mockCase,mockLabyrinthe);
    }

    @Test
    void evolveTheWorldMoveSecondPlayerLeftWall() {
        int x = game.getSecondPlayer().getX();
        int y = game.getSecondPlayer().getY();
        int xPrec = game.getSecondPlayer().getX();
        int yPrec = game.getSecondPlayer().getY();

        expect(mockLabyrinthe.getCaseLabyrinthe(anyInt(),anyInt())).andReturn(mockCase).anyTimes();
        expect(mockLabyrinthe.getSpawnableCase()).andReturn(new Stack<>()).anyTimes();
        expect(mockLabyrinthe.getLeftPastilles()).andReturn(1).anyTimes();
        expect(mockCase.estUnMur()).andReturn(true).anyTimes();
        expect(mockCase.getMonstre()).andReturn(null).anyTimes();

        mockCase.removePastille();
        expectLastCall().anyTimes();
        mockCase.addMonster(anyObject());
        expectLastCall().anyTimes();

        replay(mockCase,mockLabyrinthe);

        game.getSecondPlayer().setCurrentMoveDirection(Direction.LEFT);
        game.evolveTheWorld();

        assertEquals(x,game.getSecondPlayer().getX());
        assertEquals(y,game.getSecondPlayer().getY());
        assertEquals(xPrec,game.getSecondPlayer().getxPrec());
        assertEquals(yPrec,game.getSecondPlayer().getyPrec());
        assertFalse(game.isWon());
        assertFalse(game.isLost());

        verify(mockCase,mockLabyrinthe);
    }

    @Test
    void evolveTheWorldMovePlayerMonsterAlive() {

        expect(mockLabyrinthe.getCaseLabyrinthe(anyInt(),anyInt())).andReturn(mockCase).anyTimes();
        expect(mockLabyrinthe.getSpawnableCase()).andReturn(new Stack<>()).anyTimes();
        expect(mockLabyrinthe.getLeftPastilles()).andReturn(1).anyTimes();
        expect(mockCase.estUnMur()).andReturn(false).anyTimes();
        expect(mockCase.getMonstre()).andReturn(mockMonstre).anyTimes();
        expect(mockMonstre.getLifeState()).andReturn(MonsterState.ALIVE).anyTimes();

        mockCase.removePastille();
        expectLastCall().anyTimes();
        mockCase.addMonster(anyObject());
        expectLastCall().anyTimes();

        replay(mockCase,mockLabyrinthe,mockMonstre);

        game.getPlayer().setCurrentMoveDirection(Direction.UP);
        game.evolveTheWorld();
 
        assertTrue(game.isLost());
        assertFalse(game.isWon());

        verify(mockCase,mockLabyrinthe,mockMonstre);
    }

    @Test
    void evolveTheWorldMovePlayerMonsterFear() {

        expect(mockLabyrinthe.getCaseLabyrinthe(anyInt(),anyInt())).andReturn(mockCase).anyTimes();
        expect(mockLabyrinthe.getSpawnableCase()).andReturn(new Stack<>()).anyTimes();
        expect(mockLabyrinthe.getLeftPastilles()).andReturn(1).anyTimes();
        expect(mockCase.estUnMur()).andReturn(false).anyTimes();
        expect(mockCase.getMonstre()).andReturn(mockMonstre).anyTimes();
        expect(mockMonstre.getLifeState()).andReturn(MonsterState.FEAR1).anyTimes();

        mockCase.removePastille();
        expectLastCall().anyTimes();
        mockCase.addMonster(anyObject());
        expectLastCall().anyTimes();

        replay(mockCase,mockLabyrinthe,mockMonstre);

        game.getPlayer().setCurrentMoveDirection(Direction.UP);
        game.evolveTheWorld();

        assertFalse(game.isLost());
        assertFalse(game.isWon());

        verify(mockCase,mockLabyrinthe,mockMonstre);
    }

    @Test
    void evolveTheWorldMovePlayerMonsterDead() {

        expect(mockLabyrinthe.getCaseLabyrinthe(anyInt(),anyInt())).andReturn(mockCase).anyTimes();
        expect(mockLabyrinthe.getSpawnableCase()).andReturn(new Stack<>()).anyTimes();
        expect(mockLabyrinthe.getLeftPastilles()).andReturn(1).anyTimes();
        expect(mockCase.estUnMur()).andReturn(false).anyTimes();
        expect(mockCase.getMonstre()).andReturn(mockMonstre).anyTimes();
        expect(mockMonstre.getLifeState()).andReturn(MonsterState.DEAD).anyTimes();

        mockCase.removePastille();
        expectLastCall().anyTimes();
        mockCase.addMonster(anyObject());
        expectLastCall().anyTimes();

        replay(mockCase,mockLabyrinthe,mockMonstre);

        game.getPlayer().setCurrentMoveDirection(Direction.UP);
        game.evolveTheWorld();

        assertFalse(game.isLost());
        assertFalse(game.isWon());

        verify(mockCase,mockLabyrinthe,mockMonstre);
    }

    @Test
    void evolveTheWorldMoveSecondPlayerMonsterAlive() {

        expect(mockLabyrinthe.getCaseLabyrinthe(anyInt(),anyInt())).andReturn(mockCase).anyTimes();
        expect(mockLabyrinthe.getSpawnableCase()).andReturn(new Stack<>()).anyTimes();
        expect(mockLabyrinthe.getLeftPastilles()).andReturn(1).anyTimes();
        expect(mockCase.estUnMur()).andReturn(false).anyTimes();
        expect(mockCase.getMonstre()).andReturn(mockMonstre).anyTimes();
        expect(mockMonstre.getLifeState()).andReturn(MonsterState.ALIVE).anyTimes();

        mockCase.removePastille();
        expectLastCall().anyTimes();
        mockCase.addMonster(anyObject());
        expectLastCall().anyTimes();

        replay(mockCase,mockLabyrinthe,mockMonstre);

        game.getSecondPlayer().setCurrentMoveDirection(Direction.UP);
        game.evolveTheWorld();

        assertTrue(game.isLost());
        assertFalse(game.isWon());

        verify(mockCase,mockLabyrinthe,mockMonstre);
    }

    @Test
    void evolveTheWorldMoveSecondPlayerMonsterFear() {

        expect(mockLabyrinthe.getCaseLabyrinthe(anyInt(),anyInt())).andReturn(mockCase).anyTimes();
        expect(mockLabyrinthe.getSpawnableCase()).andReturn(new Stack<>()).anyTimes();
        expect(mockLabyrinthe.getLeftPastilles()).andReturn(1).anyTimes();
        expect(mockCase.estUnMur()).andReturn(false).anyTimes();
        expect(mockCase.getMonstre()).andReturn(mockMonstre).anyTimes();
        expect(mockMonstre.getLifeState()).andReturn(MonsterState.FEAR1).anyTimes();

        mockCase.removePastille();
        expectLastCall().anyTimes();
        mockCase.addMonster(anyObject());
        expectLastCall().anyTimes();

        replay(mockCase,mockLabyrinthe,mockMonstre);

        game.getSecondPlayer().setCurrentMoveDirection(Direction.UP);
        game.evolveTheWorld();

        assertFalse(game.isLost());
        assertFalse(game.isWon());

        verify(mockCase,mockLabyrinthe,mockMonstre);
    }

    @Test
    void evolveTheWorldMoveSecondPlayerMonsterDead() {

        expect(mockLabyrinthe.getCaseLabyrinthe(anyInt(),anyInt())).andReturn(mockCase).anyTimes();
        expect(mockLabyrinthe.getSpawnableCase()).andReturn(new Stack<>()).anyTimes();
        expect(mockLabyrinthe.getLeftPastilles()).andReturn(1).anyTimes();
        expect(mockCase.estUnMur()).andReturn(false).anyTimes();
        expect(mockCase.getMonstre()).andReturn(mockMonstre).anyTimes();
        expect(mockMonstre.getLifeState()).andReturn(MonsterState.DEAD).anyTimes();

        mockCase.removePastille();
        expectLastCall().anyTimes();
        mockCase.addMonster(anyObject());
        expectLastCall().anyTimes();

        replay(mockCase,mockLabyrinthe,mockMonstre);

        game.getSecondPlayer().setCurrentMoveDirection(Direction.UP);
        game.evolveTheWorld();

        assertFalse(game.isLost());
        assertFalse(game.isWon());

        verify(mockCase,mockLabyrinthe,mockMonstre);
    }

    @Test
    void evolveTheWorldMoveNoPastille() {

        expect(mockLabyrinthe.getCaseLabyrinthe(anyInt(),anyInt())).andReturn(mockCase).anyTimes();
        expect(mockLabyrinthe.getSpawnableCase()).andReturn(new Stack<>()).anyTimes();
        expect(mockLabyrinthe.getLeftPastilles()).andReturn(0).anyTimes();
        expect(mockCase.estUnMur()).andReturn(false).anyTimes();
        expect(mockCase.getMonstre()).andReturn(mockMonstre).anyTimes();
        expect(mockMonstre.getLifeState()).andReturn(MonsterState.DEAD).anyTimes();

        mockCase.removePastille();
        expectLastCall().anyTimes();
        mockCase.addMonster(anyObject());
        expectLastCall().anyTimes();

        replay(mockCase,mockLabyrinthe,mockMonstre);

        game.getSecondPlayer().setCurrentMoveDirection(Direction.UP);
        game.evolveTheWorld();

        assertFalse(game.isLost());
        assertTrue(game.isWon());

        verify(mockCase,mockLabyrinthe,mockMonstre);
    }


    @Test
    void summonFireballNoAmmos() {
        game.setAmmos(0);
        game.summonFireball();

        assertEquals(game.getProjectiles().size(),0);
    }

    @Test
    void summonFireballDownAmmosInVoid() {
        game.setAmmos(1);
        game.getPlayer().setCurrentMoveDirection(Direction.DOWN);

        expect(mockLabyrinthe.getCaseLabyrinthe(anyInt(),anyInt())).andReturn(mockCase).anyTimes();
        expect(mockCase.estUnMur()).andReturn(false).anyTimes();

        replay(mockCase,mockLabyrinthe);

        game.summonFireball();

        assertEquals(1,game.getProjectiles().size());
        assertEquals(0,game.getAmmos());

        Projectile p = game.getProjectiles().get(0);


        assertEquals(p.getX(),game.getPlayer().getX());
        assertEquals(p.getY(),game.getPlayer().getY()+1);

        assertEquals(((Fireball) p).getDirection(),game.getPlayer().getCurrentMoveDirection());

        verify(mockCase,mockLabyrinthe);
    }

    @Test
    void summonFireballUpAmmosInVoid() {
        game.setAmmos(1);
        game.getPlayer().setCurrentMoveDirection(Direction.UP);

        expect(mockLabyrinthe.getCaseLabyrinthe(anyInt(),anyInt())).andReturn(mockCase).anyTimes();
        expect(mockCase.estUnMur()).andReturn(false).anyTimes();

        replay(mockCase,mockLabyrinthe);

        game.summonFireball();

        assertEquals(1,game.getProjectiles().size());
        assertEquals(0,game.getAmmos());

        Projectile p = game.getProjectiles().get(0);


        assertEquals(p.getX(),game.getPlayer().getX());
        assertEquals(p.getY(),game.getPlayer().getY()-1);

        assertEquals(((Fireball) p).getDirection(),game.getPlayer().getCurrentMoveDirection());

        verify(mockCase,mockLabyrinthe);
    }

    @Test
    void summonFireballLeftAmmosInVoid() {
        game.setAmmos(1);
        game.getPlayer().setCurrentMoveDirection(Direction.LEFT);

        expect(mockLabyrinthe.getCaseLabyrinthe(anyInt(),anyInt())).andReturn(mockCase).anyTimes();
        expect(mockCase.estUnMur()).andReturn(false).anyTimes();

        replay(mockCase,mockLabyrinthe);

        game.summonFireball();

        assertEquals(1,game.getProjectiles().size());
        assertEquals(0,game.getAmmos());

        Projectile p = game.getProjectiles().get(0);


        assertEquals(p.getX(),game.getPlayer().getX()-1);
        assertEquals(p.getY(),game.getPlayer().getY());

        assertEquals(((Fireball) p).getDirection(),game.getPlayer().getCurrentMoveDirection());

        verify(mockCase,mockLabyrinthe);
    }

    @Test
    void summonFireballRightAmmosInVoid() {
        game.setAmmos(1);
        game.getPlayer().setCurrentMoveDirection(Direction.RIGHT);

        expect(mockLabyrinthe.getCaseLabyrinthe(anyInt(),anyInt())).andReturn(mockCase).anyTimes();
        expect(mockCase.estUnMur()).andReturn(false).anyTimes();

        replay(mockCase,mockLabyrinthe);

        game.summonFireball();

        assertEquals(1,game.getProjectiles().size());
        assertEquals(0,game.getAmmos());

        Projectile p = game.getProjectiles().get(0);


        assertEquals(p.getX(),game.getPlayer().getX()+1);
        assertEquals(p.getY(),game.getPlayer().getY());

        assertEquals(((Fireball) p).getDirection(),game.getPlayer().getCurrentMoveDirection());

        verify(mockCase,mockLabyrinthe);
    }

    @Test
    void summonFireballDownAmmosInWall() {
        game.setAmmos(1);
        game.getPlayer().setCurrentMoveDirection(Direction.DOWN);

        expect(mockLabyrinthe.getCaseLabyrinthe(anyInt(),anyInt())).andReturn(mockCase).anyTimes();
        expect(mockCase.estUnMur()).andReturn(true).anyTimes();

        replay(mockCase,mockLabyrinthe);

        game.summonFireball();

        assertEquals(0,game.getProjectiles().size());
        assertEquals(1,game.getAmmos());

        verify(mockCase,mockLabyrinthe);
    }

    @Test
    void summonFireballUpAmmosInWall() {
        game.setAmmos(1);
        game.getPlayer().setCurrentMoveDirection(Direction.UP);

        expect(mockLabyrinthe.getCaseLabyrinthe(anyInt(),anyInt())).andReturn(mockCase).anyTimes();
        expect(mockCase.estUnMur()).andReturn(true).anyTimes();

        replay(mockCase,mockLabyrinthe);

        game.summonFireball();

        assertEquals(0,game.getProjectiles().size());
        assertEquals(1,game.getAmmos());

        verify(mockCase,mockLabyrinthe);
    }

    @Test
    void summonFireballRightAmmosInWall() {
        game.setAmmos(1);
        game.getPlayer().setCurrentMoveDirection(Direction.RIGHT);

        expect(mockLabyrinthe.getCaseLabyrinthe(anyInt(),anyInt())).andReturn(mockCase).anyTimes();
        expect(mockCase.estUnMur()).andReturn(true).anyTimes();

        replay(mockCase,mockLabyrinthe);

        game.summonFireball();

        assertEquals(0,game.getProjectiles().size());
        assertEquals(1,game.getAmmos());

        verify(mockCase,mockLabyrinthe);
    }

    @Test
    void summonFireballLeftAmmosInWall() {
        game.setAmmos(1);
        game.getPlayer().setCurrentMoveDirection(Direction.LEFT);

        expect(mockLabyrinthe.getCaseLabyrinthe(anyInt(),anyInt())).andReturn(mockCase).anyTimes();
        expect(mockCase.estUnMur()).andReturn(true).anyTimes();

        replay(mockCase,mockLabyrinthe);

        game.summonFireball();

        assertEquals(0,game.getProjectiles().size());
        assertEquals(1,game.getAmmos());

        verify(mockCase,mockLabyrinthe);
    }

    @Test
    void summonPlayer2FireballNoAmmos() {
        Util.player = 2; game.setPlayerTurn(2);
        game.setAmmos(0);
        game.summonFireball();

        assertEquals(game.getProjectiles().size(),0);
    }

    @Test
    void summonPlayer2FireballDownAmmosInVoid() {
        Util.player = 2; game.setPlayerTurn(2);
        game.setAmmos(1);
        game.getSecondPlayer().setCurrentMoveDirection(Direction.DOWN);

        expect(mockLabyrinthe.getCaseLabyrinthe(anyInt(),anyInt())).andReturn(mockCase).anyTimes();
        expect(mockCase.estUnMur()).andReturn(false).anyTimes();

        replay(mockCase,mockLabyrinthe);

        game.summonFireball();

        assertEquals(1,game.getProjectiles().size());
        assertEquals(0,game.getAmmos());

        Projectile p = game.getProjectiles().get(0);


        assertEquals(p.getX(),game.getSecondPlayer().getX());
        assertEquals(p.getY(),game.getSecondPlayer().getY()+1);

        assertEquals(((Fireball) p).getDirection(),game.getSecondPlayer().getCurrentMoveDirection());

        verify(mockCase,mockLabyrinthe);
    }

    @Test
    void summonPlayer2FireballUpAmmosInVoid() {
        Util.player = 2; game.setPlayerTurn(2);
        game.setAmmos(1);
        game.getSecondPlayer().setCurrentMoveDirection(Direction.UP);

        expect(mockLabyrinthe.getCaseLabyrinthe(anyInt(),anyInt())).andReturn(mockCase).anyTimes();
        expect(mockCase.estUnMur()).andReturn(false).anyTimes();

        replay(mockCase,mockLabyrinthe);

        game.summonFireball();

        assertEquals(1,game.getProjectiles().size());
        assertEquals(0,game.getAmmos());

        Projectile p = game.getProjectiles().get(0);


        assertEquals(p.getX(),game.getSecondPlayer().getX());
        assertEquals(p.getY(),game.getSecondPlayer().getY()-1);

        assertEquals(((Fireball) p).getDirection(),game.getSecondPlayer().getCurrentMoveDirection());

        verify(mockCase,mockLabyrinthe);
    }

    @Test
    void summonPlayer2FireballLeftAmmosInVoid() {
        Util.player = 2; game.setPlayerTurn(2);
        game.setAmmos(1);
        game.getSecondPlayer().setCurrentMoveDirection(Direction.LEFT);

        expect(mockLabyrinthe.getCaseLabyrinthe(anyInt(),anyInt())).andReturn(mockCase).anyTimes();
        expect(mockCase.estUnMur()).andReturn(false).anyTimes();

        replay(mockCase,mockLabyrinthe);

        game.summonFireball();

        assertEquals(1,game.getProjectiles().size());
        assertEquals(0,game.getAmmos());

        Projectile p = game.getProjectiles().get(0);


        assertEquals(p.getX(),game.getSecondPlayer().getX()-1);
        assertEquals(p.getY(),game.getSecondPlayer().getY());

        assertEquals(((Fireball) p).getDirection(),game.getSecondPlayer().getCurrentMoveDirection());

        verify(mockCase,mockLabyrinthe);
    }

    @Test
    void summonPlayer2FireballRightAmmosInVoid() {
        Util.player = 2; game.setPlayerTurn(2);
        game.setAmmos(1);
        game.getSecondPlayer().setCurrentMoveDirection(Direction.RIGHT);

        expect(mockLabyrinthe.getCaseLabyrinthe(anyInt(),anyInt())).andReturn(mockCase).anyTimes();
        expect(mockCase.estUnMur()).andReturn(false).anyTimes();

        replay(mockCase,mockLabyrinthe);

        game.summonFireball();

        assertEquals(1,game.getProjectiles().size());
        assertEquals(0,game.getAmmos());

        Projectile p = game.getProjectiles().get(0);


        assertEquals(p.getX(),game.getSecondPlayer().getX()+1);
        assertEquals(p.getY(),game.getSecondPlayer().getY());

        assertEquals(((Fireball) p).getDirection(),game.getSecondPlayer().getCurrentMoveDirection());

        verify(mockCase,mockLabyrinthe);
    }

    @Test
    void summonPlayer2FireballDownAmmosInWall() {
        Util.player = 2; game.setPlayerTurn(2);
        game.setAmmos(1);
        game.getSecondPlayer().setCurrentMoveDirection(Direction.DOWN);

        expect(mockLabyrinthe.getCaseLabyrinthe(anyInt(),anyInt())).andReturn(mockCase).anyTimes();
        expect(mockCase.estUnMur()).andReturn(true).anyTimes();

        replay(mockCase,mockLabyrinthe);

        game.summonFireball();

        assertEquals(0,game.getProjectiles().size());
        assertEquals(1,game.getAmmos());

        verify(mockCase,mockLabyrinthe);
    }

    @Test
    void summonPlayer2FireballUpAmmosInWall() {
        Util.player = 2; game.setPlayerTurn(2);
        game.setAmmos(1);
        game.getSecondPlayer().setCurrentMoveDirection(Direction.UP);

        expect(mockLabyrinthe.getCaseLabyrinthe(anyInt(),anyInt())).andReturn(mockCase).anyTimes();
        expect(mockCase.estUnMur()).andReturn(true).anyTimes();

        replay(mockCase,mockLabyrinthe);

        game.summonFireball();

        assertEquals(0,game.getProjectiles().size());
        assertEquals(1,game.getAmmos());

        verify(mockCase,mockLabyrinthe);
    }

    @Test
    void summonPlayer2FireballRightAmmosInWall() {
        Util.player = 2; game.setPlayerTurn(2);
        game.setAmmos(1);
        game.getSecondPlayer().setCurrentMoveDirection(Direction.RIGHT);

        expect(mockLabyrinthe.getCaseLabyrinthe(anyInt(),anyInt())).andReturn(mockCase).anyTimes();
        expect(mockCase.estUnMur()).andReturn(true).anyTimes();

        replay(mockCase,mockLabyrinthe);

        game.summonFireball();

        assertEquals(0,game.getProjectiles().size());
        assertEquals(1,game.getAmmos());

        verify(mockCase,mockLabyrinthe);
    }

    @Test
    void summonPlayerFireballLeftAmmosInWall() {
        Util.player = 2; game.setPlayerTurn(2);
        game.setAmmos(1);
        game.getSecondPlayer().setCurrentMoveDirection(Direction.LEFT);

        expect(mockLabyrinthe.getCaseLabyrinthe(anyInt(),anyInt())).andReturn(mockCase).anyTimes();
        expect(mockCase.estUnMur()).andReturn(true).anyTimes();

        replay(mockCase,mockLabyrinthe);

        game.summonFireball();

        assertEquals(0,game.getProjectiles().size());
        assertEquals(1,game.getAmmos());

        verify(mockCase,mockLabyrinthe);
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