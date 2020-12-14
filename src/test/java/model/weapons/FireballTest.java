package model.weapons;

import model.Direction;
import model.labyrinthe.Case;
import model.labyrinthe.Labyrinthe;
import model.monster.Monster;
import model.monster.MonsterState;
import org.easymock.EasyMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.easymock.EasyMock.*;
import static org.junit.jupiter.api.Assertions.*;

class FireballTest {
    private Fireball fireball1;
    private Fireball fireball2;
    private Fireball fireball3;
    private Fireball fireball4;

    private Case caseMock;
    private Labyrinthe labyrintheMock;
    private Monster monsterMock;

    @BeforeEach
    void setUp() {
        caseMock = EasyMock.createMock(Case.class);
        labyrintheMock = EasyMock.createMock(Labyrinthe.class);
        monsterMock = EasyMock.createMock(Monster.class);

        fireball1 = new Fireball(Direction.LEFT,0,0);
        fireball2 = new Fireball(Direction.RIGHT,0,0);
        fireball3 = new Fireball(Direction.UP,0,0);
        fireball4 = new Fireball(Direction.DOWN,0,0);
    }

    @Test
    void moveLeft() {
        fireball1.move();
        assertEquals(fireball1.getX(),14);
        assertEquals(fireball1.getY(),0);
    }

    @Test
    void moveRight() {
        fireball2.move();
        assertEquals(fireball2.getX(),1);
        assertEquals(fireball2.getY(),0);
    }

    @Test
    void moveUp(){
        fireball3.move();
        assertEquals(fireball3.getX(),0);
        assertEquals(fireball3.getY(),14);
    }

    @Test
    void moveDown() {
        fireball4.move();
        assertEquals(fireball4.getX(),0);
        assertEquals(fireball4.getY(),1);
    }


    @Test
    void evolveLeftIntoMonsterAlive() {
        expect(labyrintheMock.getCaseLabyrinthe(anyInt(),anyInt())).andReturn(caseMock).anyTimes();
        expect(caseMock.getMonstre()).andReturn(monsterMock).anyTimes();
        expect(caseMock.estUnMur()).andReturn(false).anyTimes();
        expect(monsterMock.getLifeState()).andReturn(MonsterState.ALIVE).anyTimes();
        monsterMock.destroy();
        expectLastCall().anyTimes();

        replay(labyrintheMock);
        replay(caseMock);
        replay(monsterMock);

        fireball1.evolve(labyrintheMock);


        assertFalse(fireball1.isAlive());

        verify(labyrintheMock);
        verify(caseMock);
        verify(monsterMock);
    }

    @Test
    void evolveLeftIntoDeadMonster() {
        expect(labyrintheMock.getCaseLabyrinthe(anyInt(),anyInt())).andReturn(caseMock).anyTimes();
        expect(caseMock.getMonstre()).andReturn(monsterMock).anyTimes();
        expect(caseMock.estUnMur()).andReturn(false).anyTimes();
        expect(monsterMock.getLifeState()).andReturn(MonsterState.DEAD).anyTimes();
        monsterMock.destroy();
        expectLastCall().anyTimes();

        replay(labyrintheMock);
        replay(caseMock);
        replay(monsterMock);

        fireball1.evolve(labyrintheMock);

        assertTrue(fireball1.isAlive());

        verify(labyrintheMock);
        verify(caseMock);
        verify(monsterMock);
    }

    @Test
    void evolveLeftIntoFearMonster() {
        expect(labyrintheMock.getCaseLabyrinthe(anyInt(),anyInt())).andReturn(caseMock).anyTimes();
        expect(caseMock.getMonstre()).andReturn(monsterMock).anyTimes();
        expect(caseMock.estUnMur()).andReturn(false).anyTimes();
        expect(monsterMock.getLifeState()).andReturn(MonsterState.FEAR1).anyTimes();
        monsterMock.destroy();
        expectLastCall().anyTimes();

        replay(labyrintheMock);
        replay(caseMock);
        replay(monsterMock);

        fireball1.evolve(labyrintheMock);

        assertFalse(fireball1.isAlive());

        verify(labyrintheMock);
        verify(caseMock);
        verify(monsterMock);
    }

