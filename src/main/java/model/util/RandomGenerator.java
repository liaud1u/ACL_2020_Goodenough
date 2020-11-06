package model.util;

public abstract class RandomGenerator {

    /**
     * Methode qui genere un entier aleatoire entre 0 et la valeur maximum - 1
     * @param maximum borne superieure semi ouverte
     * @return entier aleatoire
     */
    public static int getRandomValue(int maximum) {
        int random = (int)(Math.random() * maximum);
        return random;
    }


}
