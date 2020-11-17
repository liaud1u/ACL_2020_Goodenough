package views.menus;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import model.PacmanGame;
import model.util.Util;

/** abstract class used for displaying the menu at the end of the level
 *  extending VBox for a vertical implementation
 *  */
public abstract class EndLevelView extends VBox {
  protected PacmanGame game;  //  the current game
  protected Button button;  // the button for exiting the menu
  protected Label text;  //  the text displayed
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
    this.getStylesheets().add("fonts/fontstyle.css");
    this.minWidthProperty().bind(Util.currentWindowWidthProperty);
    this.maxWidthProperty().bind(Util.currentWindowWidthProperty);
    this.minHeightProperty().bind(Util.currentWindowHeightProperty);
    this.maxHeightProperty().bind(Util.currentWindowHeightProperty);

    this.setAlignment(Pos.CENTER);  //  align the content on the center of the VBox
    this.setSpacing(50.d);  // set a spacing between elements of the VBox

    //Text init
    this.text = new Label(""); // init the text to an empty text
    this.text.setTextAlignment(TextAlignment.CENTER); // align the text on center
    this.text.setWrapText(true);
    this.text.getStyleClass().add("text_");
    this.text.getStyleClass().add("big_text_");
  //  this.text.setWidthProperty().bind(this.minWidthProperty().subtract(20.d)); // wrap the text to almost the width of the window

    //Button init
    this.button = new Button(""); // init the button without text
    this.button.setOnMousePressed(event -> this.game.changeLevel() ); // on click, we change the name
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
