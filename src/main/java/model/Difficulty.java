package model;

/**
 * Enum for the difficulty
 */
public enum Difficulty {
    EASY(30, 5,  3,3,2, 1, 0, 50),
    MEDIUM(40, 3,2,2,1, 1, 1, 44),
    HARD(50, 1,1,1,0, 2, 1, 40);

    /**
     * Amount of score pastille
     */
    private final int scorePastilleAmount;

    /**
     * Amount of ammos coins
     */
    private final int ammosPastilleAmount;

    /**
     * Amount of invincibility coins
     */
    private final int invincibilityPastilleAmount;

    /**
     *  Amount of time coins
     */
    private final int timePastilleAmount;

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
    Difficulty(int scorePastilleAmount, int timePastilleAmount, int invincibilityPastilleAmount, int ammosPastilleAmount,int nbMonstreStatic, int nbMonstreRandom, int nbMonstreFollowing, int time) {
        this.scorePastilleAmount = scorePastilleAmount;
        this.timePastilleAmount = timePastilleAmount;
        this.invincibilityPastilleAmount = invincibilityPastilleAmount;
        this.ammosPastilleAmount = ammosPastilleAmount;
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

    public int getAmmosPastilleAmount() {
        return ammosPastilleAmount;
    }

    public int getInvincibilityPastilleAmount() {
        return invincibilityPastilleAmount;
    }

    public int getTimePastilleAmount() {
        return timePastilleAmount;
    }
}
