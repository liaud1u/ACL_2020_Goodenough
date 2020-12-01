package model.pastille;

import model.PacmanGame;
import model.labyrinthe.Case;
import model.labyrinthe.Labyrinthe;
import model.player.Player;
import model.util.Util;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AmmoPastilleTest {

    private PacmanGame game;
    private Labyrinthe laby;
    private Case caseLaby;
    private AmmoPastille ammoPastille;
    private Player player;

    @BeforeEach
    void setUp() {
        game = new PacmanGame("");
        laby = game.getLabyrinthe();
        ammoPastille = new AmmoPastille(game, PastilleType.AMMO);
        caseLaby = laby.getCaseLabyrinthe(5,5);
        caseLaby.removePastille();
        caseLaby.setEstUnMur(false);
        caseLaby.addPastille(ammoPastille);
        player = game.getPlayer();
    }

    @Test
    void pastilleExiste() {
        assertNotNull(caseLaby.getPastille());
    }

    @Test
    void ramasser() {
        int ammosBefore = game.getAmmos(); // Getting the amount of ammos before
        player.moveToPosition(caseLaby.getX(), caseLaby.getY()); // Moving the player on the coin case
        game.isEatingAPastille(); // Checks if the player is eating a pastaga and updating the game if so
        assertEquals(ammosBefore + 1, game.getAmmos(), "Ammos not updated properly"); // Check if the ammo increases
        assertNull(caseLaby.getPastille()); // Check if the coin is deleted from the case
    }

    @Test
    void ramasserLimite() {
        int maxAmmos = Util.MAX_AMMOS;
        for(int i = 0 ; i < maxAmmos + 10 ; i ++) {
            player.moveToPosition(caseLaby.getX(), caseLaby.getY()); // Moving the player on the coin case
            game.isEatingAPastille(); // Checks if the player is eating a pastaga and updating the game if so
            caseLaby.addPastille(ammoPastille); // Adding a new pastille
        }
        assertEquals(Util.MAX_AMMOS, game.getAmmos()); // You must now have the maximum amount possible of ammo, no less no more
    }
}