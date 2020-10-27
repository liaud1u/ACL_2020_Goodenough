package fxengine;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.PacmanController;
import model.PacmanPainter;

public class GameApplication extends Application {
    private Stage primaryStage;
    private GamePainter painter;
    private GameController controller;
    private Canvas mainCanva;

    public void run(){
        launch();
    }

    public void start(Stage primaryStage) throws Exception {
        this.primaryStage=primaryStage;

        Group group = new Group();

        mainCanva = new Canvas(512,512);

        group.getChildren().add(mainCanva);

        painter = new PacmanPainter(mainCanva);
        controller=new PacmanController();

        painter.draw();

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent event) {
                System.out.println("CLOSING");
                Platform.exit();
                System.exit(0);
            }
        });

        Scene scene = new Scene(group,primaryStage.getWidth(),primaryStage.getHeight(), Color.WHITESMOKE);

        scene.setOnKeyPressed(
                new EventHandler<KeyEvent>() {
                    public void handle(KeyEvent event) {
                        controller.keyPressed(event);
                    }
                }
        );

        scene.setOnKeyReleased(
                new EventHandler<KeyEvent>() {
                    public void handle(KeyEvent event) {
                        controller.keyReleased(event);
                    }
                }
        );

        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.setTitle("Pacman");
        primaryStage.show();
    }
}
