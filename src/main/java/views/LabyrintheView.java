package views;

import javafx.scene.Group;
import javafx.scene.image.Image;
import model.Labyrinthe;
import model.util.Util;
import views.caseview.CaseView;


/**
 * Vue du labyrinthe
 */
public class LabyrintheView extends Group {

    private final Labyrinthe labyrinthe;

    private final Image sprite;

    /**
     * Constructeur de la vue
     *
     * @param l Labyrinthe à dessiner
     */
    public LabyrintheView(Labyrinthe l) {
        this.labyrinthe = l;

        sprite = new Image("maze.png", Util.slotSizeProperty.get() * 10, Util.slotSizeProperty.get() * 3, false, true);
        for (int ligne = 0; ligne < Util.MAZE_SIZE; ligne++) {
            for (int colonne = 0; colonne < Util.MAZE_SIZE; colonne++)
                this.getChildren().add(new CaseView(sprite, l.getCaseLabyrinthe(ligne, colonne)));
        }
    }
    }





