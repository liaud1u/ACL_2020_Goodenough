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

    private boolean isPaused;
    /**
     * Game controller for 1st player
     */
    private final GameController controller;

    /**
     * Game controller for 2nd player
     */
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

    /**
     * Last move of the first player
     */
    private Cmd lastMove = Cmd.IDLE;

    /**
     * Last otherCommand (shoot) of the first player
     */
    private Cmd otherCommand = Cmd.IDLE;

    /**
     * Last move of the second player
     */
    private Cmd lastMoveP2 = Cmd.IDLE;

    /**
     * Last otherCommand (shoot) of the second player
     */
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

  public boolean isPaused() {
    return isPaused;
  }

  public void pause() {
    isPaused = true;
    this.stop();
    ((PacmanGame) this.game).pauseAllInstances();
  }

  public void play() {
    isPaused = false;
    this.start();
    ((PacmanGame) this.game).replayAllInstances();
  }

  /**
     * A chaque frame, mise à jour de la vue et évolution de l'état du jeu
     *
     * @param now
     */
    public void handle(long now) {
        //Checking if the new movement of the first player is different than the past
        if (lastMove != controller.getCommand() && controller.getCommand() != Cmd.IDLE) {
            lastMove = controller.getCommand();
        }

        //Checking if the new movement of the second player is different than the past
        if (lastMoveP2 != controllerP2.getCommand() && controllerP2.getCommand() != Cmd.IDLE) {
            lastMoveP2 = controllerP2.getCommand();
        }

        //Getting the complementary command (shoot) for both player
        otherCommand = controller.getCommandComplementaire();
        otherCommandP2 = controllerP2.getCommandComplementaire();

        //Calcul des FPS

        if (lastSeconds != now / this.FRAMERATE) {
            //System.out.println(frame+ "fps");
            frame = 0;
            lastSeconds = now / this.FRAMERATE;
            ((PacmanGame)game).setPlayerTurn(1);
            game.evolve(lastMove);

            //If there's more than one player, than we evolve the second player
            if(Util.player>1){
                ((PacmanGame)game).setPlayerTurn(2);
                game.evolve(lastMoveP2);
            }

            //Update the world
            if (game instanceof PacmanGame)
                ((PacmanGame) game).evolveTheWorld();

        } else {
            frame++;
        }

        //If needed, we shoot
        ((PacmanGame)game).setPlayerTurn(1);
        game.evolve(otherCommand);


        //If needed and more than one player, we shoot
        if(Util.player>1){
            ((PacmanGame)game).setPlayerTurn(2);
            game.evolve(otherCommandP2);
        }

        //Reseting the shooting command
        otherCommand = Cmd.IDLE;
        otherCommandP2 = Cmd.IDLE;

        final double ratio = ((now % this.FRAMERATE) * 1.) / this.FRAMERATE;

        //Print all the scene
        painter.draw(ratio);
    }
}
