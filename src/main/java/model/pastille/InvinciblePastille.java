package model.pastille;

import model.PacmanGame;

public class InvinciblePastille extends Pastille{


    public InvinciblePastille(PacmanGame game) {
        super(game);
        this.type = PastilleType.INVINCIBILITY;
    }

    @Override
    public boolean ramasser() {
        super.ramasser();
        game.setPlayerInvincible();
        return true;
    }
}
