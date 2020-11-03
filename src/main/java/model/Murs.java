package model;

public enum Murs {
    NORD(true),
    SUD(true),
    EST(true),
    OUEST(true);

    private boolean existe;

     Murs(boolean existe) {
        this.existe = existe;
    }

    public boolean getExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
         this.existe = existe;
    }

}
