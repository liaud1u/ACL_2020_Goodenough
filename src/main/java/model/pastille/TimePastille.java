package model.pastille;

import model.PacmanGame;

public class TimePastille extends Pastille{

    private int time;

    public TimePastille(PacmanGame game, int time) {
        super(game);
        this.time = time;
        this.type = PastilleType.TIME;
    }

    @Override
    public boolean ramasser() {
        super.ramasser();
        game.addTime(time);
        return true;
    }

    public int getTime() {
        return time;
    }
}
