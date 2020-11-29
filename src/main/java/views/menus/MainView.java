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
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import model.PacmanGame;
import model.PacmanPainter;
import model.util.Util;

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

  /**
   *  create, initialize and launch the game
   * */
  private void startGame() {
    ( (Group) this.getRoot() ).getChildren().clear(); // clear the previous main menu
    this.initListeners(); // init game listeners

    PacmanGame game = new PacmanGame("helpFilePacman.txt"); // creates the game and bind it to the help file

    GamePainter painter = new PacmanPainter((Group) this.getRoot(), game);  // create the painter
    painter.draw(0);  // first draw

    GameLoop loop = new GameLoop(painter, this.controllerP1, this.controllerP2, game);  //creates the game loop
    loop.start();
  }

  /**
   *  Initialize all the components in the main menu
   *  At this point, the game is not created yet
   * */
  private void initMenu() {
    final Label title = new Label("P A C M A N");
    title.getStyleClass().add("title"); // add the class to refer to the stylesheet used below

    Button  onePlayerPlay = new Button("1 player"),
            twoPlayersPLay = new Button("2 players"),
            exit = new Button("Exit");

    // On action, we set the number of players to 1, and we start the game
    onePlayerPlay.setOnAction(event -> {
      Util.player = 1;
      this.startGame();
    });

    // add a tooltip to 1player button
    final Tooltip onePlayerTooltip = new Tooltip("Start a game for one player");
    onePlayerPlay.setTooltip(onePlayerTooltip);
//    onePlayerTooltip.getStyleClass().add("tooltip");

    // On action, we set the number of players to 2, and we start the game
    twoPlayersPLay.setOnAction(event -> {
      Util.player = 2;
      this.startGame();
    });

    // add a tooltip to 2players button
    final Tooltip twoPlayersTooltip = new Tooltip("Start a game for two players");
    twoPlayersPLay.setTooltip(twoPlayersTooltip);
    twoPlayersTooltip.getStyleClass().add("tooltip");

    // add tooltip to exit button
    final Tooltip exitTooltip = new Tooltip("Don't do it");
    exit.setTooltip(exitTooltip);
    exitTooltip.getStyleClass().add("tooltip");

    exit.setOnAction(event -> System.exit(0));  // exit button closes the app

    // all the containers used in the main menu
    final BorderPane container = new BorderPane();
    final VBox playButtonsContainer;
    final HBox exitButtonContainer; //FIXME: try to align its children without having to create this. Same for below
    final HBox titleContainer;

    /**
     *  Here, we are initializing all the containers, setting their alignement to center, adding spacing etc
     *  we also instantiate them and add their children at the same time
     *  We bind the stylesheet to the main container, and add extra css where needed
     * */
    playButtonsContainer = new VBox(onePlayerPlay, twoPlayersPLay);
    playButtonsContainer.setAlignment(Pos.CENTER);
    playButtonsContainer.setSpacing(50.d);

    exitButtonContainer = new HBox(exit);
    exitButtonContainer.setAlignment(Pos.CENTER);

    titleContainer = new HBox(title);
    titleContainer.setAlignment(Pos.CENTER);

    container.getStylesheets().add("fonts/fontstyle.css");
    container.setStyle("-fx-padding: 20px");

    // Set the different parts of the main container
    container.setTop(titleContainer);
    container.setCenter(playButtonsContainer);
    container.setBottom(exitButtonContainer);

    // bind the width and height to current scene width and height
    container.minHeightProperty().bind( this.heightProperty() );
    container.maxHeightProperty().bind( this.widthProperty() );
    container.minWidthProperty().bind( this.widthProperty() );
    container.maxWidthProperty().bind( this.widthProperty() );

    // add the main container to the root
    ( (Group) this.getRoot() ).getChildren().add(container);
  }

  /**
   * initialize all the listeners
   * */
  private void initListeners() {
    // actions when key is pressed
    this.setOnKeyPressed(event -> {
      //Checking if the KeyEvent is for the 1st player or the 2nd
      if(this.player1_codes.contains(event.getCode()))
        this.controllerP1.keyPressed(event);
      else
        this.controllerP2.keyPressed(event);
    });

    // actions when jey is released
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

  /**
   * @param root (: {@link Parent})                               : the current root for the scene
   * @param width (: double)                                      : the width for the scene
   * @param height (: double)                                     : the height for the scene
   * @param player1_codes (: {@link Collection}<{@link KeyCode}>) : the collection of bindings for player 1 actions
   * @param controllerP1 (: {@link GameController})               : the player 1 controller
   * @param controllerP2 (: {@link GameController})               : the player 2 controller
   * */
  public MainView(Parent root, double width, double height, Collection<KeyCode> player1_codes, GameController controllerP1, GameController controllerP2) {
    super(root, width, height, Color.BLACK);
    this.player1_codes = player1_codes;
    this.controllerP1 = controllerP1;
    this.controllerP2 = controllerP2;

    this.initMenu();
  }
}
