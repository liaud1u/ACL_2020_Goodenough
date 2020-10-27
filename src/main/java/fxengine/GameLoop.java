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
        painter.draw();
        game.evolve(controller.getCommand());
    }

}
