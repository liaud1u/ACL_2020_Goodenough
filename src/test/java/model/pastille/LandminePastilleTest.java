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

import static org.junit.jupiter.api.Assertions.*;

class LandminePastilleTest {

    private PacmanGame game;
    private Labyrinthe laby;
    private Case caseLaby;
    private LandminePastille landminePastille;
    private Player player;

    @BeforeEach
    void setUp() {
        game = new PacmanGame("");
        laby = game.getLabyrinthe();
        landminePastille = new LandminePastille(game);
        caseLaby = laby.getCaseLabyrinthe(5,5);
        caseLaby.removePastille();
        caseLaby.setEstUnMur(false);
        player = game.getPlayer();
    }

    @Test
    void pastilleExiste() {
        caseLaby.addPastille(landminePastille);
        assertNotNull(caseLaby.getPastille());
    }

    @Test
    void ramasser() {
        caseLaby.addPastille(landminePastille);
        int staticweaponsBefore = game.getStaticWeaponsCount(); // Getting the amount of ammos before
        player.moveToPosition(caseLaby.getX(), caseLaby.getY()); // Moving the player on the coin case
        game.isEatingAPastille(); // Checks if the player is eating a pastaga and updating the game if so
        assertEquals(staticweaponsBefore + 1, game.getStaticWeaponsCount(), "Mines not updated properly"); // Check if the ammo increases
        assertNull(caseLaby.getPastille()); // Check if the coin is deleted from the case
    }

    @Test
    void ramasserLimite() {
        caseLaby.addPastille(landminePastille);
        int maxMines = Util.MAX_MINES;
        for(int i = 0 ; i < maxMines + 10 ; i ++) {
            player.moveToPosition(caseLaby.getX(), caseLaby.getY()); // Moving the player on the coin case
            game.isEatingAPastille(); // Checks if the player is eating a pastaga and updating the game if so
            caseLaby.addPastille(landminePastille); // Adding a new pastille
        }
        assertEquals(Util.MAX_MINES, game.getStaticWeaponsCount()); // You must now have the maximum amount possible of ammo, no less no more
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
        caseLaby.addPastille(landminePastille);
        Pastille invinciblePastille2 = new LandminePastille(game);
        caseLaby.addPastille(invinciblePastille2);
        assertNotEquals(invinciblePastille2, caseLaby.getPastille());
    }

    @Test
    void boundaryNegativePastilleNumber() {
        assertThrows(InvalidPastilleAmountException.class, () -> {game.generatePastille(PastilleType.LANDMINE, -1);});
    }

    @Test
    void boundaryTooMuchPastilles() {
        int nbPastilles = laby.getNbCasesLibres() + 1;
        assertThrows(TooMuchPastillesException.class, () -> {game.generatePastille(PastilleType.LANDMINE, nbPastilles);});
    }
}