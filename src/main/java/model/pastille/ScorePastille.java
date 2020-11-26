package model.pastille;

/**
 * Pastille de score
 */
public class ScorePastille extends Pastille {


    private int score;

    public ScorePastille(int score) {
        super();
        this.score = score;
    }

    /**
     * Getter de la valeur de la pastille
     * @return int valeur
     */
    public int getScore() {
        return this.score;
    }



}