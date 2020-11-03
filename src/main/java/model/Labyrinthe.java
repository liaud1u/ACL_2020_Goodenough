package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Classe gérant le labyrinthe
 */
public class Labyrinthe {
    public final static int CASEVIDE = 0;  // Case vide par laquelle on peut passer
    public final static int CASEMUR = 1; // Mur

    /**
     * Ensemble de cases formant le labyrinthe
     */
    private final Case[][] cases;
    /**
     * Taille d'une ligne
     */
    private final int tailleLigne;
    /**
     * Taille d'une colonne
     */
    private final int tailleColonne;
    /**
     * Taille d'une ligne pour affichage
     */
    private final int tailleLigneGUI;
    /**
     * Taille d'une colonne pour affichage
     */
    private final int tailleColonneGUI;
    /**
     * Génération de nombre aléatoire
     */
    private final Random rand = new Random();
    /**
     * Ensemble de cases formant le labyrinthe (pour la génération)
     */
    private Case[][] labyrinthe;
    /**
     * Affichage du labyrinthe
     */
    private char[][] labyrintheGUI;

    /**
     * Constructeur de labyrinthe
     *
     * @param tailleLaby int taille du labyrinthe (carré)
     */
    public Labyrinthe(int tailleLaby) {
        //Longueur et largeur identiques
        this(tailleLaby, tailleLaby);
    }

    /**
     * Constructeur de labyrinthe
     *
     * @param tailleLigne   int taille d'une ligne
     * @param tailleColonne int taille d'une colonne
     */
    public Labyrinthe(int tailleLigne, int tailleColonne) {
        this.tailleLigne = tailleLigne;
        this.tailleColonne = tailleColonne;
        tailleLigneGUI = tailleLigne * 2 + 1;
        tailleColonneGUI = tailleColonne * 2 + 1;
        labyrintheGUI = new char[tailleLigneGUI][tailleColonneGUI];
        cases = new Case[tailleLigneGUI][tailleColonneGUI];
        initialisationLaby();
        creationLabyrinthe();
    }

    /**
     * Getter du plateau
     *
     * @return Tableau 2D de cases
     */
    public Case[][] getLabyrinthe() {
        return cases;
    }

    /**
     * Setter du tableau de case
     *
     * @param labyrinthe Tableau 2D de cases
     */
    public void setLabyrinthe(Case[][] labyrinthe) {
        this.labyrinthe = labyrinthe;
    }

    /**
     * Getter du plateau
     *
     * @return Tableau 2D de cases
     */
    public char[][] getLabyrintheGUI() {
        return labyrintheGUI;
    }

    /**
     * Setter du tableau de cases
     *
     * @param labyrintheGUI Tableau 2D de cases
     */
    public void setLabyrintheGUI(char[][] labyrintheGUI) {
        this.labyrintheGUI = labyrintheGUI;
    }

    /**
     * Initialise le labyrinthe
     */
    private void initialisationLaby() {

        labyrinthe = new Case[tailleLigneGUI][tailleColonneGUI];
        for (int i = 0; i < tailleLigne; i++) {
            for (int j = 0; j < tailleColonne; j++) {
                labyrinthe[i][j] = new Case(i, j, false);

            }
        }
    }

    /**
     * Création d'un labyrinthe
     */
    public void creationLabyrinthe() {
        creationLabyrinthe(0, 0);
    }

    /**
     * Création d'un labyrinthe ayant comme point de départ les coordonnées
     *
     * @param x int coordonnée x
     * @param y int coordonnée y
     */
    public void creationLabyrinthe(int x, int y) {
        creationLabyrinthe(getCase(x, y));
    }

