package model.util;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * @author Ribeyrolles Matthieu
 * 05/11/2020, 21:54
 */
public abstract class SpriteTools {
  private static Image digitsSprite;

  /*------------------------------------------------------------------
                              Methods
   ------------------------------------------------------------------*/

  // getters
  // setters
  // private
  // public

  public static void setImageSize(double width, double height) {
    digitsSprite = new Image("score.png", width, height, true, false);
  }

  public static ImageView getSpritedDigit(int digit) {
    return new ImageView(digitsSprite);
  }

  /**
   *  @param number (:int), the number to convert into sprites
   *  @param
   *  @return list of {@link ImageView} representing the different digits for the given number
   * */
  public static ImageView[] getSpritedNumber(int number, int desiredLength, double spaceBetweenDigits) {
    if (number < 0) number = 0;
    if (desiredLength < 0) number = 0;
    if (digitsSprite == null) digitsSprite = new Image("score.png");  // prevent null image loading

    ImageView[] spritedDigits = new ImageView[desiredLength]; // the list of images displayed

    /**
     * return the formatted number
     * e.g. with a desired size of 5 and 155, it will return "00155"
     * */
    String formattedNumber = String.format("%0" + desiredLength + "d", number, desiredLength);

    // Iterate and set the viewport for every digit of the given number
    for (int i = desiredLength - 1; i >= 0; i--) {
      int currentDigit = Integer.parseInt(String.valueOf(formattedNumber.charAt(i)));

      spritedDigits[i] = new ImageView(digitsSprite);
      System.out.printf("Height: %f, width: %f\n", digitsSprite.getHeight(), digitsSprite.getWidth());
      spritedDigits[i].setTranslateX(digitsSprite.getWidth()/10 * i);
      spritedDigits[i].setViewport(
        new Rectangle2D(
          digitsSprite.getWidth() / 10 * currentDigit,
          0,
          digitsSprite.getWidth() / 10,
          digitsSprite.getHeight())
      );
    }

    return spritedDigits;
  }
   
   /*------------------------------------------------------------------
                            Constructors
   ------------------------------------------------------------------*/
}
