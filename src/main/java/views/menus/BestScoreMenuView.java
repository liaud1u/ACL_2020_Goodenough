package views.menus;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

/**
 * Best score menu view
 */
public class BestScoreMenuView extends BorderPane {

  /**
   * @param bestScoresView (:{@link BestScoresView}): the current bestScoreView to display
   * */
  public BestScoreMenuView(BestScoresView bestScoresView) {
    this.getStylesheets().add("fonts/fontstyle.css"); // set the style to this pane
    this.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY))); // a black background
    this.setPadding(new Insets(20.)); // add padding to prevent elements to be too close to the borders

    // the button to come back to the menu, with a tooltip message
    MainMenuButton backButton = new MainMenuButton("Menu", "Back to the menu", event -> {
      ((MainView) this.getScene()).initMenu();
    });

    HBox container = new HBox(backButton);  // container to easier alignement
    container.setAlignment(Pos.CENTER);

    bestScoresView.addTitleStyleClass("big_text_"); // add style to make the title bigger
    this.setCenter(bestScoresView); // add bestScoresView to the center of the screen
    this.setBottom(container);  // the button container to the bottom of th screen
  }
}
