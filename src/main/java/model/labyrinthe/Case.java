package model.labyrinthe;


import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import model.pastille.Pastille;

import java.util.ArrayList;

/**
 * Objet case
 */
public class Case {
  /**
   * Coordonée x de la case
   */
  private int x;

  private Pastille pastille;


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
  private boolean hasMonster;  // if a monster is on the case

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
    this.hasMonster = false;
    this.pastille = null;
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

  /** @return (:boolean) if the case has a monster*/
  public boolean hasMonster() {
    return hasMonster;
  }
  /** @return (:boolean) if the case has a pastille*/
  public boolean hasPastille() {
    return this.pastille != null;
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
  /** @param hasMonster (:boolean) return if the case has a monster*/
  public void setMonster(boolean hasMonster) {
    this.hasMonster = hasMonster;
  }
  /** @param pastille (:{@link Pastille}) set the new pastille (null if none) */
  public void setPastille(Pastille pastille) {
    this.hasPastilleProperty.set(pastille != null);
    this.pastille = pastille;
  }
  /** @return (:boolean) if the case has either a monster or a pastille*/
  public boolean hasEntity() { return this.hasPastille() || this.hasMonster; }

  public Pastille getPastille() {
    return pastille;
  }

  @Override
  public String toString() {
    return "Case{" +
      "x=" + x +
      ", y=" + y +
            ", estUnMur=" + estUnMur +
            '}';
  }

  public BooleanProperty hasPastilleProperty = new SimpleBooleanProperty(false);

  public void destroyPastille() {
    this.pastille = null;
    this.hasPastilleProperty.set(false);
  }

  public int distance(Case c1) {
    int distance;
    int x = getX() - c1.getX();
    int y = getY() - c1.getY();

    distance = Math.abs(x) + Math.abs(y);

    return distance;
  }
}
