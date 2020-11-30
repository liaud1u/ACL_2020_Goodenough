package model.pastille;

import model.PacmanGame;

public class InvinciblePastille extends Pastille{


    public InvinciblePastille(PacmanGame game, PastilleType type) {
        super(game, type);
    }

    @Override
    public void ramasser() {
        super.ramasser();
        game.setPlayerInvincible();
    }
}
