package model.pastille;

import model.PacmanGame;

public class InvinciblePastille extends Pastille{


    public InvinciblePastille(PacmanGame game, PastilleType type) {
        super(game, type);
    }

    @Override
    public boolean ramasser() {
        super.ramasser();
        game.setPlayerInvincible();
        return true;
    }
}
