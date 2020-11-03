package model;

import java.util.ArrayList;

/**
 * @author adrien & florian
 */
public class Case {

    // Coordonnees X et Y de la case dans le labyrinthe
    private int x;
    private int y;
    private ArrayList<Case> voisins = new ArrayList<>();
    private boolean estUnMur = true;
    private boolean estVide = true;

    public Case(int x, int y)
    {
        this(x, y, true);
    }

    public Case(int x, int y, boolean bool) {
        this.x = x;
        this.y = y;
        this.estUnMur = bool;
    }

    void ajoutVoisin(Case c) {
        if (!this.voisins.contains(c))
        {
            this.voisins.add(c);
        }
        if (!c.voisins.contains(this))
        {
            c.voisins.add(this);
        }
    }

    boolean voisinDessous() {
        return this.voisins.contains(new Case(this.x, this.y + 1));
    }

    boolean voisinDroite() {
        return this.voisins.contains(new Case(this.x + 1, this.y));
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

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public ArrayList<Case> getVoisins() {
        return voisins;
    }

    public void setVoisins(ArrayList<Case> voisins) {
        this.voisins = voisins;
    }

    public boolean isEstUnMur() {
        return estUnMur;
    }

    public void setEstUnMur(boolean estUnMur) {
        this.estUnMur = estUnMur;
    }

    public boolean isEstVide() {
        return estVide;
    }

    public void setEstVide(boolean estVide) {
        this.estVide = estVide;
    }

    @Override
    public String toString() {
        return "Case{" +
                "x=" + x +
                ", y=" + y +
                ", estUnMur=" + estUnMur +
                ", estVide=" + estVide +
                '}';
    }
}
