package model;

import model.player.Direction;
import model.util.Util;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

/**
 * @author adrien & florian
 */
public class Labyrinthe {


    private int leftPastilles;

    //Labyrinthe permettant la création du labyrinthe parfait
    public Case[][] labyrintheFormation;

    // Ensemble des cases formant le labyrinthe
    public Case[][] labyrinthe;

    private final Stack<Case> spawnableCase;


    int tailleLigneFormation;
    int tailleColonneFormation;
    int tailleLigne;
    int tailleColonne;
    Random rand = new Random();




    public Labyrinthe(int tailleL, int tailleC) {

        spawnableCase = new Stack<>();
        this.tailleLigneFormation = tailleL / 2;
        this.tailleColonneFormation = tailleC/2;

        this.tailleLigne = tailleLigneFormation * 2 + 1;
        this.tailleColonne = tailleColonneFormation * 2 + 1;

        labyrinthe = new Case[tailleLigne][tailleColonne];
        initialisationLaby();
        creationLabyrinthe();
        this.leftPastilles = 0;
    }

    public Case[][] getLabyrinthe() {
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
                if(!c.estUnMur() && !c.hasEntity()) {
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
    private Case getCase(int x, int y) {
        try {
            return labyrintheFormation[x][y];
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

    public ArrayList<Direction> getFreeDirection(int x, int y) {
        ArrayList<Direction> directionToFreeCase = new ArrayList<>();
        if (x - 1 >= 0 && !getCaseLabyrinthe(x - 1, y).estUnMur() && !getCaseLabyrinthe(x - 1, y).hasMonster()) {
            directionToFreeCase.add(Direction.LEFT);
        }
        if (y - 1 >= 0 && !getCaseLabyrinthe(x, y - 1).estUnMur() && !getCaseLabyrinthe(x, y - 1).hasMonster()) {
            directionToFreeCase.add(Direction.UP);
        }
        if (x + 1 < Util.MAZE_SIZE - 1 && !getCaseLabyrinthe(x + 1, y).estUnMur() && !getCaseLabyrinthe(x + 1, y).hasMonster()) {
            directionToFreeCase.add(Direction.RIGHT);
        }
        if (y + 1 < Util.MAZE_SIZE - 1 && !getCaseLabyrinthe(x, y + 1).estUnMur() && !getCaseLabyrinthe(x, y + 1).hasMonster()) {
            directionToFreeCase.add(Direction.DOWN);
        }
        return directionToFreeCase;
    }

    /**
     * Methodes qui retourne une case du labyrinthe
     *
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


        determineVoisins();
        destructionImpasse();

        //On crée la "boîte pour les monstres"

        //Ligne du milieu
        labyrinthe[tailleLigne / 2 - 3][tailleColonne / 2].setEstUnMur(false); //case gauche milieu - 2
        labyrinthe[tailleLigne / 2 - 2][tailleColonne / 2].setEstUnMur(true); //case gauche milieu - 1
        labyrinthe[tailleLigne / 2 - 1][tailleColonne / 2].setEstUnMur(false); //case gauche milieu
        labyrinthe[tailleLigne / 2][tailleColonne / 2].setEstUnMur(false); //case milieu
        labyrinthe[tailleLigne / 2 + 1][tailleColonne / 2].setEstUnMur(false); //case droite milieu
        labyrinthe[tailleLigne / 2 + 2][tailleColonne / 2].setEstUnMur(true); //case droite milieu + 1
        labyrinthe[tailleLigne / 2 + 3][tailleColonne / 2].setEstUnMur(false); //case droite milieu + 2

        //Ligne du milieu - 1
        labyrinthe[tailleLigne / 2 - 3][tailleColonne / 2 - 1].setEstUnMur(false); //case gauche milieu - 2
        labyrinthe[tailleLigne / 2 - 2][tailleColonne / 2 - 1].setEstUnMur(true); //case gauche milieu - 1
        labyrinthe[tailleLigne / 2 - 1][tailleColonne / 2 - 1].setEstUnMur(true); //case gauche milieu
        labyrinthe[tailleLigne / 2][tailleColonne / 2 - 1].setEstUnMur(false); //case milieu
        labyrinthe[tailleLigne / 2 + 1][tailleColonne / 2 - 1].setEstUnMur(true); //case droite milieu
        labyrinthe[tailleLigne / 2 + 2][tailleColonne / 2 - 1].setEstUnMur(true); //case droite milieu + 1
        labyrinthe[tailleLigne / 2 + 3][tailleColonne / 2 - 1].setEstUnMur(false); //case droite milieu + 2

        //Ligne du milieu - 2
        labyrinthe[tailleLigne / 2 - 3][tailleColonne / 2 - 2].setEstUnMur(false); //case gauche milieu - 2
        labyrinthe[tailleLigne / 2 - 2][tailleColonne / 2 - 2].setEstUnMur(false); //case gauche milieu - 1
        labyrinthe[tailleLigne / 2 - 1][tailleColonne / 2 - 2].setEstUnMur(false); //case gauche milieu
        labyrinthe[tailleLigne / 2][tailleColonne / 2 - 2].setEstUnMur(false); //case milieu
        labyrinthe[tailleLigne / 2 + 1][tailleColonne / 2 - 2].setEstUnMur(false); //case droite milieu
        labyrinthe[tailleLigne / 2 + 2][tailleColonne / 2 - 2].setEstUnMur(false); //case droite milieu + 1
        labyrinthe[tailleLigne / 2 + 3][tailleColonne / 2 - 2].setEstUnMur(false); //case droite milieu + 2

        //Ligne du milieu + 1
        labyrinthe[tailleLigne / 2 - 3][tailleColonne / 2 + 1].setEstUnMur(false); //case gauche milieu - 2
        labyrinthe[tailleLigne / 2 - 2][tailleColonne / 2 + 1].setEstUnMur(true); //case gauche milieu - 1
        labyrinthe[tailleLigne / 2 - 1][tailleColonne / 2 + 1].setEstUnMur(true); //case gauche milieu
        labyrinthe[tailleLigne / 2][tailleColonne / 2 + 1].setEstUnMur(true); //case milieu
        labyrinthe[tailleLigne / 2 + 1][tailleColonne / 2 + 1].setEstUnMur(true); //case droite milieu
        labyrinthe[tailleLigne / 2 + 2][tailleColonne / 2 + 1].setEstUnMur(true); //case droite milieu + 1
        labyrinthe[tailleLigne / 2 + 3][tailleColonne / 2 + 1].setEstUnMur(false); //case droite milieu + 2

        //Ligne du milieu + 2
        labyrinthe[tailleLigne / 2 - 3][tailleColonne / 2 + 2].setEstUnMur(false); //case gauche milieu - 2
        labyrinthe[tailleLigne / 2 - 2][tailleColonne / 2 + 2].setEstUnMur(false); //case gauche milieu - 1
        labyrinthe[tailleLigne / 2 - 1][tailleColonne / 2 + 2].setEstUnMur(false); //case gauche milieu
        labyrinthe[tailleLigne / 2][tailleColonne / 2 + 2].setEstUnMur(false); //case milieu
        labyrinthe[tailleLigne / 2 + 1][tailleColonne / 2 + 2].setEstUnMur(false); //case droite milieu
        labyrinthe[tailleLigne / 2 + 2][tailleColonne / 2 + 2].setEstUnMur(false); //case droite milieu + 1
        labyrinthe[tailleLigne / 2 + 3][tailleColonne / 2 + 2].setEstUnMur(false); //case droite milieu + 2

        //On garde les case de spawn des monstres
        spawnableCase.add(labyrinthe[tailleLigne / 2 + 1][tailleColonne / 2]);
        spawnableCase.add(labyrinthe[tailleLigne / 2 - 1][tailleColonne / 2]);
        spawnableCase.add(labyrinthe[tailleLigne / 2][tailleColonne / 2]);

        //On fait des ouvertures sur certains murs

        //Mur de dessus et dessous
        for (int x = 1; x < tailleLigne; x++) {
            if (!labyrinthe[x][1].estUnMur() && !labyrinthe[x][tailleColonne - 2].estUnMur()) {
                int nbPourcentInt = 8;
                int nbAleatoire = rand.nextInt(10);
                if (nbAleatoire > nbPourcentInt) {
                    labyrinthe[x][0].setEstUnMur(false);
                    labyrinthe[x][tailleColonne-1].setEstUnMur(false);
                }
            }
        }

        //Mur de gauche et droite
        for (int y = 1;y < tailleLigne; y++)
        {
            if (!labyrinthe[1][y].estUnMur() && !labyrinthe[tailleLigne-2][y].estUnMur())
            {
                int nbPourcentInt = 8;
                int nbAleatoire = rand.nextInt(10);
                if (nbAleatoire > nbPourcentInt) {
                    labyrinthe[0][y].setEstUnMur(false);
                    labyrinthe[tailleLigne-1][y].setEstUnMur(false);
                }
            }
        }

        determineVoisins();

    }

    /**
     * nombre de pastilles restantes
     */
    public int getLeftPastilles() {
        return leftPastilles;
    }


    public void destructionImpasse()
    {
        //On détruit des murs pour enlever les impasses
        //Décalage de 2 pour éviter de taper dans les murs
        for (int x = 1; x < tailleLigne - 1; x++) {
            for (int y = 1; y < tailleColonne - 1; y++) {
                if ((x != tailleLigne - 1 && y != 1 || x != 1 && y != tailleColonne - 1) && (x != 2 && y != tailleLigne - 2 || x != tailleColonne - 2 && y != 2)) {
                    if (!labyrinthe[x][y].estUnMur() && getCaseLabyrinthe(x,y).getVoisins().size() == 3)
                    {
                        if (x != tailleLigne - 2)
                            labyrinthe[x+1][y].setEstUnMur(false);
                        if (y != tailleColonne-2)
                            labyrinthe[x][y+1].setEstUnMur(false);

                    }
                }
            }
        }
    }

    public void determineVoisins()
    {
        //Détermine voisin pour chaque case
        for (int ligne = 0; ligne < tailleLigne; ligne++)
        {
            for (int colonne = 0; colonne < tailleColonne; colonne++) {

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

    public void addPastille() {
        this.leftPastilles++;
    }

    public void removePastille() {
        if (this.leftPastilles > 0) this.leftPastilles--;
    }


    public Stack<Case> getSpawnableCase() {
        return spawnableCase;
    }

}





