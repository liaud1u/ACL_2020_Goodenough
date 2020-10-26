package model;
//  /!\ WIP
// WORKING :
//  - Maze creation
//  - Maze generation
//  - Imperfect maze
//  - Maze display AWT/Swing
// NOT IMPLEMENTED YET :
//  - Maze display JavaFX
//  - Imperfect maze
// TO ENHANCE :
//  - Imperfect maze generation (generating imperfect faster than generating perfect and then break walls)

public class Labyrinthe {


    private Case[][] plateau;
    private int taille;
    private boolean parfait;

    public Labyrinthe(int taille, boolean parfait) {
        this.taille = taille;
        this.plateau = new Case[taille][taille];
        this.parfait = parfait;
        int id = 0;
        for(int x = 0 ; x < taille ; x++) {
            for (int y = 0 ; y < taille ; y++) {
                this.plateau[x][y] = new Case(id++, x, y);
            }
        }
    }

    public int getTaille() {
        return taille;
    }

    public Case[][] getPlateau() {
        return plateau;
    }

    public void genererLabyrinthe() {
            while(!parcoursTermine()) {
                Case c = choisirCaseAleatoire();
                int murChoisi = genererValeurAleatoire(4);
                switch(murChoisi) {
                    case 0: // Nord
                        if(c.getX() - 1 >= 0) {
                            Case nord = plateau[c.getX()-1][c.getY()];
                            if(nord != null) {
                                if(nord.getNum() != c.getNum()) {
                                    c.setMurNord(false);
                                    nord.setMurSud(false);
                                    mettreAJourCases(nord.getNum(), c.getNum());
                                }
                            }
                        }

                        break;
                    case 1: // Sud
                        if(c.getX() + 1 < taille) {
                            Case sud = plateau[c.getX()+1][c.getY()];
                            if(sud != null) {
                                if(sud.getNum() != c.getNum()) {
                                    c.setMurSud(false);
                                    sud.setMurNord(false);
                                    mettreAJourCases(sud.getNum(), c.getNum());
                                }
                            }
                        }
                        break;
                    case 2: // Est
                        if(c.getY() + 1 < taille) {
                            Case est = plateau[c.getX()][c.getY()+1];
                            if(est != null) {
                                if(est.getNum() != c.getNum()) {
                                    c.setMurEst(false);
                                    est.setMurOuest(false);
                                    mettreAJourCases(est.getNum(), c.getNum());
                                }
                            }
                        }
                        break;
                    case 3: // Ouest
                        if(c.getY() - 1 >= 0) {
                            Case ouest = plateau[c.getX()][c.getY()-1];
                            if(ouest != null) {
                                if(ouest.getNum() != c.getNum()) {
                                    c.setMurOuest(false);
                                    ouest.setMurEst(false);
                                    mettreAJourCases(ouest.getNum(), c.getNum());
                                }
                            }
                        }
                        break;
                }
            }
         if(!parfait) {
             if(taille != 1) {
                 if(taille <= 7) {
                     casserMursAleatoires(taille/3);
                 } else if (taille >= 55) {
                     casserMursAleatoires(taille*10);
                 }else {
                     casserMursAleatoires(taille*5);
                 }
             }
        }
    }


    private Case choisirCaseAleatoire() {
        return plateau[genererValeurAleatoire(taille)][genererValeurAleatoire(taille)];
    }


    private int genererValeurAleatoire(int maximum) {
        int random = (int)(Math.random() * maximum);
        return random;
    }

    private void mettreAJourCases(int numCase, int nouveauNumCase) {
        for(int x = 0 ; x < taille ; x++) {
            for (int y = 0; y < taille; y++) {
                if(plateau[x][y].getNum() == numCase) {
                    plateau[x][y].setNum(nouveauNumCase);
                }
            }
        }
    }

    private void casserMursAleatoires(int nbMurs) {
        for(int i = 0 ; i < nbMurs ; i ++ ){
            Case c = choisirCaseAleatoire();
            Case c2 = null;
            int direction = genererValeurAleatoire(4);
            boolean supprimable = false;
            while(!supprimable) {
                switch(direction) {
                    case 0: // N
                        if(c.isMurNord() && c.getX() - 1 >= 0) {
                            // Creer chemin
                            c2 = plateau[c.getX()-1][c.getY()];
                            c.setMurNord(false);
                            c2.setMurSud(false);
                            supprimable = true;
                        }
                        break;
                    case 1: // S
                        if(c.isMurSud() && c.getX() + 1 < taille) {
                            // Creer chemin
                            c2 = plateau[c.getX()+1][c.getY()];
                            c.setMurSud(false);
                            c2.setMurNord(false);
                            supprimable = true;
                        }
                        break;
                    case 2: // E

                        if(c.isMurEst() && c.getY() + 1 < taille) {
                            c2 = plateau[c.getX()][c.getY()+1];
                            c.setMurEst(false);
                            c2.setMurOuest(false);
                            supprimable = true;
                        }
                        break;
                    case 3: // O
                        if(c.isMurOuest() && c.getY() - 1 >= 0) {
                            c2 = plateau[c.getX()][c.getY()-1];
                            c.setMurOuest(false);
                            c2.setMurEst(false);
                            supprimable = true;
                        }
                        break;
                }
                c = choisirCaseAleatoire();
                c2 = null;
            }
        }
    }
    private boolean parcoursTermine() {
        boolean termine = true;
        int numero = plateau[0][0].getNum();
        for(int x = 0 ; x < taille ; x++) {
            for (int y = 0; y < taille; y++) {
                Case c = plateau[x][y];
                if(c.getNum() != numero) {
                    termine = false;
                    break;
                }
            }
        }
        return termine;
    }


    // Methode de test pour afficher le labyrinthe
    // TODO : à supprimer lorsque le package views et l'affichage du labyrinthe sera implémenté en JavaFX.
    public void afficher() {
        for(int x = 0 ; x < taille ; x++) {
            for (int y = 0 ; y < taille ; y++) {
                Case courante = plateau[x][y];

            }
            System.out.println();
        }
    }

}
