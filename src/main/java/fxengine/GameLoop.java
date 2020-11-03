package fxengine;

import javafx.animation.AnimationTimer;

/**
 * @author LIAUD Alexis
 */
public class GameLoop extends AnimationTimer {
    /**
     * Vue du jeu
     */
    private final GamePainter painter;

    /**
     * Controlleur du jeu
     */
    private final GameController controller;

    /**
     * Jeu
     */
    private final Game game;

    private int frame;

    private long lastSeconds = 0;

    public GameLoop(GamePainter painter, GameController controller, Game game) {
        this.painter = painter;
        this.controller = controller;
        this.game = game;
    }

    /**
     * A chaque frame, mise à jour de la vue et évolution de l'état du jeu
     *
     * @param now
     */
    public void handle(long now) {

        //Calcul des FPS
        if (lastSeconds != now / 1000000000) {
            //System.out.println(frame+ "fps");
            frame = 0;
            lastSeconds = now / 1000000000;
        } else {
            frame++;
        }


        painter.draw();

        game.evolve(controller.getCommand());
    }

}
