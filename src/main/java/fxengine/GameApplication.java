package fxengine;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.PacmanController;
import model.PacmanGame;
import model.PacmanPainter;
import model.util.Util;
import views.TimerView;

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
   * Controlleur du jeu permettant de récupérer les différentes actions
   */
  private GameController controller;

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

    Util.currentWindowWidthProperty.bind(primaryStage.widthProperty());
    Util.currentWindowHeightProperty.bind(primaryStage.heightProperty());

    this.primaryStage = primaryStage;


    // Création d'un groupe pour les canvas et ajout des canvas au groupe
    root = new Group();

    // Création de la scene principale contenant le groupe de canvas
    Scene scene = new Scene(root, WIDTH, HEIGHT, Color.BLACK);

    Util.windowSizeProperty.bind(
            Bindings
                    .when(scene.widthProperty().lessThan(scene.heightProperty()))
                    .then(scene.widthProperty())
                    .otherwise(scene.heightProperty())
    );


    // Création du jeu
    game = new PacmanGame("helpFilePacman.txt");


    // Création de la vue et du controlleur
    painter = new PacmanPainter(root, game);
    controller = new PacmanController();

    // Premier affichage
    painter.draw(0);

    // Actions à effectuer lors de la fermeture de l'application
    primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
      public void handle(WindowEvent event) {
        System.out.println("Fermeture");
        Platform.exit();
        System.exit(0);
      }
    });


    // Actions à effectuer lors d'un click sur une touche
    scene.setOnKeyPressed(
      new EventHandler<KeyEvent>() {
        public void handle(KeyEvent event) {
          controller.keyPressed(event);
        }
      }
    );

    // Actions à effectuer lors du relâchement d'une touche
    scene.setOnKeyReleased(
      new EventHandler<KeyEvent>() {
        public void handle(KeyEvent event) {
          controller.keyReleased(event);
        }
      }
    );

    // Création et lancement de la boucle principale de gameplay
    GameLoop loop = new GameLoop(painter,controller,game);
    loop.start();

    primaryStage.minWidthProperty().bind(Util.minWindowSizeProperty.add(Util.rightWidthProperty));
    primaryStage.minHeightProperty().bind(Util.minWindowSizeProperty);

    // Paramètrage et affichage de la fenêtre principale
    primaryStage.setScene(scene);
    primaryStage.setTitle("Pacman");
    primaryStage.sizeToScene();
    //primaryStage.setResizable(false); // FIXME for responsive
    primaryStage.show();
  }
}
