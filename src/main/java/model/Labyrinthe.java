package model;

import model.util.Util;

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

    /**
     * Methodes qui crée toutes les cases du labyrinthe
     */
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

    /**
     * Methodes qui crée le labyrinthe depuis une case de départ
     */
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

    /**
     * Methodes qui retourne une case de labyrintheFormation
     * @return Case représentant une case présente dans labyrintheFormation
     */
    public Case getCase(int x, int y) {
        try {
            return labyrintheFormation[x][y];
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

    /**
     * Methodes qui retourne une case du labyrinthe
     * @return Case représentant une case dans le labyrinthe
     */
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

        labyrinthe[tailleLigne/2+2][tailleColonne/2+1].setEstUnMur(true);

        //Casse des murs pour rendre le labyrinthe parfait
        for (int x = 0; x < tailleLigneFormation; x++) {
            for (int y = 0; y < tailleColonneFormation; y++) {

                Case caseCourante = getCase(x,y);

                int X = x * 2 + 1;
                int Y = y * 2 + 1;

                if (caseCourante.voisinDessous())
                {
                    labyrinthe[X][Y + 1].setEstUnMur(false);
                }
                if (caseCourante.voisinDroite())
                {
                    labyrinthe[X + 1][Y].setEstUnMur(false);
                }

            }
        }


        //On détruit des murs au hasard pour créer des cycles
        //Décalage de 1 pour éviter de taper dans les murs
        for (int x = 1; x < tailleLigne - 1; x++) {
            for (int y = 1; y < tailleColonne - 1; y++) {
                if ((x != tailleLigne - 1 && y != 1 || x != 1 && y != tailleColonne - 1) && (x != 2 && y != tailleLigne - 2 || x != tailleColonne - 2 && y != 2)) {
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

        //On crée la "boîte pour les monstres"

        //Ligne du milieu
        labyrinthe[tailleLigne/2][tailleColonne/2].setEstUnMur(false); //case milieu
        labyrinthe[tailleLigne/2-1][tailleColonne/2].setEstUnMur(false); //case gauche milieu
        labyrinthe[tailleLigne/2-2][tailleColonne/2].setEstUnMur(true); //case gauche milieu - 1
        labyrinthe[tailleLigne/2-3][tailleColonne/2].setEstUnMur(false); //case gauche milieu - 2
        labyrinthe[tailleLigne/2+1][tailleColonne/2].setEstUnMur(false); //case droite milieu
        labyrinthe[tailleLigne/2+2][tailleColonne/2].setEstUnMur(true); //case droite milieu + 1
        labyrinthe[tailleLigne/2+3][tailleColonne/2].setEstUnMur(false); //case droite milieu + 2

        //Ligne du milieu - 1
        labyrinthe[tailleLigne/2][tailleColonne/2-1].setEstUnMur(false); //case milieu
        labyrinthe[tailleLigne/2-1][tailleColonne/2-1].setEstUnMur(true); //case gauche milieu
        labyrinthe[tailleLigne/2-2][tailleColonne/2-1].setEstUnMur(true); //case gauche milieu - 1
        labyrinthe[tailleLigne/2-3][tailleColonne/2-1].setEstUnMur(false); //case gauche milieu - 2
        labyrinthe[tailleLigne/2+1][tailleColonne/2-1].setEstUnMur(true); //case droite milieu
        labyrinthe[tailleLigne/2+2][tailleColonne/2-1].setEstUnMur(true); //case droite milieu + 1
        labyrinthe[tailleLigne/2+3][tailleColonne/2-1].setEstUnMur(false); //case droite milieu + 2

        //Ligne du milieu - 2
        labyrinthe[tailleLigne/2][tailleColonne/2-2].setEstUnMur(false); //case milieu
        labyrinthe[tailleLigne/2-1][tailleColonne/2-2].setEstUnMur(false); //case gauche milieu
        labyrinthe[tailleLigne/2-2][tailleColonne/2-2].setEstUnMur(false); //case gauche milieu - 1
        labyrinthe[tailleLigne/2-3][tailleColonne/2-2].setEstUnMur(false); //case gauche milieu - 2
        labyrinthe[tailleLigne/2+1][tailleColonne/2-2].setEstUnMur(false); //case droite milieu
        labyrinthe[tailleLigne/2+2][tailleColonne/2-2].setEstUnMur(false); //case droite milieu + 1
        labyrinthe[tailleLigne/2+3][tailleColonne/2-2].setEstUnMur(false); //case droite milieu + 2

        //Ligne du milieu + 1
        labyrinthe[tailleLigne/2][tailleColonne/2+1].setEstUnMur(true); //case milieu
        labyrinthe[tailleLigne/2-1][tailleColonne/2+1].setEstUnMur(true); //case gauche milieu
        labyrinthe[tailleLigne/2-2][tailleColonne/2+1].setEstUnMur(true); //case gauche milieu - 1
        labyrinthe[tailleLigne/2-3][tailleColonne/2+1].setEstUnMur(false); //case gauche milieu - 2
        labyrinthe[tailleLigne/2+1][tailleColonne/2+1].setEstUnMur(true); //case droite milieu
        labyrinthe[tailleLigne/2+2][tailleColonne/2+1].setEstUnMur(true); //case droite milieu + 1
        labyrinthe[tailleLigne/2+3][tailleColonne/2+1].setEstUnMur(false); //case droite milieu + 2

        //Ligne du milieu + 2
        labyrinthe[tailleLigne/2][tailleColonne/2+2].setEstUnMur(false); //case milieu
        labyrinthe[tailleLigne/2-1][tailleColonne/2+2].setEstUnMur(false); //case gauche milieu
        labyrinthe[tailleLigne/2-2][tailleColonne/2+2].setEstUnMur(false); //case gauche milieu - 1
        labyrinthe[tailleLigne/2-3][tailleColonne/2+2].setEstUnMur(false); //case gauche milieu - 2
        labyrinthe[tailleLigne/2+1][tailleColonne/2+2].setEstUnMur(false); //case droite milieu
        labyrinthe[tailleLigne/2+2][tailleColonne/2+2].setEstUnMur(false); //case droite milieu + 1
        labyrinthe[tailleLigne/2+3][tailleColonne/2+2].setEstUnMur(false); //case droite milieu + 2


        //Détermine voisin pour chaque case
        for (int ligne = 0; ligne < Util.MAZE_SIZE - 1; ligne++)
        {
            for (int colonne = 0; colonne < Util.MAZE_SIZE - 1; colonne++) {

                Case caseCourante = getCaseLabyrinthe(ligne,colonne);
                Case[] voisinsPotentiel = new Case[]{
                        getCaseLabyrinthe(caseCourante.getX() + 1, caseCourante.getY()),
                        getCaseLabyrinthe(caseCourante.getX(), caseCourante.getY() + 1),
                        getCaseLabyrinthe(caseCourante.getX() - 1, caseCourante.getY()),
                        getCaseLabyrinthe(caseCourante.getX(), caseCourante.getY() - 1)
                };

                ArrayList<Case> voisinsMur = new ArrayList<>();
                for (Case c1 : voisinsPotentiel)
                {
                    if (c1==null || !c1.estUnMur()) continue;
                    voisinsMur.add(c1);

                }
                caseCourante.setVoisins(voisinsMur);

            }
        }
    }

}





