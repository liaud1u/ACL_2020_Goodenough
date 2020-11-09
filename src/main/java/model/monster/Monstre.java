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
    private double x, y;

    public Monstre(PacmanGame game, double x, double y) {
        this.game = game;
        this.x = x;
        this.y = y;
    }
}
