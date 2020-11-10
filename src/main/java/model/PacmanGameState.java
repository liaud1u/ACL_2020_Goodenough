package model;


import java.util.Timer;
import java.util.TimerTask;
// FIXME: IMPLEMENT IN PROJECT
/**
 * Gère l'état actuel du jeu
 */
public class PacmanGameState {

    /**
     * Nombre de seconde au début du niveau
     */
    private final int SECONDES_AU_DEBUT = 60;
    /**
     * Etat actuel du jeu
     */
    private EtatJeu etatJeu;
    /**
     * Score actuel
     */
    private int score;
    /**
     * Temps restant
     */
    private int tempsRestant;
    /**
     * Nb de pastille avalées au total
     */
    private int nbPastillesNormalesAvalees;

    /**
     * Constructeur de l'état
     */
    public PacmanGameState() {
        //Initialisation des valeurs de jeu
        this.etatJeu = EtatJeu.ARRETER;
        this.score = 0;
        this.tempsRestant = SECONDES_AU_DEBUT;
        this.nbPastillesNormalesAvalees = 0;

        //Création et lancement du timer
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {

            @Override
            public void run() {
                // Compteur ici
                if (etatJeu == EtatJeu.EN_COURS) {
                    tempsRestant--;
                }
            }
        };

        timer.schedule(task, 0l, 1l);
    }

    /**
     * Getter de l'état actuel du jeu
     *
     * @return GameState état actuel du jeu
     */
    public EtatJeu getState() {
        return this.etatJeu;
    }

    /**
     * Définit l'état courant
     *
     * @param victoire EtatJeu à définir
     */
    public void setState(EtatJeu victoire) {
        this.etatJeu = victoire;
    }

    /**
     * Retourne le temps de jeu restant
     *
     * @return int temps de jeu restant
     */
    public int getTempsRestant() {
        return this.tempsRestant;
    }

    /**
     * Définit le temps restant
     *
     * @param i int i temps restant
     */
    public void setTempsRestant(int i) {
        this.tempsRestant = i;
    }

    /**
     * Retourne le score actuel
     *
     * @return int score actuel
     */
    public int getScore() {
        return this.score;
    }

    /**
     * Définit le score
     *
     * @param i int i score
     */
    public void setScore(int i) {
        this.score = i;
    }

    /**
     * Getter du nombre de pastilles avalées
     *
     * @return int nb de pastilles avalées
     */
    public int getNbPastillesNormalesAvalees() {
        return this.nbPastillesNormalesAvalees;
    }

    /**
     * Définit le nombre de pastilles normales avalée
     *
     * @param i int nombre de pastilles
     */
    public void setNbPastillesNormalesAvalees(int i) {
        this.nbPastillesNormalesAvalees = i;
    }

    /**
     * Enumetation des états possibles
     */
    public enum EtatJeu {
        EN_COURS,
        VICTOIRE,
        PERDU,
        ARRETER
    }

    public boolean isGameOver() {
        return this.etatJeu == EtatJeu.PERDU;
    }
}
