package fxengine;

import javafx.animation.AnimationTimer;
import model.util.Util;

/**
 * Boucle principale du jeu
 */
public class GameLoop extends AnimationTimer {
  private final long FRAMERATE = 1_000_000_000 / Util.speedDifficulty;

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

    /**
     * Nombre de frame affichée durant cette seconde
     */
    private int frame;

    /**
     * Long correspondant à la dernière seconde pour calculer les FPS
     */
    private long lastSeconds = 0;

    private Cmd lastCommand = Cmd.IDLE;

    /**
     * Boucle principale du jeu
     *
     * @param painter    GamePainter pour dessiner le jeu
     * @param controller GameController pour controller le jeu
     * @param game       Game jeu
     */
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

        if (lastCommand != controller.getCommand() && controller.getCommand() != Cmd.IDLE) {
            lastCommand = controller.getCommand();
        }

        if (lastSeconds != now / this.FRAMERATE) {
            //System.out.println(frame+ "fps");
            frame = 0;
            lastSeconds = now / this.FRAMERATE;
            game.evolve(lastCommand);
        } else {
            frame++;
        }

      final double ratio =  ((now % this.FRAMERATE) * 1.) / this.FRAMERATE;

      painter.draw(ratio);
    }
}
