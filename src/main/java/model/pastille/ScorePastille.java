package model.pastille;

import model.PacmanGame;

/**
 * Pastille de score
 */
public class ScorePastille extends Pastille {


    private int score;

    public ScorePastille(PacmanGame game, int score) {
        super(game);
        this.score = score;
        this.type = PastilleType.SCORE;
    }

    @Override
    public boolean ramasser() {
        super.ramasser();
        game.addScore(this.score);
        return true;
    }

    public int getScore() {
        return score;
    }
}