package fxengine;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.PacmanController;
import model.PacmanGame;
import model.player.PlayerType;
import model.util.Util;
import views.menus.MainView;

import java.util.Arrays;
import java.util.List;

/**
 * Application principale
 */
public class GameApplication extends Application {
  /**
   * Hauteur de la fenêtre
   */
  private static final int HEIGHT = 600;

  /**
   * Largeur de la fenêtre
   */
  private static final int WIDTH = 800;

  /**
   * Stage principal du jeu
   */
  private Stage primaryStage;

  /**
   * Painter du jeu permettant le rafraichissement
   */
  private GamePainter painter;

  /**
   * Game controller to get differents action for the player 1
   */
  private GameController controller;

  /**
   * Game controller to get differents action for the player 2
   */
  private GameController controllerP2;

  /**
   * Image principale du jeu
   */
  private Group root;

  /**
   * Modele
   */
  private PacmanGame game;

  /**
   * Lance l'execution de l'application
   */
  public void run() {
    launch();
  }

  /**
   * Fonction permettant de créer les scenes, de lier les event handler & contenant la boucle prinicpale.
   *
   * @param primaryStage Stage principal du jeu, créée lors du launch().
   * @throws Exception
   */
  public void start(Stage primaryStage) throws Exception {
    this.primaryStage = primaryStage;

    //Setting the size of the window
    Util.currentWindowWidthProperty.bind(this.primaryStage.widthProperty());
    Util.currentWindowHeightProperty.bind(this.primaryStage.heightProperty());

    // Création d'un groupe pour les canvas et ajout des canvas au groupe
    root = new Group();

    //Create the two controllers
    controller = new PacmanController(PlayerType.PLAYER1);
    controllerP2 = new PacmanController(PlayerType.PLAYER2);


    //Liste of the keycode for the two player (to detect for wich player the input is)
    List<KeyCode> p1Code = Arrays.asList(KeyCode.LEFT, KeyCode.RIGHT,KeyCode.UP,KeyCode.SPACE,KeyCode.DOWN);
    List<KeyCode> p2Code = Arrays.asList(KeyCode.Z, KeyCode.Q,KeyCode.S,KeyCode.D,KeyCode.A);

    // Création de la scene principale contenant le groupe de canvas
    Scene scene = new MainView(root, WIDTH, HEIGHT, p1Code, controller, controllerP2);

    Util.windowSizeProperty.bind(
            Bindings
                    .when(scene.widthProperty().lessThan(scene.heightProperty()))
                    .then(scene.widthProperty())
                    .otherwise(scene.heightProperty())
    );



    // Actions à effectuer lors de la fermeture de l'application
    primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
      public void handle(WindowEvent event) {
        System.out.println("Fermeture");
        Platform.exit();
        System.exit(0);
      }
    });


    primaryStage.minWidthProperty().bind(Util.minWindowSizeProperty.add(Util.rightWidthProperty));
    primaryStage.minHeightProperty().bind(Util.minWindowSizeProperty);

    // Paramètrage et affichage de la fenêtre principale
    primaryStage.setScene(scene);
    primaryStage.setTitle("Pacman");
    primaryStage.sizeToScene();
    primaryStage.getIcons().add(new Image("pacman/logo.png"));

    //primaryStage.setResizable(false); // FIXME for responsive

    primaryStage.show();
  }
}
