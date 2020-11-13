package model;

public enum Difficulty {
    EASY(3, 2, 1, 50),
    MEDIUM(6, 1, 2, 44),
    HARD(9, 0, 3, 40);

    private final int pastilleAmount;
    private final int nbMonstreStatic;
    private final int nbMonstreRandom;
    private final int time;

    Difficulty(int pastilleAmount, int nbMonstreStatic, int nbMonstreRandom, int time) {
        this.pastilleAmount = pastilleAmount;
        this.nbMonstreStatic = nbMonstreStatic;
        this.nbMonstreRandom = nbMonstreRandom;
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

    public int getTime() {
        return time;
    }
}
