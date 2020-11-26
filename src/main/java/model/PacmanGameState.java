package model;


/**
 * Gère l'état actuel du jeu
 */
public class PacmanGameState {


    /**
     * Etat actuel du jeu
     */
    private EtatJeu etatJeu;
    /**
     * Score actuel
     */
    private int score;
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
        this.nbPastillesNormalesAvalees = 0;
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

}
