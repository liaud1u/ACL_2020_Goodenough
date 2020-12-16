package model.bestscore;

import exceptions.BadScoreException;
import model.BestScore;
import model.util.DAO.BestScoreDAO;
import model.util.DAO.BestScoreFileXMLDAO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

public class BestScoreTest {


    private BestScore score1;
    private BestScore score2;
    private BestScore score3;
    private BestScore bestScore;
    private BestScoreDAO bestScoreDAO;

    @BeforeEach
    void setUp() throws BadScoreException {
        bestScore = new BestScore("P1", 15400);
        score1 = new BestScore("P2", 1500);
        score2 = new BestScore("P3", 1600);
        score3 = new BestScore("P4", 13);
        bestScoreDAO = BestScoreFileXMLDAO.getInstance();
        bestScoreDAO.reset(); //reset all scores for test purposes
    }


    @AfterEach
    void cleanUpEach() {
       bestScoreDAO.reset();
    }

    @Test
    void rightNewScoreIsBest() {
        bestScoreDAO.save(score1);
        bestScoreDAO.save(score2);
        bestScoreDAO.save(score3);
        bestScoreDAO.load();
        assertTrue(bestScoreDAO.isNewBestScore(bestScore.getScore()));
    }

    @Test
    void rightNewScoreNotBest() {
        // Test if new score is not the best when the list is full
        bestScoreDAO.save(score2);
        bestScoreDAO.save(score2);
        bestScoreDAO.save(score1);
        bestScoreDAO.save(score1);
        bestScoreDAO.load();
        assertFalse(bestScoreDAO.isNewBestScore(score3.getScore()));
    }

    @Test
    void boundaryNewHighScoreDeletesLowestOne() throws BadScoreException {
        bestScoreDAO.save(score1); // 1500
        bestScoreDAO.save(score2); // 1600
        bestScoreDAO.save(score3); // 13
        bestScoreDAO.save(bestScore); // 15400

        BestScore score4 = new BestScore("P1", 554);

        bestScoreDAO.save(score4);
        Collection<BestScore> bestScoreCollection = bestScoreDAO.load();
        bestScoreCollection.forEach( (score) -> {
            assertNotEquals(score3.getScore(), score.getScore()); // score 3 was removed
        });
    }

    @Test
    void scoreOrder() {
        bestScoreDAO.save(score1);
        bestScoreDAO.save(score2);
        bestScoreDAO.save(score3);
        bestScoreDAO.save(bestScore);

        Collection<BestScore> bestScoreCollection = bestScoreDAO.load();
        bestScoreCollection.stream().reduce((a,b)-> {
            assertTrue(a.getScore() >= b.getScore());
            return b;
        });
    }


    // range on score
    @Test
    void boundaryZeroScore() {
        assertThrows(BadScoreException.class,  () -> new BestScore("P1", 0) );
    }
    // range on score
    @Test
    void boundaryNegativeScore() {
        assertThrows(BadScoreException.class,  () -> new BestScore("P1", -100) );
    }

    // conformance on name size
    @Test
    void boundaryBadPlayerName() throws BadScoreException {
        bestScore = new BestScore("NomTropLong", 10);
        assertEquals(4, bestScore.getPlayerName().length());
    }
}
