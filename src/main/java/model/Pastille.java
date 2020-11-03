package model;

import javafx.scene.paint.Color;

public abstract class Pastille {
    protected double rayonPastille = 4; //A définir
    private boolean ramassee;
    private Color couleurPastille;
    private String typePastille;

    public Pastille(Color couleurPastille, String typePastille){
        this.ramassee = false;
        this.couleurPastille = couleurPastille;
        this.typePastille = typePastille;

    }

    public double getRayonPastille() {
        return rayonPastille;
    }

    public void setRayonPastille(double rayonPastille) {
        this.rayonPastille = rayonPastille;
    }

    public boolean isRamassee() {
        return ramassee;
    }

    public void ramasser() {
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

    public double getRayon() {
        return this.rayonPastille;
    }


}
