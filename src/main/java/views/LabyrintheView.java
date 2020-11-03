package views;

import javafx.scene.Group;
import model.Case;
import model.Labyrinthe;


/**
 * @author adrien
 */
public class LabyrintheView extends Group {


    public LabyrintheView(Labyrinthe l) {
        Case[][] plateau = l.getPlateau();
        for (Case[] cc : plateau) {
            for (Case c : cc) {
                this.getChildren().add(new CaseView(c));
            }
        }
    }

}

