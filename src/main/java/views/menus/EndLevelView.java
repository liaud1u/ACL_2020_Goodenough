package views.menus;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import model.PacmanGame;
import model.util.Util;


/**
 * @author Ribeyrolles Matthieu
 * 14/11/2020, 15:28
 */
public abstract class EndLevelView extends VBox {
  protected Button button;
  protected Text text;
  protected int level, score;
  private boolean isReadyToStart;
  private PacmanGame game;
  /*------------------------------------------------------------------
                              Methods
   ------------------------------------------------------------------*/

  // getters
  // setters
  // private

  protected void init() {
    this.isReadyToStart = false;

    this.minWidthProperty().bind(Util.currentWindowWidthProperty);
    this.maxWidthProperty().bind(Util.currentWindowWidthProperty);
    this.minHeightProperty().bind(Util.currentWindowHeightProperty);
    this.maxHeightProperty().bind(Util.currentWindowHeightProperty);
    this.setAlignment(Pos.CENTER);
    this.setSpacing(50.d);

    //Text init
    this.text = new Text("");
    this.text.setTextAlignment(TextAlignment.CENTER);
    this.text.setFill(Color.WHITE);
    this.text.setFont(Font.font(40));
    this.text.wrappingWidthProperty().bind(this.minWidthProperty().subtract(20.d));

    //Button init
    this.button = new Button("");
    this.button.setOnMousePressed(event -> {
      this.isReadyToStart = true;
      this.game.changeLevel();
    });
    this.button.setStyle(   //TODO: set this on an external stylesheet file
      "-fx-cursor: hand;" +
      "-fx-background-color: yellow;" +
      "-fx-font-size: 20px"
    );
  }
  // public

  public boolean isReadyToStart() {
    return this.isReadyToStart;
  }

  public void setReadyToStart(boolean readyToStart) {
    isReadyToStart = readyToStart;
  }
   
   /*------------------------------------------------------------------
                            Constructors
   ------------------------------------------------------------------*/

  public EndLevelView(int level, PacmanGame game) {
    this.level = level;
    this.score = game.getScore();
    this.game = game;
    this.init();

    this.getChildren().addAll(this.text, this.button);
  }
}
