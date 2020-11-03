package model;

import javafx.scene.paint.Color;
import model.util.Util;

public abstract class Pastille {

    private boolean ramassee;
    private Color couleurPastille;
    private String typePastille;

    private final double x;
    private final double y;

    public Pastille(Color couleurPastille, String typePastille, int x, int y) {
        this.ramassee = false;
        this.couleurPastille = couleurPastille;
        this.typePastille = typePastille;
        this.x = x;
        this.y = y;
    }

    public boolean isRamassee() {
        return ramassee;
    }

    public void ramasser() {
        if (!ramassee)
            this.ramassee = true;
    }

    public Color getCouleurPastille() {
        return couleurPastille;
    }

    public void setCouleurPastille(Color couleurPastille) {
        this.couleurPastille = couleurPastille;
    }

    public String getTypePastille() {
        return typePastille;
    }

    public void setTypePastille(String typePastille) {
        this.typePastille = typePastille;
    }

    public double getX() {
        return (x * Util.slotSizeProperty.get() + Util.slotSizeProperty.get() / 2);
    }

    public double getY() {
        return (y * Util.slotSizeProperty.get() + Util.slotSizeProperty.get() / 2);
    }
}
