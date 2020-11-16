package model;

public enum Difficulty {
    EASY(15, 2, 1, 0, 50),
    MEDIUM(10, 1, 1, 1, 44),
    HARD(5, 0, 1, 2, 40);

    private final int pastilleAmount;
    private final int nbMonstreStatic;
    private final int nbMonstreRandom;
    private final int nbMonstreFollow;
    private final int time;

    Difficulty(int pastilleAmount, int nbMonstreStatic, int nbMonstreRandom, int nbMonstreFollowing, int time) {
        this.pastilleAmount = pastilleAmount;
        this.nbMonstreStatic = nbMonstreStatic;
        this.nbMonstreRandom = nbMonstreRandom;
        this.nbMonstreFollow = nbMonstreFollowing;
        this.time = time;
    }

    public int getPastilleAmount() {
        return pastilleAmount;
    }

    public int getNbMonstreStatic() {
        return nbMonstreStatic;
    }

    public int getNbMonstreRandom() {
        return nbMonstreRandom;
    }

    public int getNbMonstreFollow() {
        return nbMonstreFollow;
    }

    public int getTime() {
        return time;
    }
}
