package views;

import javafx.scene.Group;
import model.Labyrinthe;

import static model.util.Util.MAZE_SIZE;


/**
 * Vue du labyrinthe
 */
public class LabyrintheView extends Group {

    public static int[][] labyrintheVue = new int[20][20];
    /**
     * Constructeur de la vue
     *
     * @param l Labyrinthe à dessiner
     */
    public LabyrintheView() {


        for (int ligne = 0; ligne < 20; ligne++)
        {
            for (int colonne = 0; colonne < 20; colonne++) {
                //System.out.println(labyrintheVue[ligne][colonne]);
                    this.getChildren().add(new CaseView(ligne,colonne,labyrintheVue[ligne][colonne]));
            }
        }
    }



}

