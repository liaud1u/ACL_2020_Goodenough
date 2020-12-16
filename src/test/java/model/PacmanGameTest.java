package model;

import model.labyrinthe.Case;
import model.labyrinthe.Labyrinthe;
import model.monster.GhostType;
import model.monster.Monster;
import model.monster.MonsterState;
import model.player.Player;
import model.weapons.Fireball;
import model.weapons.Landmine;
import model.weapons.Projectile;
import model.util.Util;
import model.weapons.StaticWeapon;
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
    void changeLevelWin() {
        Util.player=1;
        int level = game.getLevel();

        game.getGameState().setState(PacmanGameState.EtatJeu.VICTOIRE);
        game.changeLevel();

        assertEquals(game.getGameTimer().getCurrentTimer(),Difficulty.MEDIUM.getTime());
        assertEquals(game.getGameState().getState(), PacmanGameState.EtatJeu.EN_COURS);
        assertEquals(game.getPlayer().getX(),1);
        assertEquals(game.getPlayer().getY(),1);
        assertEquals(game.getLevel(),level+1);

    }

    @Test
    void changeLevelWinTwoPlayer() {
        Util.player=2;
        int level = game.getLevel();

        game.getGameState().setState(PacmanGameState.EtatJeu.VICTOIRE);
        game.changeLevel();

        assertEquals(game.getGameTimer().getCurrentTimer(),Difficulty.MEDIUM.getTime());
        assertEquals(game.getGameState().getState(), PacmanGameState.EtatJeu.EN_COURS);
        assertEquals(game.getPlayer().getX(),1);
        assertEquals(game.getPlayer().getY(),1);
        assertEquals(game.getSecondPlayer().getX(),Util.MAZE_SIZE-2);
        assertEquals(game.getSecondPlayer().getY(),1);
        assertEquals(game.getLevel(),level+1);

    }

    @Test
    void changeLevelLose() {
        Util.player=1;

        game.getGameState().setState(PacmanGameState.EtatJeu.PERDU);
        game.changeLevel();

        assertEquals(game.getGameTimer().getCurrentTimer(),Difficulty.EASY.getTime());
        assertEquals(game.getGameState().getState(), PacmanGameState.EtatJeu.EN_COURS);
        assertEquals(game.getPlayer().getX(),1);
        assertEquals(game.getPlayer().getY(),1);
        assertEquals(game.getLevel(),0);
        assertEquals(game.getScore(),0);
        assertEquals(game.getAmmos(),1);

    }

    @Test
    void changeLevelLooseTwoPlayer() {
        Util.player=2;

        game.getGameState().setState(PacmanGameState.EtatJeu.PERDU);
        game.changeLevel();

        System.out.println(game.getLevel());

        assertEquals(game.getGameTimer().getCurrentTimer(),Difficulty.EASY.getTime());
        assertEquals(game.getGameState().getState(), PacmanGameState.EtatJeu.EN_COURS);
        assertEquals(game.getPlayer().getX(),1);
        assertEquals(game.getPlayer().getY(),1);
        assertEquals(game.getSecondPlayer().getX(),Util.MAZE_SIZE-2);
        assertEquals(game.getSecondPlayer().getY(),1);
        assertEquals(game.getLevel(),0);
        assertEquals(game.getScore(),0);
        assertEquals(game.getAmmos(),1);
    }

    @Test
    void willFirstPlayerCollideAliveMobLeft() {

        expect(mockLabyrinthe.getCaseLabyrinthe(anyInt(),anyInt())).andReturn(mockCase).anyTimes();

        Monster aliveMonster = new Monster(game,0,0,GhostType.RED);


        expect(mockCase.getMonstre()).andReturn(aliveMonster).anyTimes();

        replay(mockCase,mockLabyrinthe);

        Util.player=1;
        game.getPlayer().setCurrentMoveDirection(Direction.LEFT);
        game.setPlayerTurn(1);
        boolean res = game.willPlayerCollideMob();

        assertTrue(res);

        verify(mockCase,mockLabyrinthe);
    }

    @Test
    void willFirstPlayerCollideAliveMobUp() {

        expect(mockLabyrinthe.getCaseLabyrinthe(anyInt(),anyInt())).andReturn(mockCase).anyTimes();

        Monster aliveMonster = new Monster(game,0,0,GhostType.RED);


        expect(mockCase.getMonstre()).andReturn(aliveMonster).anyTimes();

        replay(mockCase,mockLabyrinthe);

        Util.player=1;
        game.getPlayer().setCurrentMoveDirection(Direction.UP);
        game.setPlayerTurn(1);
        boolean res = game.willPlayerCollideMob();

        assertTrue(res);

        verify(mockCase,mockLabyrinthe);
    }

    @Test
    void willFirstPlayerCollideAliveMobDown() {

        expect(mockLabyrinthe.getCaseLabyrinthe(anyInt(),anyInt())).andReturn(mockCase).anyTimes();

        Monster aliveMonster = new Monster(game,0,0,GhostType.RED);


        expect(mockCase.getMonstre()).andReturn(aliveMonster).anyTimes();

        replay(mockCase,mockLabyrinthe);

        Util.player=1;
        game.getPlayer().setCurrentMoveDirection(Direction.DOWN);
        game.setPlayerTurn(1);
        boolean res = game.willPlayerCollideMob();

        assertTrue(res);

        verify(mockCase,mockLabyrinthe);
    }

    @Test
    void willFirstPlayerCollideAliveMobRight() {

        expect(mockLabyrinthe.getCaseLabyrinthe(anyInt(),anyInt())).andReturn(mockCase).anyTimes();

        Monster aliveMonster = new Monster(game,0,0,GhostType.RED);


        expect(mockCase.getMonstre()).andReturn(aliveMonster).anyTimes();

        replay(mockCase,mockLabyrinthe);

        Util.player=1;
        game.getPlayer().setCurrentMoveDirection(Direction.RIGHT);
        game.setPlayerTurn(1);
        boolean res = game.willPlayerCollideMob();

        assertTrue(res);

        verify(mockCase,mockLabyrinthe);
    }

    @Test
    void willFirstPlayerCollideDeadMobLeft() {

        expect(mockLabyrinthe.getCaseLabyrinthe(anyInt(),anyInt())).andReturn(mockCase).anyTimes();

        Monster deadMonster = new Monster(game,0,0,GhostType.RED);
        deadMonster.setMonsterState(MonsterState.DEAD);


        expect(mockCase.getMonstre()).andReturn(deadMonster).anyTimes();

        replay(mockCase,mockLabyrinthe);

        Util.player=1;
        game.getPlayer().setCurrentMoveDirection(Direction.LEFT);
        game.setPlayerTurn(1);
        boolean res = game.willPlayerCollideMob();

        assertFalse(res);

        verify(mockCase,mockLabyrinthe);
    }

    @Test
    void willFirstPlayerCollideDeadMobUp() {

        expect(mockLabyrinthe.getCaseLabyrinthe(anyInt(),anyInt())).andReturn(mockCase).anyTimes();

        Monster deadMonster = new Monster(game,0,0,GhostType.RED);
        deadMonster.setMonsterState(MonsterState.DEAD);


        expect(mockCase.getMonstre()).andReturn(deadMonster).anyTimes();

        replay(mockCase,mockLabyrinthe);

        Util.player=1;
        game.getPlayer().setCurrentMoveDirection(Direction.UP);
        game.setPlayerTurn(1);
        boolean res = game.willPlayerCollideMob();

        assertFalse(res);

        verify(mockCase,mockLabyrinthe);
    }

    @Test
    void willFirstPlayerCollideDeadMobDown() {

        expect(mockLabyrinthe.getCaseLabyrinthe(anyInt(),anyInt())).andReturn(mockCase).anyTimes();

        Monster deadMonster = new Monster(game,0,0,GhostType.RED);
        deadMonster.setMonsterState(MonsterState.DEAD);


        expect(mockCase.getMonstre()).andReturn(deadMonster).anyTimes();

        replay(mockCase,mockLabyrinthe);

        Util.player=1;
        game.getPlayer().setCurrentMoveDirection(Direction.DOWN);
        game.setPlayerTurn(1);
        boolean res = game.willPlayerCollideMob();

        assertFalse(res);

        verify(mockCase,mockLabyrinthe);
    }

    @Test
    void willFirstPlayerCollideDeadMobRight() {

        expect(mockLabyrinthe.getCaseLabyrinthe(anyInt(),anyInt())).andReturn(mockCase).anyTimes();

        Monster deadMonster = new Monster(game,0,0,GhostType.RED);
        deadMonster.setMonsterState(MonsterState.DEAD);


        expect(mockCase.getMonstre()).andReturn(deadMonster).anyTimes();

        replay(mockCase,mockLabyrinthe);

        Util.player=1;
        game.getPlayer().setCurrentMoveDirection(Direction.RIGHT);
        game.setPlayerTurn(1);

        boolean res = game.willPlayerCollideMob();

        assertFalse(res);

        verify(mockCase,mockLabyrinthe);
    }

    @Test
    void willInvincibleFirstPlayerCollideAliveMobLeft() {


        expect(mockLabyrinthe.getCaseLabyrinthe(anyInt(),anyInt())).andReturn(mockCase).anyTimes();

        expect(mockCase.getMonstre()).andReturn(mockMonstre).anyTimes(); 
        mockMonstre.destroy();
        expectLastCall();
        replay(mockCase,mockLabyrinthe,mockMonstre);

        Util.player=1;
        game.getPlayer().setCurrentMoveDirection(Direction.LEFT);
        game.getPlayer().setInvincible();
        game.setPlayerTurn(1);
        int score = game.getScore();
        boolean res = game.willPlayerCollideMob();

        assertFalse(res);
        assertEquals(score+Util.SCORE_ON_KILL,game.getScore());

        verify(mockCase,mockLabyrinthe,mockMonstre);
    }

    @Test
    void willInvincibleFirstPlayerCollideAliveMobUp() {


        expect(mockLabyrinthe.getCaseLabyrinthe(anyInt(),anyInt())).andReturn(mockCase).anyTimes();

        expect(mockCase.getMonstre()).andReturn(mockMonstre).anyTimes();
        mockMonstre.destroy();
        expectLastCall();
        replay(mockCase,mockLabyrinthe,mockMonstre);

        Util.player=1;
        game.getPlayer().setCurrentMoveDirection(Direction.UP);
        game.getPlayer().setInvincible();
        game.setPlayerTurn(1);
        int score = game.getScore();
        System.out.println(score);
        boolean res = game.willPlayerCollideMob();

        assertFalse(res);
        assertEquals(score+Util.SCORE_ON_KILL,game.getScore());

        verify(mockCase,mockLabyrinthe,mockMonstre);
    }

    @Test
    void willInvincibleFirstPlayerCollideAliveMobDown() {

        expect(mockLabyrinthe.getCaseLabyrinthe(anyInt(),anyInt())).andReturn(mockCase).anyTimes();

        expect(mockCase.getMonstre()).andReturn(mockMonstre).anyTimes();
        mockMonstre.destroy();
        expectLastCall();
        replay(mockCase,mockLabyrinthe,mockMonstre);

        Util.player=1;
        game.getPlayer().setCurrentMoveDirection(Direction.DOWN);
        game.getPlayer().setInvincible();
        game.setPlayerTurn(1);
        int score = game.getScore();
        boolean res = game.willPlayerCollideMob();

        assertFalse(res);
        assertEquals(score+Util.SCORE_ON_KILL,game.getScore());

        verify(mockCase,mockLabyrinthe,mockMonstre);
    }

    @Test
    void willInvincibleFirstPlayerCollideAliveMobRight() {

        expect(mockLabyrinthe.getCaseLabyrinthe(anyInt(),anyInt())).andReturn(mockCase).anyTimes();

        expect(mockCase.getMonstre()).andReturn(mockMonstre).anyTimes();
        mockMonstre.destroy();
        expectLastCall();
        replay(mockCase,mockLabyrinthe,mockMonstre);

        Util.player=1;
        game.getPlayer().setCurrentMoveDirection(Direction.RIGHT);
        game.getPlayer().setInvincible();
        game.setPlayerTurn(1);
        int score = game.getScore();
        boolean res = game.willPlayerCollideMob();

        assertFalse(res);
        assertEquals(score+Util.SCORE_ON_KILL,game.getScore());

        verify(mockCase,mockLabyrinthe,mockMonstre);
    }

    @Test
    void willSecondPlayerCollideAliveMobLeft() {

        expect(mockLabyrinthe.getCaseLabyrinthe(anyInt(),anyInt())).andReturn(mockCase).anyTimes();

        Monster aliveMonster = new Monster(game,0,0,GhostType.RED);


        expect(mockCase.getMonstre()).andReturn(aliveMonster).anyTimes();

        replay(mockCase,mockLabyrinthe);

        Util.player=2;
        game.getSecondPlayer().setCurrentMoveDirection(Direction.LEFT);
        game.setPlayerTurn(2);
        boolean res = game.willPlayerCollideMob();

        assertTrue(res);

        verify(mockCase,mockLabyrinthe);
    }

    @Test
    void willSecondPlayerCollideAliveMobUp() {

        expect(mockLabyrinthe.getCaseLabyrinthe(anyInt(),anyInt())).andReturn(mockCase).anyTimes();

        Monster aliveMonster = new Monster(game,0,0,GhostType.RED);


        expect(mockCase.getMonstre()).andReturn(aliveMonster).anyTimes();

        replay(mockCase,mockLabyrinthe);

        Util.player=2;
        game.getSecondPlayer().setCurrentMoveDirection(Direction.UP);
        game.setPlayerTurn(2);
        boolean res = game.willPlayerCollideMob();

        assertTrue(res);

        verify(mockCase,mockLabyrinthe);
    }

    @Test
    void willSecondPlayerCollideAliveMobDown() {

        expect(mockLabyrinthe.getCaseLabyrinthe(anyInt(),anyInt())).andReturn(mockCase).anyTimes();

        Monster aliveMonster = new Monster(game,0,0,GhostType.RED);


        expect(mockCase.getMonstre()).andReturn(aliveMonster).anyTimes();

        replay(mockCase,mockLabyrinthe);


        Util.player=2;
        game.getSecondPlayer().setCurrentMoveDirection(Direction.DOWN);
        game.setPlayerTurn(2);
        boolean res = game.willPlayerCollideMob();

        assertTrue(res);

        verify(mockCase,mockLabyrinthe);
    }

    @Test
    void willSecondPlayerCollideAliveMobRight() {

        expect(mockLabyrinthe.getCaseLabyrinthe(anyInt(),anyInt())).andReturn(mockCase).anyTimes();

        Monster aliveMonster = new Monster(game,0,0,GhostType.RED);


        expect(mockCase.getMonstre()).andReturn(aliveMonster).anyTimes();

        replay(mockCase,mockLabyrinthe);


        Util.player=2;
        game.getSecondPlayer().setCurrentMoveDirection(Direction.RIGHT);
        game.setPlayerTurn(2);
        boolean res = game.willPlayerCollideMob();

        assertTrue(res);

        verify(mockCase,mockLabyrinthe);
    }

    @Test
    void willSecondPlayerCollideDeadMobLeft() {

        expect(mockLabyrinthe.getCaseLabyrinthe(anyInt(),anyInt())).andReturn(mockCase).anyTimes();

        Monster deadMonster = new Monster(game,0,0,GhostType.RED);
        deadMonster.setMonsterState(MonsterState.DEAD);


        expect(mockCase.getMonstre()).andReturn(deadMonster).anyTimes();

        replay(mockCase,mockLabyrinthe);


        Util.player=2;
        game.getSecondPlayer().setCurrentMoveDirection(Direction.LEFT);
        game.setPlayerTurn(2);
        boolean res = game.willPlayerCollideMob();

        assertFalse(res);

        verify(mockCase,mockLabyrinthe);
    }

    @Test
    void willSecondPlayerCollideDeadMobUp() {

        expect(mockLabyrinthe.getCaseLabyrinthe(anyInt(),anyInt())).andReturn(mockCase).anyTimes();

        Monster deadMonster = new Monster(game,0,0,GhostType.RED);
        deadMonster.setMonsterState(MonsterState.DEAD);


        expect(mockCase.getMonstre()).andReturn(deadMonster).anyTimes();

        replay(mockCase,mockLabyrinthe);


        Util.player=2;
        game.getSecondPlayer().setCurrentMoveDirection(Direction.UP);
        game.setPlayerTurn(2);
        boolean res = game.willPlayerCollideMob();

        assertFalse(res);

        verify(mockCase,mockLabyrinthe);
    }

    @Test
    void willSecondPlayerCollideDeadMobDown() {

        expect(mockLabyrinthe.getCaseLabyrinthe(anyInt(),anyInt())).andReturn(mockCase).anyTimes();

        Monster deadMonster = new Monster(game,0,0,GhostType.RED);
        deadMonster.setMonsterState(MonsterState.DEAD);


        expect(mockCase.getMonstre()).andReturn(deadMonster).anyTimes();

        replay(mockCase,mockLabyrinthe);


        Util.player=2;
        game.getSecondPlayer().setCurrentMoveDirection(Direction.DOWN);
        game.setPlayerTurn(2);
        boolean res = game.willPlayerCollideMob();

        assertFalse(res);

        verify(mockCase,mockLabyrinthe);
    }

    @Test
    void willSecondPlayerCollideDeadMobRight() {

        expect(mockLabyrinthe.getCaseLabyrinthe(anyInt(),anyInt())).andReturn(mockCase).anyTimes();

        Monster deadMonster = new Monster(game,0,0,GhostType.RED);
        deadMonster.setMonsterState(MonsterState.DEAD);


        expect(mockCase.getMonstre()).andReturn(deadMonster).anyTimes();

        replay(mockCase,mockLabyrinthe);


        Util.player=2;
        game.getSecondPlayer().setCurrentMoveDirection(Direction.RIGHT);
        game.setPlayerTurn(2);

        boolean res = game.willPlayerCollideMob();

        assertFalse(res);

        verify(mockCase,mockLabyrinthe);
    }

    @Test
    void willInvincibleSecondPlayerCollideAliveMobLeft() {


        expect(mockLabyrinthe.getCaseLabyrinthe(anyInt(),anyInt())).andReturn(mockCase).anyTimes();

        expect(mockCase.getMonstre()).andReturn(mockMonstre).anyTimes();
        mockMonstre.destroy();
        expectLastCall();
        replay(mockCase,mockLabyrinthe,mockMonstre);

        Util.player=2;
        game.getSecondPlayer().setCurrentMoveDirection(Direction.LEFT);
        game.getSecondPlayer().setInvincible();
        game.setPlayerTurn(2);
        int score = game.getScore();
        boolean res = game.willPlayerCollideMob();

        assertFalse(res);
        assertEquals(score+Util.SCORE_ON_KILL,game.getScore());

        verify(mockCase,mockLabyrinthe,mockMonstre);
    }

    @Test
    void willInvincibleSecondPlayerCollideAliveMobUp() {


        expect(mockLabyrinthe.getCaseLabyrinthe(anyInt(),anyInt())).andReturn(mockCase).anyTimes();

        expect(mockCase.getMonstre()).andReturn(mockMonstre).anyTimes();
        mockMonstre.destroy();
        expectLastCall();
        replay(mockCase,mockLabyrinthe,mockMonstre);

        Util.player=2;
        game.getSecondPlayer().setCurrentMoveDirection(Direction.UP);
        game.getSecondPlayer().setInvincible();
        game.setPlayerTurn(2);
        int score = game.getScore();
        System.out.println(score);
        boolean res = game.willPlayerCollideMob();

        assertFalse(res);
        assertEquals(score+Util.SCORE_ON_KILL,game.getScore());

        verify(mockCase,mockLabyrinthe,mockMonstre);
    }

    @Test
    void willInvincibleSecondPlayerCollideAliveMobDown() {

        expect(mockLabyrinthe.getCaseLabyrinthe(anyInt(),anyInt())).andReturn(mockCase).anyTimes();

        expect(mockCase.getMonstre()).andReturn(mockMonstre).anyTimes();
        mockMonstre.destroy();
        expectLastCall();
        replay(mockCase,mockLabyrinthe,mockMonstre);

        Util.player=2;
        game.getSecondPlayer().setCurrentMoveDirection(Direction.DOWN);
        game.getSecondPlayer().setInvincible();
        game.setPlayerTurn(2);
        int score = game.getScore();
        boolean res = game.willPlayerCollideMob();

        assertFalse(res);
        assertEquals(score+Util.SCORE_ON_KILL,game.getScore());

        verify(mockCase,mockLabyrinthe,mockMonstre);
    }

    @Test
    void willInvincibleSecondPlayerCollideAliveMobRight() {

        expect(mockLabyrinthe.getCaseLabyrinthe(anyInt(),anyInt())).andReturn(mockCase).anyTimes();

        expect(mockCase.getMonstre()).andReturn(mockMonstre).anyTimes();
        mockMonstre.destroy();
        expectLastCall();
        replay(mockCase,mockLabyrinthe,mockMonstre);

        Util.player=1;
        game.getPlayer().setCurrentMoveDirection(Direction.RIGHT);
        game.getPlayer().setInvincible();
        game.setPlayerTurn(1);
        int score = game.getScore();
        boolean res = game.willPlayerCollideMob();

        assertFalse(res);
        assertEquals(score+Util.SCORE_ON_KILL,game.getScore());

        verify(mockCase,mockLabyrinthe,mockMonstre);
    }

    @Test
    void isFirstPlayerWalkingOnLandmine(){
        Util.player = 1;
        Player player = game.getPlayer();

        game.addWeapon(new Landmine(player.getX(),
                player.getY()));

        expect(mockLabyrinthe.getCaseLabyrinthe(anyInt(),anyInt())).andReturn(mockCase).anyTimes();
        expect(mockCase.getMonstre()).andReturn(mockMonstre).anyTimes();
        mockMonstre.destroy();
        expectLastCall();

        replay(mockCase,mockLabyrinthe,mockMonstre);

        boolean res = game.isWalkingOnLandmine();

        assertTrue(res);

        verify(mockCase,mockLabyrinthe,mockMonstre);
    }

    @Test
    void isSecondPlayerWalkingOnLandmine(){
        Util.player = 2;
        Player player = game.getSecondPlayer();
        game.setPlayerTurn(2);

        game.addWeapon(new Landmine(player.getX(),
                player.getY()));

        expect(mockLabyrinthe.getCaseLabyrinthe(anyInt(),anyInt())).andReturn(mockCase).anyTimes();
        expect(mockCase.getMonstre()).andReturn(mockMonstre).anyTimes();
        mockMonstre.destroy();
        expectLastCall();

        replay(mockCase,mockLabyrinthe,mockMonstre);

        boolean res = game.isWalkingOnLandmine();

        assertTrue(res);

        verify(mockCase,mockLabyrinthe,mockMonstre);
    }

    @Test
    void isMonsterWalkingOnLandmine(){
        Monster monster = new Monster(game,5,5,GhostType.RED);
        game.addWeapon(new Landmine(monster.getX(),
                monster.getY()));

        expect(mockLabyrinthe.getCaseLabyrinthe(anyInt(),anyInt())).andReturn(mockCase).anyTimes();
        expect(mockCase.getMonstre()).andReturn(mockMonstre).anyTimes();
        mockMonstre.destroy();
        expectLastCall();

        replay(mockCase,mockLabyrinthe,mockMonstre);

        boolean res = game.isWalkingOnLandmine();

        assertFalse(res);

        verify(mockCase,mockLabyrinthe,mockMonstre);
    }

}