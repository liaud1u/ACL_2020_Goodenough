package model.util;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 * @author Ribeyrolles Matthieu
 * 03/11/2020, 20:46
 */
public abstract class Util {
  public static DoubleProperty windowSizeProperty = new SimpleDoubleProperty();
  public static DoubleProperty slotSizeProperty = new SimpleDoubleProperty();
  public static DoubleProperty wallSizeProperty = new SimpleDoubleProperty();
}
