package views;

import javafx.scene.Group;
import model.Case;
import model.Labyrinthe;


/**
 * Vue du labyrinthe
 */
public class LabyrintheView extends Group {

    /**
     * Constructeur de la vue
     *
     * @param l Labyrinthe à dessiner
     */
    public LabyrintheView(Labyrinthe l) {
        Case[][] plateau = l.getLabyrinthe();

        for (Case[] li : plateau) {
            for (Case c : li) {
                if (c != null) {
                    this.getChildren().add(new CaseView(c));
                }
            }
        }
    }

}

