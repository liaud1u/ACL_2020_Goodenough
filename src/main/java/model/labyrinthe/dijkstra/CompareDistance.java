package model.labyrinthe.dijkstra;

import model.labyrinthe.Case;

import java.util.Comparator;

/**
 * Comparator to compare distance
 */
public class CompareDistance implements Comparator<Case> {
    /**
     * Comparator
     * @param c1 Case 1
     * @param c2 Case 2
     * @return comparation between C1 and C2 weight
     */
    @Override
    public int compare(Case c1, Case c2) {
        return c1.getWeight() - c2.getWeight();
    }
}
