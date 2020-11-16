package views.menus;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import model.PacmanGame;
import model.util.Util;

/** abstract class used for displaying the menu at the end of the level
 *  extending VBox for a vertical implementation
 *  */
public abstract class EndLevelView extends VBox {
  protected PacmanGame game;  //  the current game
  protected Button button;  // the button for exiting the menu
  protected Text text;  //  the text displayed
  /*------------------------------------------------------------------
                              Methods
   ------------------------------------------------------------------*/

  // getters
  // setters
  // private

  /** init the components
   * */
  protected void init() {
    //set the size of the VBox to fit the window size
    //FIXME remove this
    this.minWidthProperty().bind(Util.currentWindowWidthProperty);
    this.maxWidthProperty().bind(Util.currentWindowWidthProperty);
    this.minHeightProperty().bind(Util.currentWindowHeightProperty);
    this.maxHeightProperty().bind(Util.currentWindowHeightProperty);

    this.setAlignment(Pos.CENTER);  //  align the content on the center of the VBox
    this.setSpacing(50.d);  // set a spacing between elements of the VBox

    //Text init
    this.text = new Text(""); // init the text to an empty text
    this.text.setTextAlignment(TextAlignment.CENTER); // align the text on center
    this.text.setFill(Color.WHITE); // text is now white
    this.text.setFont(Font.font(40)); // change text font
    this.text.wrappingWidthProperty().bind(this.minWidthProperty().subtract(20.d)); // wrap the text to almost the width of the window

    //Button init
    this.button = new Button(""); // init the button without text
    this.button.setOnMousePressed(event -> this.game.changeLevel() ); // on click, we change the name
    this.button.setStyle(   //set the style for the button //TODO: set this on an external stylesheet file
      "-fx-cursor: hand;" +
      "-fx-background-color: yellow;" +
      "-fx-font-size: 20px"
    );
  }
  // public

   /*------------------------------------------------------------------
                            Constructors
   ------------------------------------------------------------------*/

  /** @param game (:{@link PacmanGame}), the current game
   * */
  public EndLevelView(PacmanGame game) {
    this.game = game;
    this.init();

    this.getChildren().addAll(this.text, this.button);
  }
}