    @Test
    void evolveLeftIntoVoid() {
        expect(labyrintheMock.getCaseLabyrinthe(anyInt(),anyInt())).andReturn(caseMock).anyTimes();
        expect(caseMock.getMonstre()).andReturn(null).anyTimes();
        expect(caseMock.estUnMur()).andReturn(false).anyTimes();

        replay(labyrintheMock);
        replay(caseMock);

        fireball1.evolve(labyrintheMock);

        assertTrue(fireball1.isAlive());

        verify(labyrintheMock);
        verify(caseMock);
    }

    @Test
    void evolveLeftIntoWall() {
        expect(labyrintheMock.getCaseLabyrinthe(anyInt(),anyInt())).andReturn(caseMock).anyTimes();
        expect(caseMock.getMonstre()).andReturn(null).anyTimes();
        expect(caseMock.estUnMur()).andReturn(true).anyTimes();

        replay(labyrintheMock);
        replay(caseMock);

        fireball1.evolve(labyrintheMock);

        assertFalse(fireball1.isAlive());

        verify(labyrintheMock);
        verify(caseMock);
    }

    @Test
    void evolveRightIntoMonsterAlive() {
        expect(labyrintheMock.getCaseLabyrinthe(anyInt(),anyInt())).andReturn(caseMock).anyTimes();
        expect(caseMock.getMonstre()).andReturn(monsterMock).anyTimes();
        expect(caseMock.estUnMur()).andReturn(false).anyTimes();
        expect(monsterMock.getLifeState()).andReturn(MonsterState.ALIVE).anyTimes();
        monsterMock.destroy();
        expectLastCall().anyTimes();

        replay(labyrintheMock);
        replay(caseMock);
        replay(monsterMock);

        fireball3.evolve(labyrintheMock);

        assertFalse(fireball3.isAlive());

        verify(labyrintheMock);
        verify(caseMock);
        verify(monsterMock);
    }


    @Test
    void evolveIntoDeadMonster() {
        expect(labyrintheMock.getCaseLabyrinthe(anyInt(),anyInt())).andReturn(caseMock).anyTimes();
        expect(caseMock.getMonstre()).andReturn(monsterMock).anyTimes();
        expect(caseMock.estUnMur()).andReturn(false).anyTimes();
        expect(monsterMock.getLifeState()).andReturn(MonsterState.DEAD).anyTimes();
        monsterMock.destroy();
        expectLastCall().anyTimes();

        replay(labyrintheMock);
        replay(caseMock);
        replay(monsterMock);

        fireball2.evolve(labyrintheMock);

        assertTrue(fireball2.isAlive());

        verify(labyrintheMock);
        verify(caseMock);
        verify(monsterMock);
    }

    @Test
    void evolveRightIntoFearMonster() {
        expect(labyrintheMock.getCaseLabyrinthe(anyInt(),anyInt())).andReturn(caseMock).anyTimes();
        expect(caseMock.getMonstre()).andReturn(monsterMock).anyTimes();
        expect(caseMock.estUnMur()).andReturn(false).anyTimes();
        expect(monsterMock.getLifeState()).andReturn(MonsterState.FEAR1).anyTimes();
        monsterMock.destroy();
        expectLastCall().anyTimes();

        replay(labyrintheMock);
        replay(caseMock);
        replay(monsterMock);

        fireball2.evolve(labyrintheMock);

        assertFalse(fireball2.isAlive());

        verify(labyrintheMock);
        verify(caseMock);
        verify(monsterMock);
    }

