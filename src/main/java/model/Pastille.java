package model;

import javafx.scene.paint.Color;
import model.util.Util;

public abstract class Pastille {

    private boolean ramassee;
    private Color couleurPastille;
    private String typePastille;

    public Pastille(Color couleurPastille, String typePastille){
        this.ramassee = false;
        this.couleurPastille = couleurPastille;
        this.typePastille = typePastille;

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



}
