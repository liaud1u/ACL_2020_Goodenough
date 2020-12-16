package model.pastille;

import exceptions.InvalidPastilleAmountException;
import exceptions.TooMuchPastillesException;
import model.PacmanGame;
import model.labyrinthe.Case;
import model.labyrinthe.Labyrinthe;
import model.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ScorePastilleTest {

    private PacmanGame game;
    private Labyrinthe laby;
    private Case caseLaby;
    private ScorePastille scorePastille;
    private Player player;
    int amountScore;

    @BeforeEach
    void setUp() {
        amountScore = 100;
        game = new PacmanGame("");
        laby = game.getLabyrinthe();
        scorePastille = new ScorePastille(game, amountScore);
        caseLaby = laby.getCaseLabyrinthe(4,4);
        caseLaby.removePastille();
        caseLaby.setEstUnMur(false);
        player = game.getPlayer();
    }

    @Test
    void ramasser() {
        caseLaby.addPastille(scorePastille);
        player.moveToPosition(caseLaby.getX(), caseLaby.getY()); // Moving the player on the coin case
        game.isEatingAPastille(); // Checks if the player is eating a pastaga and updating the game if so
        assertNull(caseLaby.getPastille()); // Check if the coin is deleted from the case
    }

    @Test
    void incrementeScore() {
        caseLaby.addPastille(scorePastille);
        int scoreActuel = game.getScore();
        player.moveToPosition(caseLaby.getX(), caseLaby.getY()); // Moving the player on the coin case
        game.isEatingAPastille(); // Checks if the player is eating a pastaga and updating the game if so
        scoreActuel += amountScore;
        assertEquals(scoreActuel, game.getScore()); // Checks if the new scores is ok
    }



    @Test
    void boundaryNullPastille() {
        caseLaby.addPastille(null);
        assertNull(caseLaby.getPastille());
    }


    @Test
    void boundaryNullPastilleProperty() {
        caseLaby.addPastille(null);
        assertEquals(false, caseLaby.hasPastille(),  "Case pastille attribute cannot be true without pastille");
    }


    @Test
    void boundaryMultiplePastilleSameCase() {
        caseLaby.addPastille(scorePastille);
        Pastille invinciblePastille2 = new ScorePastille(game, 10);
        caseLaby.addPastille(invinciblePastille2);
        assertNotEquals(invinciblePastille2, caseLaby.getPastille());
    }

    @Test
    void boundaryNegativePastilleNumber() {
        assertThrows(InvalidPastilleAmountException.class, () -> {game.generatePastille(PastilleType.SCORE, -1);});
    }

    @Test
    void boundaryTooMuchPastilles() {
        int nbPastilles = laby.getNbCasesLibres() + 1;
        assertThrows(TooMuchPastillesException.class, () -> {game.generatePastille(PastilleType.SCORE, nbPastilles);});
    }
}