package model.labyrinthe.dijkstra;

import model.labyrinthe.Case;
import model.labyrinthe.Labyrinthe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

import static model.util.Util.MAZE_SIZE;

/**
 * Disjktra class for determining best way throught two cases
 */
public class Dijkstra {
    /**
     * Graph of the maze
     */
    private ArrayList<Case> graphe = new ArrayList();

    /**
     * List of open edge of the graph
     */
    private ArrayList<Case> sommetOuvert = new ArrayList();

    /**
     * List of all closed edge of the graph
     */
    private ArrayList<Case> sommetFerme = new ArrayList();

    /**
     * if have found a way through the two cases
     */
    private boolean trouve = false;

    /**
     * Maze to apply dijsktra
     */
    private Labyrinthe maze;

    /**
     * Constructor
     * @param labyrinthe Maze to apply algorithm
     */
    public Dijkstra(Labyrinthe labyrinthe, Case caseDepart){
        this.maze = labyrinthe;
        initDijkstra(caseDepart);
        System.out.println("JE FAIS UN INIT");
    }

    /**
     * Get distance between two cases
     * @param from Case from
     * @param target Case target
     * @return int distance
     */
    public int getDistance(Case from, Case target){
        Case nearestFrom = maze.getNearestInternal(from.getX(),from.getY());
        Case nearestTarget = maze.getNearestInternal(target.getX(),target.getY());

        //initDijkstra(nearestFrom);
        while(!graphe.isEmpty()){
            succSommet(nearestTarget);
        }

        int distance = cheminFinal(nearestFrom, nearestTarget);
        return distance;
    }

    /**
     * Initialization of the algorithm
     * @param caseDepart Case Starting case
     */
    private void initDijkstra(Case caseDepart) {
        trouve = false;
        graphe.removeAll(graphe);
        caseConnecte(caseDepart);
        for (Case c: graphe) {
            c.setWeight(9999);
            c.setCasePrecedente(null);
        }
        graphe.get(estDansTab(graphe, caseDepart)).setWeight(0);
        Collections.sort(graphe, new CompareDistance());
        sommetFerme.removeAll(sommetFerme);
    }

    /**
     *
     * @param c
     */
    private void caseConnecte(Case c){
        Stack<Case> s;
        s = new Stack();
        ArrayList<Case> sucesseurs;
        s.push(c);
        graphe.add(c);
        while(!s.isEmpty())
        {
            c = s.pop();
            sucesseurs = creationSucc(c, true);
            for (Case c1: sucesseurs)
            {
                if (estDansTab(graphe, c1) == -1)
                {
                    s.push(c1);
                    graphe.add(c1);
                }
            }
        }
    }

