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

  /**
   * @param (width, height) (:double, double), the dimensions for the image
   * */
  public static void setImageSize(double width, double height) {
    digitsSprite = new Image("score.png", width, height, true, false);
  }

  /**
   * @param (number, desiredLength) (:int, int), the number to cast and the length it has to be
   *                                e.g. getFromattedNumber(1, 3) will return 001
   * */
  private static String getFromattedNumber(int number, int desiredLength) {
    if (number < 0) number = 0;
    if (desiredLength < 0) desiredLength = 1;

    return String.format("%0" + desiredLength + "d", number, desiredLength);
  }

  /**
   * @param digit (:int), the digit who needs to be sprited
   * @return spritedDigit, an {@link ImageView}, corresponding to the sprited digit
   * */
  public static ImageView getSpritedDigit(int digit) {
    if (digitsSprite == null) digitsSprite = new Image("score.png");  // prevent null image loading

    ImageView spritedDigit = new ImageView(digitsSprite);

    // adjust the viewport on the sprite
    spritedDigit.setViewport(
      new Rectangle2D(
        digitsSprite.getWidth() / 10 * digit,
        0,
        digitsSprite.getWidth() / 10,
        digitsSprite.getHeight())
    );

    return spritedDigit;
  }


  /**
   *  @param number (:int), the number to convert into sprites
   *  @param
   *  @return list of {@link ImageView} representing the different digits for the given number
   *
   *  TODO: use the spaceBetweenDigits param
   * */
  public static ImageView[] getSpritedNumber(int number, int desiredLength, double spaceBetweenDigits) {
    if (number < 0) number = 0;
    if (desiredLength < 0) desiredLength = 1;
    if (digitsSprite == null) digitsSprite = new Image("score.png");  // prevent null image loading

    ImageView[] spritedDigits = new ImageView[desiredLength]; // the list of images displayed

    /**
     * return the formatted number
     * e.g. with a desired size of 5 and 155, it will return "00155"
     * */
    String formattedNumber = getFromattedNumber(number, desiredLength);

    // Iterate and set the viewport for every digit of the given number
    for (int i = desiredLength - 1; i >= 0; i--) {
      int currentDigit = Integer.parseInt(String.valueOf(formattedNumber.charAt(i)));

      spritedDigits[i] = getSpritedDigit(currentDigit); // generate the sprited image view for the current digit
      spritedDigits[i].setTranslateX(digitsSprite.getWidth()/10 * i);
    }

    return spritedDigits;
  }
   
   /*------------------------------------------------------------------
                            Constructors
   ------------------------------------------------------------------*/
}
