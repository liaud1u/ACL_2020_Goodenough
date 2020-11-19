package model.pastille;

import javafx.scene.paint.Color;
import model.util.Util;

/**
 * Pastille
 */
public abstract class Pastille {

    /**
     * double coordonnée x de la pastille
     */
    private final double x;
    /**
     * double coordonnée y de la pastille
     */
    private final double y;
    /**
     * Booléen, true si la pastille a été rammassée
     */
    private boolean ramassee;
    /**
     * Couleur de la pastille
     */
    private Color couleurPastille;
    /**
     * Type de la pastille
     */
    private String typePastille;

    /**
     * Valeur de la pastille
     */
    protected int value = 0;

    /**
     * Constructeur par défaut de la pastille
     *
     * @param couleurPastille Couleur par défaut de la pastille
     * @param typePastille    Type de la pastille
     * @param x               double x
     * @param y               double y
     */
    public Pastille(Color couleurPastille, String typePastille, double x, double y) {
        this.ramassee = false;
        this.couleurPastille = couleurPastille;
        this.typePastille = typePastille;
        this.x = x;
        this.y = y;
    }

    /**
     * Getter du fait que la pastille est ramassée
     *
     * @return true si la pastille est ramassée, false sinon
     */
    public boolean isRamassee() {
        return ramassee;
    }

    /**
     * Ramasser une pastille
     */
    public void ramasser() {
        if (!ramassee) {
            this.ramassee = true;
        }
    }


    /**
     * Redéfinition de toString()
     * @return String texte représentant un objet pastille
     */
    @Override
    public String toString() {
        return "Pastille{" +
                "x=" + x +
                ", y=" + y +
                ", ramassee=" + ramassee +
                ", couleurPastille=" + couleurPastille +
                ", typePastille='" + typePastille + '\'' +
                ", value=" + value +
                '}';
    }

    /**
     * Getter de la couleur actuelle de la pastille
     *
     * @return Couleur de la pastille
     */
    public Color getCouleurPastille() {
        return couleurPastille;
    }

    /**
     * Définit la couleur de la pastille
     *
     * @param couleurPastille Couleur de la pastille
     */
    public void setCouleurPastille(Color couleurPastille) {
        this.couleurPastille = couleurPastille;
    }

    /**
     * Getter du type de la pastille
     *
     * @return String type de la pastille
     */
    public String getTypePastille() {
        return typePastille;
    }

    /**
     * Setter du type de la pastille
     *
     * @param typePastille String type de la pastille
     */
    public void setTypePastille(String typePastille) {
        this.typePastille = typePastille;
    }

    /**
     * Getter de la coordonnée x de la pastille
     *
     * @return double x
     */
    public double getX() {
        return x;
    }

    /**
     * Getter de la coordonnée y de la pastille
     * @return double y
     */
    public double getY() {
        return y;
    }


    /**
     * Renvoie la coordonnée X de la pastille pour la gestion du dessin et des collisions
     * @return double x
     */
    public double getPosX() {
        return (x * Util.slotSizeProperty.get() + Util.slotSizeProperty.get() / 2);
    }

    /**
     * Renvoie la coordonnée Y de la pastille pour la gestion du dessin et des collisions
     * @return double y
     */
    public double getPosY() {
        return (y * Util.slotSizeProperty.get() + Util.slotSizeProperty.get() / 2);
    }

    /**
     * Getter de la valeur de la pastille
     * @return int valeur
     */
    public int getValue() {
        return value;
    }


    public void setRamassee(boolean ramassee) { this.ramassee = ramassee;
    }
}
