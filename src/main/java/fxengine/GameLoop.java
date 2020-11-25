package fxengine;

import javafx.animation.AnimationTimer;
import model.PacmanGame;
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
    private final GameController controllerP2;

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

    private Cmd lastMove = Cmd.IDLE;
    private Cmd otherCommand = Cmd.IDLE;
    private Cmd lastMoveP2 = Cmd.IDLE;
    private Cmd otherCommandP2 = Cmd.IDLE;

    /**
     * Boucle principale du jeu
     *
     * @param painter    GamePainter pour dessiner le jeu
     * @param controller GameController pour controller le jeu
     * @param game       Game jeu
     */
    public GameLoop(GamePainter painter, GameController controller, GameController controllerP2 ,Game game) {
        this.painter = painter;
        this.controller = controller;
        this.controllerP2 = controllerP2;
        this.game = game;
    }

    /**
     * A chaque frame, mise à jour de la vue et évolution de l'état du jeu
     *
     * @param now
     */
    public void handle(long now) {
        //Calcul des FPS




        if (lastMove != controller.getCommand() && controller.getCommand() != Cmd.IDLE) {
            lastMove = controller.getCommand();
        }

        if (lastMoveP2 != controllerP2.getCommand() && controllerP2.getCommand() != Cmd.IDLE) {
            lastMoveP2 = controllerP2.getCommand();
        }

        otherCommand = controller.getCommandComplementaire();
        otherCommandP2 = controllerP2.getCommandComplementaire();

        if (lastSeconds != now / this.FRAMERATE) {
            //System.out.println(frame+ "fps");
            frame = 0;
            lastSeconds = now / this.FRAMERATE;
            ((PacmanGame)game).setPlayerTurn(1);
            game.evolve(lastMove);

            if(Util.player>1){
                ((PacmanGame)game).setPlayerTurn(2);
                game.evolve(lastMoveP2);
            }

            if (game instanceof PacmanGame)
                ((PacmanGame) game).evolveTheWorld();
        } else {
            frame++;
        }

        ((PacmanGame)game).setPlayerTurn(1);
        game.evolve(otherCommand);
        if(Util.player>1){
            ((PacmanGame)game).setPlayerTurn(2);
            game.evolve(otherCommandP2);
        }

        otherCommand = Cmd.IDLE;
        otherCommandP2 = Cmd.IDLE;

        final double ratio = ((now % this.FRAMERATE) * 1.) / this.FRAMERATE;

        painter.draw(ratio);
    }
}
