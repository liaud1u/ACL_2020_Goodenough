package model.labyrinthe.dijkstra;

import model.labyrinthe.Case;

import java.util.Comparator;

public class CompareDistance implements Comparator<Case> {
    @Override
    public int compare(Case c1, Case c2) {
        return c1.getWeight() - c2.getWeight();
    }
}
