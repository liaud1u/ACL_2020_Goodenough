package model.pastille;

import model.PacmanGame;
import model.labyrinthe.Case;
import model.labyrinthe.Labyrinthe;
import model.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TimePastilleTest {

    private PacmanGame game;
    private Labyrinthe laby;
    private Case caseLaby;
    private TimePastille timePastille;
    private Player player;
    private int timeAmount;

    @BeforeEach
    void setUp() {
        timeAmount = 10;
        game = new PacmanGame("");
        laby = game.getLabyrinthe();
        timePastille = new TimePastille(game, PastilleType.TIME, timeAmount);
        caseLaby = laby.getCaseLabyrinthe(4,4);
        caseLaby.removePastille();
        caseLaby.setEstUnMur(false);
        caseLaby.addPastille(timePastille);
        player = game.getPlayer();
    }


    @Test
    void ramasser()  {
        player.moveToPosition(caseLaby.getX(), caseLaby.getY()); // Moving the player on the coin case
        game.isEatingAPastille(); // Checks if the player is eating a pastaga and updating the game if so
        assertNull(caseLaby.getPastille()); // Check if the coin is deleted from the case
    }

    @Test
    void tempsAjoute() {
        game.pauseAllInstances(); // pause the timer before testing
        int tempsActuel = game.getGameTimer().getCurrentTimer(); // get current timer value
        player.moveToPosition(caseLaby.getX(), caseLaby.getY()); // Moving the player on the coin case
        game.isEatingAPastille(); // Checks if the player is eating a pastaga and updating the game if so
        tempsActuel += timeAmount;
        assertEquals(tempsActuel, game.getGameTimer().getCurrentTimer());
    }

}