    @Test
    void evolveRightIntoVoid() {
        expect(labyrintheMock.getCaseLabyrinthe(anyInt(),anyInt())).andReturn(caseMock).anyTimes();
        expect(caseMock.getMonstre()).andReturn(null).anyTimes();
        expect(caseMock.estUnMur()).andReturn(false).anyTimes();

        replay(labyrintheMock);
        replay(caseMock);

        fireball2.evolve(labyrintheMock);

        assertTrue(fireball2.isAlive());

        verify(labyrintheMock);
        verify(caseMock);
    }

    @Test
    void evolveRightIntoWall() {
        expect(labyrintheMock.getCaseLabyrinthe(anyInt(),anyInt())).andReturn(caseMock).anyTimes();
        expect(caseMock.getMonstre()).andReturn(null).anyTimes();
        expect(caseMock.estUnMur()).andReturn(true).anyTimes();

        replay(labyrintheMock);
        replay(caseMock);

        fireball2.evolve(labyrintheMock);

        assertFalse(fireball2.isAlive());

        verify(labyrintheMock);
        verify(caseMock);
    }


    @Test
    void evolveUpIntoDeadMonster() {
        expect(labyrintheMock.getCaseLabyrinthe(anyInt(),anyInt())).andReturn(caseMock).anyTimes();
        expect(caseMock.getMonstre()).andReturn(monsterMock).anyTimes();
        expect(caseMock.estUnMur()).andReturn(false).anyTimes();
        expect(monsterMock.getLifeState()).andReturn(MonsterState.DEAD).anyTimes();
        monsterMock.destroy();
        expectLastCall().anyTimes();

        replay(labyrintheMock);
        replay(caseMock);
        replay(monsterMock);

        fireball3.evolve(labyrintheMock);

        assertTrue(fireball3.isAlive());

        verify(labyrintheMock);
        verify(caseMock);
        verify(monsterMock);
    }

    @Test
    void evolveUpIntoFearMonster() {
        expect(labyrintheMock.getCaseLabyrinthe(anyInt(),anyInt())).andReturn(caseMock).anyTimes();
        expect(caseMock.getMonstre()).andReturn(monsterMock).anyTimes();
        expect(caseMock.estUnMur()).andReturn(false).anyTimes();
        expect(monsterMock.getLifeState()).andReturn(MonsterState.FEAR1).anyTimes();
        monsterMock.destroy();
        expectLastCall().anyTimes();

        replay(labyrintheMock);
        replay(caseMock);
        replay(monsterMock);

        fireball3.evolve(labyrintheMock);

        assertFalse(fireball3.isAlive());

        verify(labyrintheMock);
        verify(caseMock);
        verify(monsterMock);
    }

    @Test
    void evolveDownIntoVoid() {
        expect(labyrintheMock.getCaseLabyrinthe(anyInt(),anyInt())).andReturn(caseMock).anyTimes();
        expect(caseMock.getMonstre()).andReturn(null).anyTimes();
        expect(caseMock.estUnMur()).andReturn(false).anyTimes();

        replay(labyrintheMock);
        replay(caseMock);

        fireball4.evolve(labyrintheMock);

        assertTrue(fireball4.isAlive());

        verify(labyrintheMock);
        verify(caseMock);
    }

    @Test
    void evolveDownIntoWall() {
        expect(labyrintheMock.getCaseLabyrinthe(anyInt(),anyInt())).andReturn(caseMock).anyTimes();
        expect(caseMock.getMonstre()).andReturn(null).anyTimes();
        expect(caseMock.estUnMur()).andReturn(true).anyTimes();

        replay(labyrintheMock);
        replay(caseMock);

        fireball4.evolve(labyrintheMock);

        assertFalse(fireball4.isAlive());

        verify(labyrintheMock);
        verify(caseMock);
    }

