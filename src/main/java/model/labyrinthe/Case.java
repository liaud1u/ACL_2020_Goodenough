package model.labyrinthe;


import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import model.monster.Monster;
import model.pastille.*;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Case class
 */
public class Case {
  /**
   * X coord of the case
   */
  private int x;

  /**
   * Y coord of the case
   */
  private int y;

  /**
   * Optionnal pastilles on the case
   */
  private Pastille pastille;


  /**
   * List of neighbour cases
   */
  private ArrayList<Case> voisins = new ArrayList<>();

  /**
   * Bool, true if the case is a wall
   */
  private boolean estUnMur = true;

  /**
   * Bool, true if the case is void
   */
  private boolean estVide = true;

  /**
   * Optionnal Monster on the case
   */
  private Monster monstre;

  /**
   * Previous case (for Dijsktra)
   */
  private Case casePrecedente;

  /**
   * Weight of the edge (for graph)
   */
  private int weight;


  /**
   * Property to get if there is a Pastille on the Case
   */
  public BooleanProperty hasPastilleProperty = new SimpleBooleanProperty(false);

  /**
   * Constructor of Case
   *
   * @param x int coord x of the case
   * @param y int coord y of the case
   */
  public Case(int x, int y) {
    this(x, y, true);
  }


  /**
   * Constructor of Case
   *
   * @param x    int coord x of the case
   * @param y    int coord y of the case
   * @param bool bool true if the case is a wall
   */
  public Case(int x, int y, boolean bool) {
    this.x = x;
    this.y = y;
    this.estUnMur = bool;
    this.monstre = null;
    this.pastille = null;
  }

  /**
   * Add a neighbour to the case
   *
   * @param c Case neighbour to add
   */
  public void ajoutVoisin(Case c) {
    if (c != null)
    {
      if (!this.voisins.contains(c)) {
        this.voisins.add(c);
      }
      if (!c.voisins.contains(this)) {
        c.voisins.add(this);
      }
    }
  }

  /**
   * Check if there's a neighbour down
   *
   * @return true if neighbour down
   */
  public boolean voisinDessous() {
    return this.voisins.contains(new Case(this.x, this.y + 1));
  }

  /**
   * Check if there's a neighbour right
   *
   * @return true if neighbour right
   */
  public boolean voisinDroite() {
    return this.voisins.contains(new Case(this.x + 1, this.y));
  }

  /**
   * Check if there's a neighbour left
   *
   * @return true if there's a neighbour left
   */
  public boolean voisinGauche() {
    return this.voisins.contains(new Case(this.x - 1, this.y));
  }

  /**
   * Check if there's a neighbour up
   *
   * @return true if neighbour up
   */
  public boolean voisinDessus() {
    return this.voisins.contains(new Case(this.x, this.y - 1));
  }

  /**
   * Check if two cases are equals
   * @param o
   * @return boolean equals
   */
  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Case)) return false;
    Case c = (Case) o;
    return (this.x == c.x && this.y == c.y);
  }

  /**
   * HashCode
   * @return int Hashcode of the case
   */
  @Override
  public int hashCode() {
    return this.x + this.y * 256;
  }

  /**
   * Getter for the X coord of the case
   *
   * @return int x
   */
  public int getX() {
    return x;
  }

  /**
   * Setter for the X coord of the case
   *
   * @param x int coord x
   */
  public void setX(int x) {
    this.x = x;
  }

  /**
   * Getter for the y coord of the case
   *
   * @return int y
   */
  public int getY() {
    return y;
  }

  /**
   * Setter for the y coord of the case
   *
   * @param y int coord y
   */
  public void setY(int y) {
    this.y = y;
  }

  /**
   * Return neighbour list
   *
   * @return List of neighbour cases
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

  /**
   * Set the monster on the case
   * @param monstre Mosnter to set on the case
   */
  public void addMonster(Monster monstre) {
    this.monstre = monstre;
  }

  /**
   * Get the monster on the case
   * @return Monster monster on the case
   */
  public Monster getMonstre() {
    return monstre;
  }

  /**
   * Add a pastille to the case
   * @param p Pastille to add
   */
  public void addPastille(Pastille p ) {
    if(p != null && this.pastille == null) {
      this.pastille = p;
      this.hasPastilleProperty.setValue(true);
    }
  }

  /**
   *  Remove pastille in the case
   */
  public void removePastille() {
    if(this.pastille != null) {
      if(this.pastille.ramasser()) {
        this.pastille = null;
        this.hasPastilleProperty.setValue(false);
      }
    }
  }

  /**
   * Getter to know if case have a pastille
   * @return boolean true if there's a pastille
   */
  public boolean hasPastille() {
    return pastille != null;
  }

  /**
   * Getter to get pastille in the case
   * @return Pastille pastille in the case
   */
  public Pastille getPastille() { return this.pastille; }

  /**
   * @return (: boolean) if the case has either a monster or a pastille
   */
  public boolean hasEntity() {
    return this.pastille != null  || this.monstre != null;
  }



  @Override
  public String toString() {
    return "Case{" +
      "x=" + x +
      ", y=" + y +
            ", estUnMur=" + estUnMur +
            '}';
  }

  /**
   * Determine distance between this Case and the arg
   * @param c1 Case distant case to determine distance
   * @return int distance
   */
  public int distance(Case c1) {

    int distance = 0;
  
      if (c1 != null)
      {
        int x = getX() - c1.getX();
        int y = getY() - c1.getY();
  
        distance = Math.abs(x) + Math.abs(y);
  
      }
      return distance;

  }

  /**
   * Get weight of the case
   * @return int weight of the case
   */
  public int getWeight() {
    return weight;
  }

  /**
   * Set weight of the case
   * @param weight int weight of the case
   */
  public void setWeight(int weight) {
    this.weight = weight;
  }

  /**
   * Get previous case for Dijsktra
   * @return Case previous
   */
  public Case getCasePrecedente() {
    return casePrecedente;
  }

  /**
   * Set next case for Dijsktra
   * @param casePrecedente Next case
   */
  public void setCasePrecedente(Case casePrecedente) {
    this.casePrecedente = casePrecedente;
  }
}
