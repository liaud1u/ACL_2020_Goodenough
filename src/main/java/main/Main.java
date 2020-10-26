package main;

import model.Labyrinthe;
import views.MazeView;

public class Main {

    public static void main(String[] args) {
        Labyrinthe l = new Labyrinthe(10, true);
        l.genererLabyrinthe();
        MazeView mv = new MazeView(l);
    }
}
