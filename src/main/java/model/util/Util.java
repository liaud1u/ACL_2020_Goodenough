package model.util;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 * Classe contenant les valeurs utiles commune à tout le jeu
 */
public abstract class Util {
  public final static String BEST_SCORES_URL = "best_scores.xml";

  // ELEMENTS SIZE
  public static final int MAZE_SIZE = 15; //Taille du labyrinthe (cases)

  // RATIOS
  public static double RATIO_PERSONNAGE = 1.;  //Ratio de la taille du personnage par rapport à la taille d'une case
  public static double RATIO_PASTILLE = 0.3;  //Ratio de la taille d'une pastille par rapport à la taille d'une case
  public static double RATIO_MONSTRE = 1;
  public static double RATIO_FIREBALL = 1;

  // PROPERTIES
  public static DoubleProperty windowSizeProperty = new SimpleDoubleProperty(); //Taille de la fenêtre
  public static DoubleProperty slotSizeProperty = new SimpleDoubleProperty(); //Taille d'une case
  public static DoubleProperty wallSizeProperty = new SimpleDoubleProperty(); //Taille d'un mur
  public static DoubleProperty currentWindowWidthProperty = new SimpleDoubleProperty();
  public static DoubleProperty currentWindowHeightProperty = new SimpleDoubleProperty();
  public static DoubleProperty minWindowSizeProperty = new SimpleDoubleProperty(300.);
  public static DoubleProperty rightWidthProperty = new SimpleDoubleProperty();

  // VALUES
  public static boolean DEBUG_MODE = false;
  public static double rightTextHeight = 64;
  public static int speedDifficulty = 3;  //Vitesse du personnage
  public static int SCORE_SIZE = 8; //Nombre de chiffre dans le score
  public static int timer = 45; // timer (seconds)
  public static int maxTimerSize = 3;
  public static int player = 2;
  public static int MAX_AMMOS = 3;

  /**
   * Initialisation des valeurs
   */
  public static void init() {
    Util.slotSizeProperty.bind(Util.windowSizeProperty.divide(MAZE_SIZE));
    Util.wallSizeProperty.bind(Util.slotSizeProperty.divide(10));
    Util.rightWidthProperty.bind(Util.minWindowSizeProperty.divide(1.5));
  }
}
