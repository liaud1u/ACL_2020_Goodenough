package model.pastille;

import model.PacmanGame;
import model.labyrinthe.Case;
import model.labyrinthe.Labyrinthe;
import model.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

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
        scorePastille = new ScorePastille(game, PastilleType.SCORE, amountScore);
        caseLaby = laby.getCaseLabyrinthe(4,4);
        caseLaby.removePastille();
        caseLaby.setEstUnMur(false);
        caseLaby.addPastille(scorePastille);
        player = game.getPlayer();
    }

    @Test
    void ramasser() {
        int scoreActuel = game.getScore();
        player.moveToPosition(caseLaby.getX(), caseLaby.getY()); // Moving the player on the coin case
        game.isEatingAPastille(); // Checks if the player is eating a pastaga and updating the game if so
        assertNull(caseLaby.getPastille()); // Check if the coin is deleted from the case
    }

    @Test
    void incrementeScore() {
        int scoreActuel = game.getScore();
        player.moveToPosition(caseLaby.getX(), caseLaby.getY()); // Moving the player on the coin case
        game.isEatingAPastille(); // Checks if the player is eating a pastaga and updating the game if so
        scoreActuel += amountScore;
        assertEquals(scoreActuel, game.getScore()); // Checks if the new scores is ok
    }
}