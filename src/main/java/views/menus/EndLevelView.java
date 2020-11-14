package views.menus;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
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
  /*------------------------------------------------------------------
                              Methods
   ------------------------------------------------------------------*/

  // getters
  // setters
  // private

  protected void init() {
    this.isReadyToStart = false;
//    this.getStylesheets().add("fonts/fontstyle.css");

    //Text init
    this.setAlignment(Pos.CENTER);
    this.text = new Text("");
    this.text.setTextAlignment(TextAlignment.CENTER);
    this.text.setFill(Color.WHITE);
    this.text.setWrappingWidth(200.);
    this.text.setFont(Font.font(50));

    //Button init
    this.button = new Button("");
    this.button.setOnAction(event -> this.isReadyToStart = true);
  }
  // public

  public boolean isReadyToStart() {
    return this.isReadyToStart;
  }
   
   /*------------------------------------------------------------------
                            Constructors
   ------------------------------------------------------------------*/

  public EndLevelView(int level, int score) {
    this.level = level;
    this.score = score;

    this.init();

    this.getChildren().addAll(this.text, this.button);
  }
}
