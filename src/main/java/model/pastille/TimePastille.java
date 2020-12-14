package model.pastille;

import model.PacmanGame;

public class TimePastille extends Pastille{

    private int time;

    public TimePastille(PacmanGame game, PastilleType type, int time) {
        super(game, type);
        this.time = time;
        this.triggeredPastille.setValue(time);
    }

    @Override
    public boolean ramasser() {
        super.ramasser();
        game.addTime(time);
        return true;
    }
}
