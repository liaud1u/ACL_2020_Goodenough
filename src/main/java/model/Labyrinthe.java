package model;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author adrien & florian
 */
public class Labyrinthe {

    // Ensemble des cases formant le labyrinthe
    public Case[][] labyrintheFormation;

    //On utilise un tableau de CHAR pour pouvoir modifier le type des cases à volonté
    public Case[][] labyrinthe;


    int tailleLigne;
    int tailleColonne;
    int tailleLigneVue;
    int tailleColonneVue;
    Random rand = new Random();




    public Labyrinthe(int tailleL, int tailleC) {

        this.tailleLigne = tailleL/2 - 1;
        this.tailleColonne = tailleC/2 - 1;

        this.tailleLigneVue = tailleLigne * 2 + 1;
        this.tailleColonneVue = tailleColonne * 2 + 1;

        labyrinthe = new Case[tailleLigneVue][tailleColonneVue];
        initialisationLaby();
        creationLabyrinthe();
    }

    public Case[][] getLabyrintheVUE() {
        return labyrinthe;
    }

    //Création des cases
    private void initialisationLaby() {

        labyrintheFormation = new Case[tailleLigne][tailleColonne];

        for (int i = 0; i < tailleLigne; i++)
        {
            for (int j = 0; j < tailleColonne; j++)
            {
                labyrintheFormation[i][j] = new Case(i,j,false);
            }
        }
        for (int i = 0; i < tailleLigneVue; i++) {
            for (int j = 0; j < tailleColonneVue; j++) {
                labyrinthe[i][j] = new Case(i, j, false);
            }
        }

    }


    private void creationLabyrinthe() {
        creationLabyrinthe(0, 0);
    }

    private void creationLabyrinthe(int x, int y) {
        creationLabyrinthe(getCase(x, y));
    }
    private void creationLabyrinthe(Case caseDepart) {
        if (caseDepart == null) return;
        caseDepart.setEstVide(true);

        ArrayList<Case> listeCase = new ArrayList<>();
        listeCase.add(caseDepart);

        while (!listeCase.isEmpty()) {
            Case c;
            if (rand.nextInt(10)==0)
            {
                c = listeCase.remove(rand.nextInt(listeCase.size()));
            }
            else
            {
                c = listeCase.remove(listeCase.size() - 1);
            }
            ArrayList<Case> voisins = new ArrayList<>();

            Case[] voisinsPotentiel = new Case[]{
                    getCase(c.getX() + 1, c.getY()),
                    getCase(c.getX(), c.getY() + 1),
                    getCase(c.getX() - 1, c.getY()),
                    getCase(c.getX(), c.getY() - 1)
            };

            for (Case c1 : voisinsPotentiel)
            {
                if (c1==null || c1.estUnMur()|| !c1.estVide()) continue;
                voisins.add(c1);
            }
            if (voisins.isEmpty()) continue;

            Case caseCourante = voisins.get(rand.nextInt(voisins.size()));
            caseCourante.setEstVide(false);
            c.ajoutVoisin(caseCourante);
            listeCase.add(c);
            listeCase.add(caseCourante);
        }
        updateLabyrinthe();
    }


    public Case getCase(int x, int y) {
        try {
            return labyrintheFormation[x][y];
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }


    //Seems good sauf les fonctions voisins droite et voisins dessous
    public void updateLabyrinthe() {

        char vide = ' ';
        char mur = 'X';

        for (int x = 0; x < tailleLigneVue; x++) {
            for (int y = 0; y < tailleColonneVue; y++) {
                labyrinthe[x][y].setEstUnMur(false);
            }
        }

        //Murs du milieu
        for (int x = 0; x < tailleLigneVue; x++) {
            for (int y = 0; y < tailleColonneVue; y++) {

                if (x % 2 == 0 || y % 2 == 0)
                {
                    labyrinthe[x][y].setEstUnMur(true);
                }

            }
        }

        //Casse des murs pour rendre le labyrinthe parfait
        for (int x = 0; x < tailleLigne; x++) {
            for (int y = 0; y < tailleColonne; y++) {

                Case caseCourante = getCase(x,y);

                int XGUI = x * 2 + 1;
                int YGUI = y * 2 + 1;


                if (caseCourante.voisinDessous())
                {
                    labyrinthe[XGUI][YGUI + 1].setEstUnMur(false);
                }
                if (caseCourante.voisinDroite())
                {
                    labyrinthe[XGUI + 1][YGUI].setEstUnMur(false);
                }

            }
        }


        //On détruit des murs au hasard pour créer des cycles
        //Décalage de 1 pour éviter de taper dans les murs
        for (int x = 1; x < tailleLigneVue - 1; x++) {
            for (int y = 1; y < tailleColonneVue - 1; y++) {
                if ((x != tailleLigneVue - 1 && y != 1 || x != 1 && y != tailleColonneVue - 1) && (x != 2 && y != tailleLigneVue -2 || x != tailleColonneVue -2 && y != 2)) {
                    if (labyrinthe[x][y].estUnMur()) {
                        int nbPourcentInt = 10;
                        int nbAleatoire = rand.nextInt(10);
                        if (nbAleatoire > nbPourcentInt) {
                            labyrinthe[x][y].setEstUnMur(false);
                        }
                    }
                }
            }
        }
    }

}





