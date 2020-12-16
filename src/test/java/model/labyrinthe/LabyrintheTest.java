package model.labyrinthe;

import model.Direction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class LabyrintheTest {
    private Labyrinthe labyrinthe;

    @BeforeEach
    void setUp() {
        labyrinthe = new Labyrinthe(15,15);
    }

    @AfterEach
    public void tearDown()
    {
        labyrinthe = null;
    }

    @Test
    void getNbCasesLibres() {
        //Le nombre de cases libres doit être différent de 0
        assertNotEquals(labyrinthe.getNbCasesLibres(),0);
    }

    @Test
    void getFreeDirection() {
        ArrayList<Direction> directionToFreeCase;
        directionToFreeCase = labyrinthe.getFreeDirection(1,1);
        assertNotEquals(directionToFreeCase.size(),0);
    }

    @Test
    void testStructureLabyrinthe() {
        //La case x = 1, y = 1 ne doit jamais être un mur car c'est la case de spawn
        //De même pour les cases au milieu du labyrinthe où les monstres spawn

        Case c = labyrinthe.getCaseLabyrinthe(1,1);
        assertTrue(c.estVide());

        Case c1 = labyrinthe.getCaseLabyrinthe(7,7);
        Case c2 = labyrinthe.getCaseLabyrinthe(6,7);
        Case c3 = labyrinthe.getCaseLabyrinthe(8,7);

        assertTrue(c1.estVide());
        assertTrue(c2.estVide());
        assertTrue(c3.estVide());

    }

    @Test
    void destructionImpasse() {
        //On découpe en deux fois pour éviter la box au milieu qui est forcément une impasse
        labyrinthe.destructionImpasse();
        for (int x = 1; x < 6; x++) {
            for (int y = 1; y < 6; y++) {
                if (!labyrinthe.getCaseLabyrinthe(x,y).estUnMur())
                 assertNotEquals(3,labyrinthe.getCaseLabyrinthe(x,y).getVoisins().size());

            }
        }

        for (int x = 8; x < 13; x++) {
            for (int y = 8; y < 13; y++) {
                if (!labyrinthe.getCaseLabyrinthe(x,y).estUnMur())
                    assertNotEquals(3,labyrinthe.getCaseLabyrinthe(x,y).getVoisins().size());

            }
        }
    }

    @Test
    void add1Pastille() {
        int tailleAvant = labyrinthe.getLeftPastilles();
        labyrinthe.addPastille();
        assertEquals(tailleAvant + 1,labyrinthe.getLeftPastilles());

    }

    @Test
    void add2Pastille() {
        int tailleAvant = labyrinthe.getLeftPastilles();
        labyrinthe.addPastille();
        labyrinthe.addPastille();
        assertEquals(tailleAvant + 2,labyrinthe.getLeftPastilles());

    }

    @Test
    void add3Pastille() {
        int tailleAvant = labyrinthe.getLeftPastilles();
        labyrinthe.addPastille();
        labyrinthe.addPastille();
        labyrinthe.addPastille();
        assertEquals(tailleAvant + 3,labyrinthe.getLeftPastilles());

    }

    @Test
    void add10Pastille() {
        int tailleAvant = labyrinthe.getLeftPastilles();
        labyrinthe.addPastille();
        labyrinthe.addPastille();
        labyrinthe.addPastille();
        labyrinthe.addPastille();
        labyrinthe.addPastille();
        labyrinthe.addPastille();
        labyrinthe.addPastille();
        labyrinthe.addPastille();
        labyrinthe.addPastille();
        labyrinthe.addPastille();
        assertEquals(tailleAvant + 10,labyrinthe.getLeftPastilles());

    }

    @Test
    void add1PastilleApresUneAutre() {
        labyrinthe.addPastille();
        int tailleAvant = labyrinthe.getLeftPastilles();
        labyrinthe.addPastille();
        assertEquals(tailleAvant + 1,labyrinthe.getLeftPastilles());

    }

    @Test
    void determineVoisins() {
        labyrinthe.determineVoisins();
        Case c = labyrinthe.getCaseLabyrinthe(1,1);
        assertNotEquals(c.getVoisins().size(),4);
    }
}