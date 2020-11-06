package views;

import javafx.scene.Group;
import model.Labyrinthe;
import model.util.Util;

import static model.util.Util.MAZE_SIZE;


/**
 * Vue du labyrinthe
 */
public class LabyrintheView extends Group {

    private Labyrinthe labyrinthe;

    /**
     * Constructeur de la vue
     *
     * @param l Labyrinthe à dessiner
     */
    public LabyrintheView(Labyrinthe l) {
        this.labyrinthe = l;
        int[][] tabLaby = labyrinthe.getLabyrintheVue();
        for (int ligne = 0; ligne < Util.MAZE_SIZE-1; ligne++)
        {
            for (int colonne = 0; colonne < Util.MAZE_SIZE-1; colonne++) {
                this.getChildren().add(new CaseView(ligne,colonne,tabLaby[ligne][colonne]));
            }
        }
    }




}

