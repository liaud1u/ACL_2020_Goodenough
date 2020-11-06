package model;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author adrien & florian
 */
public class Labyrinthe {

    // Ensemble des cases formant le labyrinthe
    public Case[][] labyrinthe;

    //On utilise un tableau de CHAR pour pouvoir modifier le type des cases à volonté
    public char[][] labyrintheCHAR;

    private int[][] labyrintheVue;

    int tailleLigne;
    int tailleColonne;
    int tailleLigneCHAR;
    int tailleColonneCHAR;
    Random rand = new Random();




    public Labyrinthe(int tailleL, int tailleC) {
        this.labyrintheVue = new int[tailleL][tailleC];

        this.tailleLigne = tailleL/2 - 1;
        this.tailleColonne = tailleC/2 - 1;

        System.out.println(tailleLigne);
        System.out.println(tailleColonne);

        this.tailleLigneCHAR = tailleLigne * 2 + 1;
        this.tailleColonneCHAR = tailleColonne * 2 + 1;

        labyrintheCHAR = new char[tailleLigneCHAR][tailleColonneCHAR];
        initialisationLaby();
        creationLabyrinthe();
    }


    //Création des cases
    private void initialisationLaby() {

        labyrinthe = new Case[tailleLigne][tailleColonne];

        for (int i = 0; i < tailleLigne; i++)
        {
            for (int j = 0; j < tailleColonne; j++)
            {
                labyrinthe[i][j] = new Case(i,j,false);
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
                if (c1==null || c1.isEstUnMur()|| !c1.isEstVide()) continue;
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

    public int[][] getLabyrintheVue() {
        return labyrintheVue;
    }

    public Case getCase(int x, int y) {
        try {
            return labyrinthe[x][y];
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }


    //Seems good sauf les fonctions voisins droite et voisins dessous
    public void updateLabyrinthe() {

        char vide = ' ';
        char mur = 'X';

        for (int x = 0; x < tailleLigneCHAR; x++) {
            for (int y = 0; y < tailleColonneCHAR; y++) {
                labyrintheCHAR[x][y] = vide;
            }
        }

        //Murs sur les côtés
/*
       //Bordure Ouest
        for (int y = 0; y < tailleColonneCHAR; y++) {
            labyrintheCHAR[0][y] = mur;
        }

        //Bordure Nord
        for (int x = 0; x < tailleColonneCHAR; x++) {
            labyrintheCHAR[x][0] = mur;
        }

        //Bordure Est
        for (int y = 0; y < tailleColonneCHAR; y++) {
            labyrintheCHAR[tailleColonneCHAR - 1][y] = mur;
        }

        //Bordure Sud
        for (int x = 0; x < tailleColonneCHAR; x++) {
            labyrintheCHAR[x][tailleColonneCHAR - 1] = mur;
        }
*/
        //Murs du milieu
        for (int x = 0; x < tailleLigneCHAR; x++) {
            for (int y = 0; y < tailleColonneCHAR; y++) {

                if (x % 2 == 0 || y % 2 == 0)
                {
                    labyrintheCHAR[x][y] = mur;
                }

            }
        }

        //Casse des murs pour rendre le labyrinthe parfait
        for (int x = 0; x < tailleLigne; x++) {
            for (int y = 0; y < tailleColonne; y++) {

                Case caseCourante = getCase(x,y);

                int XGUI = x * 2 + 1;
                int YGUI = y * 2 + 1;
                //labyrintheCHAR[XGUI][YGUI] = vide;

             //   System.out.println(labyrintheCHAR[x][y]);
                if (caseCourante.voisinDessous())
                {
                    labyrintheCHAR[XGUI][YGUI + 1] = vide;
                }
                if (caseCourante.voisinDroite())
                {
                    labyrintheCHAR[XGUI + 1][YGUI] = vide;
                }

            }
        }


        //On détruit des murs au hasard pour créer des cycles
        //Décalage de 1 pour éviter de taper dans les murs
        for (int x = 1; x < tailleLigneCHAR - 1; x++) {
            for (int y = 1; y < tailleColonneCHAR - 1; y++) {
                if ((x != tailleLigneCHAR - 1 && y != 1 || x != 1 && y != tailleColonneCHAR - 1) && (x != 2 && y != tailleLigneCHAR -2 || x != tailleColonneCHAR -2 && y != 2)) {
                    if (labyrintheCHAR[x][y] == mur) {
                        int nbPourcentInt = 10;
                        int nbAleatoire = rand.nextInt(10);
                        if (nbAleatoire > nbPourcentInt) {
                            labyrintheCHAR[x][y] = vide;
                        }
                    }
                }
            }
        }


        for (int ligne = 0; ligne < tailleLigneCHAR; ligne++)
        {
            for (int colonne = 0; colonne < tailleColonneCHAR; colonne++)
            {
                if (labyrintheCHAR[ligne][colonne] == mur)
                {
                    labyrintheVue[ligne][colonne] = 1;
                }
                else
                {
                    labyrintheVue[ligne][colonne] = 0;
                }
            }
        }

       // System.out.println("FIN DE LABYRINTHE");
    }

}





