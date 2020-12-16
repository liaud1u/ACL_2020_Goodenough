package model.labyrinthe.dijkstra;

import model.labyrinthe.Case;
import model.labyrinthe.Labyrinthe;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DijkstraTest {

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
    void getCheminNormal() {
        //Case où spawn le pacman donc n'est jamais un mur
        Case c1 = new Case(1,1);

        //Case au milieu de la boite donc n'est jamais un mur
        Case c2 = new Case(7,7);

        Dijkstra dijkstra = new Dijkstra(labyrinthe,c1);

        ArrayList<Case> chemin = dijkstra.getChemin(c1,c2);
        assertNotNull(chemin);

        for (int i = 0; i < chemin.size(); i++) {
            assertNotNull(chemin.get(i));
            assertTrue(chemin.get(i).estVide());
        }
    }

    @Test
    void getChemin2CasesIdentiques() {
        //Case où spawn le pacman donc n'est jamais un mur
        Case c1 = new Case(1,1);

        Dijkstra dijkstra = new Dijkstra(labyrinthe,c1);

        ArrayList<Case> chemin = dijkstra.getChemin(c1,c1);
        assertNotNull(chemin);

        for (int i = 0; i < chemin.size(); i++) {
            assertNotNull(chemin.get(i));
            assertTrue(chemin.get(i).estVide());
        }
    }
    


}