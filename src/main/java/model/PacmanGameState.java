package model;


import java.util.Timer;
import java.util.TimerTask;

public class PacmanGameState {

    public enum EtatJeu {
        EN_COURS,
        VICTOIRE,
        PERDU,
        ARRETER
    }

    private final int SECONDES_AU_DEBUT = 60;

    private EtatJeu etatJeu;
    private int score;
    private int tempsRestant;
    private int nbPastillesNormalesAvalees;

    public PacmanGameState() {
        this.etatJeu = EtatJeu.ARRETER;
        this.score = 0;
        this.tempsRestant = SECONDES_AU_DEBUT;
        this.nbPastillesNormalesAvalees = 0;

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {

            @Override
            public void run() {
                // Compteur ici
                if(etatJeu == EtatJeu.EN_COURS) {
                    tempsRestant--;
                }
            }
        };

        timer.schedule(task, 0l, 1l);
    }

    public EtatJeu getState() {
        return this.etatJeu;
    }

    public int getTempsRestant() {
        return this.tempsRestant;
    }

    public int getScore() {
        return this.score;
    }

    public void setState(EtatJeu victoire) {
        this.etatJeu = victoire;
    }

    public int getNbPastillesNormalesAvalees() {
        return this.nbPastillesNormalesAvalees;
    }

    public void setNbPastillesNormalesAvalees(int i) {
        this.nbPastillesNormalesAvalees = i;
    }

    public void setScore(int i) {
        this.score = i;
    }

    public void setTempsRestant(int i) {
        this.tempsRestant = i;
    }

}
