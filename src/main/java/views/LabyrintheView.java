package views;

import javafx.scene.Group;
import model.Case;
import model.Labyrinthe;


/**
 * @author adrien
 */
public class LabyrintheView extends Group {



    public LabyrintheView(Labyrinthe l) {
        Case[][] plateau = l.getLabyrinthe();

        for (int i = 0; i < plateau.length; i++) {
            for (int j = 0; j < plateau.length; j++) {
                  System.out.println(plateau[i][j]);
            }

        }
        System.out.println(plateau);
        for (Case[] cc : plateau) {
            for (Case c : cc) {
                this.getChildren().add(new CaseView(c));
            }
        }
    }

}

