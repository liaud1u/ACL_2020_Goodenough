package model.pastille;

import model.PacmanGame;

public class LandminePastille extends Pastille{

    public LandminePastille(PacmanGame game, PastilleType type) {
        super(game, type);
    }


    @Override
    public boolean ramasser() {
        if(!game.hasMaximumLandmines()) {
            super.ramasser();
            this.game.addLandMine();
            return true;
        }
        return false;
    }

}
