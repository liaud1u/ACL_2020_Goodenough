package model.pastille;

import model.PacmanGame;
import model.labyrinthe.Case;
import model.labyrinthe.Labyrinthe;
import model.player.Player;
import model.util.Util;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import start.Main;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class InvinciblePastilleTest {

    private PacmanGame game;
    private Labyrinthe laby;
    private Case caseLaby;
    private InvinciblePastille invinciblePastille;
    private Player player;

    @BeforeEach
    void setUp() {
        Main main = new Main();
        game = new PacmanGame("");
        laby = game.getLabyrinthe();
        invinciblePastille = new InvinciblePastille(game, PastilleType.INVINCIBILITY);
        caseLaby = laby.getCaseLabyrinthe(4,4);
        caseLaby.removePastille();
        caseLaby.setEstUnMur(false);
        caseLaby.addPastille(invinciblePastille);
        player = game.getPlayer();
    }


    @Test
    void ramasser()  {
        player.moveToPosition(caseLaby.getX(), caseLaby.getY()); // Moving the player on the coin case
        game.isEatingAPastille(); // Checks if the player is eating a pastaga and updating the game if so
        assertNull(caseLaby.getPastille()); // Check if the coin is deleted from the case
    }

    @Test
    void invincibilite() {
        assertEquals(false, player.isInvincible());
        player.moveToPosition(caseLaby.getX(), caseLaby.getY()); // Moving the player on the coin case
        game.isEatingAPastille(); // Checks if the player is eating a pastaga and updating the game if so
        assertEquals(true, player.isInvincible());
    }



    // FIXME : doesn't work because JavaFX Timeline never runs during tests.
   /* @Test
    void invincibiliteDesactivation() {
        player.moveToPosition(caseLaby.getX(), caseLaby.getY()); // Moving the player on the coin case
        game.isEatingAPastille(); // Checks if the player is eating a pastaga and updating the game if so
        assertEquals(true, player.isInvincible());
        await().atLeast(Util.INVINCIBLE_TIME, SECONDS).until(() -> {
            return !player.isInvincible();
        });
    } */
}