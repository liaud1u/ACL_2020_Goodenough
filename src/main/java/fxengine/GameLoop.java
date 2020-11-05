package fxengine;

import javafx.animation.AnimationTimer;

/**
 * Boucle principale du jeu
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

    /**
     * Nombre de frame affichée durant cette seconde
     */
    private int frame;

    /**
     * Long correspondant à la dernière seconde pour calculer les FPS
     */
    private long lastSeconds = 0;

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
        if (lastSeconds != now / 1_000_000_000) {
            //System.out.println(frame+ "fps");
            frame = 0;
            lastSeconds = now / 1_000_000_000;
        } else {
            frame++;
        }


        painter.draw();

        // On fait évoluer le jeu
        game.evolve(controller.getCommand());
    }

}
