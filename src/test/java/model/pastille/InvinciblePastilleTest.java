package model.pastille;

import exceptions.InvalidPastilleAmountException;
import exceptions.TooMuchPastillesException;
import model.PacmanGame;
import model.labyrinthe.Case;
import model.labyrinthe.Labyrinthe;
import model.player.Player;
import model.util.Util;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import start.Main;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.jupiter.api.Assertions.*;

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
        invinciblePastille = new InvinciblePastille(game);
        caseLaby = laby.getCaseLabyrinthe(4,4);
        caseLaby.removePastille();
        caseLaby.setEstUnMur(false);
        player = game.getPlayer();
    }


    @Test
    void ramasser()  {
        caseLaby.addPastille(invinciblePastille);
        player.moveToPosition(caseLaby.getX(), caseLaby.getY()); // Moving the player on the coin case
        game.isEatingAPastille(); // Checks if the player is eating a pastaga and updating the game if so
        assertNull(caseLaby.getPastille()); // Check if the coin is deleted from the case
    }

    @Test
    void invincibilite() {
        caseLaby.addPastille(invinciblePastille);
        assertEquals(false, player.isInvincible());
        player.moveToPosition(caseLaby.getX(), caseLaby.getY()); // Moving the player on the coin case
        game.isEatingAPastille(); // Checks if the player is eating a pastaga and updating the game if so
        assertEquals(true, player.isInvincible());
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
        caseLaby.addPastille(invinciblePastille);
        Pastille invinciblePastille2 = new InvinciblePastille(game);
        caseLaby.addPastille(invinciblePastille2);
        assertNotEquals(invinciblePastille2, caseLaby.getPastille());
    }

    @Test
    void boundaryNegativePastilleNumber() {
        assertThrows(InvalidPastilleAmountException.class, () -> {game.generatePastille(PastilleType.INVINCIBILITY, -1);});
    }

    @Test
    void boundaryTooMuchPastilles() {
        int nbPastilles = laby.getNbCasesLibres() + 1;
        assertThrows(TooMuchPastillesException.class, () -> {game.generatePastille(PastilleType.INVINCIBILITY, nbPastilles);});
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