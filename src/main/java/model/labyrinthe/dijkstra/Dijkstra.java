package model.labyrinthe.dijkstra;

import model.labyrinthe.Case;
import model.labyrinthe.Labyrinthe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

import static model.util.Util.MAZE_SIZE;

public class Dijkstra {

    private ArrayList<Case> graphe = new ArrayList();
    private ArrayList<Case> sommetOuvert = new ArrayList();
    private ArrayList<Case> sommetFerme = new ArrayList();
    private boolean trouve = false;
    private Labyrinthe maze;

    public Dijkstra(Labyrinthe labyrinthe){
        this.maze = labyrinthe;

    }

    public int getDistance(Case from, Case target){
        Case nearestFrom = maze.getNearestButNotWall(from.getX(),from.getY());
        Case nearestTarget = maze.getNearestButNotWall(target.getX(),target.getY());

        initDijkstra(nearestFrom);
        while(!graphe.isEmpty()){
            succSommet(nearestTarget);
        }

        int distance = cheminFinal(nearestFrom, nearestTarget);
        return distance;
    }

    private void initDijkstra(Case caseDepart) {
        trouve = false;
        graphe.removeAll(graphe);
        caseConnecte(caseDepart);
        for (Case c: graphe) {
            c.setDistance(9999);
            c.setCasePrecedente(null);
        }
        graphe.get(estDansTab(graphe, caseDepart)).setDistance(0);
        Collections.sort(graphe, new CompareDistance());
        sommetFerme.removeAll(sommetFerme);
    }



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
        if (c.getDistance() == 9999)
        {
            return;
        }
        ArrayList<Case> voisins = creationSucc(c, false);
        for (Case c1: voisins){
            int nouvelleDist = c.getDistance() + c.distance(c1);
            if (nouvelleDist < c1.getDistance())
            {
                c1.setDistance(nouvelleDist);
                c1.setCasePrecedente(c);
                Collections.sort(graphe, new CompareDistance());
            }
        }
    }

    private int cheminFinal(Case caseDepart, Case caseFinale){

        System.out.println("Case de départ");
        System.out.println(caseDepart.toString());
        System.out.println("Case d'arrivé");
        System.out.println(caseFinale.toString());

        int distanceFinale = 0;
        int index = estDansTab(sommetFerme, caseFinale);
        Case caseCourante = sommetFerme.get(index);
        //On part de la case finale pour retracer le chemin parcouru
        do {
            distanceFinale++;
            caseCourante = caseCourante.getCasePrecedente();

        } while (!(caseCourante.getX() == caseDepart.getX() && caseCourante.getY() == caseDepart.getY()));

        System.out.println(distanceFinale);
        return distanceFinale;
    }
}
