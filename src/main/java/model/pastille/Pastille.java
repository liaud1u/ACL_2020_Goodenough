package model.pastille;

import model.PacmanGame;
import views.animations.TriggeredPastille;

/**
 * Pastille
 */
public abstract class Pastille {
    protected TriggeredPastille triggeredPastille;
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

        this.triggeredPastille = new TriggeredPastille(0, 0, 0, this.type);
    }

    public TriggeredPastille getTriggeredPastille() {
        return triggeredPastille;
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
    public boolean ramasser() {
        if (!this.ramassee) {
            this.ramassee = true;
            this.getTriggeredPastille().animate();
        }
        return true;
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
