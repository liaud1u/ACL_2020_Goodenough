package model.pastille;

import model.PacmanGame;

public class AmmoPastille extends Pastille{

    public AmmoPastille(PacmanGame game) {
        super(game);
        this.type = PastilleType.AMMO;
    }

    @Override
    public boolean ramasser() {
        if(!game.hasMaximumAmmos()) {
            super.ramasser();
            game.addAmmos();
            return true;
        }
        return false;
    }
}
