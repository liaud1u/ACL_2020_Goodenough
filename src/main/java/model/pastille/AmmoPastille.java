package model.pastille;

import model.PacmanGame;

public class AmmoPastille extends Pastille{

    public AmmoPastille(PacmanGame game, PastilleType type) {
        super(game,type);
    }

    @Override
    public void ramasser() {
        super.ramasser();
        game.addAmmos();
    }
}
