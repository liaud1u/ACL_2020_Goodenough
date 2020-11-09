package model.monster;

import model.PacmanGame;

public class Monstre {
    /**
     * PacmanGame jeu princial
     */
    private final PacmanGame game;

    /**
     * Coordonnées actuelles du monstre dans la fenêtre
     */
    private double x;

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    private double y;

    public Monstre(PacmanGame game, double x, double y) {
        this.game = game;
        this.x = x;
        this.y = y;
    }
}
