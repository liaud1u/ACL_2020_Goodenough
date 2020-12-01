package model.labyrinthe;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CaseTest {

    private Case c1;
    private ArrayList<Case> voisins;

    @BeforeEach
    void setUp() {
        c1 = new Case(1,1);
        voisins = new ArrayList<>();
    }

    @AfterEach
    public void tearDown()
    {
        c1 = null;
        voisins = null;

    }

    @Test
    void ajout1Voisins() {
        Case c2 = new Case(1,2);
        int tailleAvant = c1.getVoisins().size();
        c1.ajoutVoisin(c2);
        assertTrue(c1.getVoisins().contains(c2));
        assertSame(c1.getVoisins().size(),tailleAvant + 1);

    }

    @Test
    void ajout2Voisins() {
        Case c2 = new Case(1,2);
        Case c3 = new Case(2,1);
        int tailleAvant = c1.getVoisins().size();
        c1.ajoutVoisin(c2);
        c1.ajoutVoisin(c3);
        assertTrue(c1.getVoisins().contains(c2) && c1.getVoisins().contains(c3));
        assertSame(c1.getVoisins().size(),tailleAvant + 2);

    }

    @Test
    void ajout3Voisins() {
        Case c2 = new Case(1,2);
        Case c3 = new Case(2,1);
        Case c4 = new Case(0,1);
        int tailleAvant = c1.getVoisins().size();
        c1.ajoutVoisin(c2);
        c1.ajoutVoisin(c3);
        c1.ajoutVoisin(c4);
        assertTrue(c1.getVoisins().contains(c2) && c1.getVoisins().contains(c3) && c1.getVoisins().contains(c4));
        assertSame(c1.getVoisins().size(),tailleAvant + 3);

    }

    @Test
    void ajout4Voisins() {
        Case c2 = new Case(1,2);
        Case c3 = new Case(2,1);
        Case c4 = new Case(0,1);
        Case c5 = new Case(1,0);
        int tailleAvant = c1.getVoisins().size();
        c1.ajoutVoisin(c2);
        c1.ajoutVoisin(c3);
        c1.ajoutVoisin(c4);
        c1.ajoutVoisin(c5);
        assertTrue(c1.getVoisins().contains(c2) && c1.getVoisins().contains(c3) && c1.getVoisins().contains(c4) && c1.getVoisins().contains(c5));
        assertSame(c1.getVoisins().size(),tailleAvant + 4);

    }

    //Vérifier que l'on ne rentre pas un objet nul en tant que voisin
    @Test
    void ajoutVoisinsNull() {
        Case c2 = null;
        int tailleAvant = c1.getVoisins().size();
        c1.ajoutVoisin(c2);
        assertNotNull(c1.getVoisins().contains(null));
        assertSame(c1.getVoisins().size(),tailleAvant);

    }

    @Test
    void ajoutVoisins2CasesIdentiques() {
        Case c2 = new Case(1,2);
        int tailleAvant = c1.getVoisins().size();
        c1.ajoutVoisin(c2);
        c1.ajoutVoisin(c2);
        assertTrue(c1.getVoisins().contains(c2));
        assertSame(c1.getVoisins().size(),tailleAvant + 1);

    }

    @Test
    void distance() {
        Case c2 = new Case(1,2);
        int dist = c1.distance(c2);
        assertEquals(dist,1);
    }

}