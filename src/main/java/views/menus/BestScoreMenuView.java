package views.menus;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

/**
 * @author Ribeyrolles Matthieu
 * 01/12/2020, 12:15
 */
public class BestScoreMenuView extends BorderPane {
  /*------------------------------------------------------------------
                              Methods
   ------------------------------------------------------------------*/

  // getters
  // setters
  // private
  // public
   
   /*------------------------------------------------------------------
                            Constructors
   ------------------------------------------------------------------*/

  public BestScoreMenuView(BestScoresView bestScoresView) {
    this.getStylesheets().add("fonts/fontstyle.css");
    this.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
    this.setPadding(new Insets(20.));

    Button backButton = new Button("Menu");
    backButton.setOnAction(event -> {
      ((MainView) this.getScene()).initMenu();
    });

    HBox container = new HBox(backButton);
    container.setAlignment(Pos.CENTER);

    bestScoresView.addTitleStyleClass("big_text_");
    this.setCenter(bestScoresView);
    this.setBottom(container);
  }
}
