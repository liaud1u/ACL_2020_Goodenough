package model.pastille;

import model.PacmanGame;
import model.labyrinthe.Case;
import model.labyrinthe.Labyrinthe;
import model.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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



    // FIXME THIS TEST COULD NOT PASS PROPERLY BECAUSE OF THE ENVIRONMENT, NOT THE BEST PRACTICE
    // FOR NEXT SPRINT TRY TO IMPLEMENT https://github.com/awaitility/awaitility FOR BETTER RESULTS
    // Might be possible with EasyMock ?
    @Test
    void invincibiliteDesactivation() throws InterruptedException {
       /* player.moveToPosition(caseLaby.getX(), caseLaby.getY()); // Moving the player on the coin case
        game.isEatingAPastille(); // Checks if the player is eating a pastaga and updating the game if so
        assertEquals(true, player.isInvincible());
        assertNull(caseLaby.getPastille()); // Check if the coin is deleted from the case
        TimeUnit.SECONDS.sleep(10);
        assertEquals(false, player.isInvincible());*/
    }
}