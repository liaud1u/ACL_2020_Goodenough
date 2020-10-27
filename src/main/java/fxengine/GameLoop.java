package fxengine;

import javafx.animation.AnimationTimer;

public class GameLoop extends AnimationTimer {
    private final GamePainter painter;
    private final GameController controller;
    private final Game game;

    public GameLoop(GamePainter painter, GameController controller, Game game) {
        this.painter = painter;
        this.controller = controller;
        this.game = game;
    }

    public void handle(long now) {
                painter.draw();
                game.evolve(controller.getCommand());
        }

}
