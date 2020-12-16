package model.bestscore;

import exceptions.NegativeScoreException;
import model.BestScore;
import model.PacmanGame;
import model.labyrinthe.Case;
import model.labyrinthe.Labyrinthe;
import model.pastille.AmmoPastille;
import model.player.Player;
import model.util.DAO.BestScoreDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BestScoreTest {


    private BestScore score1;
    private BestScore score2;
    private BestScore score3;
    private BestScore bestScore;
   // private BestScoreDAO bestScoreDAO;

    @BeforeEach
    void setUp() throws NegativeScoreException {
        bestScore = new BestScore("P1", 15400);
        score1 = new BestScore("P2", 1500);
        score2 = new BestScore("P3", 1600);
        score3 = new BestScore("P4", 0);

    }



    @Test
    void rightNewScoreIsBest() {
        // Fixme....
    }

    // range on score
    @Test
    void boundaryNegativeScore() {
        assertThrows(NegativeScoreException.class, () -> { new BestScore("P1", -100); });
    }

    // conformance on name size
    @Test
    void boundaryBadPlayerName() throws NegativeScoreException {
        bestScore = new BestScore("NomTropLong", 10);
        assertEquals(4, bestScore.getPlayerName().length());
    }
}