    /**
     *
     * @param casePosCourante
     * @param sontConnecte
     * @return
     */
    private ArrayList<Case> creationSucc(Case casePosCourante, boolean sontConnecte) {
        int lig = casePosCourante.getX();
        int col = casePosCourante.getY();
        ArrayList<Case> listeCasePosTemp = new ArrayList<>();
        Case[][] labyrinthe = maze.getLabyrinthe();

        if (lig > 0) {
            if (lig > 0 && !labyrinthe[lig - 1][col].estUnMur() && ((estDansTab(sommetOuvert, new Case(lig - 1, col)) == -1 && estDansTab(sommetFerme, new Case(lig - 1, col)) == -1))) {
                Case c = new Case(lig - 1, col);

                if (sontConnecte) {
                    listeCasePosTemp.add(c);
                } else {
                    int indexGraphe = estDansTab(graphe, c);
                    if (indexGraphe > -1) {
                        listeCasePosTemp.add(graphe.get(indexGraphe));
                    }
                }
            }
        }
        if (col < MAZE_SIZE - 1) {
            if (!labyrinthe[lig][col + 1].estUnMur() && ((estDansTab(sommetOuvert, new Case(lig, col + 1)) == -1 && estDansTab(sommetFerme, new Case(lig, col + 1)) == -1))) {
                Case c1 = new Case(lig, col + 1);

                if (sontConnecte) {
                    listeCasePosTemp.add(c1);
                } else {
                    int indexGraphe = estDansTab(graphe, c1);
                    if (indexGraphe > -1) {
                        listeCasePosTemp.add(graphe.get(indexGraphe));
                    }
                }
            }
        }
        if (lig < MAZE_SIZE - 1) {
            if (!labyrinthe[lig + 1][col].estUnMur() && ((estDansTab(sommetOuvert, new Case(lig + 1, col)) == -1 && estDansTab(sommetFerme, new Case(lig + 1, col)) == -1))) {
                Case c2 = new Case(lig + 1, col);
                if (sontConnecte) {
                    listeCasePosTemp.add(c2);
                } else {
                    int indexeGraphe = estDansTab(graphe, c2);
                    if (indexeGraphe > -1) {
                        listeCasePosTemp.add(graphe.get(indexeGraphe));
                    }
                }
            }
        }
        if (col > 0) {
            if (!labyrinthe[lig][col - 1].estUnMur() && ((estDansTab(sommetOuvert, new Case(lig, col - 1)) == -1 && estDansTab(sommetFerme, new Case(lig, col - 1)) == -1))) {
                Case c3 = new Case(lig, col - 1);
                if (sontConnecte) {
                    listeCasePosTemp.add(c3);
                } else {
                    int indexeGraphe = estDansTab(graphe, c3);
                    if (indexeGraphe > -1) {
                        listeCasePosTemp.add(graphe.get(indexeGraphe));
                    }
                }
            }

        }
        return listeCasePosTemp;
    }


    /**
     * Check if the case is in the Arraylist
     * @param listeCase List to check
     * @param caseCourante Case to check
     * @return index of the case
     */
    private int estDansTab (ArrayList < Case > listeCase, Case caseCourante){
        int index = -1;
        for (int i = 0; i < listeCase.size(); i++) {
            if (caseCourante.getY() == listeCase.get(i).getY() && caseCourante.getX() == listeCase.get(i).getX()) {
                index = i;
                break;
            }
        }
        return index;
    }

    /**
     *
     * @param caseFinale
     */
    private void succSommet(Case caseFinale){
        Case c;
        if (graphe.isEmpty())
        {
            return;
        }
        c = graphe.remove(0);
        sommetOuvert.add(c);
        sommetFerme.add(c);
        if (c.getY() == caseFinale.getY() && c.getX() == caseFinale.getX())
        {
            trouve = true;
            return;
        }
        if (c.getWeight() == 9999)
        {
            return;
        }
        ArrayList<Case> voisins = creationSucc(c, false);
        for (Case c1: voisins){
            int nouvelleDist = c.getWeight() + c.distance(c1);
            if (nouvelleDist < c1.getWeight())
            {
                c1.setWeight(nouvelleDist);
                c1.setCasePrecedente(c);
                Collections.sort(graphe, new CompareDistance());
            }
        }
    }

    /**
     * Return the weight between the two cases
     * @param caseDepart Starting case
     * @param caseFinale Target case
     * @return int distance
     */
    private int cheminFinal(Case caseDepart, Case caseFinale){

        System.out.println("Case de départ");
        System.out.println(caseDepart.toString());
        //System.out.println("Case finale");
        //System.out.println(caseFinale.toString());

        System.out.println("_____________________________________");

        int distanceFinale = 0;
        System.out.println("tableau des sommets fermés");
        System.out.println(sommetFerme.toString());

        int index = estDansTab(sommetFerme, caseFinale);

        Case caseCourante = sommetFerme.get(index);
        System.out.println(caseCourante.toString());
        //On part de la case finale pour retracer le chemin parcouru
        do {
            distanceFinale++;
            System.out.println("Case courante");
            //System.out.println(caseCourante.toString());
            caseCourante = caseCourante.getCasePrecedente();
            System.out.println(caseCourante.toString());

        } while (caseCourante.getX() != caseDepart.getX() || caseCourante.getY() != caseDepart.getY());

        return distanceFinale;
    }
}
