package views;

import javafx.scene.Group;
import model.Case;
import model.Labyrinthe;

import java.util.ArrayList;
import java.util.List;

public class LabyrintheView extends Group {




    public LabyrintheView(Labyrinthe l) {
            Case[][] plateau = l.getPlateau();
            for(Case[] cc : plateau){
                for(Case c : cc) {
                    this.getChildren().add(new CaseView(c));
                }
            }
    }



}
