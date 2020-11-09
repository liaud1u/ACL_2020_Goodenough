package model;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author adrien & florian
 */
public class Labyrinthe {

    //Labyrinthe permettant la création du labyrinthe parfait
    public Case[][] labyrintheFormation;

    // Ensemble des cases formant le labyrinthe
    public Case[][] labyrinthe;


    int tailleLigneFormation;
    int tailleColonneFormation;
    int tailleLigne;
    int tailleColonne;
    Random rand = new Random();




    public Labyrinthe(int tailleL, int tailleC) {

        this.tailleLigneFormation = tailleL/2 - 1;
        this.tailleColonneFormation = tailleC/2 - 1;

        this.tailleLigne = tailleLigneFormation * 2 + 1;
        this.tailleColonne = tailleColonneFormation * 2 + 1;

        labyrinthe = new Case[tailleLigne][tailleColonne];
        initialisationLaby();
        creationLabyrinthe();
    }

    public Case[][] getLabyrintheVUE() {
        return labyrinthe;
    }

    //Création des cases
    private void initialisationLaby() {

        labyrintheFormation = new Case[tailleLigneFormation][tailleColonneFormation];

        for (int i = 0; i < tailleLigneFormation; i++)
        {
            for (int j = 0; j < tailleColonneFormation; j++)
            {
                labyrintheFormation[i][j] = new Case(i,j,false);
            }
        }
        for (int i = 0; i < tailleLigne; i++) {
            for (int j = 0; j < tailleColonne; j++) {
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

    /**
     * Methodes qui retourne le nombre de cases
     * @return entier représentant le nombre de cases vides du labyrinthe
     */
    public int getNbCasesLibres() {
        int nbCasesDisponibles = 0;
        for(Case[] ligne : labyrinthe) {
            for(Case c : ligne) {
                if(!c.estUnMur() && !c.possedeEntite()) {
                    nbCasesDisponibles++;
                }
            }
        }
        return nbCasesDisponibles;
    }
    public Case getCase(int x, int y) {
        try {
            return labyrintheFormation[x][y];
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

    public Case getCaseLabyrinthe(int x, int y) {
        try {
            return labyrinthe[x][y];
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

    public void updateLabyrinthe() {


        for (int x = 0; x < tailleLigne; x++) {
            for (int y = 0; y < tailleColonne; y++) {
                labyrinthe[x][y].setEstUnMur(false);
            }
        }

        for (int x = 0; x < tailleLigne; x++) {
            for (int y = 0; y < tailleColonne; y++) {

                if (x % 2 == 0 || y % 2 == 0)
                {
                    labyrinthe[x][y].setEstUnMur(true);
                }

            }
        }

        //Casse des murs pour rendre le labyrinthe parfait
        for (int x = 0; x < tailleLigneFormation; x++) {
            for (int y = 0; y < tailleColonneFormation; y++) {

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
        for (int x = 1; x < tailleLigne - 1; x++) {
            for (int y = 1; y < tailleColonne - 1; y++) {
                if ((x != tailleLigne - 1 && y != 1 || x != 1 && y != tailleColonne - 1) && (x != 2 && y != tailleLigne -2 || x != tailleColonne -2 && y != 2)) {
                    if (labyrinthe[x][y].estUnMur() && !labyrinthe[x][y].voisinDessous() && !labyrinthe[x][y].voisinDroite() || labyrinthe[x][y].estUnMur() && !labyrinthe[x][y].voisinDessous() && !labyrinthe[x][y].voisinGauche() || labyrinthe[x][y].estUnMur() && !labyrinthe[x][y].voisinDessus() && !labyrinthe[x][y].voisinDroite() || labyrinthe[x][y].estUnMur() && !labyrinthe[x][y].voisinDessus() && !labyrinthe[x][y].voisinGauche()) {
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





