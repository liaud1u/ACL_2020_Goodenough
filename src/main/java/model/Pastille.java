package model;

import javafx.scene.paint.Color;

public abstract class Pastille {
    protected double rayonPastille; //A définir
    private Labyrinthe labyrintheCourant;
    private Case casePastille;
    private boolean ramassee;
    private Color couleurPastille;
    private String typePastille;

    public Pastille(Case casePastille, Labyrinthe labyrintheCourant, Color couleurPastille, String typePastille){
        this.ramassee = false;
        this.casePastille = casePastille;
        this.labyrintheCourant = labyrintheCourant;
        this.couleurPastille = couleurPastille;
        this.typePastille = typePastille;

    }

    public boolean isRamassee() {
        return ramassee;
    }

    public void ramasser() {
        this.ramassee = true;
    }

    public Case getCasePastille(){
        return casePastille;
    }

    public Labyrinthe getLabyrintheCourant() {
        return labyrintheCourant;
    }

    public void setLabyrintheCourant(Labyrinthe labyrintheCourant) {
        this.labyrintheCourant = labyrintheCourant;
    }

    public void setCasePastille(Case casePastille) {
        this.casePastille = casePastille;
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

    public abstract String toString();
    public double getRayon() {
        return this.rayonPastille;
    }


}
