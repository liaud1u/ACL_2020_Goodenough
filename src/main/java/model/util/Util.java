package model.util;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 * Classe contenant les valeurs utiles commune à tout le jeu
 */
public abstract class Util {
  /**
   * Taille du labyrinthe (cases)
   */
  public static final int MAZE_SIZE = 20;

  /**
   * Vitesse du personnage
   */
  public static double speedDifficulty = 1.;


  /**
   * Ratio de la taille du personnage par rapport à la taille d'une case
   */
  public static double RATIO = 0.8;

  /**
   * Taille de la fenêtre
   */
  public static DoubleProperty windowSizeProperty = new SimpleDoubleProperty();

  /**
   * Taille d'une case
   */
  public static DoubleProperty slotSizeProperty = new SimpleDoubleProperty();

  /**
   * Taille d'un mur
   */
  public static DoubleProperty wallSizeProperty = new SimpleDoubleProperty();

  /**
   * Initialisation des valeurs
   */
  public static void init() {
    Util.slotSizeProperty.bind(Util.windowSizeProperty.divide(MAZE_SIZE));
    Util.wallSizeProperty.bind(Util.slotSizeProperty.divide(10));
  }
}
