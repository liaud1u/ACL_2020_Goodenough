package views.menus;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.HBox;
import model.BestScore;
import model.PacmanGame;
import model.util.DAO.ConcreteFileFactory;
import model.util.Util;
import model.util.DAO.BestScoreFileXMLDAO;
import model.util.DAO.files.FileType;


/** class used to display the lost level menu
 * */
public class LostLevelView extends EndLevelView{
  private boolean hasAScoreBeenAdded;
  private BestScoresView bestScoresView;

  /**
   * Initialize the view
   */
  @Override
  protected void init() {
    super.init();

    this.text.setText(String.format(  // set the text for a lost, with the level and the score reached
      "Level failed!\n%s\nYou reached the level %d, with a score of %d!\n\nReady to start again?",
      (this.game.getGameTimer().isOver()) ? "You ran out of time :(" : "",  // if timer is over, print a message
      this.game.getLevel() + 1,
      this.game.getScore()
    ));

    this.button.setText("Try again"); // set text for the button
    this.addBestScoreView();
  }

  /**
   * Add new score view
   */
  private void addBestScoreView() {
    try {
      BestScoreFileXMLDAO bestScoresDAO = (BestScoreFileXMLDAO) new ConcreteFileFactory().getLeaderboardDAO(FileType.XML);
      FileType fileType = FileType.XML;

      this.bestScoresView = new BestScoresView(bestScoresDAO.load());

      this.getChildren().add(bestScoresView);

      if (bestScoresDAO.isNewBestScore(this.game.getScore())) {
        TextField nameField = new TextField();
        nameField.setPrefColumnCount(10);
        nameField.setPromptText("NAME");
        nameField.setEditable(true);

        // prevent more than (max size) characters for the name
        nameField.textProperty().addListener((observableValue, oldValue, newValue) -> {
          if (nameField.getText().length() > Util.maxPlayerNameSize) {
            nameField.setText( nameField.getText().substring(0, Util.maxPlayerNameSize));
          }
        });

        // to display name uppercased
        nameField.setTextFormatter(new TextFormatter<>((change) -> {
          change.setText(change.getText().toUpperCase());
          return change;
        }));


        Button addButton = new Button("Add score");
        addButton.setOnAction(event -> {
          if (nameField.getText().trim().length() == 0) return; // prevent null or full spaced text

          if (! this.hasAScoreBeenAdded) {  // prevent multiple adding of score
            try {
              bestScoresDAO.save(new BestScore(nameField.getText(), this.game.getScore())); //save the new score
              this.getChildren().set(this.getChildren().indexOf(this.bestScoresView), new BestScoresView(bestScoresDAO.load()));
            } catch (Exception e) {
              e.printStackTrace();
            }
            this.hasAScoreBeenAdded = true;
          }
        });

        HBox container = new HBox(nameField, addButton);
        container.setAlignment(Pos.CENTER);
        container.setSpacing(50.d);

        this.getChildren().add(container);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /** @param game (:{@link PacmanGame}) the current game
   * */
  public LostLevelView(PacmanGame game) {
    super(game);

    this.hasAScoreBeenAdded = false;
  }
}