    /**
     * Création d'un labyrinthe ayant comme point de départ le point
     *
     * @param caseDepart case de départ
     */
    public void creationLabyrinthe(Case caseDepart) {
        //TODO Commenter l'algo de génération
        if (caseDepart == null) return;
        caseDepart.setEstVide(false);
        ArrayList<Case> listeCase = new ArrayList<>();
        listeCase.add(caseDepart);

        while (!listeCase.isEmpty()) {
            Case c;
            if (rand.nextInt(10) == 0) {
                c = listeCase.remove(rand.nextInt(listeCase.size()));
            } else {
                c = listeCase.remove(listeCase.size() - 1);
            }
            ArrayList<Case> voisins = new ArrayList<>();

            Case[] voisinsPotentiel = new Case[]{
                    getCase(c.getX() + 1, c.getY()),
                    getCase(c.getX(), c.getY() + 1),
                    getCase(c.getX() - 1, c.getY()),
                    getCase(c.getX(), c.getY() - 1)
            };
            for (Case c1 : voisinsPotentiel) {
                if (c1==null || c1.isEstUnMur() || !c1.isEstVide()) continue;
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
     * Retourne la case aux coordonnées
     *
     * @param x int coordonnée x
     * @param y int coordonnée y
     * @return Case case
     */
    public Case getCase(int x, int y) {
        try {
            return labyrinthe[x][y];
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

    @Override
    public String toString() {
        return "Labyrinthe{" +
                "labyrinthe=" + Arrays.toString(labyrinthe) +
                ", labyrintheGUI=" + Arrays.toString(labyrintheGUI) +
                ", tailleLigne=" + tailleLigne +
                ", tailleColonne=" + tailleColonne +
                ", tailleLigneGUI=" + tailleLigneGUI +
                ", tailleColonneGUI=" + tailleColonneGUI +
                ", rand=" + rand +
                '}';
    }

    /**
     * Mise à jour du labyrinthe
     */
    public void updateLabyrinthe() {
        //TODO Commenter l'algo
        char vide = ' ';
        char mur = 'X';
        char casec = ' ';

        for (int x = 0; x < tailleLigneGUI; x++) {
            for (int y = 0; y < tailleColonneGUI; y++) {
                labyrintheGUI[x][y] = vide;
            }
        }
        for (int x = 0; x < tailleLigneGUI; x++) {
            for (int y = 0; y < tailleColonneGUI; y++) {
                if (x % 2 == 0 || y % 2 == 0) {
                    labyrintheGUI[x][y] = mur;
                }

            }
        }
        for (int x = 0; x < tailleLigne; x++) {
            for (int y = 0; y < tailleColonne; y++) {
                Case caseCourante = getCase(x, y);
                int XGUI = x * 2 + 1;
                int YGUI = y * 2 + 1;
                labyrintheGUI[XGUI][YGUI] = casec;
                if (caseCourante.voisinDessous()) {
                    labyrintheGUI[XGUI][YGUI + 1] = casec;
                }
                if (caseCourante.voisinDroite()) {
                    labyrintheGUI[XGUI + 1][YGUI] = casec;
                }
            }
        }

        //On détruit des murs au hasard pour créer des cycles
        //Décalage de 1 pour éviter de taper dans les murs
        for (int x = 1; x < tailleLigneGUI - 1; x++) {
            for (int y = 1; y < tailleColonneGUI - 1; y++) {
                if ((x != tailleLigneGUI - 1 && y != 1 || x != 1 && y != tailleColonneGUI - 1) && (x != 2 && y != tailleLigneGUI - 2 || x != tailleColonneGUI - 2 && y != 2)) {
                    if (labyrintheGUI[x][y] == mur) {
                        int nbPourcentInt = 5;
                        int nbAleatoire = rand.nextInt(10);
                        if (nbAleatoire > nbPourcentInt) {
                            labyrintheGUI[x][y] = vide;
                        }
                    }
                }
            }
        }

        for (int x = 1; x < tailleLigneGUI - 1; x++) {
            for (int y = 1; y < tailleColonneGUI - 1; y++) {
                if(labyrintheGUI[x][y] == 'X') {
                    cases[x][y] = new Case(x, y, true);
                } else {
                    cases[x][y] = new Case(x, y, false);
                }
            }
        }
    }
}