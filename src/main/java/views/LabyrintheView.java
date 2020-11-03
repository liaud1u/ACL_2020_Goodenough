package views;

import javafx.scene.Group;
import model.Case;
import model.Labyrinthe;
import model.util.Util;


/**
 * @author adrien
 */
public class LabyrintheView extends Group {



    public LabyrintheView(Labyrinthe l) {
        Case[][] plateau = l.getLabyrinthe();

        for (Case[] li : plateau) {
            for (Case c : li) {
                if(c != null) {
                    this.getChildren().add(new CaseView(c));
                }
            }
        }
    }

}

