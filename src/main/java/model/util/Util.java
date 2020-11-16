package model.util;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 * Classe contenant les valeurs utiles commune à tout le jeu
 */
public abstract class Util {
  // ELEMENTS SIZE

  public static final int MAZE_SIZE = 15; //Taille du labyrinthe (cases)

  //RATIOS
  public static double RATIO_PERSONNAGE = 1.;  //Ratio de la taille du personnage par rapport à la taille d'une case
  public static double RATIO_PASTILLE = 0.3;  //Ratio de la taille d'une pastille par rapport à la taille d'une case
  public static double RATIO_MONSTRE = 1;

  // PROPERTIES
  public static DoubleProperty windowSizeProperty = new SimpleDoubleProperty(); //Taille de la fenêtre
  public static DoubleProperty slotSizeProperty = new SimpleDoubleProperty(); //Taille d'une case
  public static DoubleProperty wallSizeProperty = new SimpleDoubleProperty(); //Taille d'un mur


  public static int speedDifficulty = 3;  //Vitesse du personnage
  public static int SCORE_SIZE = 8; //Nombre de chiffre dans le score
  public static int timer = 45; // timer (seconds)
  public static int maxTimerSize = 3;
  public static int DISPLAY_MSG_DURATION = 3;

  /**
   * Initialisation des valeurs
   */
  public static void init() {
    Util.slotSizeProperty.bind(Util.windowSizeProperty.divide(MAZE_SIZE));
    Util.wallSizeProperty.bind(Util.slotSizeProperty.divide(10));
  }
}
