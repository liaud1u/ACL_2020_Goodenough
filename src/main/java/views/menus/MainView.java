package views.menus;

import fxengine.GameController;
import fxengine.GameLoop;
import fxengine.GamePainter;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import model.PacmanGame;
import model.PacmanPainter;

import java.util.Collection;

/**
 * @author Ribeyrolles Matthieu
 * 29/11/2020, 11:38
 */
public class MainView extends Scene {
  private final Collection<KeyCode> player1_codes;
  private final GameController controllerP1;
  private final GameController controllerP2;

  /*------------------------------------------------------------------
                              Methods
   ------------------------------------------------------------------*/

  // getters
  // setters
  // private
  private void startGame() {
    ( (Group) this.getRoot() ).getChildren().clear();
    this.initListeners();

    // Création du jeu
    PacmanGame game = new PacmanGame("helpFilePacman.txt");

    // Création de la vue et du controlleur
    GamePainter painter = new PacmanPainter((Group) this.getRoot(), game);

    // Premier affichage
    painter.draw(0);


    // Création et lancement de la boucle principale de gameplay
    GameLoop loop = new GameLoop(painter, this.controllerP1, this.controllerP2, game);
    loop.start();
  }

  private void initMenu() {
    final Label title = new Label("P A C M A N");
    title.getStyleClass().add("title");

    Button  onePlayerPlay = new Button("1 player"),
            twoPlayersPLay = new Button("2 players"),
            exit = new Button("Exit");

    onePlayerPlay.setOnAction(event -> this.startGame());
    exit.setOnAction(event -> System.exit(0));

    final BorderPane container = new BorderPane();
    final VBox playButtonsContainer;
    final HBox exitButtonContainer;
    final HBox titleContainer;

    playButtonsContainer = new VBox(onePlayerPlay, twoPlayersPLay);
    playButtonsContainer.setAlignment(Pos.CENTER);
    playButtonsContainer.setSpacing(50.d);

    exitButtonContainer = new HBox(exit);
    exitButtonContainer.setAlignment(Pos.CENTER);

    titleContainer = new HBox(title);
    titleContainer.setAlignment(Pos.CENTER);

    container.getStylesheets().add("fonts/fontstyle.css");
    container.setStyle("-fx-padding: 20px");

    container.setTop(titleContainer);
    container.setCenter(playButtonsContainer);
    container.setBottom(exitButtonContainer);

    container.minHeightProperty().bind( this.heightProperty() );
    container.maxHeightProperty().bind( this.widthProperty() );
    container.minWidthProperty().bind( this.widthProperty() );
    container.maxWidthProperty().bind( this.widthProperty() );

    ( (Group) this.getRoot() ).getChildren().add(container);
  }

  private void initListeners() {
    // Actions à effectuer lors d'un click sur une touche
    this.setOnKeyPressed(event -> {
      //Checking if the KeyEvent is for the 1st player or the 2nd
      if(this.player1_codes.contains(event.getCode()))
        this.controllerP1.keyPressed(event);
      else
        this.controllerP2.keyPressed(event);
    });

    // Actions à effectuer lors du relâchement d'une touche
    this.setOnKeyReleased(event -> {
      //Checking if the KeyEvent is for the 1st player or the 2nd
      if(this.player1_codes.contains(event.getCode()))
        this.controllerP1.keyReleased(event);
      else
        this.controllerP2.keyReleased(event);
    });
  }

  // public
   
   /*------------------------------------------------------------------
                            Constructors
   ------------------------------------------------------------------*/

  public MainView(Parent root, double width, double height, Collection<KeyCode> player1_codes, GameController controllerP1, GameController controllerP2) {
    super(root, width, height, Color.BLACK);
    this.player1_codes = player1_codes;
    this.controllerP1 = controllerP1;
    this.controllerP2 = controllerP2;

    this.initMenu();
  }
}
