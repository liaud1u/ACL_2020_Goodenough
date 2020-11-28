package model.labyrinthe;

import model.comparerCaseDistance;
import model.player.Direction;
import model.util.Util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Stack;

/**
 * Maze class
 */
public class Labyrinthe {

    /**
     * Amount of leaving pastille on the labyrinthe
     */
    private int leftPastilles;

    /**
     * Case[][] for the generation
     */
    private Case[][] labyrintheFormation;

    /**
     * Case[][] for the final maze
     */
    private Case[][] labyrinthe;

    /**
     * Stack of all Case where Monster can spawn
     */
    private final Stack<Case> spawnableCase;

    /**
     * Row size for the generation
     */
    private int tailleLigneFormation;

    /**
     * Column size for the generation
     */
    private int tailleColonneFormation;

    /**
     * Row size
     */
    private int tailleLigne;

    /**
     * Column size
     */
    private int tailleColonne;

    /**
     * Random generator
     */
    private Random rand = new Random();

    //A mettre dans la classe ou tu veux éxecuter dijkstra (les 4)
    private ArrayList<Case> graphe = new ArrayList();
    private ArrayList<Case> sommetOuvert = new ArrayList();
    private ArrayList<Case> sommetFerme = new ArrayList();
    private boolean trouve = false;

    /**
     * Constructor of a labyrinthe
     * @param tailleL Row size
     * @param tailleC Column size
     */
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

    /**
     * Getter of the Case[][] of the labyrinthe
     * @return Case[][] of the labyrinthe
     */
    public Case[][] getLabyrinthe() {
        return labyrinthe;
    }

    /**
     * Methodes qui crée toutes les cases du labyrinthe
     */
    private void initialisationLaby() {

        labyrintheFormation = new Case[tailleLigneFormation][tailleColonneFormation];

        //Initializing the maze for the generation
        for (int i = 0; i < tailleLigneFormation; i++)
        {
            for (int j = 0; j < tailleColonneFormation; j++)
            {
                labyrintheFormation[i][j] = new Case(i,j,false);
            }
        }

        //Initializing the maze
        for (int i = 0; i < tailleLigne; i++) {
            for (int j = 0; j < tailleColonne; j++) {
                labyrinthe[i][j] = new Case(i, j, false);
            }
        }

    }

    /**
     * Create a maze starting at x:0 y:0
     */
    private void creationLabyrinthe() {
        creationLabyrinthe(0, 0);
    }


    /**
     * Create a maze starting at x y
     * @param x int starting x
     * @param y int starting y
     */
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

    /**
     * Getter for all the free direction for monster or player around a case
     * @param x int x case x
     * @param y int y case y
     * @return List of all free direction
     */
    public ArrayList<Direction> getFreeDirection(int x, int y) {
        ArrayList<Direction> directionToFreeCase = new ArrayList<>();
        if (!getCaseLabyrinthe(x - 1, y).estUnMur() && getCaseLabyrinthe(x - 1, y).getMonstre() == null) {
            directionToFreeCase.add(Direction.LEFT);
        }
        if (!getCaseLabyrinthe(x, y - 1).estUnMur() && getCaseLabyrinthe(x, y - 1).getMonstre() == null) {
            directionToFreeCase.add(Direction.UP);
        }
        if (!getCaseLabyrinthe(x + 1, y).estUnMur() && getCaseLabyrinthe(x + 1, y).getMonstre() == null) {
            directionToFreeCase.add(Direction.RIGHT);
        }
        if (!getCaseLabyrinthe(x, y + 1).estUnMur() && getCaseLabyrinthe(x, y + 1).getMonstre() == null) {
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
            return labyrinthe[(x + Util.MAZE_SIZE) % Util.MAZE_SIZE][(y + Util.MAZE_SIZE) % Util.MAZE_SIZE];
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

    /**
     * Update maze for the generation
     */
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

    /**
     * Destruction of all Impasse of the maze
     */
    public void destructionImpasse()
    {
        //On détruit des murs pour enlever les impasses
        //Décalage de 2 pour éviter de taper dans les murs
        for (int x = 1; x < tailleLigne - 1; x++) {
            for (int y = 1; y < tailleColonne - 1; y++) {
                    if (!labyrinthe[x][y].estUnMur() && getCaseLabyrinthe(x,y).getVoisins().size() == 3)
                    {
                        if (!labyrinthe[x-1][y].estUnMur() && x+1 != tailleLigne-1)
                            labyrinthe[x+1][y].setEstUnMur(false);

                        if (!labyrinthe[x][y-1].estUnMur() && y+1 != tailleColonne-1)
                            labyrinthe[x][y+1].setEstUnMur(false);


                        if (!labyrinthe[x+1][y].estUnMur() && x-1 != 0)
                            labyrinthe[x-1][y].setEstUnMur(false);

                        if (!labyrinthe[x][y+1].estUnMur() && y-1 != 0)
                            labyrinthe[x][y-1].setEstUnMur(false);

                    }
            }
        }
    }

    /**
     * Determine all Case neighboor
     */
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

    /**
     * Add a pastille to the counter
     */
    public void addPastille() {
        this.leftPastilles++;
    }

    /**
     * Remove a pastille to the counter
     */
    public void removePastille() {
        if (this.leftPastilles > 0) this.leftPastilles--;
    }

    /**
     * Getter of all spawnable case for monster
     * @return
     */
    public Stack<Case> getSpawnableCase() {
        return spawnableCase;
    }


    public void initDijkstra(Case caseDepart) {
        trouve = false;
        graphe.removeAll(graphe);
        caseConnecte(caseDepart);
        for (Case c: graphe) {
            c.setDistance(9999);
        }
        graphe.get(estDansTab(graphe, caseDepart)).setDistance(0);
        Collections.sort(graphe, new comparerCaseDistance());
        sommetFerme.removeAll(sommetFerme);
    }

    public void caseConnecte(Case c){
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

    public ArrayList<Case> creationSucc(Case casePosCourante, boolean sontConnecte) {
        int lig = casePosCourante.getX();
        int col = casePosCourante.getY();
        ArrayList<Case> listeCasePosTemp = new ArrayList<>();
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
        if (!labyrinthe[lig][col + 1].estUnMur() && (( estDansTab(sommetOuvert, new Case(lig, col + 1)) == -1 && estDansTab(sommetFerme, new Case(lig, col + 1)) == -1))) {
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
        return listeCasePosTemp;
    }


    public static int estDansTab (ArrayList < Case > listeCase, Case caseCourante){
        int index = -1;
        for (int i = 0; i < listeCase.size(); i++) {
            if (caseCourante.getY() == listeCase.get(i).getY() && caseCourante.getX() == listeCase.get(i).getX()) {
                index = i;
                break;
            }
        }
        return index;
    }

    public void succSommet(Case caseFinale){
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
            int nouvelleDist = c.getDistance() + distanceEntre2Cases(c,c1);
            if (nouvelleDist < c1.getDistance())
            {
                c1.setDistance(nouvelleDist);
                Collections.sort(graphe, new comparerCaseDistance());
            }
        }
    }
    public int distanceEntre2Cases(Case c1, Case c2){
        int distance;
        int x = c1.getX() - c2.getX();
        int y = c1.getY() - c2.getY();

        distance = Math.abs(x)+Math.abs(y);

        return distance;
    }

}





