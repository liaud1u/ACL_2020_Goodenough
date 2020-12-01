package model;

/**
 * Enum for the difficulty
 */
public enum Difficulty {
    EASY(5, 2, 0, 1, 50),
    MEDIUM(10, 1, 1, 1, 44),
    HARD(15, 0, 1, 2, 40);

    /**
     * Amount of pastille
     */
    private final int scorePastilleAmount;

    /**
     * Amount of static monster
     */
    private final int nbMonstreStatic;

    /**
     * Amount of random movement monster
     */
    private final int nbMonstreRandom;

    /**
     * Amount of following player monster
     */
    private final int nbMonstreFollow;

    /**
     * Time for the level
     */
    private final int time;

    /**
     * Constructor of the difficulty
     * @param scorePastilleAmount Amount of pastille with that difficulty
     * @param nbMonstreStatic amount of static monster with that difficulty
     * @param nbMonstreRandom amount of random monster with that difficulty
     * @param nbMonstreFollowing amount of following monster with that difficulty
     * @param time time with that difficulty
     */
    Difficulty(int scorePastilleAmount, int nbMonstreStatic, int nbMonstreRandom, int nbMonstreFollowing, int time) {
        this.scorePastilleAmount = scorePastilleAmount;
        this.nbMonstreStatic = nbMonstreStatic;
        this.nbMonstreRandom = nbMonstreRandom;
        this.nbMonstreFollow = nbMonstreFollowing;
        this.time = time;
    }

    /**
     * Getter of the amount of pastille
     * @return int amount of pastille
     */
    public int getScorePastilleAmount() {
        return scorePastilleAmount;
    }

    /**
     * Getter of the amount of static monster
     * @return int amount of static monster
     */
    public int getNbMonstreStatic() {
        return nbMonstreStatic;
    }

    /**
     * Getter of the amount of random monster
     * @return int amount of random monster
     */
    public int getNbMonstreRandom() {
        return nbMonstreRandom;
    }

    /**
     * Getter of the amount of following monster
     * @return int amount
     */
    public int getNbMonstreFollow() {
        return nbMonstreFollow;
    }

    /**
     * Getter for the time with that difficulty
     * @return int time
     */
    public int getTime() {
        return time;
    }
}
