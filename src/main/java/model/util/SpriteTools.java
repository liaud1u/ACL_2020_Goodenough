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

  public static ImageView[] getSpritedNumber(int number, int desiredLength, double width, double height, double spaceBetweenDigits) {
    if (digitsSprite == null) setImageSize(width, height);  // prevent null image loading
    ImageView[] spritedDigits = new ImageView[desiredLength]; // the list of images displayed

    /**
     * Return the formatted number
     * e.g. with a desired size of 5 and 155, it will return "00155"
     * */
    String formattedNumber = String.format("%0" + desiredLength + "d", number, desiredLength);

    for (int i = desiredLength - 1; i >= 0; i--) {
      int currentDigit = Integer.parseInt(String.valueOf(formattedNumber.charAt(i)));

      spritedDigits[i] = new ImageView(digitsSprite);
      spritedDigits[i].setTranslateX(digitsSprite.getWidth()/10 * i);
      spritedDigits[i].setViewport(
        new Rectangle2D(
          (digitsSprite.getWidth() / 10) * currentDigit,
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
