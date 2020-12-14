package model.pastille;

import model.PacmanGame;

/**
 * Pastille de score
 */
public class ScorePastille extends Pastille {


    private int score;

    public ScorePastille(PacmanGame game, PastilleType type, int score) {
        super(game, type);
        this.score = score;

        this.triggeredPastille.setValue(score);
    }

    @Override
    public boolean ramasser() {
        super.ramasser();
        game.addScore(this.score);
        return true;
    }
}