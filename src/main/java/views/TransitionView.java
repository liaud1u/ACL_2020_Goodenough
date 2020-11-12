package views;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import model.util.*;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

import java.util.Timer;
import java.util.TimerTask;

public class TransitionView extends Group {

    private int timeLeft;

    /**
     * Vue affichant le message de transition entre 2 labyrinthes
     * @param transitionText : texte à afficher (Victoire, défaite, ...)
     */
    public TransitionView(String transitionText) {
        Label label = new Label(transitionText);
        label.setTextFill(Color.rgb(246,246,82)); // Couleur jaune pacman
        this.getStylesheets().add("fonts/fontstyle.css"); // Importation font pacman
        HBox box = new HBox(10, label);
        box.setPrefSize(Util.windowSizeProperty.get(), Util.windowSizeProperty.get()); // s'adapter a la fenetre
        box.setAlignment(Pos.CENTER); // Centrer verticalement le texte
        this.getChildren().add(box);

        this.timeLeft = Util.DISPLAY_MSG_DURATION;
        TimerTask decrement = new TimerTask() {
            public void run() {
                timeLeft--;
            }
        };
        Timer timer = new Timer();
        timer.schedule(decrement, 0, 1000L);
    }


    public boolean timerOver() {
        return timeLeft <= 0;
    }
}

