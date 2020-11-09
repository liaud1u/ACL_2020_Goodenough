package model;


import java.util.ArrayList;

/**
 * Objet case
 */
public class Case {
    /**
     * Coordonée x de la case
     */
    private int x;

    /**
     * Coordonnée y de la case
     */
    private int y;

    /**
     * Liste des cases voisines de la case
     */
    private ArrayList<Case> voisins = new ArrayList<>();

    /**
     * Booléen, vrai si la case courante est un mur
     */
    private boolean estUnMur = true;

    /**
     * Booléen, vrai si la case courante est vide
     */
    private boolean estVide = true;

    /**
     * Booléen, vrai si une entité (monstre ou pastille) est présente sur la case
     */
    private boolean possedeEntite = false;

    private Case casePrecedente;

    private int distance;

    /**
     * Constructeur d'une case
     *
     * @param x int coordonnée x de la case
     * @param y int coordonnée y de la case
     */
    public Case(int x, int y) {
        this(x, y, true);
    }

    /**
     * Constructeur d'une case
     *
     * @param x    int coordonnée x de la case
     * @param y    int coordonnée y de la case
     * @param bool booléen vrai si la case est un mur
     */
    public Case(int x, int y, boolean bool) {
        this.x = x;
        this.y = y;
        this.estUnMur = bool;
    }

    /**
     * Ajout d'un voisin à la case courante
     *
     * @param c Case voisin à ajouter
     */
    public void ajoutVoisin(Case c) {
        if (!this.voisins.contains(c)) {
            this.voisins.add(c);
        }
        if (!c.voisins.contains(this)) {
            c.voisins.add(this);
        }
    }

    /**
     * Vérifie si il y a un voisin dessous
     *
     * @return true si il y a un voisin en dessous
     */
    public boolean voisinDessous() {
        return this.voisins.contains(new Case(this.x, this.y + 1));
    }

    /**
     * Vérifie si il y a un voisin à droite
     *
     * @return true si il y a un voisin à droite
     */
    public boolean voisinDroite() {
        return this.voisins.contains(new Case(this.x + 1, this.y));
    }

    /**
     * Vérifie si il y a un voisin à gauche
     *
     * @return true si il y a un voisin à gauche
     */
    public boolean voisinGauche() {
        return this.voisins.contains(new Case(this.x - 1, this.y));
    }

    /**
     * Vérifie si il y a un voisin au dessus
     *
     * @return true si il y a un voisin au dessus
     */
    public boolean voisinDessus() {
        return this.voisins.contains(new Case(this.x, this.y - 1));
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Case)) return false;
        Case c = (Case) o;
        return (this.x == c.x && this.y == c.y);
    }

    @Override
    public int hashCode() {
        return this.x + this.y * 256;
    }

    /**
     * Renvoie la coordonnée x de la case
     *
     * @return int x
     */

    public int getX() {
        return x;
    }


    /**
     * Setter de la coordonnée x de la case
     *
     * @param x int coordonnée x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Renvoie la coordonée y de la case
     *
     * @return int y
     */
    public int getY() {
        return y;
    }

    /**
     * Setter de la coordonnée y de la case
     *
     * @param y int coordonnée y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Retourne la liste des voisins
     *
     * @return Liste de cases voisines
     */
    public ArrayList<Case> getVoisins() {
        return voisins;
    }

    /**
     * Permet de définir la liste des voisins
     *
     * @param voisins Liste de cases voisines
     */
    public void setVoisins(ArrayList<Case> voisins) {
        this.voisins = voisins;
    }

    /**
     * Vérifie si la case est un mur
     *
     * @return true si la case est un mur, false sinon
     */
    public boolean estUnMur() {
        return estUnMur;
    }

    /**
     * Permet de définir la case courante comme un mur
     *
     * @param estUnMur booléen true si la case est un mur, faux sinon
     */
    public void setEstUnMur(boolean estUnMur) {
        this.estUnMur = estUnMur;
    }

    public Case getCasePrecedente() {
        return casePrecedente;
    }

    public void setCasePrecedente(Case casePrecedente) {
        this.casePrecedente = casePrecedente;
    }

    /**
     * Vérifie si la case est vide
     *
     * @return true si la case est vide, false sinon
     */
    public boolean estVide() {
        return estVide;
    }

    /**
     * Permet de définir la case courante comme vidé
     *
     * @param estVide true si la case est vide, false sinon
     */
    public void setEstVide(boolean estVide) {
        this.estVide = estVide;
    }

    @Override
    public String toString() {
        return "Case{" +
                "x=" + x +
                ", y=" + y +
                ", estUnMur=" + estUnMur +
                '}';
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public boolean possedeEntite() {
        return possedeEntite;
    }

    public void setPossedeEntite(boolean possedePastille) {
        this.possedeEntite = possedePastille;
    }
}
