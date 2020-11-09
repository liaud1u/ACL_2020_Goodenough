package model.monster;

import model.PacmanGame;
import model.util.Util;

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

    /**
     * Renvoie la coordonnée X du monstre pour la gestion du dessin et des collisions
     * @return double x
     */
    public double getPosX() {
        return (x * Util.slotSizeProperty.get() + Util.slotSizeProperty.get() / 2);
    }

    /**
     * Renvoie la coordonnée Y du monstre pour la gestion du dessin et des collisions
     * @return double y
     */
    public double getPosY() {
        return (y * Util.slotSizeProperty.get() + Util.slotSizeProperty.get() / 2);
    }

}
