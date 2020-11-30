package model.pastille;

import model.PacmanGame;

/**
 * Pastille
 */
public abstract class Pastille {

    protected PacmanGame game;

    protected PastilleType type;

    /**
     * Booléen, true si la pastille a été rammassée
     */
    private boolean ramassee;

    /**
     * Constructeur d'une pastille
     */
    public Pastille(PacmanGame game, PastilleType type) {
        this.ramassee = false;
        this.game = game;
        this.type = type;
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


    public PastilleType getType() {
        return type;
    }
    
    /**
     * Setter to set if the pastille has been eaten
     * @param ramassee boolean eaten
     */
    public void setRamassee(boolean ramassee) { this.ramassee = ramassee;
    }
}
