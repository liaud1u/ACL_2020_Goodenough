package model.util;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import model.PacmanGame;

/**
 * @author Ribeyrolles Matthieu
 * 03/11/2020, 20:46
 */
public abstract class Util {
  public static final int MAZE_SIZE = 5;

  public static double speedDifficulty = 1.;

  public static DoubleProperty windowSizeProperty = new SimpleDoubleProperty();
  public static DoubleProperty slotSizeProperty = new SimpleDoubleProperty();
  public static DoubleProperty wallSizeProperty = new SimpleDoubleProperty();

  public static void init() {
    Util.slotSizeProperty.bind(Util.windowSizeProperty.divide(MAZE_SIZE));
    Util.wallSizeProperty.bind(Util.slotSizeProperty.divide(10));
  }
}
