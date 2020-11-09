package views;

import javafx.scene.Group;
import model.Case;
import model.Labyrinthe;
import model.util.Util;


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
        for (int ligne = 0; ligne < Util.MAZE_SIZE-1; ligne++)
        {
            for (int colonne = 0; colonne < Util.MAZE_SIZE-1; colonne++) {
                Case caseCourante = labyrinthe.getCaseLabyrinthe(ligne,colonne);
                System.out.println("La case est :" + caseCourante.toString());
                System.out.println("Ses voisins sont :" + caseCourante.getVoisins().toString());
                this.getChildren().add(new CaseView(l.labyrinthe[ligne][colonne]));
            }
        }
    }




}

