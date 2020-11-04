package views;

import javafx.scene.Group;
import model.Case;
import model.Labyrinthe;

import static model.Labyrinthe.labyrinthe;
import static model.util.Util.MAZE_SIZE;


/**
 * Vue du labyrinthe
 */
public class LabyrintheView extends Group {

    /**
     * Constructeur de la vue
     *
     * @param l Labyrinthe à dessiner
     */
    public LabyrintheView() {

        for (int ligne = 0; ligne < 19; ligne++)
        {
            for (int colonne = 0; colonne < 19; colonne++) {
                if(labyrinthe[ligne][colonne] != null) {
                    this.getChildren().add(new CaseView(labyrinthe[ligne][colonne]));
                }
            }
        }
    }

}