    @Test
    void evolveDownIntoMonsterAlive() {
        expect(labyrintheMock.getCaseLabyrinthe(anyInt(),anyInt())).andReturn(caseMock).anyTimes();
        expect(caseMock.getMonstre()).andReturn(monsterMock).anyTimes();
        expect(caseMock.estUnMur()).andReturn(false).anyTimes();
        expect(monsterMock.getLifeState()).andReturn(MonsterState.ALIVE).anyTimes();
        monsterMock.destroy();
        expectLastCall().anyTimes();

        replay(labyrintheMock);
        replay(caseMock);
        replay(monsterMock);

        fireball4.evolve(labyrintheMock);

        assertFalse(fireball4.isAlive());

        verify(labyrintheMock);
        verify(caseMock);
        verify(monsterMock);
    }


    @Test
    void evolveDownIntoDeadMonster() {
        expect(labyrintheMock.getCaseLabyrinthe(anyInt(),anyInt())).andReturn(caseMock).anyTimes();
        expect(caseMock.getMonstre()).andReturn(monsterMock).anyTimes();
        expect(caseMock.estUnMur()).andReturn(false).anyTimes();
        expect(monsterMock.getLifeState()).andReturn(MonsterState.DEAD).anyTimes();
        monsterMock.destroy();
        expectLastCall().anyTimes();

        replay(labyrintheMock);
        replay(caseMock);
        replay(monsterMock);

        fireball4.evolve(labyrintheMock);

        assertTrue(fireball4.isAlive());

        verify(labyrintheMock);
        verify(caseMock);
        verify(monsterMock);
    }

    @Test
    void evolveDownIntoFearMonster() {
        expect(labyrintheMock.getCaseLabyrinthe(anyInt(),anyInt())).andReturn(caseMock).anyTimes();
        expect(caseMock.getMonstre()).andReturn(monsterMock).anyTimes();
        expect(caseMock.estUnMur()).andReturn(false).anyTimes();
        expect(monsterMock.getLifeState()).andReturn(MonsterState.FEAR1).anyTimes();
        monsterMock.destroy();
        expectLastCall().anyTimes();

        replay(labyrintheMock);
        replay(caseMock);
        replay(monsterMock);

        fireball4.evolve(labyrintheMock);

        assertFalse(fireball4.isAlive());

        verify(labyrintheMock);
        verify(caseMock);
        verify(monsterMock);
    }

    @Test
    void evolveUpIntoVoid() {
        expect(labyrintheMock.getCaseLabyrinthe(anyInt(),anyInt())).andReturn(caseMock).anyTimes();
        expect(caseMock.getMonstre()).andReturn(null).anyTimes();
        expect(caseMock.estUnMur()).andReturn(false).anyTimes();

        replay(labyrintheMock);
        replay(caseMock);

        fireball3.evolve(labyrintheMock);

        assertTrue(fireball3.isAlive());

        verify(labyrintheMock);
        verify(caseMock);
    }

    @Test
    void evolveUpIntoWall() {
        expect(labyrintheMock.getCaseLabyrinthe(anyInt(),anyInt())).andReturn(caseMock).anyTimes();
        expect(caseMock.getMonstre()).andReturn(null).anyTimes();
        expect(caseMock.estUnMur()).andReturn(true).anyTimes();

        replay(labyrintheMock);
        replay(caseMock);

        fireball3.evolve(labyrintheMock);

        assertFalse(fireball3.isAlive());

        verify(labyrintheMock);
        verify(caseMock);
    }

    @Test
    void evolveUpIntoMonsterAlive() {
        expect(labyrintheMock.getCaseLabyrinthe(anyInt(),anyInt())).andReturn(caseMock).anyTimes();
        expect(caseMock.getMonstre()).andReturn(monsterMock).anyTimes();
        expect(caseMock.estUnMur()).andReturn(false).anyTimes();
        expect(monsterMock.getLifeState()).andReturn(MonsterState.ALIVE).anyTimes();
        monsterMock.destroy();
        expectLastCall().anyTimes();

        replay(labyrintheMock);
        replay(caseMock);
        replay(monsterMock);

        fireball2.evolve(labyrintheMock);

        assertFalse(fireball2.isAlive());

        verify(labyrintheMock);
        verify(caseMock);
        verify(monsterMock);
    }
}