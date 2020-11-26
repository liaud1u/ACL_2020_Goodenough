package model.pastille;

/**
 * Pastille
 */
public abstract class Pastille {

    /**
     * type des pastilles
     */
    public enum Type {
        SCORE, AMMO, TIME
    }

    /**
     * Booléen, true si la pastille a été rammassée
     */
    private boolean ramassee;

    /**
     * Constructeur d'une pastille
     */
    public Pastille() {
        this.ramassee = false;
    }

    /**
     * Getter du fait que la pastille est ramassée
     *
     * @return true si la pastille est ramassée, false sinon
     */
    public boolean isRamassee() {
        return ramassee;
    }

    /**
     * Ramasser une pastille
     */
    public void ramasser() {
        if (!this.ramassee) {
            this.ramassee = true;
        }
    }


    /**
     * Setter to set if the pastille has been eaten
     * @param ramassee boolean eaten
     */
    public void setRamassee(boolean ramassee) { this.ramassee = ramassee;
    }
}
