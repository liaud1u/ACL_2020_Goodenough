package model.pastille;

import model.PacmanGame;

public class LandminePastille extends Pastille{

    public LandminePastille(PacmanGame game) {
        super(game);
        this.type = PastilleType.LANDMINE;
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
