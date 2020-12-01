package views.menus;

import fxengine.GameController;
import fxengine.GameLoop;
import fxengine.GamePainter;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import model.PacmanGame;
import model.PacmanPainter;
import model.util.DAO.ConcreteFileFactory;
import model.util.DAO.files.FileType;
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
  private GameLoop loop;
  private GamePainter painter;
  private PacmanGame game;
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

    game = new PacmanGame("helpFilePacman.txt"); // creates the game and bind it to the help file

    painter = new PacmanPainter((Group) this.getRoot(), game);  // create the painter
    painter.draw(0);  // first draw

    loop = new GameLoop(painter, this.controllerP1, this.controllerP2, game);  //creates the game loop
    loop.start();
  }

  private VBox pauseContainer;
  private Label pauseLabel;

  private void pause() {
    for (Node n : this.getRoot().getChildrenUnmodifiable()) {
      if (n instanceof RightSideView) {
        for (Node nn : ((RightSideView) n).getChildren()) {
          if (nn.getStyleClass().contains("button")) n.setOpacity(1.);
          else nn.setOpacity(.3);
        }
      }
      else n.setOpacity(.3);
    }

    ((Group) this.getRoot()).getChildren().add(this.pauseContainer);
  }

  private void play() {
    for (Node n : this.getRoot().getChildrenUnmodifiable())
      n.setOpacity(1.);

    ((Group) this.getRoot()).getChildren().remove(this.pauseContainer);
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

      if (event.getCode().equals(KeyCode.ESCAPE)) {
        if (this.loop.isPaused()) {
          this.play();
          this.loop.play();
        }
        else {
          this.pause();
          this.loop.pause();
        }
      }
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

  public void killInstances() {
    if (this.loop != null) this.loop.stop();
  }

  // public
  /**
   *  Initialize all the components in the main menu
   *  At this point, the game is not created yet
   * */
  public void initMenu() {
    this.killInstances();
    this.setRoot(new Group());
    final Label title = new Label("P A C M A N");
    title.getStyleClass().add("title"); // add the class to refer to the stylesheet used below

    MainMenuButton
      onePlayerPlay = new MainMenuButton("1 player", "Start a game for one player", event -> {
      Util.player = 1;  // On action, we set the number of players to 1, and we start the game
      this.startGame();
    }),
      twoPlayersPLay = new MainMenuButton("2 players", "Start a game for two players", event -> {
        Util.player = 2; // On action, we set the number of players to 2, and we start the game
        this.startGame();
      }),
      bestScoresButton = new MainMenuButton("Best scores", "The current best scores", event -> {
        this.setRoot(new BestScoreMenuView(new BestScoresView(new ConcreteFileFactory().getLeaderboardDAO(FileType.XML).load())));
      }),
      exit = new MainMenuButton("Exit", "Don't do it", event -> System.exit(0));


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
    playButtonsContainer = new VBox(onePlayerPlay, twoPlayersPLay, bestScoresButton);
    playButtonsContainer.setAlignment(Pos.CENTER);
    playButtonsContainer.setSpacing(50.d);

    exitButtonContainer = new HBox(exit);
    exitButtonContainer.setAlignment(Pos.CENTER_RIGHT);

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
    this.getStylesheets().add("fonts/fontstyle.css");
    this.pauseLabel = new Label("PAUSED");
    this.pauseLabel.getStyleClass().add("text_");
    this.pauseLabel.getStyleClass().add("big_text_");
    this.pauseLabel.setText("PAUSED");

    this.pauseContainer = new VBox(this.pauseLabel);
    this.pauseContainer.setAlignment(Pos.CENTER);
    this.pauseContainer.prefHeightProperty().bind(Util.currentWindowHeightProperty);
    this.pauseContainer.prefWidthProperty().bind(Util.currentWindowWidthProperty);
    this.pauseContainer.setMouseTransparent(true);
  }
}
