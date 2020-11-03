package model;

public class Case {

    // Identifiant unique de la case
    private final int id;

    // Numero utilise pour la fusion des chemins dans la generation d'un labyrinthe parfait
    private int num;

    // Coordonnees X et Y de la case dans le labyrinthe
    private final int x;
    private final int y;

    // Booleens representant les murs de la case (modifies a la creation d'un chemin)
    private boolean murNord;
    private boolean murSud;
    private boolean murEst;
    private boolean murOuest;


    public Case(int id, int px, int py) {
        this.id = id;
        this.num = id;
        this.x = px;
        this.y = py;
        this.murNord = true;
        this.murSud = true;
        this.murEst = true;
        this.murOuest = true;
    }


    public String toString() {
        StringBuilder strB = new StringBuilder();
        strB.append("UID=");
        strB.append(id);
        strB.append(", FNUM=");
        strB.append(num);
        strB.append(", X=");
        strB.append(x);
        strB.append(", Y=");
        strB.append(y);
        return strB.toString();
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getNum() {
        return num;
    }

    public boolean isMurSud() {
        return murSud;
    }

    public boolean isMurEst() {
        return murEst;
    }

    public boolean isMurOuest() {
        return murOuest;
    }

    public boolean isMurNord() {
        return murNord;
    }

    public void setNum(int num) {
        this.num = num;
    }
    public void setMurNord(boolean murNord) {
        this.murNord = murNord;
    }

    public void setMurSud(boolean murSud) {
        this.murSud = murSud;
    }

    public void setMurEst(boolean murEst) {
        this.murEst = murEst;
    }

    public void setMurOuest(boolean murOuest) {
        this.murOuest = murOuest;
    }
}