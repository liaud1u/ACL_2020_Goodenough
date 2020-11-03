package views;

import javafx.scene.Group;
import model.Case;
import model.Labyrinthe;


/**
 * @author adrien
 */
public class LabyrintheView extends Group {



    public LabyrintheView(Labyrinthe l) {
        char[][] plateau = l.getLabyrintheGUI();

        for (int i = 0; i < plateau.length; i++) {
            for (int j = 0; j < plateau.length; j++) {
                  System.out.println(plateau[i][j]);
            }
        }
        System.out.println(plateau);
        for (char[] cc : plateau) {
            for (char c : cc) {
                //this.getChildren().add(new CaseView(c));
            }
        }
    }

}

